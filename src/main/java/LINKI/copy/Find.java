package LINKI.copy;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class Find extends Behaviour {
	private Agent agent;
	private String name;
	private String name1;
	public String STR;
	private String stroka;
	private String stroka1;
	private boolean log = false;
	//private boolean flag = false;
	//private boolean flag1 = false;
	//private boolean flag2 = false;
	//private boolean B1 = false;
	//private boolean B2 = false;
	private boolean msgArried = false;
	private double bestway;
	private double bestway1;
	private double way;
	private double weight;
	private AID REC1;
	//private String name6 = "Agent6";
	//private String name8 = "Agent8";

	public Find(Agent agent,  DataStore ds) {
		super();
		setDataStore(ds);
		this.agent = agent;
		this.name = name;
		this.STR = STR;
		//this.name =  (String) ds.get("KEY");
		//this.bestway = (Double) ds.get("way");
		this.way = way;
		//this.Agent6 = Agent6;
		//this.Agent8 = Agent8;

	}
	@Override
	public void action() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Object R = getDataStore().get("KEY");
		//System.out.println(agent.getLocalName()+": "+R);
		MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
				MessageTemplate.MatchProtocol("NAME"));
		ACLMessage request1 = agent.receive(mt1);
		if (request1!= null){
			name = (request1.getContent());
			getDataStore().put("END", name);
			System.out.println(agent.getLocalName()+": CONTENT KONEC SKORO: "+name);
		}

		//MessageTemplate mt3 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
		//		MessageTemplate.MatchProtocol("BEST"));
		//ACLMessage request3 = agent.receive(mt3);
		//if (request3!= null){
		//	bestway = Double.parseDouble(request3.getContent());
		//	getDataStore().put("best", bestway);
		//	System.out.println(agent.getLocalName()+ ": CONTEeeeNT: "+bestway);
		//}

		MessageTemplate mt4 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
				MessageTemplate.MatchProtocol("stroka"));
		ACLMessage request4 = agent.receive(mt4);
		if (request4!= null){
			getDataStore().put("set", stroka);
			stroka = (request4.getContent())+this.agent.getLocalName();
			//System.out.println("STROCHKA: "+stroka);
		}

		MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
				MessageTemplate.MatchProtocol("LINK"));
		ACLMessage request = agent.receive(mt);
		if (request!= null){
			weight = Double.parseDouble(request.getContent());

			System.err.println(agent.getLocalName()+": NACHALO FINDING");
			String LOL = this.agent.getLocalName()+".xml";
			System.out.println(LOL);

			stroka1=(String) getDataStore().get("set");
			name1=(String) getDataStore().get("END");
			//bestway1 = (Double) getDataStore().get("best");

			//getDataStore().put("END", name1);
			//getDataStore().put("best", bestway1);

			System.out.println(agent.getLocalName()+": NOW THE enduser - "+name1+" and the bestway - "+bestway1);
			Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
			int s = s2.getLinks().size();
			for (int j=0; j<s;j++) {

				if (request.getSender().getLocalName().equals(s2.getLinks().get(j).getAgentName())) {
					System.out.println(agent.getLocalName()+": NETUDA");
					msgArried = true;
				}
				else {
					System.out.println(agent.getLocalName()+": EST SVAZ S "+j+" - "+s2.getLinks().get(j).getAgentName()+" VES SVAZI - "+s2.getLinks().get(j).getWeight());
					//System.out.println(agent.getLocalName()+": VES SVAZI "+s2.getLinks().get(j).getWeight());
					System.err.println(agent.getLocalName()+": KNECHNAYA TOCHKA "+name1);
					if (name1.equals(s2.getLinks().get(j).getAgentName())) {
						System.out.println("LUCKY YOU");
						way = s2.getLinks().get(j).getWeight();
						weight= weight + way;
						//if (weight < bestway1) {
						//	bestway1=weight;
						//	getDataStore().put("best", bestway1);
						//	log = true;
						//	System.err.println("NOW THE BEST WEIGHT IS "+bestway1);
							//STR=STR+this.agent.getLocalName()+"";
							//	DataStore ds = new DataStore();
							//	ds.put("way",bestway);
						//}
						//else {
						//	System.err.println("EST I POLUCHE");
						//}

					}
					else {
						//way = s2.getLinks().get(j).getWeight();
						//weight= weight + way;
						//stroka1=stroka1+agent.getLocalName();


						//STR=STR+this.agent.getLocalName()+"";
						//System.out.println("STRRRR _______"+STR);
						//System.out.println(this.agent.getLocalName()+": Otpravil poka chto ves'"+request.getContent()+"' to "+REC1.getLocalName());
					}
					System.out.println("________________________________");
					if (j==(s-1)) {
						System.out.println("MOMOOOMMMOMOM");
						msgArried = true;
					}	
				}
			}
		}

		else{
			block();
		}
	}

	@Override
	public boolean done() {
		return msgArried;
	}
	@Override
	public int onEnd() {
		name1=(String) getDataStore().get("END");
		//bestway1 = (Double) getDataStore().get("best");
		DataStore ds = new DataStore();
		ds.put("END", name1);
		//ds.put("best", bestway1);
		String LOL = this.agent.getLocalName()+".xml";
		Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, LOL);
		int s = s2.getLinks().size();
		for (int j=0; j<s;j++) {
			if (s2.getLinks().get(j).getAgentName().equals(name1)) {
				System.out.println(agent.getLocalName()+": ETO TVOI KONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEC");
				System.err.println(agent.getLocalName()+ " "+weight);
				System.err.println(agent.getLocalName()+ " "+stroka1);
			}
			else {
				ACLMessage request2 = new ACLMessage(ACLMessage.REQUEST);
				way = s2.getLinks().get(j).getWeight();
				weight= weight + way;
				request2.setContent(weight+""); 
				REC1 = new AID (s2.getLinks().get(j).getAgentName(), false);
				request2.addReceiver(REC1);
				request2.setProtocol("LINK");
				agent.send(request2);

				//ACLMessage BEST = new ACLMessage(ACLMessage.INFORM);
				//BEST.setContent(bestway1+""); 
				//REC1= new AID(s2.getLinks().get(j).getAgentName(), false);
				//BEST.addReceiver(REC1);
				//BEST.setProtocol("BEST");
				//agent.send(BEST);
				//System.out.println(this.agent.getLocalName()+": Otpravil THE BEEEEST '"+BEST.getContent()+"' to "+REC1.getLocalName());
				
				stroka1=stroka1+agent.getLocalName();
				ACLMessage st = new ACLMessage(ACLMessage.INFORM);
				st.setContent(stroka1+""); 
				REC1 = new AID (s2.getLinks().get(j).getAgentName(), false);
				st.addReceiver(REC1);
				st.setProtocol("stroka");
				agent.send(st);

				ACLMessage infa = new ACLMessage(ACLMessage.INFORM);
				infa.setContent(name1+""); 
				//REC1 = new AID (s2.getLinks().get(j).getAgentName(), false);
				infa.addReceiver(REC1);
				infa.setProtocol("NAME");
				agent.send(infa);
			}
		}


		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.err.println(ds.get("END"));
		return super.onEnd();
	}
}
