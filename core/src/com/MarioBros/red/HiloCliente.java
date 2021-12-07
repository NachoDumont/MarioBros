package com.MarioBros.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.MarioBros.Utilidades.Utiles;

public class HiloCliente extends Thread {

	private DatagramSocket conexion;
	private boolean fin = false;
	private InetAddress ipServer;
	private int puertoServer;

	public HiloCliente() {

		try {
			// "255.255.255.255"
			ipServer = InetAddress.getByName("192.168.100.30");
			puertoServer = 9998;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		try {
			conexion = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			byte[] data = new byte[1024];
			DatagramPacket paquete = new DatagramPacket(data, data.length);
			try {
				conexion.receive(paquete);
				procesarMensaje(paquete);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!fin);

	}

	private void procesarMensaje(DatagramPacket paquete) {
		String msg = (new String(paquete.getData())).trim();

		String mensajeCompuesto[] = msg.split("!");

		if (mensajeCompuesto.length == 1) {
			if (msg.equals("Empieza")) {
				Utiles.listener.empieza();
			}
		} else {

			if (mensajeCompuesto[0].equals("ConexionAceptada")) {
				Utiles.listener.asignarJugador(Integer.valueOf(mensajeCompuesto[1]));
				// ipServer mensajeCompuesto[2]
			}

			if (mensajeCompuesto[0].equals("coordenadas")) {
				if (mensajeCompuesto[1].equals("p1")) {
					Utiles.listener.asignarCoordenadas(1, Float.parseFloat(mensajeCompuesto[2]));
				} else if (mensajeCompuesto[1].equals("p2")) {
					Utiles.listener.asignarCoordenadas(2, Float.parseFloat(mensajeCompuesto[2]));
				}
			}

			if (mensajeCompuesto[0].equals("pelota")) {
				float posX = Float.valueOf(mensajeCompuesto[1]);
				float posY = Float.valueOf(mensajeCompuesto[2]);
				Utiles.listener.actualizarPelota(posX, posY);
			}

			if (mensajeCompuesto[0].equals("punto")) {
				Utiles.listener.actualizarPuntaje(Integer.parseInt(mensajeCompuesto[1]));
			}

			if (mensajeCompuesto[0].equals("termino")) {
				Utiles.listener.terminoJuego(Integer.parseInt(mensajeCompuesto[1]));
			}
		}

	}

	public void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket paquete = new DatagramPacket(data, data.length, ipServer, puertoServer);
		try {
			conexion.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
