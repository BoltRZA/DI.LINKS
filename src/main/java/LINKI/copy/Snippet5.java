package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet5 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent6",5));
		links.add(new Link("Agent4",5));
		Setting s5 = new Setting();
		s5.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s5, "Agent5.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
