package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

	/**
	 * It returns the estimated cost to reach the goal from a NTree node.
	 */
	@Override
	public double getEstimatedCost(NTree node) {
		CaperucitaState agState = (CaperucitaState) node.getAgentState();
		//Por cada dulce obtenido restar� 10 puntos de costo a cada paso futuro del camino
		//Por cada celda visitada restar� 5 puntos de costo.
		//Por cada vida perdida sumar� 20 puntos de costo a cada paso futuro del camino
		// m�s 30 puntos por tener que volver a juntar los dulces.


		//�ptimo (costo estimado igual a cero): 
		//	Llegar al estado meta sin vidas perdidas, conociendo todas las celdas y habiendo recolectado todos los dulces

		return (agState.getDulcesPorJuntar()*90000000 + agState.getCeldasPorVisitar()*500 + agState.getVidasPerdidas()*10000);
	}
}
