package LINKI.copy2;

public class Link {
	private String AgentName;
	private double weight;
	public Link(String AgentName, double weight) {
		this.AgentName = AgentName;
		this.weight = weight;
	}
	public String getAgentName() {
		return AgentName;
	}
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Link() {
		// TODO Auto-generated constructor stub
	}
}
