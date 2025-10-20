package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import application.dao.usuarioDAO;

public class loginController {

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Label lblNovoUsuario;

    // Método para sair do sistema
    public void sair() {
        System.exit(0);
    }

    // Método para entrar no sistema
    public void entrar() {
        try {
            String usuario = txtUsuario.getText();
            String senha = txtSenha.getText();
            
            usuarioDAO dao = new usuarioDAO();
            if (dao.autenticar(usuario, senha)) {
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Confirmação");
                aviso.setHeaderText(null);
                aviso.setContentText("Bem-vindo ao sistema, " + usuario);
                aviso.showAndWait();

                // Fechar tela de login
                txtUsuario.getScene().getWindow().hide();

                // Abrir a tela principal
                Parent root = FXMLLoader.load(getClass().getResource("/view/aplicativo.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                Alert aviso = new Alert(Alert.AlertType.ERROR);
                aviso.setTitle("Erro");
                aviso.setHeaderText(null);
                aviso.setContentText("Usuário ou senha incorretos");
                aviso.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método chamado ao clicar em "Novo Usuário"
    @FXML
    private void novoUsuario(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/usuario.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inicialização da tela de login
    @FXML
    private void initialize() {
        // Pressionar Enter no campo usuário foca no campo senha
        txtUsuario.setOnAction(e -> txtSenha.requestFocus());

        // Pressionar Enter no campo senha executa o login
        txtSenha.setOnAction(e -> entrar());
    }
}
