package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.exercise.juegocaperucita.search.actions.IrArriba;
import frsf.cidisi.exercise.juegocaperucita.search.actions.IrAbajo;
import frsf.cidisi.exercise.juegocaperucita.search.actions.IrIzquierda;
import frsf.cidisi.exercise.juegocaperucita.search.actions.IrDerecha;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class CaperucitaAgent extends SearchBasedAgent {

	public CaperucitaAgent(int escenario) {

		// The Agent Goal
		CaperucitaGoal agGoal = new CaperucitaGoal();

		// The Agent State
		CaperucitaState agState = new CaperucitaState(escenario, false);

		this.setAgentState(agState);

		// Create the operators
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.addElement(new IrDerecha());
		operators.addElement(new IrAbajo());
		operators.addElement(new IrArriba());
		operators.addElement(new IrIzquierda());

		// Create the Problem which the agent will resolve
		Problem problem = new Problem(agGoal, agState, operators);
		this.setProblem(problem);
	}



	/**
	 * This method is executed by the simulator to ask the agent for an action.
	 */
	@Override
	public Action selectAction() {

		// Create the search strategy
		//A Star Search:
//				IStepCostFunction cost = new CostFunction();
//				IEstimatedCostFunction heuristic = new Heuristic();
//				AStarSearch strategy = new AStarSearch(cost, heuristic);

		//		DepthFirstSearch strategy = new DepthFirstSearch();          

		//Breath First Search:
//		BreathFirstSearch strategy = new BreathFirstSearch();

		//Uniform Cost:
		 IStepCostFunction costFunction = new CostFunction();
		 UniformCostSearch strategy = new UniformCostSearch(costFunction);

		//Greedy Search:
		//         IEstimatedCostFunction heuristic = new Heuristic();
		//         GreedySearch strategy = new GreedySearch(heuristic);


		// Create a Search object with the strategy
		Search searchSolver = new Search(strategy);

		/* Generate an XML file with the search tree. It can also be generated
		 * in other formats like PDF with PDF_TREE */
		searchSolver.setVisibleTree(Search.EFAIA_TREE);

		// Set the Search searchSolver.
		this.setSolver(searchSolver);

		// Ask the solver for the best action
		Action selectedAction = null;
		try {
			selectedAction =
					this.getSolver().solve(new Object[]{this.getProblem()});
		} catch (Exception ex) {
			Logger.getLogger(CaperucitaAgent.class.getName()).log(Level.SEVERE, null, ex);
		}

		// Return the selected action
		return selectedAction;

	}

	/**
	 * This method is executed by the simulator to give the agent a perception.
	 * Then it updates its state.
	 * @param p
	 */
	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}
}
