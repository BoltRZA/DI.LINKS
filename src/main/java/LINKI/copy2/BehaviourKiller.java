package LINKI.copy2;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;

public class BehaviourKiller extends WakerBehaviour {
	private Agent agent;
	private Behaviour Killer;
	private long timeout;

	public BehaviourKiller (Agent a, long timeout,Behaviour Killer) {
		super(a, timeout);
		this.agent = a;
		this.Killer=Killer;
		this.timeout=timeout;
	}
	protected void onWake() {
		super.onWake();
		agent.removeBehaviour(Killer);
		System.out.println(agent.getLocalName()+ ": Behaviour " + Killer.getBehaviourName()+ " REMOVED");

	}
}
