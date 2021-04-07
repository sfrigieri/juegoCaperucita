package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CaperucitaAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int sensorsuperior;
	private int sensorinferior;
	private int sensorizquierdo;
	private int sensorderecho;
	
 

    public  CaperucitaAgentPerception() {
    	//TODO: Complete Method
    }

    public CaperucitaAgentPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        //CaperucitaAgent agent = (CaperucitaAgent) agentIn;
        //Bosque environment = (Bosque) environmentIn;
        //BosqueState environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public int getsensorsuperior(){
        return sensorsuperior;
     }
     public void setsensorsuperior(int arg){
        this.sensorsuperior = arg;
     }
     public int getsensorinferior(){
        return sensorinferior;
     }
     public void setsensorinferior(int arg){
        this.sensorinferior = arg;
     }
     public int getsensorizquierdo(){
        return sensorizquierdo;
     }
     public void setsensorizquierdo(int arg){
        this.sensorizquierdo = arg;
     }
     public int getsensorderecho(){
        return sensorderecho;
     }
     public void setsensorderecho(int arg){
        this.sensorderecho = arg;
     }
	
   
}
