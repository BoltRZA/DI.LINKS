package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet8 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent3",15));
		links.add(new Link("Agent10",20));
		Setting s2 = new Setting();
		s2.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s2, "Agent8.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
