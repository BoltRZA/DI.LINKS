package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet2 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent4",20));
		links.add(new Link("Agent1",10));
		Setting s2 = new Setting();
		s2.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s2, "Agent2.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
