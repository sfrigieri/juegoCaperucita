package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class CaperucitaState extends SearchBasedAgentState {

	private int[][] mapaBosque;
	private int[] posicion;
	private int vidasPerdidas;
	private int dulcesPorJuntar;
	private int celdasPorVisitar;

	private int[] posicionInicial;

	private FrameTest frame = new FrameTest();
	private int times = 0;

	public CaperucitaState(int escenarioAmbiente) {

		mapaBosque = this.getMapaInicial(escenarioAmbiente);
		posicionInicial = this.getPosicionInicial(escenarioAmbiente);
		posicion = new int[2];
		posicion[0] = posicionInicial[0];
		posicion[1] = posicionInicial[1];
		vidasPerdidas = 0;
		dulcesPorJuntar = 3;
		this.actualizarCeldasPorVisitar();

		//this.initState();
	}



	/**
	 * This method clones the state of the agent. It's used in the search
	 * process, when creating the search tree.
	 */
	@Override
	public SearchBasedAgentState clone() {

		//TODO: Complete Method

		return null;
	}

	/**
	 * This method is used to update the Agent State when a Perception is
	 * received by the Simulator.
	 */
	@Override
	public void updateState(Perception p) {

		CaperucitaAgentPerception percepcionCaperucita = (CaperucitaAgentPerception) p;

		int row = this.getPosicionFila();
		int col = this.getPosicionColumna();

		int i = row-1;

		//El orden de las celdas percibidas parte desde la celda más cercana al agente y en la dirección del sensor. 

		//El último elemento en la lista es un árbol o la fila 0
		for (Integer valor : percepcionCaperucita.getSensorSuperior()) 
		{ 
			mapaBosque[i][col] = valor.intValue();
			i--;
		}


		i = row+1;
		//El último elemento en la lista es un árbol o la fila máxima
		for (Integer valor : percepcionCaperucita.getSensorInferior()) 
		{ 
			mapaBosque[i][col] = valor.intValue();
			i++;
		}


		i = col-1;
		//El último elemento en la lista es un árbol o la columna 0
		for (Integer valor : percepcionCaperucita.getSensorIzquierdo()) 
		{ 
			mapaBosque[row][i] = valor.intValue();
			i--;
		}


		i = col+1;
		//El último elemento en la lista es un árbol o la columna máxima
		for (Integer valor : percepcionCaperucita.getSensorSuperior()) 
		{ 
			mapaBosque[row][col] = valor.intValue();
			i++;
		}

		//    	 if(times == 0) {
		//         	frame.initBoard("Pacman", world, position);
		//         	times++;
		//         }
		//         else
		//         	frame.repaint(world, position);
		//         
		//         try {
		// 			TimeUnit.SECONDS.sleep(1);
		// 		} catch (InterruptedException e) {
		// 			// TODO Auto-generated catch block
		// 			e.printStackTrace();
		// 		}
	}

	/**
	 * This method is optional, and sets the initial state of the agent.
	 */
	@Override
	public void initState() {

		//TODO: Complete Method

	}

	/**
	 * This method returns the String representation of the agent state.
	 */
	@Override
	public String toString() {
		String str = "";

		//TODO: Complete Method

		return str;
	}

	/**
	 * This method is used in the search process to verify if the node already
	 * exists in the actual search.
	 */
	@Override
	public boolean equals(Object obj) {

		//TODO: Complete Method

		return true;
	}

	//TODO: Complete this section with agent-specific methods
	// The following methods are agent-specific:

	public int[][] getMapaBosque(){
		return mapaBosque;
	}

	public void setMapaBosque(int[][] arg){
		mapaBosque = arg;
	}

	public int getPosicionMapa(int row, int col) {
		return mapaBosque[row][col];
	}

	public void setMapaPosicion(int row, int col, int value) {
		this.mapaBosque[row][col] = value;
	}

	public int[] getPosicion(){
		return posicion;
	}

	public void setPosicion(int[] p) {
		posicion = p;
	}

	public int[] getPosicionInicial(int escenario) {
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


	private int[][] getMapaInicial(int escenario) {
		
		if(escenario == 1)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 1
			            mapaInicial[7][7] = CaperucitaAgentPerception.FLORES;
			            mapaInicial[8][7] = CaperucitaAgentPerception.FLORES;


			return mapaInicial;
		}
		if(escenario == 2)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 2
            mapaInicial[7][6] = CaperucitaAgentPerception.FLORES;
            mapaInicial[8][6] = CaperucitaAgentPerception.FLORES;
            
			return mapaInicial;
		}

		if(escenario == 3)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial[0].length; row++) {
				for (int col = 0; col < mapaInicial.length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 3
            mapaInicial[0][3] = CaperucitaAgentPerception.FLORES;
            mapaInicial[1][3] = CaperucitaAgentPerception.FLORES;

			return mapaInicial;
		}

		return null;
	}

	public int[] getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionFila(int value) {
		this.posicion[0] = value;
	}

	public void setPosicionColumna(int value) {
		this.posicion[1] = value;
	}

	public int getPosicionFila() {
		return posicion[0];
	}

	public int getPosicionColumna() {
		return posicion[1];
	}

	public int getVidasPerdidas(){
		return vidasPerdidas;
	}
	public void setVidasPerdidas(int arg){
		vidasPerdidas = arg;
	}
	public int getDulcesPorJuntar(){
		return dulcesPorJuntar;
	}
	public void setDulcesPorJuntar(int arg){
		dulcesPorJuntar = arg;
	}
	public int getCeldasPorVisitar(){
		return celdasPorVisitar;
	}
	public void setCeldasPorVisitar(int arg){
		celdasPorVisitar = arg;
	}


	public int actualizarCeldasPorVisitar() {

		int celdas = 0;

		for (int row = 0; row < mapaBosque[0].length; row++)
			for (int col = 0; col < mapaBosque.length; col++)
				if (mapaBosque[row][col] == CaperucitaAgentPerception.NO_VISIBLE)
					celdas++;


		celdasPorVisitar = celdas;

		return celdas;
	}


	//Las celdas percibidas quedan ordenadas en la dirección de cada sensor, partiendo desde la más cercana. 

	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapaBosque[i-1][col]);

			//Última celda visible
			if(mapaBosque[i-1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesInferiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxRow = mapaBosque[col].length-1;

		if(row == maxRow)
			return list;

		for(int i = row; i < maxRow; i++) {
			list.add(mapaBosque[i+1][col]);

			if(mapaBosque[i+1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesIzquierdas(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(col == 0)
			return list;

		for(int i = col; i > 0; i--) {
			list.add(mapaBosque[row][i-1]);

			if(mapaBosque[row][i-1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesDerechas(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxCol = mapaBosque.length-1;

		if(col == maxCol)
			return list;

		for(int i = col; i < maxCol; i++) {
			list.add(mapaBosque[row][i+1]);

			if(mapaBosque[row][i+1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}


}

