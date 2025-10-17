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
import javafx.stage.Stage;
import view.calculadoraController; 

public class calcularIMCController {

    @FXML private Button btnCalcularIMC;
    @FXML private Label lblResultado;
    @FXML private TextField txtAltura;
    @FXML private TextField txtNome;
    @FXML private TextField txtPeso;
    @FXML private Label lblClassificação;


    public void calcularIMC() {
        String nome=txtNome.getText();
        double peso=Double.valueOf(txtPeso.getText());
        double altura=calculadoraController.strtodouble(txtAltura.getText());
        double imc=peso / (altura*altura);
        String clasf;
        if(imc<18.5) {
            clasf = "Peso Baixo";
        } else if(imc<25){
            clasf = "Peso Normal";
        } else if(imc<35) {
            clasf = "Obesidade Grau I";
        } else if(imc<40) {
            clasf = "Obesidade Grau II";
        } else {
            clasf = "Obesidade Grau III";
        }
        lblResultado.setText(String.format(nome)+", O seu IMC é "+String.format("%.2f",imc));
        lblClassificação.setText("Classificação: "+String.format(clasf));
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
}