package br.edu.ifpi.restaurante.aplicacao;

import br.edu.ifpi.restaurante.modelo.Restaurante;
import br.edu.ifpi.restaurante.modelo.Mesa;
import br.edu.ifpi.restaurante.modelo.Pedido;

public class TestaRestaurante {

	public static void main(String[] args) {
		Restaurante restaurante = new Restaurante("Cajuina", 10);
		
		System.out.println(restaurante);
		
		restaurante.abrirMesa(1);
		restaurante.abrirMesa(2);
		Pedido p1 = new Pedido("Agua", 2);
		Mesa m1 = restaurante.getMesas()[0];
		Mesa m2 = restaurante.getMesas()[1];
		m1.adicionarPedido(p1);
		Pedido p2 = new Pedido("Picanha", 75);
		m2.adicionarPedido(p2);
		m2.setRateio(3);
		m2.setComTaxa(false);
		m2.adicionarPedido(p1);
		m1.juntarMesas(m2);
		
		for (Mesa mesa : restaurante.getMesas()){
			System.out.println(mesa);
		}
		
		System.out.println(restaurante.verSituacaoEValoresDasMesas());
	}
}
