package br.edu.ifpi.restaurante.modelo;

public class Mesa {
	
	public final static String DISPONIVEL = "Aberta";
	public final static String OCUPADA = "Fechada";
	private final static int MAX_PEDIDOS = 4;
	public final static String PRINCIPAL = "Principal";
	public final static String SECUNDARIA = "Secundaria";
	
	private int numero;
	private String status;
	private Pedido[] pedidos;
	private int qtdMaximaDePedidosAtual = MAX_PEDIDOS;
	private int contadorDePedidos;
	private boolean comTaxa;
	private int rateio;
	private String tipo;
	private Mesa mesaJuntada;
	
	public Mesa(int numero) {
		this.numero = numero;
		this.status = Mesa.DISPONIVEL;
		this.contadorDePedidos = 0;
		this.tipo = Mesa.PRINCIPAL;
		this.comTaxa = true;
		this.pedidos = new Pedido[qtdMaximaDePedidosAtual];
	}

	public void abrir(){
		this.status = Mesa.OCUPADA;
	}
	
	public void adicionarPedido(Pedido pedido){
		if (this.status != Mesa.OCUPADA){
			System.out.println("Por favor, abra a mesa primeiro!");
			return;
		}		
		pedidos[contadorDePedidos++] = pedido;
		
		if (contadorDePedidos == qtdMaximaDePedidosAtual){
			qtdMaximaDePedidosAtual += MAX_PEDIDOS;
			
			Pedido[] temp = this.pedidos;
			
			this.pedidos = new Pedido[qtdMaximaDePedidosAtual];
			
			for (int i = 0; i < temp.length; i++) {
				this.pedidos[i] = temp[i];
			}
		}
	}
	
	public double conta(){
		double valor = 0;
		
		for (Pedido pedido : this.pedidos) {
			if (pedido == null) break;
			valor += pedido.getValor();
		}
		
		if (comTaxa){
			valor *= 1.1;
		}
		
		if (this.tipo == Mesa.SECUNDARIA){
			valor = 0;
			return valor;
		}
		return valor;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setComTaxa(boolean comTaxa){
		this.comTaxa = comTaxa;
	}

	public void setRateio(int rateio){
		this.rateio = rateio;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public Pedido[] getPedidos() {
		return pedidos;
	}
	
	public void juntarMesas(Mesa mesa){
		this.mesaJuntada = mesa;
		mesa.mesaJuntada = this;
		mesa.tipo = Mesa.SECUNDARIA;
		int temp = this.contadorDePedidos;
		for (Pedido pedido : mesa.getPedidos()) {
			adicionarPedido(pedido);
		}
		if (mesa.getStatus() != Mesa.OCUPADA)
			mesa.setStatus(Mesa.OCUPADA);
		this.contadorDePedidos = temp + mesa.contadorDePedidos;
	}
	
	public String toString() {
		String str = "\nMesa n. "+numero;
					if (this.status != Mesa.OCUPADA){
						str += "\nMesa fechada.\n";
						return str;
					}
					if (this.tipo == Mesa.SECUNDARIA){
						str += "\nA conta desta mesa foi pra mesa " + mesaJuntada.getNumero()+".";
						return str;
					}
					str += "\nNumero de pedidos: "+this.contadorDePedidos
					+ "\nPedido\t\tValor\n**********************\n";
					for (Pedido pedido : pedidos) {
						if (pedido == null) break;
						str +=pedido.getDescricao()+"\t\t"+pedido.getValor()+"\n";
					}
					str += "**********************\n10% "+this.comTaxa+"\nTotal da conta: R$ " + conta();
					
					if (this.rateio > 1){
						str += "\nValor do rateio para " + this.rateio + ": R$" +conta()/rateio + "\n";
					}
		return str;
	}
}
