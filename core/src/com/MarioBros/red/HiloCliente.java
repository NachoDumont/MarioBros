package com.MarioBros.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.MarioBros.Utilidades.Utiles;

public class HiloCliente extends Thread {

	DatagramSocket socket;
	boolean fin = false;
	private InetAddress ipServer;
	private int puerto = 3333;

	public HiloCliente() {
		// Cuando creas un socket en el servidor tenes que indicarle el puerto que se va a usar
		try {
			socket = new DatagramSocket();//Hay que buscar la manera de que se pueda conectar desde cualquier red
			ipServer = InetAddress.getByName("192.168.0.55"); //Se puede hacer broadcast también 255.255.255.255
			enviarMensaje("Conectar");
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void run() {
		
		while(!fin) {
			byte[] datos = new byte[1024];
			DatagramPacket paquete = new DatagramPacket(datos, datos.length);
			try {
				socket.receive(paquete);
				procesarMensaje(paquete);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	private void procesarMensaje(DatagramPacket paquete) {
		String msg = (new String(paquete.getData())).trim();

		String mensajeCompuesto[] = msg.split("!");

		if (mensajeCompuesto.length == 1) {
			if (msg.equals("Empieza")) {
				Utiles.listener.empezar();
			}
		} else {
			
			if (mensajeCompuesto[0].equals("ConexionAceptada")) {
				Utiles.listener.asignarJugador(Integer.valueOf(mensajeCompuesto[1]));
				// ipServer mensajeCompuesto[2]
			}

			if (mensajeCompuesto[0].equals("coordenadas")) {
				if (mensajeCompuesto[1].equals("player")) {
					Utiles.listener.asignarCoordenadas(1, mensajeCompuesto[2]);
				} else if (mensajeCompuesto[1].equals("player2")) {
					Utiles.listener.asignarCoordenadas(2, mensajeCompuesto[2]);
				}
			}

//			if (mensajeCompuesto[0].equals("termino")) {
//				Utiles.listener.terminoJuego(Integer.parseInt(mensajeCompuesto[1]));
//			}
			
		}
	}

	void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		try {
			DatagramPacket paquete = new DatagramPacket(data, data.length, ipServer, puerto);
			socket.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
