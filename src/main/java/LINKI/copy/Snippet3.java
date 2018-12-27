package LINKI.copy;


import java.util.ArrayList;
import java.util.List;

public class Snippet3 {
	public static void main(String[] args) {
		List<Link> links = new ArrayList<Link>();
		links.add(new Link("Agent7",5));
		links.add(new Link("Agent8",15));
		links.add(new Link("Agent1",25));
		Setting s3 = new Setting();
		s3.setLinks(links);
		//Создание xml 
		WorkWithConfiGFiles.marshalAny(Setting.class, s3, "Agent3.xml");
		//Setting s2 = WorkWithConfiGFiles.unMarshalAny(Setting.class, "Ageent1.xml");
	}

}
