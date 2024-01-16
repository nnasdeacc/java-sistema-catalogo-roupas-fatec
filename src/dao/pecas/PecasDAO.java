/*
 * Interface: PecasDAO
 * Objetivo: Serviços que serão assinados por uma ou mais classes.
 * Autor: Nathan Neves
 */
package dao.pecas;

import java.util.List;

import models.pecas.Pecas;

public interface PecasDAO {
	void adicionar(Pecas p);

	List<Pecas> pesquisarPorCategoria(Object categoria);

	List<Pecas> pesquisarPorMarca(String marca);
	
	List<Pecas> pesquisarCategoriaMarca(Object categoria, String marca);

	List<Pecas> pesquisarTodos();
	
	void deletarItem(int id);
}
