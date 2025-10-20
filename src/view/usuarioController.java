package view;

import application.dao.usuarioDAO;
import application.model.usuarioModel;
import javafx.fxml.FXML;
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
			if(cadastrado) {
				// MENSASEM DE CADASTRO REALIZADO
			} else {
				// MENSAGEM DE ERRO AO CADASTRAR
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
