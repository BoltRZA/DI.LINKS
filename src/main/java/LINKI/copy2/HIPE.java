package LINKI.copy2;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class HIPE extends Behaviour {
	private Agent agent;
	private String name;
	private AID REC = null;
	private String REC1 ;
	private boolean p = false;
	private long count=0;
	private double bestway=1000;

	public HIPE(Agent agent, DataStore ds) {
		super();
		setDataStore(ds);
		this.agent = agent;
	}

	@Override
	public void action() {
		Object R = getDataStore().get("KEY");
		System.out.println(R);
		REC1 = (String) R;

		System.err.println("NACHALO FINDING "+REC1);
		String LOL = this.agent.getLocalName()+".xml";
		//System.out.println("ETO XML TVOEGO AGENTA "+LOL);
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		for (int j=0; j<s;j++) {
			ACLMessage infa = new ACLMessage(ACLMessage.REQUEST); //ender agent
			//infa.setContent(REC1+","+count+","+agent.getLocalName()+""); 
			infa.setContent(REC1+","+agent.getLocalName()+""); 
			REC = new AID (s2.getLinks().get(j).getAgentName(), false);
			infa.addReceiver(REC);
			infa.setProtocol("NAME");
			agent.send(infa);

		}
		p=true;
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return p;
	}
	@Override
	public int onEnd() {
		Read Rd = new Read(agent, getDataStore(),bestway);
		agent.addBehaviour(Rd);
		agent.addBehaviour(new BehaviourKiller(agent,5000, Rd));
		return super.onEnd();
	}
}
