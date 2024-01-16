/*
 * Classe: Executar
 * Objetivo: Instancia o método responsável por iniciar o programa.
 * Autor: Nathan Neves
 * Data: 05/12/2017
 */
package executar.pecas;

import controllers.pecas.HomeCtrl;
import views.pecas.HomeView;

public class Executar {

	public static void main(String[] args) {

		new HomeCtrl(new HomeView());

	}
}
