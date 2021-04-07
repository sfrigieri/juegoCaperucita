package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.concurrent.TimeUnit;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class CaperucitaState extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private int[][] mapaBosque;
    private int[] posicion;
    private int vidasPerdidas;
    private int dulcesPorJuntar;
    private int celdasPorVisitar;
    
    private FrameTest frame = new FrameTest();
    private int times = 0;

    public CaperucitaState() {
    
    	//TODO: Complete Method
    	/*
			// mapaBosque = initData0;
			// posicion = initData1;
			// vidasPerdidas = initData2;
			// dulcesPorJuntar = initData3;
			// celdasPorVisitar = initData4;
        */
        this.initState();
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
	
}

