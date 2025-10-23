package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class detalhesController {

    @FXML
    private Label infoCarro;

    @FXML
    private ImageView imagemCarro;

    public void receberInfo(String texto, String caminhoImagem) {
        infoCarro.setText(texto);
        imagemCarro.setImage(new Image(getClass().getResourceAsStream(caminhoImagem)));
    }
    @FXML
    private void voltarTelaCarros(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Carros.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    @FXML
    public void logout(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

            // Fecha a tela atual
            Stage atual = (Stage) ((Label) event.getSource()).getScene().getWindow();
            atual.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Label botaoLigar;

    @FXML
    private Circle luzStatus;

    private boolean carroLigado = false;

    @FXML
    public void alternarEstadoCarro(MouseEvent event) {
        carroLigado = !carroLigado;

        if (carroLigado) {
            luzStatus.setFill(Color.GREEN); // carro ligado: vermelho vivo
            botaoLigar.setText("Desligar");
            botaoLigar.setStyle("-fx-text-fill: red;");
        } else {
            luzStatus.setFill(Color.DARKRED); // carro desligado: vermelho escuro
            botaoLigar.setText("Ligar");
            botaoLigar.setStyle("-fx-text-fill: green;");
        }
    }
 }

