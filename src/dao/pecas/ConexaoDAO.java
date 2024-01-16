/*
 * Classe: ConexaoDAO
 * Objetivo: Cont�m par�metros para conex�o com o banco de dados MySQL.
 * 			 Utiliza o Padr�o Singleton conforme ensinado em sala de aula.
 * Autor: Nathan Neves
 */
package dao.pecas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

	private static ConexaoDAO instancia;
	private Connection con;

	private ConexaoDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Driver de conex�o com o banco.
			String url = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10209464"; // Endere�o local para conex�o.
			String user = "sql10209464"; // Nome de usu�rio para login.
			String pass = "6qbqqxDlhs"; // Senha de usu�rio para login.

			con = DriverManager.getConnection(url, user, pass); // Usa o driver para efetuar a conex�o atrav�s dos par�metros acima
			System.out.println("Conex�o realizada");
		} catch (ClassNotFoundException e) {
			System.out.println("Classe n�o existe"); // Poss�vel exce��o
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException"); // Poss�vel exce��o
			e.printStackTrace();
		}
	}

	/*Verificar se j� existe um inst�ncia daquela conex�o.*/
	public static ConexaoDAO getInstance() {
		if (instancia == null) {
			instancia = new ConexaoDAO();
		}
		return instancia;
	}

	public Connection getConnection() {
		return con; // Obter inst�ncia da conex�o.
	}
}