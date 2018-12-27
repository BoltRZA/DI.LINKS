package LINKI.copy2;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Lol extends Behaviour {
	private Agent agent;
	private String name;
	private boolean p = false;
	private double way;
	private String ans;
	private String[] ans1;
	private double waay;
	private AID Sunny = null;
	private String stroka="";
	private String stroka9;
	private String[] stroka2;

	public Lol(Agent agent, DataStore ds) {
		super();
		setDataStore(ds);
		this.agent = agent;
	}

	@Override
	public void action() {
		Sunny = (AID) getDataStore().get("SENDER");//sender requesta
		//System.out.println(agent.getLocalName()+" DLYA "+Sunny.getLocalName());
		//name = (String) getDataStore().get("END");
		//System.out.println(agent.getLocalName()+": ENDAGENT - "+name);
		String LOL = this.agent.getLocalName()+".xml";
		//System.out.println("ETO XML TVOEGO AGENTA "+LOL);
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();

		MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
				MessageTemplate.MatchProtocol("WAY"));
		ACLMessage request1 = agent.receive(mt1);
		if (request1!= null){
			ans=request1.getContent();
			ans1=ans.split(";");
			stroka9=ans1[1];
			way = Double.parseDouble(ans1[0]);
			//System.out.println(agent.getLocalName()+" "+ans1[1]);
			if (ans1.length==3) {
				stroka=ans1[2];
			}
			else {
				stroka=ans1[2];
				for(int k=3;k<(ans1.length);k++) {
					stroka=stroka+","+ans1[k];
				}	
			}
			stroka=stroka.replaceAll(","+agent.getLocalName(), "");
			stroka2=stroka.split(",");
			//System.err.println(agent.getLocalName()+" OLOLO++++++++++++++++++++ooooooLOL"+stroka);
			getDataStore().put("WAY", way);
			//System.out.println(agent.getLocalName()+": ITOG: "+way);
			for (int j=0; j<s;j++) {
				if (s2.getLinks().get(j).getAgentName().equals(stroka2[stroka2.length-1])) {
					//System.out.println(s2.getLinks().get(j).getAgentName()+" =? "+Sunny.getLocalName());
					waay = s2.getLinks().get(j).getWeight();;
				}
			}
			//System.out.println(agent.getLocalName()+":JAST THE way "+ way);
			//System.out.println(agent.getLocalName()+":JAST THE my way "+ waay);
			way=way+waay;
			//System.out.println(agent.getLocalName()+": sum way "+ way);
			ACLMessage infa = new ACLMessage(ACLMessage.CONFIRM); //ender agent
			infa.setContent(way+";"+stroka9+";"+stroka); 
			infa.addReceiver(new AID (stroka2[stroka2.length-1],false));
			//infa.addReceiver(Sunny);
			infa.setProtocol("WAY");
			agent.send(infa);
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
		//System.err.println(agent.getLocalName()+": POVEDENIE THE END");
		agent.addBehaviour(new GOOL(agent, getDataStore()));
		return super.onEnd();
	}
}
