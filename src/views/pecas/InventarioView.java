/*
 * Classe: IncluirView
 * Objetivo: Cria os elementos para gerar a interface com o usuário.
 * 			 Exibe a tabela contendo informações sobre as peças já registradas.
 * Autor: Nathan Neves
 */
package views.pecas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.pecas.Pecas;
import models.pecas.TabelaModel;

@SuppressWarnings("serial")
public class InventarioView extends JFrame implements ListSelectionListener, ActionListener {

	private JFrame inventarioFrame = new JFrame("Catálogo de Roupas: Inventário");
	private JComboBox<Object> comboCategoria = new JComboBox<Object>();
	private JTextField tfMarca = new JTextField();
	private JTable tabela = new JTable();
	private TabelaModel tableModel = new TabelaModel();
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JButton btnDeletar = new JButton("Deletar");
	private JButton btnVoltar = new JButton("Voltar");

	public InventarioView() {
		JPanel geralPanel = new JPanel(new BorderLayout());
		JPanel consultaPanel = new JPanel(new GridLayout(1, 4));
		JPanel botaoPanel = new JPanel();
		JScrollPane scroll = new JScrollPane();
		
		/*Criar as opções do combo box e defini-las como modelo default. */
		String[] categorias = { "", "Calçados", "Calças", "Camisas", "Jaquetas", "Acessórios" };
		DefaultComboBoxModel<Object> comboModel = new DefaultComboBoxModel<Object>(categorias);
		comboCategoria.setModel(comboModel);
		
		/*Configurar a tabela:*/
		tabela.setModel(tableModel); // Aponta para o modelo adotado.
		carregarInventario(); // Carrega a tabela com todos os registros disponíveis.

		/*Adicionar os componentes dos paineis:*/
		consultaPanel.add(new JLabel("Pesquisar por categoria "));
		consultaPanel.add(comboCategoria);
		consultaPanel.add(new JLabel(" Pesquisar por marca"));
		consultaPanel.add(tfMarca);
		consultaPanel.add(btnPesquisar); // Painel de consulta
		scroll.getViewport().add(tabela); // Visão da tabela
		botaoPanel.add(btnDeletar);
		botaoPanel.add(btnVoltar);// Painel de botões
		geralPanel.add(consultaPanel, BorderLayout.NORTH);
		geralPanel.add(scroll, BorderLayout.CENTER);
		geralPanel.add(botaoPanel, BorderLayout.SOUTH);// Painel geral
		
		
		/*Adicionar Listeners*/
		tabela.getSelectionModel().addListSelectionListener(this); // Adiciona o Listener para quando houver mudanças
		btnDeletar.addActionListener(this);
		btnPesquisar.addActionListener(this);
		btnVoltar.addActionListener(this);
		
		/*Configurar o JFrame*/
		inventarioFrame.setContentPane(geralPanel);
		inventarioFrame.setSize(775, 475);
		inventarioFrame.setVisible(true);
		inventarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/* Limpar os campos preenchidos após clicar em "Pesquisar".*/
	public void limparPesquisa() {
		comboCategoria.setSelectedItem("");
		tfMarca.setText("");
	} 
	
	/*Trazer todos os registros ao carregar a tabela.*/
	public void carregarInventario() {
		System.out.println("Carregando inventario com todos os registros...");
		List<Pecas> lista = tableModel.pesquisar();
		if (lista.size() > 0) {
			lista.get(0);
		}
		tabela.invalidate();
		tabela.revalidate();
		tabela.repaint();
	}

	/*Implementa o evento na mudança da tabela.*/
	@Override
	public void valueChanged(ListSelectionEvent e) {
		tableModel.getItem(tabela.getSelectedRow());
	}

	/*Implemnta os eventos dos botões "Deletar", "Pesquisar" e "Voltar".*/
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Deletar".equals(cmd)){ // Eventos de deletar
			System.out.println("Deletar acionado...");
			tabela.getModel(); // Recebe o modelo sendo usado pela tabela
			try {
				int i = tableModel.getItem(tabela.getSelectedRow()).getId(); // Guarda em i o "id" da linha selecionada 
				tableModel.deletar(i); 
				carregarInventario(); // Recarrega inventário com todos os registros ainda disponíveis.
			} catch (Exception ee) {
				
			}
		} else if ("Pesquisar".equals(cmd)) { // Eventos de Pesquisar
			System.out.println("Pesquisar acionado...");
			
			// PESQUISAR CATEGORIA E MARCA
			if ((!(tfMarca.getText().equals(""))) && (!(comboCategoria.getSelectedItem().equals("")))) {
				System.out.println("Procurando por categoria & marca...");
				List<Pecas> lista = tableModel.pesquisar(comboCategoria.getSelectedItem(), tfMarca.getText());
				if (lista.size() > 0) {
					lista.get(0);
				}
				tabela.invalidate();
				tabela.revalidate();
				tabela.repaint();
				limparPesquisa();
			} else if (!(tfMarca.getText().equals(""))) { // PESQUISAR POR MARCA
				System.out.println("Procurando por marca...");
				List<Pecas> lista = tableModel.pesquisar(tfMarca.getText());
				if (lista.size() > 0) {
					lista.get(0);
				}
				tabela.invalidate();
				tabela.revalidate();
				tabela.repaint();
				limparPesquisa();
			} else if (!(comboCategoria.getSelectedItem().equals(""))) { // PESQUISAR POR CATEGORIA
				System.out.println("Procurando por categoria...");
				List<Pecas> lista = tableModel.pesquisar(comboCategoria.getSelectedItem());
				if (lista.size() > 0) {
					lista.get(0);
				}
				tabela.invalidate();
				tabela.revalidate();
				tabela.repaint();
			} else
				carregarInventario();
		} else if ("Voltar".equals(cmd)) { // Evento de Voltar
			inventarioFrame.dispose();
		}
	}
}