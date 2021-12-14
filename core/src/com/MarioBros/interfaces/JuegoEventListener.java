package com.MarioBros.interfaces;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface JuegoEventListener extends EventListener {

	public void empezar();
	public void keyUp(int keycode);
	public void keyDown(int keycode);
	
	public void asignarJugador(int jugador);
	public void asignarCoordenadas(int nroJugador, String msg);
	public void actualizarPuntaje(int nroJugador);
	public void terminoJuego(int nroJugador);

}
