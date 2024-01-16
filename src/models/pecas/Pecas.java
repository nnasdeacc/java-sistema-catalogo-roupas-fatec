/*Classe: Pecas
 *Objetivo: Reposit�rio de dados.
 *Autor: Nathan Neves*/
package models.pecas;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Pecas {
	private int id;
	private String categoria;
	private String marca;
	private String cor;
	private String tamanho;
	private String descricao;
	private String preco;
	private String data;
	private boolean modificada; /*Neste caso as vari�veis s�o do tipo String para facilitar
	 							  o insert no banco de dados.*/

	/*M�todos para obter e modificar as vari�veis desta classe.*/
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(Object categoria) {
		this.categoria = categoria.toString();
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public boolean getModificada() {
		return modificada;
	}

	public void setModificada(boolean modificada) {
		this.modificada = modificada;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}
	
	/*Subclasse respons�vel por limitar a quantidade de caracteres permitidos em cada campo do formul�rio.*/
	@SuppressWarnings("serial")
	public class LimitarCaracteres extends PlainDocument {
		private int limit;

		public LimitarCaracteres(int limit) { // Recebe o limite enviado no argumento em IncluirView
			this.limit = limit; 
		}

		public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
			// A String � referente ao texto no campo do formul�rio, as outras vari�veis s�o dispens�veis
			if (str == null) {
				return; // Se o campo estiver em branco, n�o retorna nada.
			} else if ((getLength() + str.length()) <= limit) { // Se o conte�do digitado for menor ou igual ao limite
				str.toUpperCase(); // Transforma o conte�do em mai�scula para n�o haver erros de interpreta��o entre mai�scula e min�scula
				super.insertString(offset, str, set); // Chama o m�todo recursivamente at� que d� o limite estabelecido
			}
		}
	}
}