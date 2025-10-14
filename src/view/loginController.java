package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
	public void sair() {
		System.exit(0);
	}
	public void entrar() {
		try {
		String usuario=txtUsuario.getText();
		String senha=txtSenha.getText();
		
		if (usuario.equals("Henrique")&& senha.equals("admin")) {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.CONFIRMATION);
			aviso.setTitle("Confimação");
			aviso.setHeaderText(null);
			aviso.setContentText("Bem Vindo ao Sistema "+usuario);
			aviso.showAndWait();
			
			//Fechar tela de login
			txtUsuario.getScene().getWindow().hide();
			
			//Abrir a tela principal
			Parent root = FXMLLoader.load(getClass().getResource("aplicativo.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} else {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.ERROR);
			aviso.setTitle("Erro");
			aviso.setHeaderText(null);
			aviso.setContentText("Usuario ou Senha incorretos");
			aviso.showAndWait();
		}
	} catch(Exception e) {
		e.printStackTrace();}
	}
	
	@FXML
	// @FXML ou @override: indica que será executado assim que a página carregar
	private void initialize() {
		// Ao pressionar enter no campo usuário foca a edição no campo senha
		txtUsuario.setOnAction(e->{txtSenha.requestFocus();});
		// Ao pressionar enter no campo senha aciona o metodo entrar
		txtSenha.setOnAction(e->{entrar();});
	}
}
