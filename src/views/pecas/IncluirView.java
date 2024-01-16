/*
 * Classe: IncluirView
 * Objetivo: Cria os elementos para gerar a interface com o usu�rio.
 * 			 Exibe o formul�rio onde ser�o inseridas as informa��es
 * 			 sobre a pe�a que deseja-se catalogar. 
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

	private JFrame incluirFrm = new JFrame("Cat�logo de Roupas: Incluir Pe�a");
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
										 * Essa vari�vel n�o foi inicializada
										 * porque usar� uma m�scara criada
										 * localmente no construtor.
										 */

	public IncluirView(Pecas p) {
		JPanel panel = new JPanel();

		/* Criar as op��es do combo box e defini-las como modelo default. */
		String[] categorias = { "", "Cal�ados", "Cal�as", "Camisas", "Jaquetas", "Acess�rios" };
		DefaultComboBoxModel<Object> comboModel = new DefaultComboBoxModel<Object>(categorias);
		comboCategoria.setModel(comboModel);

		/* Criar a m�scara para aplicar no campo "Data da compra" */
		try {
			MaskFormatter datamask = new MaskFormatter("##/##/####");
			tfData = new JFormattedTextField(datamask);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		/*
		 * Adicionar no painel os labels (todos localmente), suas respectivas
		 * posi��es e dimens�es. Incluir os demais componentes declarados como
		 * vari�veis globais.
		 */

		panel.add(new JLabel("Categoria")).setBounds(35, 20, 75, 15);
		panel.add(new JLabel("Marca")).setBounds(235, 20, 50, 15);
		panel.add(new JLabel("Cor")).setBounds(355, 20, 25, 15);
		panel.add(new JLabel("Tamanho")).setBounds(35, 100, 75, 15);
		panel.add(new JLabel("Pre�o")).setBounds(355, 100, 50, 15);
		panel.add(new JLabel("Data da Compra")).setBounds(235, 100, 100, 15);
		panel.add(new JLabel("Descri��o")).setBounds(35, 180, 75, 15);
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
		tfMarca.setToolTipText("Insira a marca da pe�a ou \"S/m\" se n�o houver.");
		tfCor.setToolTipText("Insira a cor da pe�a.\r\n Use \"/\" para mais de uma cor (ex: Preto/Branco).");
		tfTamanho.setToolTipText("Tamanho em n�meros (38, 40...) ou letras (P, M...).");
		cbModificada.setToolTipText("Selecione caso tenha modificado a pe�a (ex: ajuste no costureiro).");
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

		/* Ajustes triviais de posi��o de texto e quebra de p�gina. */
		tfTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		taDescricao.setLineWrap(true);

		/*
		 * Adicionar o painel ao JFrame, setar visibilidade e definir opera��o
		 * de fechamento.
		 */
		incluirFrm.setContentPane(panel);
		incluirFrm.setVisible(true);
		incluirFrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/* M�todos para obter as informa��es do formul�rio. */
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
	 * M�todo para fechar o frame ao apertar o bot�o "Voltar". O ActionListener
	 * foi implementado na classe IncluirCtrl.
	 */
	public void fecharFrame() {
		System.out.println("Voltar apertado...");
		incluirFrm.dispose();
	}

	/*
	 * M�todo para limpar o formul�rio. Evita que o usu�rio tenha que apagar
	 * manualmente todos os campos para cadastrar outra pe�a.
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
	 * M�todos para adicionar Listeners para os bot�es, Limpar, Salvar e Voltar. 
	 * O campo Pre�o recebe um Listener para a tecla digitada. Ele ser� usado no
	 * m�todo que limita o tipo de caractere digitado no campo, implementado na
	 * classe IncluirCtrl.
	 */
	public void addLimparListener(ActionListener limpar) {
		System.out.println("Chamando m�todo limpar...");
		btnLimpar.addActionListener(limpar);
	}
	
	public void addSalvarListener(ActionListener salvar) {
		System.out.println("Chamando m�todo salvar...");
		btnSalvar.addActionListener(salvar);
	}

	public void addVoltarListener(ActionListener voltar) {
		System.out.println("Chamando m�todo voltar...");
		btnVoltar.addActionListener(voltar);
	}
	
	public void addPrecoInput(KeyListener preco) {
		System.out.println("Chamando m�todo PrecoInput");
		tfPreco.addKeyListener(preco);
	}
}