package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet6 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent5",5));
		links.add(new Link("Agent7",15));
		Setting s2 = new Setting();
		s2.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s2, "Agent6.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
