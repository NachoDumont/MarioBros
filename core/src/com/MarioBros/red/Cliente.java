package com.MarioBros.red;

public class Cliente {

	private HiloCliente hc;

	public Cliente() {
		hc = new HiloCliente();
		hc.start();
		
		System.out.println("Esperando otro jugador");
	}

	public void enviarMensaje(String msg) {
		this.hc.enviarMensaje(msg);
	}
}
