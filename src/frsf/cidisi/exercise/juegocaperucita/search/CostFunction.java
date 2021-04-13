package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.state.AgentState;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {

    public double calculateCost(AgentState agentState) {
        return ((CaperucitaState) agentState).getCostoAcciones();
    }
	
	
    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
        return ((CaperucitaState) node.getAgentState()).getCostoAcciones();
    }
}
