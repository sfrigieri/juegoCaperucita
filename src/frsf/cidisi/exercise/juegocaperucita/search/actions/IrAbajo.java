package frsf.cidisi.exercise.juegocaperucita.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.juegocaperucita.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrAbajo extends SearchAction {

	/**
	 * This method updates a tree node state when the search process is running.
	 * It does not updates the real world state.
	 */
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {

		CaperucitaState agState = (CaperucitaState) s;

		((CaperucitaState) s).incrementarCostoAccion(this.getCost());

		int fila = agState.getPosicionFila();
		int col = agState.getPosicionColumna();

		ArrayList<Integer> listaCeldas = agState.getCeldasInferiores(fila, col);

		if(agState.getVidasPerdidas() < 3 && !listaCeldas.isEmpty() 
				&& listaCeldas.get(0) != CaperucitaAgentPerception.ARBOL) {

			if(listaCeldas.contains(CaperucitaAgentPerception.LOBO)){
				int vidasPerdidas = agState.getVidasPerdidas();
				agState.setVidasPerdidas(++vidasPerdidas);
				agState.setDulcesPorJuntar(3);
				agState.setPosicion(agState.getPosicionInicial().clone());
				agState.actualizarCeldasPorVisitar();
				agState.setMapaPosicion(fila+listaCeldas
						.indexOf(CaperucitaAgentPerception.LOBO)+1, col, CaperucitaAgentPerception.NO_VISIBLE);
				((CaperucitaState) s).incrementarCostoAccion(20);
			}	
			else {
				int avance = 0;
				int posicionFilaAnterior = agState.getPosicionFila();
				for(Integer valor : listaCeldas) {
					avance++;
					//Por cada celda en columna 'col' que contenga dulces
					if(valor.intValue() == CaperucitaAgentPerception.DULCES) {
						int dulcesPorJuntar = agState.getDulcesPorJuntar();
						agState.setDulcesPorJuntar(--dulcesPorJuntar);
						agState.setMapaPosicion(fila+avance, col, CaperucitaAgentPerception.LIBRE);
						((CaperucitaState) s).incrementarCostoAccion(-1);
					}
				}
				//Si en la última celda visible hay un árbol, la posición final será la anterior al árbol
				if(listaCeldas.contains(CaperucitaAgentPerception.ARBOL))
					agState.setPosicionFila(fila+avance-1);
				//Sino, la posición final coincide con la última celda visible
				else
					agState.setPosicionFila(fila+avance);

				agState.actualizarCeldasPorVisitar(posicionFilaAnterior,"irAbajo");
			}

			return agState;

		}
		else
			return null;

	}

	/**
	 * This method updates the agent state and the real world state.
	 */
	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		BosqueState environmentState = (BosqueState) est;
		CaperucitaState agState = ((CaperucitaState) ast);

		int fila = agState.getPosicionFila();
		int col = agState.getPosicionColumna();

		ArrayList<Integer> listaCeldas = agState.getCeldasInferiores(fila, col);

		if(agState.getVidasPerdidas() < 3 && !listaCeldas.isEmpty() 
				&& listaCeldas.get(0) != CaperucitaAgentPerception.ARBOL) {

			if(listaCeldas.contains(CaperucitaAgentPerception.LOBO)){
				int vidasPerdidas = agState.getVidasPerdidas();

				//Actualizo el contador de vidas perdidas en estadoAmbiente (necesario al evaluar agentFailed())
				environmentState.setVidasPerdidasAgente(++vidasPerdidas);
				//Se reinicializa el mapa y la posición del agente en estadoAmbiente
				environmentState.resetPosicionAgente();
				environmentState.resetMapa();

				agState.setVidasPerdidas(vidasPerdidas); 
				agState.setDulcesPorJuntar(3);//Las posiciones de dulces se actualizarán con las futuras percepciones
				agState.setPosicion(agState.getPosicionInicial().clone());
				agState.actualizarCeldasPorVisitar();
				agState.setMapaPosicion(fila+listaCeldas
						.indexOf(CaperucitaAgentPerception.LOBO)+1, col, CaperucitaAgentPerception.NO_VISIBLE);
			}	
			else {
				int avance = 0;
				int posicionFilaAnterior = agState.getPosicionFila();
				for(Integer valor : listaCeldas) {
					avance++;
					//Por cada celda en columna 'col' que contenga dulces
					if(valor.intValue() == CaperucitaAgentPerception.DULCES) {

						environmentState.setMapaPosicion(fila+avance, col, CaperucitaAgentPerception.LIBRE);

						int dulcesPorJuntar = agState.getDulcesPorJuntar();
						agState.setDulcesPorJuntar(--dulcesPorJuntar);
						agState.setMapaPosicion(fila+avance, col, CaperucitaAgentPerception.LIBRE);
					}
				}
				//Si en la última celda visible hay un árbol, la posición final será la anterior al árbol
				if(listaCeldas.contains(CaperucitaAgentPerception.ARBOL)) {
					environmentState.setPosicionAgenteFila(fila+avance-1);
					agState.setPosicionFila(fila+avance-1);
				}
				//Sino, la posición final coincide con la última celda visible
				else {
					environmentState.setPosicionAgenteFila(fila+avance);
					agState.setPosicionFila(fila+avance);
				}

				agState.actualizarCeldasPorVisitar(posicionFilaAnterior,"irAbajo");
				agState.updateGameBoard();

			}

			return environmentState;

		}
		else
			return null;
	}

	/**
	 * This method returns the action cost.
	 */
	@Override
	public Double getCost() {
		return new Double(1);
	}

	/**
	 * This method is not important for a search based agent, but is essensial
	 * when creating a calculus based one.
	 */
	@Override
	public String toString() {
		return "IrAbajo";
	}
}