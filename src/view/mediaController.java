package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class mediaController {

    @FXML private TextField txtNota1;
    @FXML private TextField txtNota2;
    @FXML private TextField txtNota3;
    @FXML private TextField txtNota4;
    @FXML private Label lblResultado;
    @FXML private Label lblClassificação;
    @FXML private Button btMedia;
    
    
    @FXML
    public void initialize() {
        txtNota1.setOnAction(e -> txtNota2.requestFocus());
        txtNota2.setOnAction(e -> txtNota3.requestFocus());
        txtNota3.setOnAction(e -> txtNota4.requestFocus());
        txtNota4.setOnAction(e -> btMedia.requestFocus());
        
        txtNota4.setOnAction(e -> calcularMedia());

    }


    public void calcularMedia() {
            double num1 = Double.parseDouble(txtNota1.getText());
            double num2 = Double.parseDouble(txtNota2.getText());
            double num3 = Double.parseDouble(txtNota3.getText());
            double num4 = Double.parseDouble(txtNota4.getText());
            double media = (num1 + num2 + num3 + num4) / 4;
            
            String clasf = null;
            if(media<5) {
                clasf = "Aluno Reprovado!";
            } else if(media<7){
                clasf = "Aluno em Recuperação!";
            } else if(media>=7) {
                clasf = "Aluno Aprovado!";
            } 

            lblResultado.setText("A média é: " + String.format("%.2f", media));
            lblClassificação.setText("Classificação: "+ String.format(clasf));
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
