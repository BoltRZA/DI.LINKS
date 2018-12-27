package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet9 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent8",20));
		links.add(new Link("Agent7",10));
		Setting s10 = new Setting();
		s10.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s10, "Agent9.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
