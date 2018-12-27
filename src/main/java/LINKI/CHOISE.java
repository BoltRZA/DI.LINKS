package LINKI;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CHOISE extends Behaviour {
	private Agent agent;
	private String stroka;
	//private String stroka1;
	private double bestway;
	//private double bestway1;
	private double way;
	private double weight;
	private int count =0;
	private boolean flag = false;


	public CHOISE(Agent agent,  DataStore ds) {
		super();
		setDataStore(ds);
		this.agent = agent;
		myAgent=agent;
	}
	@Override
	public void action() {
		System.out.println("JJJDUUUU");
		//MessageTemplate mt4 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
		//		MessageTemplate.MatchProtocol("str"));
		//ACLMessage request4 = agent.receive(mt4);
		//if (request4!= null){
		//	stroka = (request4.getContent());
		//	System.out.println(agent.getLocalName()+": this is stroka "+stroka);
		//	getDataStore().put("set", stroka);
		//}

		MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
				MessageTemplate.MatchProtocol("LIN"));
		ACLMessage request = agent.receive(mt);
		if (request!= null){
			weight = Double.parseDouble(request.getContent());
			if (weight<bestway) {
				bestway=weight;
				System.err.println("SSSSUUUUUCSEEESS");
				count++;
				System.out.println(count);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = true;
			}
		}
		else{
			block();
		}
	}

	@Override
	public boolean done() {

		return flag;
	}
@Override
public int onEnd() {
	System.out.println("WELL DONE!!! THE BEST WAY - "+"dlina sostavila"+bestway);
	return super.onEnd();
}
}
