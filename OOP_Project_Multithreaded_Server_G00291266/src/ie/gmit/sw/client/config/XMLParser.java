package ie.gmit.sw.client.config;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser implements Parseator {
	private Context ctx;

	public XMLParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	public void init() throws Throwable{
		/* These three lines are part of JAXP (Java API for XML Processing) and are designed to
		 * completely encapsulate how a DOM node tree in manufactured. The concrete classes that
		 * are doing the actual work are part of the Apache Xerces project. JAXP shields us from
		 * having to know and understand the complexity of Xerces through encapsulating the
		 * process.
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONF_FILE);
		
		/* The type Document in the line above is an org.w3c.dom.Document. From this
		 * point on in the method, we will only use the DOM "STANDARD" to do the
		 * processing. DOM is language neutral and completely abstract. As a result,
		 * it is completely stable! The DOM standard hasn't changed much since the
		 * year 2000...! Abstractions are highly stable and can be relied upon. 
		 */
		Element root = doc.getDocumentElement(); //Get the root of the node tree
		NodeList children = root.getChildNodes(); //Get the child node of the root
		System.out.println("Were here now about to start looking at the xml file nodes(Element & attributes) in the XML Parser class");
		//Find the elements database and driver
		for (int i = 0; i < children.getLength(); i++)
		{ //Loop over the child nodes
			Node next = children.item(i); //Get the next child
			
			if (next instanceof Element)
			{ //Check if it is an element node. There are 12 different types of Node
				Element e = (Element) next; //Cast the general node to an element node
				
				if (e.getNodeName().equals("server-details"))
				{ //Check if it is the element <server-details>
					NamedNodeMap atts = e.getAttributes(); //Get the attributes as a map of name/value pairs
					
					for (int j = 0; j < atts.getLength(); j++)
					{ //Look over the attributes. This is similar to looping over a ket set in a Map
						if (atts.item(j).getNodeName().equals("username"))
						{ //if username=gmit-
							ctx.setUsername(atts.item(j).getNodeValue()); //Update the contex object
						}
						
						else if (atts.item(j).getNodeName().equals("host"))
						{ //If host="127.0.0.1"
							ctx.setHost(atts.item(j).getNodeValue()); //Update the contex object
						}
						
						else if (atts.item(j).getNodeName().equals("port")) 
						{//Any port in a storm...
							ctx.setPort(Integer.parseInt(atts.item(j).getNodeValue()));//update context obj
						}
					}
					
				}
				else if (e.getNodeName().equals("download-dir"))
				{ 
					/* The XML document contains the element <download-dir>:
					 * 		<driver>ie.gmit.sw.MyDBDriverImpl</driver>
					 * The text "ie.gmit.sw.MyDBDriverImpl" is just parseable character data (PCDATA) and is contained
					 * as a child node (a text node) of the element node "driver".  
					 */
					
					String downloadDir = e.getFirstChild().getNodeValue(); //Read the text ie.gmit.sw.MyDBDriverImpl as a String
					System.out.println("Were here now in the else of XML Parser class");
					ctx.setDir(downloadDir);
					/* Class.forName(...) invokes the class loader and asks it to check its CLASSPATH variable for the
					 * class called ie.gmit.sw.MyDBDriverImpl. If the class loader cannot find the class, it will throw a
					 * ClassNotFoundException. Otherwise, it will use the method newInstance() of the class java.lang.Class
					 * to invoke the null constructor of the class ie.gmit.sw.MyDBDriverImpl. This will result in an
					 * instance of ie.gmit.sw.MyDBDriverImpl being created and assigned the context.
						
						Downloadable d = (Downloadable) Class.forName(downloadDir).newInstance();
						ctx.setDir(d); 
					 */
					
				}
			}
		}		
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

}
