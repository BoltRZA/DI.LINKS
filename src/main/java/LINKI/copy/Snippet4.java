package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet4 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent5",5));
		links.add(new Link("Agent7",10));
		links.add(new Link("Agent2",20));
		Setting s4 = new Setting();
		s4.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s4, "Agent4.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
