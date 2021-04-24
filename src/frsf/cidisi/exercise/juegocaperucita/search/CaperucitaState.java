package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import graphics.GameBoard;

/**
 * Represent the internal state of the Agent.
 */
public class CaperucitaState extends SearchBasedAgentState {

	private int[][] mapaBosque;
	private int[] posicion;
	private int vidasPerdidas;
	private int dulcesPorJuntar;
	private int celdasPorVisitar;
	private Double costoAcciones;

	private int escenario;
	private int[] posicionInicial;
	private int[] posicionAnterior;
	ArrayList<int[]> listaCeldasPorVisitar;
	private GameBoard gameBoard;

	public CaperucitaState(int escenarioAmbiente, boolean isAClone) {

		mapaBosque = this.getMapaInicial(escenarioAmbiente);
		posicionInicial = this.getPosicionInicial(escenarioAmbiente);
		posicion = new int[2];
		posicionAnterior = new int[2];
		posicion[0] = posicionInicial[0];
		posicion[1] = posicionInicial[1];
		vidasPerdidas = 0;
		dulcesPorJuntar = 3;
		costoAcciones = 0.0;
		listaCeldasPorVisitar = new ArrayList<int[]>();
		this.actualizarCeldasPorVisitar();

		escenario = escenarioAmbiente;

		//Utilizado para la representación gráfica del estadoAgente
		if(!isAClone) {
			gameBoard = new GameBoard();
			this.initGameBoard();
		}
	}




	/**
	 * This method clones the state of the agent. It's used in the search
	 * process, when creating the search tree.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SearchBasedAgentState clone() {

		CaperucitaState nuevoEstado = new CaperucitaState(this.escenario, true);

		int[][] nuevoMapaBosque = new int[this.mapaBosque.length][this.mapaBosque[0].length];

		for(int row = 0; row < this.mapaBosque.length; row++) {
			for(int col = 0; col<this.mapaBosque[0].length; col++) {
				nuevoMapaBosque[row][col] = this.mapaBosque[row][col];
			}
		}

		nuevoEstado.setMapaBosque(nuevoMapaBosque);
		nuevoEstado.setPosicionFila(this.getPosicionFila());
		nuevoEstado.setPosicionColumna(this.getPosicionColumna());
		nuevoEstado.setVidasPerdidas(this.vidasPerdidas);
		nuevoEstado.setDulcesPorJuntar(this.dulcesPorJuntar);
		nuevoEstado.setListaCeldasPorVisitar((ArrayList<int[]>) this.listaCeldasPorVisitar.clone());
		nuevoEstado.setCeldasPorVisitar(this.celdasPorVisitar);
		
		return nuevoEstado;

	}






	/**
	 * This method is used in the search process to verify if the node already
	 * exists in the actual search.
	 */
	@Override
	public boolean equals(Object obj) {


		if (!(obj instanceof CaperucitaState))
			return false;

		CaperucitaState objeto = (CaperucitaState)obj;		
		int[][] mapaBosque = objeto.getMapaBosque();

		//Compara ambos mapas (Referencia: Pacman)
		for (int row = 0; row < this.mapaBosque.length; row++) {
			for (int col = 0; col < this.mapaBosque[0].length; col++) {
				if (this.mapaBosque[row][col] != mapaBosque[row][col]) {
					return false;
				}
			}
		}

		boolean posicionFila = objeto.getPosicion()[0] == this.posicion[0];
		boolean posicionColumna = objeto.getPosicion()[1] == this.posicion[1];
		boolean vidas = objeto.getVidasPerdidas() == this.vidasPerdidas;
		boolean dulces = objeto.getDulcesPorJuntar() == this.dulcesPorJuntar;
		boolean celdas = objeto.getCeldasPorVisitar() == this.celdasPorVisitar;
				
		return posicionFila && posicionColumna && vidas && dulces && celdas;

	}

	
	

	/**
	 * This method is used to update the Agent State when a Perception is
	 * received by the Simulator.
	 */
	@Override
	public void updateState(Perception p) {

		CaperucitaAgentPerception percepcionCaperucita = (CaperucitaAgentPerception) p;

		//bring me back this.resetValorPrevioCeldaLobo();

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
		for (Integer valor : percepcionCaperucita.getSensorDerecho()) 
		{ 
			mapaBosque[row][i] = valor.intValue();
			i++;
		}
		
		
		this.updateGameBoard();

	}


	private void resetValorPrevioCeldaLobo() {

		for (int row = 0; row < mapaBosque.length; row++) {
			for (int col = 0; col < mapaBosque[0].length; col++) {
				if (mapaBosque[row][col] == CaperucitaAgentPerception.LOBO)
					mapaBosque[row][col] = this.getMapaInicial(this.escenario)[row][col];
			}
		}
	}



	private void initGameBoard() {
		gameBoard.initBoard("Mapa estado caperucita",this.getMapaBosque(), this.getPosicion(), this.escenario, true);	
	}


	public void updateGameBoard() {
		gameBoard.repaint(this.getMapaBosque(), this.getPosicion());
	}



	/**
	 * This method is optional, and sets the initial state of the agent.
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

		str = str + "mapa Caperucita=\"[ \n";
		for (int row = 0; row < mapaBosque.length; row++) {
			str = str + "[ ";
			for (int col = 0; col < mapaBosque[0].length; col++) {
				if (mapaBosque[row][col] == -1) {
					str = str + "* ";
				} else {
					str = str + mapaBosque[row][col] + " ";
				}
			}
			str = str + " ]\n";
		}
		str = str +" ]\"";

		return str;
	}




	//Métodos Auxiliares

	private void setListaCeldasPorVisitar(ArrayList<int[]> collect) {
		this.listaCeldasPorVisitar = collect;

	}


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

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 1
			mapaInicial[7][7] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][7] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][8] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[8][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[this.getPosicionInicial(escenario)[0]][this.getPosicionInicial(escenario)[1]] = CaperucitaAgentPerception.LIBRE;

			return mapaInicial;
		}
		if(escenario == 2)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 2
			mapaInicial[7][6] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][6] = CaperucitaAgentPerception.FLORES;
			mapaInicial[8][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[7][5] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[8][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[0][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[1][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][7] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[5][11] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][10] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[8][10] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[4][10] = CaperucitaAgentPerception.DULCES;
			mapaInicial[this.getPosicionInicial(escenario)[0]][this.getPosicionInicial(escenario)[1]] = CaperucitaAgentPerception.LIBRE;
			return mapaInicial;
		}

		if(escenario == 3)
		{ 
			int[][] mapaInicial = new int[9][14];

			for (int row = 0; row < mapaInicial.length; row++) {
				for (int col = 0; col < mapaInicial[0].length; col++) {
					mapaInicial[row][col] = CaperucitaAgentPerception.NO_VISIBLE;
				}
			}

			//Completar con elementos de escenario 3
			mapaInicial[0][3] = CaperucitaAgentPerception.FLORES;
			mapaInicial[1][3] = CaperucitaAgentPerception.FLORES;
			mapaInicial[2][6] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[0][4] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[1][2] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[7][2] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[8][2] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[7][9] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[2][8] = CaperucitaAgentPerception.ARBOL;
			mapaInicial[6][8] = CaperucitaAgentPerception.DULCES;
			mapaInicial[this.getPosicionInicial(escenario)[0]][this.getPosicionInicial(escenario)[1]] = CaperucitaAgentPerception.LIBRE;
			return mapaInicial;
		}

		return null;
	}

	public int[] getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionFila(int value) {
       if(value < 0 || value > 8)
		System.out.println("out of index > value fila:"+value);
		this.posicion[0] = value;
	}

	public void setPosicionColumna(int value) {
	       if(value < 0 || value > 13)
	   		System.out.println("out of index > value fila:"+value);
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


	public void actualizarCeldasPorVisitar() {

		for (int row = 0; row < mapaBosque.length; row++)
			for (int col = 0; col < mapaBosque[0].length; col++) {
					int[] posicionActual = new int[2];
					posicionActual[0] = row;
					posicionActual[1] = col;
					this.listaCeldasPorVisitar.add(posicionActual);
			}

		this.listaCeldasPorVisitar
		.removeIf(posicion -> (
				posicion[0] == this.getPosicionFila() && posicion[1] == this.getPosicionColumna()));

		this.celdasPorVisitar = this.listaCeldasPorVisitar.size();
	}


	public void actualizarCeldasPorVisitar(int posicionAnteriorFilaOColumna, String accion) {

		if(accion == "irAbajo") {
			for(int i = posicionAnteriorFilaOColumna; i <= this.getPosicionFila(); i++) {
				int[] posAnterior =  new int[2];
				posAnterior[0] = i;
				posAnterior[1]= this.getPosicionColumna();
				this.setPosicionAnterior(posAnterior);
				this.listaCeldasPorVisitar
				.removeIf(posicion -> (
						posicion[0] == this.getPosicionAnterior()[0] && posicion[1] == this.getPosicionColumna()));
			}
		}
		else {
			if(accion == "irArriba") {
				for(int i = posicionAnteriorFilaOColumna; i >= this.getPosicionFila(); i--) {
					int[] posAnterior =  new int[2];
					posAnterior[0] = i;
					posAnterior[1]= this.getPosicionColumna();
					this.setPosicionAnterior(posAnterior);
					this.listaCeldasPorVisitar
					.removeIf(posicion -> (
							posicion[0] == this.getPosicionAnterior()[0] && posicion[1] == this.getPosicionColumna()));
				}
			}
			else {
				if(accion == "irDerecha") {

					for(int i = posicionAnteriorFilaOColumna; i <= this.getPosicionColumna(); i++) {
						int[] posAnterior =  new int[2];
						posAnterior[0] = this.getPosicionFila();
						posAnterior[1]= i;
						this.setPosicionAnterior(posAnterior);
						this.listaCeldasPorVisitar
						.removeIf(posicion -> (
								posicion[0] == this.getPosicionFila() && posicion[1] == this.getPosicionAnterior()[1]));
					}
				}
				else
				{
					for(int i = posicionAnteriorFilaOColumna; i >= this.getPosicionColumna(); i--) {
						int[] posAnterior =  new int[2];
						posAnterior[0] = this.getPosicionFila();
						posAnterior[1]= i;
						this.setPosicionAnterior(posAnterior);
						this.listaCeldasPorVisitar
						.removeIf(posicion -> (
								posicion[0] == this.getPosicionFila() && posicion[1] == this.getPosicionAnterior()[1]));
					}
				}
			}
		}

		this.celdasPorVisitar = this.listaCeldasPorVisitar.size();
		//System.out.println("celdasporvisitar: "+this.celdasPorVisitar);
	}


	private int[] getPosicionAnterior() {
		return this.posicionAnterior;

	}


	private void setPosicionAnterior(int[] is) {
		this.posicionAnterior = is;

	}




	public void incrementarCostoAcciones(Double costo) {
		this.costoAcciones += costo;

	}

	public double getCostoAcciones() {
		return this.costoAcciones;
	}


	//Las celdas percibidas quedan ordenadas en la dirección de cada sensor, partiendo desde la más cercana. 

	public ArrayList<Integer> getCeldasSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapaBosque[i-1][col]);

			if( mapaBosque[i-1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasInferiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxRow = mapaBosque.length-1;

		if(row == maxRow)
			return list;

		for(int i = row; i < maxRow; i++) {
			list.add(mapaBosque[i+1][col]);

			if( mapaBosque[i+1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasIzquierdas(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(col == 0)
			return list;

		for(int i = col; i > 0; i--) {
			list.add(mapaBosque[row][i-1]);

			if( mapaBosque[row][i-1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasDerechas(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxCol = mapaBosque[row].length-1;

		if(col == maxCol)
			return list;

		for(int i = col; i < maxCol; i++) {
			list.add(mapaBosque[row][i+1]);

			if( mapaBosque[row][i+1] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}


}

