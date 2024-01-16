/*Classe: IncluirCtrl
 * Objetivo: Fazer a conexão entre a view e a model.
 * 			 Adicionar eventos para botões e campos.
 * Autor: Nathan Neves
 * */
package controllers.pecas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import dao.pecas.PecasDAO;
import dao.pecas.PecasDAOImpl;
import models.pecas.Pecas;
import views.pecas.IncluirView;

public class IncluirCtrl {
	private IncluirView view;
	private Pecas model;

	/* Construtor sem parâmetros. */
	public IncluirCtrl() {

	}

	/* Construtor com parâmetros. */
	public IncluirCtrl(IncluirView form, Pecas model) {
		this.view = form;
		this.model = model;

		/* Chamadar os Listeners no formulário. */
		this.view.addLimparListener(new LimparListener(view));
		this.view.addSalvarListener(new SalvarListener());
		this.view.addVoltarListener(new VoltarListener(view));
		this.view.addPrecoInput(new ControladorInput(view));
	}

	/*
	 * Adicionar adicionar a peça no banco de dados usando o método implementado
	 * em PecasDAOImpl.
	 */
	public void adicionar(Pecas p) {
		PecasDAO pDao = new PecasDAOImpl();
		pDao.adicionar(p);
	}

	/* Transferir as informações do formulário para a model.*/
	public Pecas formToPecas() {
		model.setCategoria(view.getCategoria());
		model.setMarca(view.getMarca());
		model.setCor(view.getCor());
		model.setTamanho(view.getTamanho());
		model.setModificada(view.getAjustada());
		model.setDescricao(view.getDescricao());
		model.setData(view.getData());
		model.setPreco(view.getPreco());

		return model;
	}

	/*Implementa os eventos do botão Limpar.*/
	private class LimparListener implements ActionListener {
		private IncluirView view;

		public LimparListener(IncluirView view) {
			this.view = view;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Acionou limpar...");
			view.limparForm();
		}
	}
	
	/*Implementa os eventos do botão Salvar.*/
	private class SalvarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			try {
				System.out.println("Acionou salvar...");
				adicionar(formToPecas());
				view.limparForm();
				JOptionPane.showMessageDialog(null, "Peça salva\ncom sucesso!");
			} catch (Exception exception) {
				exception.printStackTrace();
				JOptionPane.showMessageDialog(null, "A peça não pôde ser salva.\nPreencha novamente, por favor.");
				view.limparForm();
			}
		}
	}

	/*Implementa os eventos do botão Voltar.*/
	private class VoltarListener implements ActionListener {
		private IncluirView view;

		public VoltarListener(IncluirView view) {
			this.view = view;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Acionou voltar...");
			view.fecharFrame();
		}
	}

	/*Implementa os eventos ao digitar no campo Preço.*/
	private class ControladorInput implements KeyListener {

		public ControladorInput(IncluirView view) {
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent input) {
			char c = input.getKeyChar();
			if (!(Character.isDigit(c) || c == KeyEvent.VK_COMMA || c == KeyEvent.VK_BACK_SPACE
					|| c == KeyEvent.VK_DELETE)) {
				input.consume();
			}
		}

	}
}
