package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CaperucitaAgentPerception extends Perception {

    public static int NO_VISIBLE = -1;
    public static int LIBRE = 0;
    public static int ARBOL = 1;
    public static int DULCES = 2; 
    public static int LOBO = 3; 
    public static int FLORES = 4;

	private ArrayList<Integer> sensorSuperior;
	private ArrayList<Integer> sensorInferior;
	private ArrayList<Integer> sensorIzquierdo;
	private ArrayList<Integer> sensorDerecho;
	
 

    public  CaperucitaAgentPerception() {
    	
    }

    public CaperucitaAgentPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */

    public void initPerception(Environment environmentIn) {
         
        Bosque environment = (Bosque) environmentIn;
        BosqueState environmentState =
                environment.getEnvironmentState();
        
        int row = environmentState.getPosicionAgente()[0];
        int col = environmentState.getPosicionAgente()[1];

        this.setSensorSuperior(environment.getCeldasVisiblesSuperiores(row, col));
        this.setSensorInferior(environment.getCeldasVisiblesInferiores(row, col));
        this.setSensorIzquierdo(environment.getCeldasVisiblesIzquierdas(row, col));
        this.setSensorDerecho(environment.getCeldasVisiblesDerechas(row, col));
       
        
    }
    
	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public ArrayList<Integer> getSensorSuperior(){
        return sensorSuperior;
     }
     public void setSensorSuperior(ArrayList<Integer> arg){
        this.sensorSuperior = arg;
     }
     public ArrayList<Integer> getSensorInferior(){
        return sensorInferior;
     }
     public void setSensorInferior(ArrayList<Integer> arg){
        this.sensorInferior = arg;
     }
     public ArrayList<Integer> getSensorIzquierdo(){
        return sensorIzquierdo;
     }
     public void setSensorIzquierdo(ArrayList<Integer> arg){
        this.sensorIzquierdo = arg;
     }
     public ArrayList<Integer> getSensorDerecho(){
        return sensorDerecho;
     }
     public void setSensorDerecho(ArrayList<Integer> arg){
        this.sensorDerecho = arg;
     }


	
   
}
