package LINKI.copy2;

import java.util.List;
import java.util.Scanner;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Agent1 extends Agent {
	private Agent myAgent = this;
	private List<AID> REC;
	@Override
	protected void setup() {
		System.out.println("Hello! i am "+getAID().getLocalName()+". Now I try to find another Agent");
		DataStore ds = new DataStore();
		addBehaviour(new Norma(this, ds));
		//addBehaviour(new CHOISE(this, ds));
		super.setup();
	}
}
