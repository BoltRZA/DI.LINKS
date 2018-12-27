package LINKI;


import java.util.ArrayList;
import java.util.List;

public class Snippet {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent2",10));
		links.add(new Link("Agent3",25));
		Setting s = new Setting();
		s.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s, "Agent1.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Agent1.xml");
		//System.out.println(s2.getLinks().get(0).getWeight());
	}

}
