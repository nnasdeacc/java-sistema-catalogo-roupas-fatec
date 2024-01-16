/*
 * Classe: HomeView
 * Objetivo: Adicionar eventos para os botões
 * 			 ligando-os com as respectivas interfaces.
 * Autor: Nathan Neves
 */
package controllers.pecas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.pecas.Pecas;
import views.pecas.HomeView;
import views.pecas.IncluirView;
import views.pecas.InventarioView;

public class HomeCtrl {
	HomeView home;

	public HomeCtrl(HomeView view) {
		this.home = view;

		this.home.addIncluirListener(new IncluirListener());
		this.home.addConsultarListener(new ConsultarListener());
	}

	/*Subclasse que implementa os eventos do botão Incluir.*/
	class IncluirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Acionou incluir...");
			Pecas model = new Pecas();
			IncluirView form = new IncluirView(model);
			new IncluirCtrl(form, model);
		}
	}

	/*Subclasse que implementa os eventos do botão Consultar.*/
	class ConsultarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Acionou consultar...");
			new InventarioView();
		}

	}
}
