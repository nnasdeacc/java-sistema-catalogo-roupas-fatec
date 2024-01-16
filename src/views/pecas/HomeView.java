/*
 * Classe: HomeView
 * Objetivo: Cria os elementos para gerar a interface com o usu�rio.
 * 			 Permitir� que o usu�rio escolha entre os m�dulos de
 * 			 inclus�o ou consulta de pe�as.
 * Autor: Nathan Neves
 */
package views.pecas;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HomeView extends JFrame {

	private JButton btnIncluir = new JButton("Incluir Pe�a");
	private JButton btnConsultar = new JButton("Consultar Invent�rio");

	public HomeView() {
		JFrame homeFrame = new JFrame("Cat�lago de Roupas: Home");
		JPanel homePanel = new JPanel();

		/*Adicionar os bot�es na interface.*/
		homePanel.add(btnIncluir);
		homePanel.add(btnConsultar);
		
		/*Dimensionar e posicionar os componentes.*/
		homePanel.setLayout(null);
		homePanel.setBounds(10, 10, 320, 305);
		btnIncluir.setBounds(10, 10, 295, 125);
		btnConsultar.setBounds(10, 140, 295, 125);
		homeFrame.setSize(330, 315);
		
		homeFrame.setContentPane(homePanel);
		homeFrame.setVisible(true);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 *Adicionar um Listener nos bot�es Incluir e Consultar
	 */
	
	public void addIncluirListener(ActionListener incluir) {
		System.out.println("Est� chamando o m�todo incluir");
		btnIncluir.addActionListener(incluir);
	}

	public void addConsultarListener(ActionListener consultar) {
		System.out.println("Est� chamando o m�todo consultar");
		btnConsultar.addActionListener(consultar);
	}
}
