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

	private int escenario;

	public BosqueState(int escenarioAmbiente) {

		escenario = escenarioAmbiente;

		mapa = this.getMapaInicial(escenario);
		posicionAgente = this.getPosicionInicialAgente(escenario);
		vidasPerdidasAgente = 0;

		//No se utiliza
		//	this.initState();
	}


	public int getEscenarioAmbiente() {
		return escenario;
	}

	public int[][] getMapaInicial(int escenario) {

		if(escenario == 1)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.LIBRE;
				}
			}

			//Completar con elementos de escenario 1
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;

			return mapaInicial;
		}
		if(escenario == 2)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.LIBRE;
				}
			}

			//Completar con elementos de escenario 2
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;

			return mapaInicial;
		}

		if(escenario == 3)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.LIBRE;
				}
			}

			//Completar con elementos de escenario 3
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;
			//            mapaInicial[0][0] = CaperucitaAgentPerception.ARBOL;

			return mapaInicial;
		}

		return null;

	}

	public int[] getPosicionInicialAgente(int escenario) {

		int[] pos = new int[2];

		if(escenario == 1) {
			pos[0]=5;
			pos[1]=11;
			return pos;
		}
		
		if(escenario == 2) {
			pos[0]=6;
			pos[1]=3;
			return pos;
		}
		
		if(escenario == 3) {
			pos[0]=4;
			pos[1]=11;
			return pos;
		}
		
		return null;
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

	//Las celdas percibidas quedan ordenadas en la direcci�n de cada sensor, partiendo desde la m�s cercana. 

	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapa[i-1][col]);

			//�ltima celda visible
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

	public void setPosicionAgenteFila(int value) {
		this.posicionAgente[0] = value;
	}

	public void setPosicionAgenteColumna(int value) {
		this.posicionAgente[1] = value;
	}

	public int getPosicionAgenteFila() {
		return posicionAgente[0];
	}

	public int getPosicionAgenteColumna() {
		return posicionAgente[1];
	}

	public void resetMapa() {
		this.setMapa(this.getMapaInicial(escenario));

	}

	public void resetPosicionAgente() {
		this.setPosicionAgente(this.getPosicionInicialAgente(escenario));
		
	}


	public void setMapaPosicion(int row, int col, int valor) {
	        this.mapa[row][col] = valor;
	    
	}
}

