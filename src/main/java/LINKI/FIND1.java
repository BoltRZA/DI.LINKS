package LINKI;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class FIND1 extends Behaviour {
	private Agent agent;
	private String stroka;
	private boolean dok = false;
	private boolean flag = false;
	private AID REC = null;
	private String REC1 ;
	private double bestway = 1000000;
	private double weight;

	public FIND1(Agent agent, DataStore ds) {
		this.agent  = agent;
		setDataStore(ds);
	}


	@Override
	public void action() {
		Object R = getDataStore().get("KEY");
		System.out.println(R);
		REC1 = (String) R;

		System.err.println("NACHALO FINDING "+REC1);
		String LOL = this.agent.getLocalName()+".xml";
		System.out.println("ETO XML TVOEGO AGENTA "+LOL);
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		for (int j=0; j<s;j++) {
			System.out.println(agent.getLocalName()+": EST SVAZ S "+j+" - "+s2.getLinks().get(j).getAgentName()+" VES SVAZI - "+s2.getLinks().get(j).getWeight());
			//System.out.println("VES SVAZI "+s2.getLinks().get(j).getWeight());
			System.err.println("KNECHNAYA TOCHKA "+REC1);
			if (REC1.equals(s2.getLinks().get(j).getAgentName())) {
				System.out.println("LUCK");
				bestway = s2.getLinks().get(j).getWeight();
				System.err.println("NOW THE BEST WEIGHT IS "+bestway);
				flag = true;
				DataStore ds = new DataStore();
				getDataStore().put("way",bestway);
			}
			else {
				System.out.println("LoSE");

				ACLMessage infa = new ACLMessage(ACLMessage.INFORM); //ender agent
				infa.setContent(REC1+""); 
				REC = new AID (s2.getLinks().get(j).getAgentName(), false);
				infa.addReceiver(REC);
				infa.setProtocol("NAME");
				agent.send(infa);

				ACLMessage request1 = new ACLMessage(ACLMessage.REQUEST);
				request1.setContent(s2.getLinks().get(j).getWeight()+""); 
				//REC = new AID (s2.getLinks().get(j).getAgentName(), false);
				request1.addReceiver(REC);
				request1.setProtocol("LINK");
				agent.send(request1);
				System.out.println(this.agent.getLocalName()+": Otpravil '"+request1.getContent()+"' to "+REC.getLocalName());
			}


			System.out.println("____________________________________________");
			if (j==(s-1)) {
				dok = true;
			}
		}

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return dok;
	}
	@Override
	public int onEnd() {
		stroka = this.agent.getLocalName()+"";
		//	SMS1 behaviour = new SMS1 (agent, bestway, stroka);
		//	agent.addBehaviour(behaviour);
		///if (flag==false) {
		String LOL = this.agent.getLocalName()+".xml";
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		for (int j=0; j<s;j++) {

			ACLMessage STROK = new ACLMessage(ACLMessage.INFORM);
			STROK.setContent(this.agent.getLocalName()+""); 
			REC = new AID (s2.getLinks().get(j).getAgentName(), false);
			STROK.addReceiver(REC);
			STROK.setProtocol("stroka");
			agent.send(STROK);
			System.out.println(this.agent.getLocalName()+": Otpravil stroku'"+STROK.getContent()+"' to "+REC.getLocalName());


		}
		return super.onEnd();
	}
}
