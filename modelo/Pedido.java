package br.edu.ifpi.restaurante.modelo;

public class Pedido {

	private double valor;
	private String descricao;
	
	public Pedido(String descricao, double valor){
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public double getValor() {
		return valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String toString() {
		return this.descricao + ", R$ " + this.valor;
	}
}
