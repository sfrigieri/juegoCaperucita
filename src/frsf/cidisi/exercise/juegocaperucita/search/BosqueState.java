package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class BosqueState extends EnvironmentState {

	//TODO: Setup Variables
	private int[][] mapa;
	private int[] posicionAgente;
	private int vidasPerdidasAgente;

	public BosqueState() {

		//TODO: Complete Method
		/*
			// mapa = initData0;
			// posicionAgente = initData1;
			// vidasPerdidas = initData2;
		 */
		this.initState();
	}

	/**
	 * This method is used to setup the initial real world.
	 */
	@Override
	public void initState() {

		//TODO: Complete Method
	}

	/**
	 * String representation of the real world state.
	 */
	@Override
	public String toString() {
		String str = "";

		//TODO: Complete Method

		return str;
	}

	//TODO: Complete this section with agent-specific methods
	// The following methods are agent-specific:

	public int[][] getMapa(){
		return mapa;
	}
	public void setMapa(int[][] arg){
		mapa = arg;
	}
	public int[] getPosicionAgente(){
		return posicionAgente;
	}
	public void setPosicionAgente(int[] arg){
		posicionAgente = arg;
	}
	public int getVidasPerdidasAgente(){
		return vidasPerdidasAgente;
	}
	public void setVidasPerdidasAgente(int arg){
		vidasPerdidasAgente = arg;
	}

	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapa[i-1][col]);

			if(mapa[i-1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesInferiores(int row, int col) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int maxRow = mapa[col].length-1;
		
		if(row == maxRow)
			return list;

		for(int i = row; i < maxRow; i++) {
			list.add(mapa[i+1][col]);

			if(mapa[i+1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesIzquierdas(int row, int col) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();

		if(col == 0)
			return list;

		for(int i = col; i > 0; i--) {
			list.add(mapa[row][i-1]);

			if(mapa[row][i-1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesDerechas(int row, int col) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxCol = mapa.length-1;
		
		if(col == maxCol)
			return list;

		for(int i = col; i < maxCol; i++) {
			list.add(mapa[row][i+1]);

			if(mapa[row][i+1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}
}

