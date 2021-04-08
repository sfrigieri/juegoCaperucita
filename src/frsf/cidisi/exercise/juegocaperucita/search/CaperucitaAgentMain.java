package frsf.cidisi.exercise.juegocaperucita.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class CaperucitaAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
        CaperucitaAgent agent = new CaperucitaAgent(1);

        Bosque environment = new Bosque(1);

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
