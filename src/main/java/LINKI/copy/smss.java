package LINKI.copy;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class smss extends OneShotBehaviour {
	private Agent agent;
	private String stroka;
	private double bestway;
	private AID REC;
	
	public smss(Agent agent, double bestway, String stroka) {
		this.agent = agent;
		this.bestway = bestway;
		this.stroka = stroka;
		
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}
