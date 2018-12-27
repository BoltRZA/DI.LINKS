package LINKI.copy2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Norma extends Behaviour {
	private Agent agent;
	private String REC2;

	private boolean dok = false;
	String rec1;
	String rec12;
	int k = 0;
	public Norma(Agent agent, DataStore ds ) {
		this.agent=agent;
		setDataStore(ds);
	}

	@Override
	public void action() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("helper");
		dfd.addServices(sd);
		DFAgentDescription[] result = null;
		try {
			result = DFService.search(myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}



		List<AID> receiver = new ArrayList<AID>();
		for (int i=0; i<result.length;i++) {
			//System.out.println("Seller -  "+result[i].getName().getLocalName());
			receiver.add(new AID(result[i].getName().getLocalName(), false));
		}

		//getDataStore().put("receiverList", receiver);
		//System.out.println(": Size of list-receiver: "+receiver.size());

		int l = result.length;
		for (int i=0; i<l;i++) {
			String rec = result[i].getName().getLocalName();
			int j1=i+1;
			System.out.println("Poisk "+ j1 +" - "+rec);
			if (i==(l-1)) {
				dok = true;
			}
		}


		Scanner in = new Scanner(System.in);
		System.out.println("/Vvedite nomer iscomogo agenta ");
		String name = in.nextLine();
		int myInt = Integer.parseInt(name)-1;


		for (int i=0; i<l;i++) {
			String rec1 = result[i].getName().getLocalName();
			if (myInt == i) {
				System.err.println("ICHEM "+ rec1);
				REC2 = rec1;
				getDataStore().put("KEY",REC2);
			}
		}


		//ACLMessage infa = new ACLMessage(ACLMessage.INFORM); //ender agent
		//infa.setContent(REC2+""); 
		//infa.setProtocol("NAME");
		//for (int ii=0; ii<result.length;ii++) {
		//	infa.addReceiver(result[ii].getName());
		//	receiver.add(new AID(result[ii].getName().getLocalName(), false));
		//}
		//agent.send(infa);



	}

	@Override
	public boolean done() {
		return dok;
	}
	@Override
	public int onEnd() {
		agent.addBehaviour(new HIPE(agent, getDataStore()));
		//System.err.println("передается в поиск "+REC2);
		//Object R = getDataStore().get("KEY");
		//System.out.println(R);
		//FIND1 behaviour = new FIND1(agent, getDataStore());
		//agent.addBehaviour(behaviour);
		return super.onEnd();
	}
}
