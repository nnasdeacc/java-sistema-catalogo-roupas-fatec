/*
 * Classe: TabelaModel
 * Objetivo: Modificar o modelo de tabela utilizado no inventário.
 * Data: 06/12/2017
 */
package models.pecas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import dao.pecas.PecasDAO;
import dao.pecas.PecasDAOImpl;

public class TabelaModel implements TableModel {

	private List<Pecas> lista = new ArrayList<Pecas>();
	private PecasDAO pDao = new PecasDAOImpl();

	/*Pesquisar pela marca (parâmetro String)*/
	public List<Pecas> pesquisar(String marca) {
		lista = pDao.pesquisarPorMarca(marca);
		return lista;
	}

	/*Pesquisar pela categoria (parâmetro Object).*/
	public List<Pecas> pesquisar(Object categoria) {
		lista = pDao.pesquisarPorCategoria(categoria);
		return lista;
	}

	/*Pesquisar pelos dois parâmetros anteriores (Object e String).*/
	public List<Pecas> pesquisar(Object categoria, String marca) {
		lista = pDao.pesquisarCategoriaMarca(categoria, marca);
		return lista;
	}
	
	/*Pesquisar sem parâmetro. Retorna todos os registros.*/
	public List<Pecas> pesquisar() {
		lista = pDao.pesquisarTodos();
		return lista;
	}
	
	public void deletar(int id) {
		pDao.deletarItem(id);
	}

	/*Recebe um índice e busca o item neste índice.*/
	public Pecas getItem(int i) {
		return lista.get(i);
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	/*Configurar o tipo de dado das colunas*/
	@Override
	public Class<?> getColumnClass(int coluna) {
		switch (coluna) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return String.class;
		case 6:
			return String.class;
		case 7:
			return String.class;
		}
		return String.class;
	}

	/*Retornar quantidade de colunas.*/
	@Override
	public int getColumnCount() {
		return 8;
	} // Neste caso o número é fixo porque a tabela não é editável.

	/*Configurar os nomes das colunas.*/
	@Override
	public String getColumnName(int coluna) {
		switch (coluna) {
		case 0:
			return "Categoria";
		case 1:
			return "Marca";
		case 2:
			return "Cor";
		case 3:
			return "Tamanho";
		case 4:
			return "Modificada";
		case 5:
			return "Descrição";
		case 6:
			return "Data";
		case 7:
			return "Preço";
		}
		return "";
	}

	/*Retornar quantidade de linhas.*/
	@Override
	public int getRowCount() {
		return lista.size(); // Varia de acordo com a quantidade de registros
	}

	/*Configurar em qual variável da classe Peças está a informação da coluna correspondente.*/
	@Override
	public Object getValueAt(int linha, int coluna) {
		Pecas p = lista.get(linha);
		switch (coluna) {
		case 0:
			return p.getCategoria();
		case 1:
			return p.getMarca();
		case 2:
			return p.getCor();
		case 3:
			return p.getTamanho();
		case 4:
			return p.getModificada();
		case 5:
			return p.getDescricao();
		case 6:
			return p.getData();
		case 7:
			return p.getPreco();
		}
		return "";
	}

	/*Definir se a tabela é ou não editável.*/
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}
}
