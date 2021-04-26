package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import frsf.cidisi.faia.state.EnvironmentState;
import graphics.GameBoard;

/**
 * This class represents the real world state.
 */
public class BosqueState extends EnvironmentState {

	private int[][] mapa;
	private int[] posicionAgente;
	private int vidasPerdidasAgente;

	private int escenario;
	private int valorPrevioCeldaLobo;
	private int[] posicionLobo;
	private GameBoard gameBoard;

	public BosqueState(int escenarioAmbiente) {

		escenario = escenarioAmbiente;

		mapa = this.getMapaInicial(escenario);
		posicionAgente = this.getPosicionInicialAgente(escenario);
		vidasPerdidasAgente = 0;

		//Utilizado para la representación gráfica del estadoAmbiente
		gameBoard = new GameBoard();
		this.initGameBoard();

		valorPrevioCeldaLobo = -1;
		posicionLobo = new int[2];
		this.inicializarPosicionLobo();
	}


	public int getEscenarioAmbiente() {
		return escenario;
	}

	public void actualizarPosicionLobo() {

		//Reasignar valor previo de la posición en la que se encuentra el lobo
		this.setMapaPosicion(this.posicionLobo[0], this.posicionLobo[1], this.valorPrevioCeldaLobo);

		this.posicionLobo = this.crearPosicionNuevaLobo();

		//Guardar valor previo para reasignarlo luego
		this.guardarValorPrevioCeldaLobo();

		//Actualizar mapa con nueva posición lobo
		this.setMapaPosicion(this.posicionLobo[0], this.posicionLobo[1], CaperucitaAgentPerception.LOBO);
		this.updateGameBoard();
	}


	private void guardarValorPrevioCeldaLobo() {
		this.valorPrevioCeldaLobo = this.getPosicionMapa(this.posicionLobo[0], this.posicionLobo[1]);
	}


	private int[] crearPosicionNuevaLobo() {

		int[] posicion = new int[2];

		//nextInt no incluye Max. 
		//Ej: si length col es 14, devolverá como máximo un 13 que es correcto (0-13 igual a 14 columnas).
		do{

			posicion[0] = ThreadLocalRandom.current().nextInt(0, this.mapa.length);

			posicion[1] = ThreadLocalRandom.current().nextInt(0, this.mapa[0].length);

			//El Lobo no puede aparecer en la misma celda, la celda de Caperucita o en celda de Dulces
		}
		while((this.getPosicionMapa(posicion[0], posicion[1]) == CaperucitaAgentPerception.LOBO)
				|| (posicion[0] == this.getPosicionAgenteFila() && posicion[1] == this.getPosicionAgenteColumna())
				|| (this.getPosicionMapa(posicion[0], posicion[1]) == CaperucitaAgentPerception.DULCES)
				);

		return posicion;
	}



	public void inicializarPosicionLobo() {
		//TODO inicializarPosicionLobo
		//		this.posicionLobo = this.crearPosicionNuevaLobo();
		//		
		//		//Guardar valor previo para reasignarlo luego
		//		this.guardarValorPrevioCeldaLobo();
		//
		//		//Actualizar mapa con nueva posición lobo
		//		this.setMapaPosicion(this.posicionLobo[0], this.posicionLobo[1], CaperucitaAgentPerception.LOBO);

		if(this.escenario == 1)
			this.setMapaPosicion(7, 8,CaperucitaAgentPerception.LOBO);
		else
			if(this.escenario == 2)
				this.setMapaPosicion(3, 6,CaperucitaAgentPerception.LOBO);
			else
				this.setMapaPosicion(3, 5,CaperucitaAgentPerception.LOBO);

		this.updateGameBoard();
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

			//Árboles
			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < 3; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			for (int col = 3; col < 12; col++) {
				mapaInicial[0][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[8][col] = CaperucitaAgentPerception.ARBOL;
			}

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 12; col < 14; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			mapaInicial[1][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[1][11] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[3][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][3] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][8] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][6] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][11] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[7][6] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[7][11] = CaperucitaAgentPerception.ARBOL;

			//Dulces
			mapaInicial[1][3] = CaperucitaAgentPerception.DULCES;
			mapaInicial[1][10] = CaperucitaAgentPerception.DULCES;
			mapaInicial[3][8] = CaperucitaAgentPerception.DULCES;

			//Flores 
			mapaInicial[7][7] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][7] = CaperucitaAgentPerception.FLORES;

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

			//Árboles
			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < 3; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			for (int col = 3; col < 11; col++) {
				mapaInicial[0][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[1][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[7][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[8][col] = CaperucitaAgentPerception.ARBOL;
			}

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 11; col < 14; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			mapaInicial[2][3] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][8] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][10] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[3][3] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[3][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][9] = CaperucitaAgentPerception.ARBOL;

			//Libres
			mapaInicial[1][6] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][8] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][9] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][10] = CaperucitaAgentPerception.LIBRE;

			//Dulces
			mapaInicial[1][5] = CaperucitaAgentPerception.DULCES;
			mapaInicial[3][8] = CaperucitaAgentPerception.DULCES;
			mapaInicial[4][10] = CaperucitaAgentPerception.DULCES;

			//Flores 
			mapaInicial[7][6] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][6] = CaperucitaAgentPerception.FLORES;


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


			//Árboles
			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < 3; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			for (int col = 3; col < 11; col++) {
				mapaInicial[0][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[1][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[7][col] = CaperucitaAgentPerception.ARBOL;
				mapaInicial[8][col] = CaperucitaAgentPerception.ARBOL;
			}

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 11; col < 14; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.ARBOL;
				}
			}

			mapaInicial[2][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][6] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][8] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][10] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[3][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][3] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][10] = CaperucitaAgentPerception.ARBOL;

			//Libres
			mapaInicial[1][5] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[1][6] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[1][7] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[1][6] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][6] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][7] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[7][8] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[4][11] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[5][11] = CaperucitaAgentPerception.LIBRE;
			mapaInicial[6][11] = CaperucitaAgentPerception.LIBRE;

			//Dulces
			mapaInicial[2][7] = CaperucitaAgentPerception.DULCES;
			mapaInicial[6][4] = CaperucitaAgentPerception.DULCES;
			mapaInicial[6][8] = CaperucitaAgentPerception.DULCES;

			//Flores 
			mapaInicial[0][3] = CaperucitaAgentPerception.FLORES;
			mapaInicial[1][3] = CaperucitaAgentPerception.FLORES;

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

	}


	/**
	 * This method returns the String representation of the agent state.
	 */
	@Override
	public String toString() {
		String str = "";

		str = str + "mapa Bosque=\"[ \n";
		for (int row = 0; row < mapa.length; row++) {
			str = str + "[ ";
			for (int col = 0; col < mapa[0].length; col++) {
				if (mapa[row][col] == -1) {
					str = str + "* ";
				} else {
					str = str + mapa[row][col] + " ";
				}
			}
			str = str + " ]\n";
		}
		str = str +" ]\"";

		return str;
	}

	private void initGameBoard() {
		gameBoard.initBoard("Mapa estado bosque",this.getMapa(), this.getPosicionAgente());	
	}


	public void updateGameBoard() {
		gameBoard.repaint(this.getMapa(), this.getPosicionAgente());
	}


	//Métodos Auxiliares

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

		int maxRow = mapa.length-1;

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

		int maxCol = mapa[row].length-1;

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

