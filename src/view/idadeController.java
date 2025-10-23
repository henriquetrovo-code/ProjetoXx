package view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class idadeController {

    @FXML
    private Button btIdade;

    @FXML
    private DatePicker dpIdade;

    @FXML
    private TextField txtIdade;

    @FXML
    private Label lblResultado;

    @FXML
    private void initialize() {
        // Oculta o label ao iniciar
        lblResultado.setText("");
        lblResultado.setVisible(false);
    }

    @FXML
    private void calcularIdade(ActionEvent event) {
        String nome = txtIdade.getText();
        LocalDate dataNascimento = dpIdade.getValue();

        if (dataNascimento != null && nome != null && !nome.isEmpty()) {
            LocalDate hoje = LocalDate.now();
            Period idade = Period.between(dataNascimento, hoje);
            long diasVividos = ChronoUnit.DAYS.between(dataNascimento, hoje);
            String diaSemanaNascimento = dataNascimento.format(
                DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            );

            lblResultado.setText(
                nome + ", sua idade é: " + idade.getYears() + " anos.\n" +
                "Você já viveu " + diasVividos + " dias.\n" +
                "Você nasceu em uma " + diaSemanaNascimento + "."
            );
        } else {
            lblResultado.setText("Por favor, preencha todos os campos.");
        }

        // Exibe o resultado após o clique
        lblResultado.setVisible(true);
    }
    @FXML
    private void voltarTelaInicial(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aplicativo.fxml"));
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

}
