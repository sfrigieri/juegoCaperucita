package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class BosqueState extends EnvironmentState {
	
	//TODO: Setup Variables
    private int[][] mapa;
    private int[] posicionAgente;
    private int vidasPerdidas;
	
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
	
     public int[][] getmapa(){
        return mapa;
     }
     public void setmapa(int[][] arg){
        mapa = arg;
     }
     public int[] getposicionAgente(){
        return posicionAgente;
     }
     public void setposicionAgente(int[] arg){
        posicionAgente = arg;
     }
     public int getvidasPerdidas(){
        return vidasPerdidas;
     }
     public void setvidasPerdidas(int arg){
        vidasPerdidas = arg;
     }
	

}

