package view;

import application.dao.usuarioDAO;
import application.model.usuarioModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class usuarioController {
	@FXML private Button btSalvar;
	@FXML private TextField txtUsuario;
	@FXML private TextField txtLogin;
	@FXML private TextField txtSenha;
	
	public void Salvar() {
		try {
			usuarioDAO dao = new usuarioDAO();
			String nome=txtUsuario.getText();
			String login=txtLogin.getText();
			String senha=txtSenha.getText();
			
			usuarioModel usuarioNovo= new usuarioModel(0,nome,login,senha);
			boolean cadastrado =dao.inserirUsuario(usuarioNovo);
			Alert mensagem;
			mensagem= new Alert(Alert.AlertType.INFORMATION);
			
			if(cadastrado) {
				// MENSASEM DE CADASTRO REALIZADO
				mensagem.setTitle("Confirmação");
				mensagem.setHeaderText(null);
				mensagem.setContentText("Cadastro realizado com sucesso");
				mensagem.showAndWait();
				
				// LIMPAR CAMPOS APÓS CONFIRMAÇÃO DO CADASTRO
				txtUsuario.setText("");
				txtLogin.setText("");
				txtSenha.setText("");
				
			} else {
				// MENSAGEM DE ERRO AO CADASTRAR
			}	mensagem.setTitle("ERRO");
				mensagem.setHeaderText(null);
				mensagem.setContentText("Erro ao realizar cadastro");
				mensagem.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
    private void initialize() {
        // Pressionar Enter no campo usuário foca no campo senha
        txtUsuario.setOnAction(e -> txtLogin.requestFocus());

        txtLogin.setOnAction(e-> txtSenha.requestFocus());
        // Pressionar Enter no campo senha executa o login
        txtSenha.setOnAction(e -> Salvar());
    }
}
