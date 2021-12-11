package com.MarioBros.red;

public class Cliente {

	private HiloCliente hc;
	private boolean fin = false;
	private boolean empiezaJuego = false;

	public Cliente() {
		hc = new HiloCliente();
		hc.start();

		System.out.println("Esperando otro jugador");

		while (!fin) {
			hc = new HiloCliente();
			hc.start();
		}
	}

	public void enviarMensaje(String msg) {
		this.hc.enviarMensaje(msg);
	}
}
