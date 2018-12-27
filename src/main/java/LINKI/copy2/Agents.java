package LINKI.copy2;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Agents extends Agent {
	private Agent myAgent = this;
	@Override
	protected void setup() {
		 DataStore ds = new DataStore();
		System.out.println("Hello! i am "+getAID().getLocalName());
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(myAgent.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("helper");
		sd.setName(getLocalName()+" - spy-agent");
		dfd.addServices(sd);

		try {
			DFService.register(myAgent, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new GOOL(this, ds ));
		
		//addBehaviour(new Find(this, ds ));
		super.setup();
	}
		
	

}
