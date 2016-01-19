package br.edu.ifpi.restaurante.modelo;

public class Restaurante {
	private String nome;
	private Mesa[] mesas;

	public Restaurante(String nome, int numeroDeMesas){
		this.nome = nome;
		this.mesas = new Mesa[numeroDeMesas];
		for (int i = 0; i < mesas.length; i++) {
			mesas[i] = new Mesa(i+1);
		}
	}
	
	public Mesa abrirMesa(int numeroDaMesa){
		mesas[numeroDaMesa-1].abrir();
		return mesas[numeroDaMesa-1];
	}
	
	public Mesa[] getMesas() {
		return mesas;
	}
	
	public String verSituacaoEValoresDasMesas(){
		int contDisponivel = 0;
		int contOcupada = 0;
		double valorTotalAtual = 0;
		
		for (Mesa mesa : mesas) {
			if (mesa.getStatus() == Mesa.DISPONIVEL)
				contDisponivel++;
			if (mesa.getStatus() == Mesa.OCUPADA)
				contOcupada++;
			
			valorTotalAtual += mesa.conta();
		}
		
		return "Mesas ocupadas:  "+contOcupada+" | Mesas disponíveis: "+contDisponivel
				+ "\nGanho atual com as mesas ocupadas: R$ " + valorTotalAtual;
	}
	
	public String toString() {
		return nome + ", " + "Qtd Mesas: " + mesas.length;
	}
}
