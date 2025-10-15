package view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class jogoController {

    @FXML
    private Canvas canva;

    private double playerX = 200;
    private final double playerY = 500;
    private final double raio = 16;
    private final double larguraTela = 360;
    private final double alturaTela = 600;
    private int pontuacao = 0;

    private ArrayList<Obstaculos> obstaculos = new ArrayList<>();
    private Random random = new Random();
    private boolean esquerda, direita;

    private Image imagemPlayer;
    private Image imagemObstaculos;

    // Nitro
    private boolean nitroAtivo = false;
    private boolean recarregandoNitro = false;
    private double nitroCarga = 1.0;
    private final double consumoNitro = 0.005;
    private final double recargaNitro = 0.002;

    @FXML
    public void initialize() {
        InputStream playerStream = getClass().getResourceAsStream("f1_azul.png");
        InputStream obstaculosStream = getClass().getResourceAsStream("f1_vermelho.png");
        imagemPlayer = new Image(playerStream);
        imagemObstaculos = new Image(obstaculosStream);

        canva.setFocusTraversable(true);
        canva.requestFocus();

        GraphicsContext gc = canva.getGraphicsContext2D();

        canva.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = true;
            if (e.getCode() == KeyCode.RIGHT) direita = true;
            if (e.getCode() == KeyCode.SHIFT && !recarregandoNitro && nitroCarga > 0) {
                nitroAtivo = true;
            }
        });

        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
            if (e.getCode() == KeyCode.SHIFT) nitroAtivo = false;
        });

        AnimationTimer timer = new AnimationTimer() {
            long ultimoSpaw = 0;
            long intervaloSpaw = 1_000_000_000;

            @Override
            public void handle(long now) {
                atualizar();
                desenhar(gc);
                if (now - ultimoSpaw > intervaloSpaw) {
                    obstaculos.add(new Obstaculos(random.nextInt((int) larguraTela - 40), -40));
                    ultimoSpaw = now;
                }
            }
        };
        timer.start();
    }

    private void atualizar() {
        if (esquerda && playerX - raio > 0) playerX -= 5;
        if (direita && playerX + raio < larguraTela) playerX += 5;

        if (nitroAtivo && nitroCarga > 0) {
            nitroCarga -= consumoNitro;
            if (nitroCarga <= 0) {
                nitroCarga = 0;
                nitroAtivo = false;
                recarregandoNitro = true;
            }
        } else if (recarregandoNitro) {
            nitroCarga += recargaNitro;
            if (nitroCarga >= 1.0) {
                nitroCarga = 1.0;
                recarregandoNitro = false;
            }
        }

        double velocidade = nitroAtivo ? 10 : 5;

        Iterator<Obstaculos> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculos obs = it.next();
            obs.y += velocidade;

            double centroPlayerX = playerX + raio;
            double centroPlayerY = playerY + raio;
            double obsTopo = obs.y;
            double obsBase = obs.y + obs.altura;
            double obsEsquerda = obs.x;
            double obsDireita = obs.x + obs.largura;

            boolean colidiu = centroPlayerX >= obsEsquerda &&
                              centroPlayerX <= obsDireita &&
                              centroPlayerY >= obsTopo &&
                              centroPlayerY <= obsBase;

            if (colidiu) {
                pontuacao = 0;
                it.remove();
            } else if (obs.y > alturaTela) {
                pontuacao++;
                it.remove();
            }
        }
    }

    private void desenhar(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 4, raio * 4);

        if (nitroAtivo) {
            gc.setFill(Color.RED);
            gc.fillOval(playerX + raio - 10, playerY + raio * 2.5, 20, 20);
            gc.setFill(Color.ORANGE);
            gc.fillOval(playerX + raio - 8, playerY + raio * 2.8, 16, 16);
            gc.setFill(Color.YELLOW);
            gc.fillOval(playerX + raio - 6, playerY + raio * 3.0, 12, 12);
        }

        for (Obstaculos obs : obstaculos) {
            gc.drawImage(imagemObstaculos, obs.x, obs.y, obs.largura, obs.altura);
        }

        gc.setFill(Color.BLACK);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText("Pontuação " + pontuacao, 10, 30);

        // Barra de nitro abaixo da pontuação
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(10, 40, 100, 8);

        gc.setFill(nitroAtivo ? Color.ORANGE : Color.LIMEGREEN);
        gc.fillRect(10, 40, 100 * nitroCarga, 8);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(10, 40, 100, 8);
    }

    class Obstaculos {
        double x, y;
        final double largura = 70;
        final double altura = 60;

        Obstaculos(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
