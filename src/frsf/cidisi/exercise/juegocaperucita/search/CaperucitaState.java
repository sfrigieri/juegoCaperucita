package frsf.cidisi.exercise.juegocaperucita.search;

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
        
        //TODO: Complete Method
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
   	
     public int[][] getmapaBosque(){
        return mapaBosque;
     }
     public void setmapaBosque(int[][] arg){
        mapaBosque = arg;
     }
     public int[] getposicion(){
        return posicion;
     }
     public void setposicion(int[] arg){
        posicion = arg;
     }
     public int getvidasPerdidas(){
        return vidasPerdidas;
     }
     public void setvidasPerdidas(int arg){
        vidasPerdidas = arg;
     }
     public int getdulcesPorJuntar(){
        return dulcesPorJuntar;
     }
     public void setdulcesPorJuntar(int arg){
        dulcesPorJuntar = arg;
     }
     public int getceldasPorVisitar(){
        return celdasPorVisitar;
     }
     public void setceldasPorVisitar(int arg){
        celdasPorVisitar = arg;
     }
	
}

