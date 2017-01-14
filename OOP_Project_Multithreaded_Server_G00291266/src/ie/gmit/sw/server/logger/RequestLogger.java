package ie.gmit.sw.server.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.omg.CORBA.Request;

//This class is ment to be used to take request from the blocking que on the server and write them to a log file (Not working)
public class RequestLogger {
	//Variables---------------------------------------------------------------------------
	private BlockingQueue q;
	private FileWritter file;
	FileWritter fw;
	
	//Constructor---------------------------------------------------------------------------
	public RequestLogger (BlockingQueue q) throws IOException
	{
		this.q = q;
		this.fw = (FileWritter) newFileWriter(new FileWriter("log.txt"));
	}
	
	//Accessors---------------------------------------------------------------------------
	private Object newFileWriter(FileWriter fileWriter) 
	{
	
		return null;
	}
	
	//Methods---------------------------------------------------------------------------
	public void run() throws Exception
	{
		boolean keepRunning = false;
		while(keepRunning)
		{
		Request r = (Request) q.take();
		fw.write(r.toString() + "\n");  
		}
		fw.close();

	}
}