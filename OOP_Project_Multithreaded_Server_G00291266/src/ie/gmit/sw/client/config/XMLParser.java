package ie.gmit.sw.client.config;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser implements Parseator {
	//Variables----------------------------------------------------------------------------------------------
	private Context ctx;

	//Constructor-------------------------------------------------------------------------------------------
	public XMLParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	//Accessors-----------------------------------------------------------------------------------------------
	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
	
	//Methods------------------------------------------------------------------------------------------------
	public void init() throws Throwable{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONF_FILE);
		
		Element root = doc.getDocumentElement(); //Get the root of the node tree(XML file)
		NodeList children = root.getChildNodes(); //Get the child node of the root
		//find the elements
				NamedNodeMap atts = root.getAttributes();
				
				for(int j=0;j<atts.getLength();j++)
				{
					if(atts.item(j).getNodeName().equals("username")){
						ctx.setUsername(atts.item(0).getNodeValue());
					}
				}//end for
				
				for(int i=0;i<children.getLength();i++){
					Node next = children.item(i);
					
					if(next instanceof Element){
						Element e = (Element)next;
						
					if(e.getNodeName().equals("server-host")){
							ctx.setHost((e.getTextContent()));
						}
					else if(e.getNodeName().equals("server-port")){
							ctx.setPort(e.getTextContent());
						}
					else if(e.getNodeName().equals("download-dir")){
							ctx.setDir(e.getTextContent());
						}
						
					}	//end if 
		} //end for 
	}

}
