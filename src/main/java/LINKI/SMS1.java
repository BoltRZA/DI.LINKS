package LINKI;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SMS1 extends OneShotBehaviour {
	private Agent agent;
	private String stroka;
	private double bestway;
	private AID REC;

	public SMS1(Agent agent, double bestway, String stroka) {
		this.agent = agent;
		this.bestway = bestway;
		this.stroka = stroka;
	}

	@Override
	public void action() {

		String LOL = this.agent.getLocalName()+".xml";
		System.out.println("ETO XML TVOEGO AGENTA "+LOL);
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		for (int j=0; j<s;j++) {
			ACLMessage BEST = new ACLMessage(ACLMessage.INFORM);
			BEST.setContent(bestway+""); 
			REC = new AID (s2.getLinks().get(j).getAgentName(), false);
			BEST.addReceiver(REC);
			BEST.setProtocol("BEST");
			agent.send(BEST);
			System.out.println(this.agent.getLocalName()+": Otpravil BBEEEESSSTTT'"+BEST.getContent()+"' to "+REC.getLocalName());

			ACLMessage STROK = new ACLMessage(ACLMessage.INFORM);
			STROK.setContent(this.agent.getLocalName()+""); 
			REC = new AID (s2.getLinks().get(j).getAgentName(), false);
			STROK.addReceiver(REC);
			STROK.setProtocol("stroka");
			agent.send(STROK);
			System.out.println(this.agent.getLocalName()+": Otpravil BBEEEESSSTTT'"+STROK.getContent()+"' to "+REC.getLocalName());
		}
	}
}
