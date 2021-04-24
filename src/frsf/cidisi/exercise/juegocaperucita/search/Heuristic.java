package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
	
public class Heuristic implements IEstimatedCostFunction {
	
	//Ponderación máxima
	private double pondMax = 100;
	
	/**
	 * It returns the estimated cost to reach the goal from a NTree node.
	 */
	@Override
	public double getEstimatedCost(NTree node) {
		CaperucitaState agState = (CaperucitaState) node.getAgentState();
		//Por cada dulce obtenido restará pondMax puntos de costo a cada paso futuro del camino
		//Por cada celda visitada restará 0.001*pondMax puntos de costo.
		//Por cada vida perdida sumará 0.5*pondMax puntos de costo a cada paso futuro del camino
		// más 3*pondMax puntos por tener que volver a juntar los dulces.


		//Óptimo (costo estimado igual a cero): 
		//	Llegar al estado meta sin vidas perdidas, conociendo todas las celdas y habiendo recolectado todos los dulces

		return (agState.getDulcesPorJuntar()*pondMax + agState.getCeldasPorVisitar()*pondMax*0.001 + agState.getVidasPerdidas()*pondMax*0.5);
	}
}
