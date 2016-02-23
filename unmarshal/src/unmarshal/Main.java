package unmarshal;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) {
		try {

			File fXmlFile = new File("users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			String indent = "";
			listAttrsAndChildNodes(doc, indent);
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static void listAttrsAndChildNodes(Node node, String indent) {
		
		System.out.println(indent + node.getNodeName());
		listAttrs(node, indent);
		NodeList children = node.getChildNodes();
		int nch = children.getLength();
		for (int i = 0; i < nch; i++) {
			Node n = children.item(i);
			String nnam = n.getNodeName();
			short type = n.getNodeType(); 
			if(type == org.w3c.dom.Node.ELEMENT_NODE) {
				indent += " ";
				listAttrsAndChildNodes(n, indent);
				
			}
			else {
				String val = n.getNodeValue();
				if (!val.matches("^\\s+$"))
					System.out.println(indent + val);
			}
		}
	}

	private static void listAttrs(Node node, String indent) {
		NamedNodeMap attrs = node.getAttributes();
		if (attrs != null) {
			int nattrs = attrs.getLength();
			for (int i = 0; i < nattrs; i++) {
				Node a = attrs.item(i);
				System.out.println(indent + " " + a);
			}
		}
	}

}
