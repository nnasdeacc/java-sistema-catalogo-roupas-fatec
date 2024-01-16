/*
 * Classe: PecasDAOImpl
 * Objetivo: Implementação dos serviços que serão assinados por uma ou mais classes.
 * Autor: Nathan Neves
 */
package dao.pecas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.pecas.Pecas;

public class PecasDAOImpl implements PecasDAO {

	/* Método para fazer inserts no banco. */
	@Override
	public void adicionar(Pecas p) {
		try {
			Connection con = ConexaoDAO.getInstance().getConnection();

			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO pecas " + " (id, categoria, marca, cor, tamanho, descricao, "
							+ "preco, data, ajustada) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, 0); // Este item é auto incrementável, não precisa
								// passar nenhum valor.
			stmt.setString(2, p.getCategoria());
			stmt.setString(3, p.getMarca());
			stmt.setString(4, p.getCor());
			stmt.setString(5, p.getTamanho());
			stmt.setString(6, p.getDescricao());
			stmt.setString(7, p.getPreco());
			stmt.setString(8, p.getData());
			stmt.setString(9, Boolean.toString(p.getModificada())); // Passar para String para ter consistência no banco.
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
	}

	/* Método para fazer select no banco. */
	@Override
	public List<Pecas> pesquisarPorCategoria(Object categoria) {

		categoria.toString();

		List<Pecas> lista = new ArrayList<Pecas>();

		try {
			Connection con = ConexaoDAO.getInstance().getConnection();
			String cmd = "SELECT * FROM pecas WHERE categoria like ?";
			PreparedStatement stmt = con.prepareStatement(cmd);
			stmt.setString(1, "%" + categoria + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pecas p = new Pecas();
				p.setId(rs.getInt("id"));
				p.setCategoria(rs.getString("categoria"));
				p.setMarca(rs.getString("marca"));
				p.setCor(rs.getString("cor"));
				p.setTamanho(rs.getString("tamanho"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getString("preco"));
				p.setData(rs.getString("data"));
				p.setModificada(Boolean.parseBoolean(rs.getString("ajustada")));

				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* Método para fazer select no banco. */
	@Override
	public List<Pecas> pesquisarPorMarca(String marca) {

		List<Pecas> lista = new ArrayList<Pecas>();

		try {
			Connection con = ConexaoDAO.getInstance().getConnection();
			String cmd = "SELECT * FROM pecas WHERE marca like ?";
			PreparedStatement stmt = con.prepareStatement(cmd);
			stmt.setString(1, "%" + marca + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pecas p = new Pecas();
				p.setId(rs.getInt("id"));
				p.setCategoria(rs.getString("categoria"));
				p.setMarca(rs.getString("marca"));
				p.setCor(rs.getString("cor"));
				p.setTamanho(rs.getString("tamanho"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getString("preco"));
				p.setData(rs.getString("data"));
				p.setModificada(Boolean.parseBoolean(rs.getString("ajustada")));

				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	

	/* Método para fazer select no banco. */
	@Override
	public List<Pecas> pesquisarTodos() {

		List<Pecas> lista = new ArrayList<Pecas>();

		try {
			Connection con = ConexaoDAO.getInstance().getConnection();
			String cmd = "SELECT * FROM pecas";
			PreparedStatement stmt = con.prepareStatement(cmd);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pecas p = new Pecas();
				p.setId(rs.getInt("id"));
				p.setCategoria(rs.getString("categoria"));
				p.setMarca(rs.getString("marca"));
				p.setCor(rs.getString("cor"));
				p.setTamanho(rs.getString("tamanho"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getString("preco"));
				p.setData(rs.getString("data"));
				p.setModificada(Boolean.parseBoolean(rs.getString("ajustada")));

				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/* Método para fazer select no banco. */
	@Override
	public List<Pecas> pesquisarCategoriaMarca(Object categoria, String marca) {

		List<Pecas> lista = new ArrayList<Pecas>();

		try {
			Connection con = ConexaoDAO.getInstance().getConnection();
			String cmd = "SELECT * FROM pecas WHERE categoria like ? AND marca like ?";
			PreparedStatement stmt = con.prepareStatement(cmd);
			stmt.setString(1, "%" + categoria + "%");
			stmt.setString(2, "%" + marca + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pecas p = new Pecas();
				p.setId(rs.getInt("id"));
				p.setCategoria(rs.getString("categoria"));
				p.setMarca(rs.getString("marca"));
				p.setCor(rs.getString("cor"));
				p.setTamanho(rs.getString("tamanho"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getString("preco"));
				p.setData(rs.getString("data"));
				p.setModificada(Boolean.parseBoolean(rs.getString("ajustada")));

				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* Método para deletar do banco. */
	public void deletarItem(int id) {
		try {
			Connection con = ConexaoDAO.getInstance().getConnection();
			String cmd = "DELETE FROM pecas WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(cmd);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}