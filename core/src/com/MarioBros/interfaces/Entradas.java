package com.MarioBros.interfaces;

import com.MarioBros.Utilidades.Utiles;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Entradas implements InputProcessor {
	
	private boolean arriba,abajo,derecha,izquierda = false;
	
	private boolean j,k,l,i = false; 

	@Override
	public boolean keyDown(int keycode) {
	//Player 1
		if(keycode==Keys.DOWN) abajo = true;
		if(keycode==Keys.UP) arriba = true;
		if(keycode==Keys.RIGHT) derecha = true;
		if(keycode==Keys.LEFT) izquierda = true;	
//		Player 2
		if(keycode == Keys.J) j = true;
		if(keycode == Keys.K) k = true;
		if(keycode == Keys.L) l = true;
		if(keycode == Keys.I) i = true;
		
		Utiles.listener.keyDown(keycode);		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
//	Player 1	
		if(keycode==Keys.DOWN) abajo = false;
		if(keycode==Keys.UP) arriba = false;
		if(keycode==Keys.RIGHT) derecha = false;
		if(keycode==Keys.LEFT) izquierda = false;	
		
//		Player 2
		if(keycode == Keys.J) j = true;
		if(keycode == Keys.K) k = true;
		if(keycode == Keys.L) l = true;
		if(keycode == Keys.I) i = true;
		
		Utiles.listener.keyDown(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isJ() {
		return j;
	}

	public boolean isK() {
		return k;
	}

	public boolean isL() {
		return l;
	}

	public boolean isI() {
		return i;
	}	

}
