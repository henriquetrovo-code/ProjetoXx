package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}
