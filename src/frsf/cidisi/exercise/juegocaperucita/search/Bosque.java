package frsf.cidisi.exercise.juegocaperucita.search;

import java.util.ArrayList;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;



	
public class Bosque extends Environment {

	
    public static boolean permitirMovimientoLobo = true;
    

	public Bosque(int escenario) {
       
    	this.environmentState = new BosqueState(escenario);

    }


	public BosqueState getEnvironmentState() {
        return (BosqueState) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  CaperucitaAgentPerception getPercept() {
    	
        // Create a new perception to return
         CaperucitaAgentPerception perception = new CaperucitaAgentPerception();

		perception.initPerception(this);
        
        // Return the perception
        return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        BosqueState envState =
                this.getEnvironmentState();

        int vidasPerdidasAgente = envState.getVidasPerdidasAgente();

        if (vidasPerdidasAgente > 2)
            return true;    

        return false;
    }
    
    @Override
    public void updateState(AgentState ast, Action action) {
    	 super.updateState(ast, action);
    	((BosqueState) this.environmentState).updateGameBoard();
    }
    
    // Metodos especificos de agente:
	public ArrayList<Integer> getCeldasVisiblesSuperiores(int row, int col) {
		return ((BosqueState) this.environmentState).getCeldasVisiblesSuperiores(row, col);
	}

	public ArrayList<Integer> getCeldasVisiblesInferiores(int row, int col) {
		return ((BosqueState) this.environmentState).getCeldasVisiblesInferiores(row, col);
	}

	public ArrayList<Integer> getCeldasVisiblesIzquierdas(int row, int col) {
		return ((BosqueState) this.environmentState).getCeldasVisiblesIzquierdas(row, col);
	}

	public ArrayList<Integer> getCeldasVisiblesDerechas(int row, int col) {
		return ((BosqueState) this.environmentState).getCeldasVisiblesDerechas(row, col);
	}

    
    
}
