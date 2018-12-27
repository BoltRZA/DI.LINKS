package LINKI.copy2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Read extends Behaviour {
	private Agent agent;
	private boolean p = false;
	private double way;
	private double bestway;
	private String ans;
	private String mom;
	private String mom2="";
	private String bestpu=null;
	private String[] mom1;
	private String[] ans1;


	public Read(Agent agent, DataStore ds, double bestway) {
		super();
		setDataStore(ds);
		this.agent = agent;
		this.bestway= bestway;
	}

	@Override
	public void action() {
		MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
				MessageTemplate.MatchProtocol("WAY"));
		ACLMessage request1 = agent.receive(mt1);
		if (request1!= null){
			ans=request1.getContent();
			ans1=ans.split(";");			
			way = Double.parseDouble(ans1[0]);
			mom=ans1[1];
			mom1=mom.split(",");
			//System.out.println(mom.length());
			//System.out.println(mom.substring(0, 18));
			for (int i=0;i<mom1.length;i++) {
				mom2=mom2+","+mom1[i];
			}
			if (way<bestway) {
				bestway=way;
				//bestpu=mom2;
				
			}
			getDataStore().put("WAY", way);
			System.err.println(agent.getLocalName()+": ITOG: "+way+" po puti "+mom2);
			agent.addBehaviour(new Read(agent, getDataStore(),bestway));
			
			p=true;
		}
		else{
			block();
			
		}

	}

	@Override
	public boolean done() {
		return p;
	}
	@Override
	public int onEnd() {
		System.out.println(agent.getLocalName()+": BEST: "+bestway);
		
		return super.onEnd();
	}

}
