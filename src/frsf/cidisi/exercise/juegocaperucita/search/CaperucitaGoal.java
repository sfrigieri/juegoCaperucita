

package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class CaperucitaGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    
    	// TODO: Complete Method
        if  (true) //((vidasPerdidas < 3) & (mapaBosque.getCellValue(posicion) = FLORES))
        	{
            return true;
        	}
        return false;
	}
}