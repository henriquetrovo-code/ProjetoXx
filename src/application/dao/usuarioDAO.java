package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.model.usuarioModel;
import application.util.conexao;

public class usuarioDAO {
	//INSERIOR USUARIO
	public boolean inserirUsuario(usuarioModel u) {
		try {
		Connection conn=null;
		PreparedStatement query=null;
		
		conn=conexao.getConnection();
		String sql="insert usuario (nomeCompleto,login,senha)" + "values (?,?,?)";
		
		query= conn.prepareStatement(sql);
		query.setString(1, u.getNomeCompleto());
		query.setString(2, u.getLogin());
		query.setString(3, u.getSenha());
		
		int insert = query.executeUpdate();
		
		return insert > 0;
		
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
