package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	//ENDEREÇO DO BANCO DE DADOS
	private static final String URL="jdbc:mysql://localhost:3306/DB";
	//USUÁRIO DO BANCO DE DADOS
	private static final String USER="root";
	//SENHA DO BANCO DE DADOS
	private static final String PASS="Senai2016@";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASS);
	}
	
			
}
