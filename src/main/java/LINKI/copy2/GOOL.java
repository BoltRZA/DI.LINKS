package LINKI.copy2;

import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class GOOL extends Behaviour {
	private Agent agent;
	private String name;
	private String stroka="";
	private String stroka2;
	private String stroka4;
	private String[] stroka3;
	private String[] stroka1;
	private String[] name1;
	private boolean p = false;
	private boolean b = false;
	private AID REC = null;
	private AID SAND = null;
	private double waay;
	private long count;

	public GOOL(Agent agent, DataStore ds) {
		super();
		setDataStore(ds);
		this.agent = agent;
	}

	@Override


	public void action() {

		String LOL = this.agent.getLocalName()+".xml";
		//System.out.println("ETO XML TVOEGO AGENTA "+LOL);
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		//MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
		//		MessageTemplate.MatchPerformative(ACLMessage.INFORM)),
		//		MessageTemplate.MatchProtocol("NAME"));
		MessageTemplate mt1 = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
				MessageTemplate.MatchProtocol("NAME"));
		ACLMessage request1 = agent.receive(mt1);
		if (request1!= null){
			//if (request1.getPerformative() == ACLMessage.REQUEST) {
			name = (request1.getContent());
			name1=name.split(",");
			SAND=request1.getSender();
			getDataStore().put("SENDER", SAND);
			getDataStore().put("END", name1[0]);
			//System.out.println(agent.getLocalName()+": CONTENT KONEC SKORO: "+name);
			//System.out.println("-------------------"+name1[1]);
			//count=Long.parseLong(name1[1]);
			//count++;
			//System.out.println(count);
			//if (count<200) {
				if (name1.length==2) {
					stroka=name1[1];
				}
				else {
					stroka=name1[1];
					for(int k=2;k<(name1.length);k++) {
						stroka=stroka+","+name1[k];
					}	
				}
				
				stroka1=stroka.split(",");				
				//System.out.println(stroka1.length);
				for (int i=0;i<stroka1.length;i++) {
					if (agent.getLocalName().equals(stroka1[i])) {
						b=true;
					}
				}
				stroka=stroka+","+agent.getLocalName();
				//System.out.println(agent.getLocalName()+"++++++++++++++++++++"+stroka);
				if (b==false) {
					if (name1[0].equals(agent.getLocalName())) {
						//System.err.println(agent.getLocalName()+": LUCK!");
						for (int j=0; j<s;j++) {
							if (s2.getLinks().get(j).getAgentName().equals(request1.getSender().getLocalName())) {
								waay = s2.getLinks().get(j).getWeight();
							}
						}
						ACLMessage infa = new ACLMessage(ACLMessage.CONFIRM); //ender agent
						stroka2=stroka.replaceAll(","+agent.getLocalName(), "");
						stroka3=stroka2.split(",");
						//int g= stroka3.length;
						infa.addReceiver(new AID (stroka3[stroka3.length-1],false));
						//System.out.println(agent.getLocalName()+"UUUUUU"+stroka);
						//System.out.println(agent.getLocalName()+"OOOOOOOOOO"+stroka2);
						//System.out.println(agent.getLocalName()+" nnnnnnnnnn  "+stroka3[stroka3.length-1]);
						infa.setContent(waay+";"+stroka+";"+stroka2); 
						//REC = request1.getSender();
						//infa.addReceiver(REC);
						infa.setProtocol("WAY");
						agent.send(infa);
						p=true;

					}
					else {
						//System.out.println(agent.getLocalName()+": FAIL!");

						for (int j=0; j<s;j++) {
							ACLMessage infa = new ACLMessage(ACLMessage.REQUEST); //ender agent
							//infa.setContent(name1[0]+","+count+","+stroka+""); 
							infa.setContent(name1[0]+","+stroka+"");
							if (!s2.getLinks().get(j).getAgentName().equals(request1.getSender().getLocalName())) {
								REC = new AID (s2.getLinks().get(j).getAgentName(), false);
								infa.addReceiver(REC);
								infa.setProtocol("NAME");
								agent.send(infa);
								//System.out.println(agent.getLocalName()+": zapros u "+REC.getLocalName());
								agent.addBehaviour(new Lol(agent, getDataStore()));
							}
						}
						p=true;
					}
				}

			//}
			else {
				//System.out.println("UJE BILOOOO");
				//b=true;
				p=true;

			}
		}
		else{
			block();
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return p;
	}
	@Override
	public int onEnd() {
		//if (b==false) {
		//if (count<200) {			
			//System.out.println(agent.getLocalName()+": SCHETCHIK "+count);
			agent.addBehaviour(new GOOL(agent, getDataStore()));
			agent.addBehaviour(new Lol(agent, getDataStore()));
		//}
		//}
		return super.onEnd();
	}

}
