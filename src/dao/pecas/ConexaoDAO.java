/*
 * Classe: ConexaoDAO
 * Objetivo: Contém parâmetros para conexão com o banco de dados MySQL.
 * 			 Utiliza o Padrão Singleton conforme ensinado em sala de aula.
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
			Class.forName("com.mysql.jdbc.Driver"); // Driver de conexão com o banco.
			String url = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10209464"; // Endereço local para conexão.
			String user = "sql10209464"; // Nome de usuário para login.
			String pass = "6qbqqxDlhs"; // Senha de usuário para login.

			con = DriverManager.getConnection(url, user, pass); // Usa o driver para efetuar a conexão através dos parâmetros acima
			System.out.println("Conexão realizada");
		} catch (ClassNotFoundException e) {
			System.out.println("Classe não existe"); // Possível exceção
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException"); // Possível exceção
			e.printStackTrace();
		}
	}

	/*Verificar se já existe um instância daquela conexão.*/
	public static ConexaoDAO getInstance() {
		if (instancia == null) {
			instancia = new ConexaoDAO();
		}
		return instancia;
	}

	public Connection getConnection() {
		return con; // Obter instância da conexão.
	}
}