package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
	private int valorPrevioCeldaLobo;
	private int posicionFilaLobo;
	private int posicionColumnaLobo;

	public BosqueState(int escenarioAmbiente) {

		escenario = escenarioAmbiente;

		mapa = this.getMapaInicial(escenario);
		posicionAgente = this.getPosicionInicialAgente(escenario);
		vidasPerdidasAgente = 0;
		valorPrevioCeldaLobo = -1;
		//No se utiliza
		//	this.initState();
	}


	public int getEscenarioAmbiente() {
		return escenario;
	}

	public void actualizarPosicionLobo() {

		int mx;
		int my;

		//Si no es la primera vez que se actualiza posicion lobo, reasignar valor previo en mapa
		//El valor -1 (NO_VISIBLE) no se utiliza en el mapa real, solo en el estado del agente.
		if(this.valorPrevioCeldaLobo != -1)
			this.setMapaPosicion(this.posicionFilaLobo, this.posicionColumnaLobo, this.valorPrevioCeldaLobo);


		//nextInt no incluye Max. 
		//Ej: si length col es 14, devolverá como máximo un 13 que es correcto (0-13 igual a 14 columnas).
		do{


			mx = ThreadLocalRandom.current().nextInt(0, this.mapa.length);

			my = ThreadLocalRandom.current().nextInt(0, this.mapa[0].length);

			//El Lobo no puede aparecer en la misma celda, la celda de Caperucita o en celda de Dulces
		}
		while((this.getPosicionMapa(mx, my) == CaperucitaAgentPerception.LOBO)
				|| (mx == this.getPosicionAgenteFila() && my == this.getPosicionAgenteColumna())
				|| (this.getPosicionMapa(mx, my) == CaperucitaAgentPerception.DULCES)
				);

		//Guardar valor previo para reasignarlo luego
		this.valorPrevioCeldaLobo = this.getPosicionMapa(mx, my);
		FrameTest.valorPrevioCeldaLobo = this.getPosicionMapa(mx, my);

		this.posicionFilaLobo = mx;
		this.posicionColumnaLobo = my;

		this.setMapaPosicion(mx, my, CaperucitaAgentPerception.LOBO);
	}


	public int[][] getMapaInicial(int escenario) {

		if(escenario == 1)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
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

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
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

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
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

	//Las celdas percibidas quedan ordenadas en la dirección de cada sensor, partiendo desde la más cercana. 

	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapa[i-1][col]);

			//Última celda visible
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

	public int getPosicionMapa(int row, int col) {
		return mapa[row][col];
	}
}

