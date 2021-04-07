

package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class CaperucitaGoal extends GoalTest {

	@Override
	public boolean isGoalState (AgentState agentState) {

		CaperucitaState caperucitaState = (CaperucitaState) agentState;

		int row = caperucitaState.getPosicionFila();
		int col = caperucitaState.getPosicionColumna();

		if  (caperucitaState.getVidasPerdidas() < 3 
				&& caperucitaState.getPosicionMapa(row, col) == CaperucitaAgentPerception.FLORES)
			return true;

		return false;
	}
}