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

public class calculadoraController {

    @FXML
    private Button btnDividir;

    @FXML
    private Button btnMultiplicar;

    @FXML
    private Button btnSomar;

    @FXML
    private Button btnSubtrair;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;
    
    @FXML
    private Button btnReset;

    @FXML
    private void initialize() {
    	
    	//adiciona um escutador de evento no text field de numero 1 ao digitar dentro do text field ele vai trocar a letra por uma informação vazia
    	txtNumero1.textProperty().addListener((observable, oldValue, newValue)->{
    		txtNumero1.setText(newValue.replaceAll("[^\\d.]",""));
    	});
    	txtNumero2.textProperty().addListener((observable, oldValue, newValue)->{
    		txtNumero2.setText(newValue.replaceAll("[^\\d.]",""));
    	});
    }
    
    @FXML
    public void Resetar() {
        txtNumero1.setText("0");
        txtNumero2.setText("0");
        lblResultado.setText("0");
    }
    
    @FXML
    public void Somar () {
    	double numero1;
    	double numero2;
    	try {
        numero1 = Double.valueOf(txtNumero1.getText()); //utiliza o getText para retornar
    	} catch(Exception e) {
    		numero1=0;
    		txtNumero1.setText("0");
    	}
    	try {
        numero2 = Double.valueOf(txtNumero2.getText()); //converte o tipo de texto para double
    	} catch(Exception e) {
    		numero2=0;
    		txtNumero2.setText("0");
    	}
    	double Resultado = numero1+numero2;
        // RETORNA O VALOR DE DOUBLE PARA string
        // INFORMA O RESULTADO NA LABEL COM O setText
        lblResultado.setText(String.valueOf(Resultado));
        
        String parOuImpar;
        if(Resultado % 2 == 0) {
        	parOuImpar=" É Par.";
        } else {
        	parOuImpar=" É Impar.";
        }
        
        lblResultado.setText("Resultado: "+String.valueOf(Resultado)+parOuImpar);
    }
    
    @FXML
    public void Subtrair () {
        double numero1 = strtodouble(txtNumero1.getText());
        double numero2 = strtodouble(txtNumero2.getText());
        txtNumero1.setText(String.valueOf(numero1));
        txtNumero2.setText(String.valueOf(numero2));
        double Resultado = numero1-numero2;
        
        lblResultado.setText(String.valueOf(Resultado));
        
        String parOuImpar;
        if(Resultado % 2 == 0) {
        	parOuImpar=" É Par.";
        } else {
        	parOuImpar=" É Impar.";
        }
        
        lblResultado.setText("Resultado: "+String.valueOf(Resultado)+parOuImpar);
    }
    
    @FXML
    public void Multiplicar () {
    	double numero1 = strtodouble(txtNumero1.getText());
        double numero2 = strtodouble(txtNumero2.getText());
        txtNumero1.setText(String.valueOf(numero1));
        txtNumero2.setText(String.valueOf(numero2));
        double Resultado = numero1 * numero2;
        
        lblResultado.setText(String.valueOf(Resultado));
        
        String parOuImpar;
        if(Resultado % 2 == 0) {
        	parOuImpar=" É Par.";
        } else {
        	parOuImpar=" É Impar.";
        }
        
        lblResultado.setText("Resultado: "+String.valueOf(Resultado)+parOuImpar);
    }
    
    @FXML
    public void Dividir () {
    	double numero1 = strtodouble(txtNumero1.getText());
        double numero2 = strtodouble(txtNumero2.getText());
        txtNumero1.setText(String.valueOf(numero1));
        txtNumero2.setText(String.valueOf(numero2));
        double Resultado = numero1 / numero2;
        lblResultado.setText(String.valueOf(Resultado));
        
        String parOuImpar;
        if(Resultado % 2 == 0) {
        	parOuImpar=" É Par.";
        } else {
        	parOuImpar=" É Impar.";
        }
        
        lblResultado.setText("Resultado: "+String.valueOf(Resultado)+parOuImpar);
    }
    @FXML
	public static Double strtodouble(String numero) {
    	try {
    		return Double.valueOf(numero);
    	} catch(Exception e) {
    		return (double) 0;
    	}
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