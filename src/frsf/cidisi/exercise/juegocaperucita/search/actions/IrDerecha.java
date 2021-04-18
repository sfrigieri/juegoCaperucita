package frsf.cidisi.exercise.juegocaperucita.search.actions;

import java.util.ArrayList;

import frsf.cidisi.exercise.juegocaperucita.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrDerecha extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	
    	CaperucitaState agState = (CaperucitaState) s;

		((CaperucitaState) s).incrementarCostoAcciones(this.getCost());
		
		int fila = agState.getPosicionFila();
		int col = agState.getPosicionColumna();

		ArrayList<Integer> listaCeldas = agState.getCeldasVisiblesDerechas(fila, col);

		if(agState.getVidasPerdidas() < 3 && !listaCeldas.isEmpty() 
				&& listaCeldas.get(0) != CaperucitaAgentPerception.ARBOL) {

			if(listaCeldas.contains(CaperucitaAgentPerception.LOBO)){
				int vidasPerdidas = agState.getVidasPerdidas();
				agState.setVidasPerdidas(++vidasPerdidas);
				agState.setDulcesPorJuntar(3);
				agState.setPosicion(agState.getPosicionInicial());
			}	
			else {
				int avance = 0;
				for(Integer valor : listaCeldas) {
					avance++;
					//Por cada celda en columna 'col' que contenga dulces
					if(valor.intValue() == CaperucitaAgentPerception.DULCES) {
						int dulcesPorJuntar = agState.getDulcesPorJuntar();
						agState.setDulcesPorJuntar(--dulcesPorJuntar);
						agState.setMapaPosicion(fila, col+avance, CaperucitaAgentPerception.LIBRE);
					}
				}
				//Si en la �ltima celda visible hay un �rbol, la posici�n final ser� la anterior al �rbol
				if(listaCeldas.contains(CaperucitaAgentPerception.ARBOL))
					agState.setPosicionColumna(col+avance-1); //TODO VER
				//Sino, la posici�n final coincide con la �ltima celda visible
				else
					agState.setPosicionColumna(col+avance);
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

		int fila = environmentState.getPosicionAgenteFila();
		int col = environmentState.getPosicionAgenteColumna();

		ArrayList<Integer> listaCeldas = agState.getCeldasVisiblesDerechas(fila, col);

		if(agState.getVidasPerdidas() < 3 && !listaCeldas.isEmpty() 
				&& listaCeldas.get(0) != CaperucitaAgentPerception.ARBOL) {

			if(listaCeldas.contains(CaperucitaAgentPerception.LOBO)){
				int vidasPerdidas = agState.getVidasPerdidas();
				
				//Actualizo el contador de vidas perdidas en estadoAmbiente (necesario al evaluar agentFailed())
				environmentState.setVidasPerdidasAgente(++vidasPerdidas);
				//Se reinicializa el mapa y la posici�n del agente en estadoAmbiente
				environmentState.resetPosicionAgente();
				environmentState.resetMapa();
				
				agState.setVidasPerdidas(++vidasPerdidas); 
				agState.setDulcesPorJuntar(3);//Las posiciones de dulces se actualizar�n con las futuras percepciones
				agState.setPosicion(agState.getPosicionInicial());
			}	
			else {
				int avance = 0;
				for(Integer valor : listaCeldas) {
					avance++;
					//Por cada celda en columna 'col' que contenga dulces
					if(valor.intValue() == CaperucitaAgentPerception.DULCES) {
						
						environmentState.setMapaPosicion(fila, col+avance, CaperucitaAgentPerception.LIBRE);
						
						int dulcesPorJuntar = agState.getDulcesPorJuntar();
						agState.setDulcesPorJuntar(--dulcesPorJuntar);
						agState.setMapaPosicion(fila, col+avance, CaperucitaAgentPerception.LIBRE);
					}
				}
				//Si en la �ltima celda visible hay un �rbol, la posici�n final ser� la anterior al �rbol
				if(listaCeldas.contains(CaperucitaAgentPerception.ARBOL)) {
					environmentState.setPosicionAgenteColumna(col+avance-1);
					agState.setPosicionColumna(col+avance-1);
				}
				//Sino, la posici�n final coincide con la �ltima celda visible
				else {
					environmentState.setPosicionAgenteColumna(col+avance);
					agState.setPosicionColumna(col+avance);
				}

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
        return "IrDerecha";
    }
}