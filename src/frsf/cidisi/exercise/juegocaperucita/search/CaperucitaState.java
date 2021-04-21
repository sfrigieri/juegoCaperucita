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
	private Double costoAcciones;
	
	private int escenario;
	private int[] posicionInicial;
	private GameBoard frame;
	private int times;

	
	public CaperucitaState(int escenarioAmbiente) {

		mapaBosque = this.getMapaInicial(escenarioAmbiente);
		posicionInicial = this.getPosicionInicial(escenarioAmbiente);
		posicion = new int[2];
		posicion[0] = posicionInicial[0];
		posicion[1] = posicionInicial[1];
		vidasPerdidas = 0;
		dulcesPorJuntar = 3;
		this.actualizarCeldasPorVisitar();
		
		escenario = escenarioAmbiente;
		
		//Utilizado para la representaci�n gr�fica del estadoAmbiente/Agente
		frame = new GameBoard();
		times = 0;
		
		this.actualizarFrame();
		//this.initState();
	}



	/**
	 * This method clones the state of the agent. It's used in the search
	 * process, when creating the search tree.
	 */
	@Override
	public SearchBasedAgentState clone() {
		
		CaperucitaState nuevoEstado = new CaperucitaState(this.escenario);
		
		int[][] nuevoMapaBosque = new int[this.mapaBosque.length][this.mapaBosque.length];
		
		for(int row = 0; row < this.mapaBosque.length; row++) {
			for(int col = 0; col<this.mapaBosque[0].length; col++) {
				nuevoMapaBosque[row][col] = this.mapaBosque[row][col];
			}
		}
		
		nuevoEstado.setMapaBosque(nuevoMapaBosque);
		nuevoEstado.setPosicion(this.posicion);
		nuevoEstado.setVidasPerdidas(this.vidasPerdidas);
		nuevoEstado.setDulcesPorJuntar(this.dulcesPorJuntar);
		nuevoEstado.setCeldasPorVisitar(this.celdasPorVisitar);

		return nuevoEstado;
		
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

		//El orden de las celdas percibidas parte desde la celda m�s cercana al agente y en la direcci�n del sensor. 

		//El �ltimo elemento en la lista es un �rbol o la fila 0
		for (Integer valor : percepcionCaperucita.getSensorSuperior()) 
		{ 
			mapaBosque[i][col] = valor.intValue();
			i--;
		}


		i = row+1;
		//El �ltimo elemento en la lista es un �rbol o la fila m�xima
		for (Integer valor : percepcionCaperucita.getSensorInferior()) 
		{ 
			mapaBosque[i][col] = valor.intValue();
			i++;
		}


		i = col-1;
		//El �ltimo elemento en la lista es un �rbol o la columna 0
		for (Integer valor : percepcionCaperucita.getSensorIzquierdo()) 
		{ 
			mapaBosque[row][i] = valor.intValue();
			i--;
		}


		i = col+1;
		//El �ltimo elemento en la lista es un �rbol o la columna m�xima
		for (Integer valor : percepcionCaperucita.getSensorSuperior()) 
		{ 
			mapaBosque[row][col] = valor.intValue();
			i++;
		}

		this.actualizarFrame();

	}

	private void actualizarFrame() {
		 
		if(times == 0) {
      	frame.initBoard("Caperucita en el bosque",this.getMapaBosque(), this.getPosicion(), this.escenario);
      }
      else
      	frame.repaint(this.getMapaBosque(), this.getPosicion());
      
    	times++;
	}



	/**
	 * This method is optional, and sets the initial state of the agent.
	 */
	@Override
	public void initState() {

		//No se puede eliminar por restricciones de extensi�n

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

	/**
	 * This method is used in the search process to verify if the node already
	 * exists in the actual search.
	 */
	@Override
	public boolean equals(Object obj) {


		if (!(obj instanceof CaperucitaState))
            return false;
        
		CaperucitaState objeto = (CaperucitaState)obj;		
        int[][] mapaBosque = ((CaperucitaState) obj).getMapaBosque();
        
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


	//M�todos Auxiliares
	
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

		for (int row = 0; row < mapaBosque.length; row++)
			for (int col = 0; col < mapaBosque[0].length; col++)
				if (mapaBosque[row][col] == CaperucitaAgentPerception.NO_VISIBLE)
					celdas++;


		celdasPorVisitar = celdas;

		return celdas;
	}

	public void incrementarCostoAcciones(Double costo) {
		this.costoAcciones += costo;

	}

	public double getCostoAcciones() {
		return this.costoAcciones;
	}


	//Las celdas percibidas quedan ordenadas en la direcci�n de cada sensor, partiendo desde la m�s cercana. 

	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		if(row == 0)
			return list;

		for(int i = row; i > 0; i--) {
			list.add(mapaBosque[i-1][col]);

			//�ltima celda visible
			if(mapaBosque[i-1][col] == CaperucitaAgentPerception.ARBOL)
				break;
		}

		return list;
	}

	public ArrayList<Integer> getCeldasVisiblesInferiores(int row, int col) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		int maxRow = mapaBosque.length-1; //TODO Ver 

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

		int maxCol = mapaBosque[row].length-1;

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

