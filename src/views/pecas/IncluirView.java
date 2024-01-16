/*
 * Classe: IncluirView
 * Objetivo: Cria os elementos para gerar a interface com o usuário.
 * 			 Exibe o formulário onde serão inseridas as informações
 * 			 sobre a peça que deseja-se catalogar. 
 * Autor: Nathan Neves
 */
package views.pecas;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import models.pecas.Pecas;

@SuppressWarnings("serial")
public class IncluirView extends JFrame {

	private JFrame incluirFrm = new JFrame("Catálogo de Roupas: Incluir Peça");
	private JComboBox<Object> comboCategoria = new JComboBox<Object>();
	private JTextField tfMarca = new JTextField();
	private JTextField tfCor = new JTextField();
	private JTextField tfTamanho = new JTextField();
	private JTextField tfPreco = new JTextField();
	private JTextArea taDescricao = new JTextArea();
	private JCheckBox cbModificada = new JCheckBox("Modificada");
	private JButton btnLimpar = new JButton("Limpar");
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnVoltar = new JButton("Voltar");
	private JFormattedTextField tfData; /*
										 * Essa variável não foi inicializada
										 * porque usará uma máscara criada
										 * localmente no construtor.
										 */

	public IncluirView(Pecas p) {
		JPanel panel = new JPanel();

		/* Criar as opções do combo box e defini-las como modelo default. */
		String[] categorias = { "", "Calçados", "Calças", "Camisas", "Jaquetas", "Acessórios" };
		DefaultComboBoxModel<Object> comboModel = new DefaultComboBoxModel<Object>(categorias);
		comboCategoria.setModel(comboModel);

		/* Criar a máscara para aplicar no campo "Data da compra" */
		try {
			MaskFormatter datamask = new MaskFormatter("##/##/####");
			tfData = new JFormattedTextField(datamask);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		/*
		 * Adicionar no painel os labels (todos localmente), suas respectivas
		 * posições e dimensões. Incluir os demais componentes declarados como
		 * variáveis globais.
		 */

		panel.add(new JLabel("Categoria")).setBounds(35, 20, 75, 15);
		panel.add(new JLabel("Marca")).setBounds(235, 20, 50, 15);
		panel.add(new JLabel("Cor")).setBounds(355, 20, 25, 15);
		panel.add(new JLabel("Tamanho")).setBounds(35, 100, 75, 15);
		panel.add(new JLabel("Preço")).setBounds(355, 100, 50, 15);
		panel.add(new JLabel("Data da Compra")).setBounds(235, 100, 100, 15);
		panel.add(new JLabel("Descrição")).setBounds(35, 180, 75, 15);
		panel.add(comboCategoria);
		panel.add(tfMarca);
		panel.add(tfCor);
		panel.add(tfTamanho);
		panel.add(cbModificada);
		panel.add(tfData);
		panel.add(tfPreco);
		panel.add(taDescricao);
		panel.add(btnLimpar);
		panel.add(btnSalvar);
		panel.add(btnVoltar);

		/*
		 * Posicionar e dimensionar todos os componentes por causa do Layout
		 * livre do painel.
		 */
		panel.setLayout(null);
		comboCategoria.setBounds(35, 35, 155, 25);
		tfMarca.setBounds(235, 35, 80, 25);
		tfCor.setBounds(355, 35, 80, 25);
		tfTamanho.setBounds(65, 115, 25, 25);
		cbModificada.setBounds(110, 115, 95, 25);
		tfData.setBounds(235, 115, 95, 25);
		tfPreco.setBounds(355, 115, 80, 25);
		taDescricao.setBounds(35, 195, 400, 50);
		btnLimpar.setBounds(145 ,280, 90, 30);
		btnSalvar.setBounds(245, 280, 90, 30);
		btnVoltar.setBounds(345, 280, 90, 30);
		incluirFrm.setSize(480, 370);

		/* Definir dicas para alguns dos campos. */
		tfMarca.setToolTipText("Insira a marca da peça ou \"S/m\" se não houver.");
		tfCor.setToolTipText("Insira a cor da peça.\r\n Use \"/\" para mais de uma cor (ex: Preto/Branco).");
		tfTamanho.setToolTipText("Tamanho em números (38, 40...) ou letras (P, M...).");
		cbModificada.setToolTipText("Selecione caso tenha modificado a peça (ex: ajuste no costureiro).");
		tfPreco.setToolTipText("Inserir o valor pago na data da compra.");

		/*
		 * Limita a quantidade de caracteres permitida em cada campo usando uma
		 * subclasse do tipo PlainDocument implementada na classe Pecas.
		 */
		tfMarca.setDocument(p.new LimitarCaracteres(20));
		tfCor.setDocument(p.new LimitarCaracteres(20));
		tfTamanho.setDocument(p.new LimitarCaracteres(2));
		tfPreco.setDocument(p.new LimitarCaracteres(8));
		taDescricao.setDocument(p.new LimitarCaracteres(150));

		/* Ajustes triviais de posição de texto e quebra de página. */
		tfTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		taDescricao.setLineWrap(true);

		/*
		 * Adicionar o painel ao JFrame, setar visibilidade e definir operação
		 * de fechamento.
		 */
		incluirFrm.setContentPane(panel);
		incluirFrm.setVisible(true);
		incluirFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/* Métodos para obter as informações do formulário. */
	public String getCategoria() {
		return comboCategoria.getSelectedItem().toString();
	}

	public String getMarca() {
		return tfMarca.getText();
	}

	public String getCor() {
		return tfCor.getText();
	}

	public String getTamanho() {
		return tfTamanho.getText();
	}

	public boolean getAjustada() {
		if (cbModificada.isSelected())
			return true;
		else
			return false;
	}

	public String getDescricao() {
		return taDescricao.getText();
	}

	public String getData() {
		return tfData.getText();
	}

	public String getPreco() {
		return tfPreco.getText();
	}

	/*
	 * Método para fechar o frame ao apertar o botão "Voltar". O ActionListener
	 * foi implementado na classe IncluirCtrl.
	 */
	public void fecharFrame() {
		System.out.println("Voltar apertado...");
		incluirFrm.dispose();
	}

	/*
	 * Método para limpar o formulário. Evita que o usuário tenha que apagar
	 * manualmente todos os campos para cadastrar outra peça.
	 */
	public void limparForm() {
		comboCategoria.setSelectedItem("");
		tfMarca.setText("");
		tfCor.setText("");
		tfTamanho.setText("");
		cbModificada.setSelected(false);
		taDescricao.setText("");
		tfData.setText("");
		tfPreco.setText("");
	}

	/*
	 * Métodos para adicionar Listeners para os botões, Limpar, Salvar e Voltar. 
	 * O campo Preço recebe um Listener para a tecla digitada. Ele será usado no
	 * método que limita o tipo de caractere digitado no campo, implementado na
	 * classe IncluirCtrl.
	 */
	public void addLimparListener(ActionListener limpar) {
		System.out.println("Chamando método limpar...");
		btnLimpar.addActionListener(limpar);
	}
	
	public void addSalvarListener(ActionListener salvar) {
		System.out.println("Chamando método salvar...");
		btnSalvar.addActionListener(salvar);
	}

	public void addVoltarListener(ActionListener voltar) {
		System.out.println("Chamando método voltar...");
		btnVoltar.addActionListener(voltar);
	}
	
	public void addPrecoInput(KeyListener preco) {
		System.out.println("Chamando método PrecoInput");
		tfPreco.addKeyListener(preco);
	}
}