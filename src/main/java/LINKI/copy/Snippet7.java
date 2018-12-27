package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet7 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent6",15));
		links.add(new Link("Agent9",10));
		links.add(new Link("Agent4",10));
		links.add(new Link("Agent3",5));
		Setting s7 = new Setting();
		s7.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s7, "Agent7.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
