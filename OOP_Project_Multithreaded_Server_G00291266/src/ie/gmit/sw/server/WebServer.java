package ie.gmit.sw.server;


import java.io.File;

/* This class provides a very simple implementation of a web server. As a web server
 * must be capable of handling multiple requests from web browsers at the same time,
 * it is essential that the server is threaded, i.e. that the web server can perform
 * tasks in parallel and serially (one request at a time, after another).
 * 
 * In programming languages, all network communication is handled using sockets. A 
 * socket is a software abstraction of a connection between one computer on a network
 * and another. A server-socket is a process that listens on a port number for 
 * incoming client requests. For example, the standard port number for a HTTP server (a
 * web server) is port 80. Most of the commonly used Java networking classes are 
 * available in the java.net package. The java.io package contains a set of classes
 * designed to handle Input/Output (I/O) activity. We will use both packages in the web
 * server class below.  
 */

//Contains classes for all kinds of I/O activity
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class WebServer
{
	//Variables------------------------------------------------------------------------------------
	private ServerSocket ss; 
	private static final int SERVER_PORT = 7777;  
	private volatile boolean keepRunning = true;
	private String message = null;
	
	
	//constructor----------------------------------------------------------------------------------
	private WebServer()
	{
		try 
		{ 
			ss = new ServerSocket(SERVER_PORT); //SERVER_PORT Start the server socket listening on port 7777
			
			Thread server = new Thread(new Listener(), "Web Server Listener");
			server.setPriority(Thread.MAX_PRIORITY);
			server.start(); 
			
			System.out.println("Server started and listening on port " + SERVER_PORT);
			
		} catch (IOException e) { 
			System.out.println("Yikes! Something bad happened..." + e.getMessage());
		}
	}
	
	//Methods--------------------------------------------------------------------------------------------------------------------
	//method was ment to be use to download files(Not working)
	public void download() 
	{	
		//need to fill up
	}
	
	//method was ment to be use to list files(Not working)
	public void list() 
	{		
		File dir = new File("./downloads");
		System.out.println(dir);
		//ArrayList<> 
	}
	
	//Main Method-----------------------------------------------------------------------------------------------------------------
	//A main method is required to start a standard Java application
	public static void main(String[] args) 
	{
		new WebServer(); //Create an instance of a WebServer. This fires the constructor of WebServer() above on the main stack 
	}
	
	//Inner Classes---------------------------------------------------------------------------------------------------------------
	//Inner class which implements the runnable interface
	private class Listener implements Runnable
	{ 
		//Run method------------------------------------------------------------------------------------
		public void run() 
		{
			int counter = 0; //A counter to track the number of requests
			while (keepRunning)
			{ //Loop will keepRunning is true. Note that keepRunning is "volatile"
				try 
				{ 
					
					Socket s = ss.accept(); //This is a blocking method, causing this thread to stop and wait here for an incoming request
					
					/* If we get to this line, it means that a client request was received and that the socket "s" is a real network
					 * connection between some computer and this programme. We'll farm out this request to a new Thread (worker), 
					 * allowing us to handle the next incoming request (we could have many requests hitting the server at the same time),
					 * so we have to be able to handle them quickly.
					 */
					new Thread(new HTTPRequest(s), "T-" +  counter).start(); //Give the new job to the new worker and tell it to start work
					counter++; //Increment counter
				} catch (IOException e) 
				{ 
					System.out.println("Error handling incoming request..." + e.getMessage());
				}
			}
		}
	}//End of inner class Listener
	
	//Inner class which implements the runnable interface
	private class HTTPRequest implements Runnable
	{
		private Socket sock; //A specific socket connection between some computer on a network and this programme
		
		private HTTPRequest(Socket request) 
		{ //Taking the client socket as a constructor enables the Listener class above to farm out the request quickly
			this.sock = request; //Assign to the instance variable sock the value passed to the constructor. 
		}

		//The interface Runnable declare the method "public void run();" that must be implemented
        public void run() 
        {
            try
            { //Try the following. If anything goes wrong, the error will be passed to the catch block
            	
            	//Read in the request from the remote computer to this programme. This process is called Deserialization or Unmarshalling
            	ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
                String command = (String)in.readObject(); //Deserialise the request into an Object
                System.out.println(command);
                if(command=="2")
                {
                	list();
                	System.out.println("listing files");
                }
                else if(command=="3")
                {
                	download();
                	System.out.println("downloading files");
                }
                
                //Write out a response back to the client. This process is called Serialization or Marshalling
                
            	ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
                out.writeObject(message);
                out.flush();
                out.close(); //Tidy up after and don't wolf up resources unnecessarily
                
            } catch (Exception e) 
            { //Something nasty happened. We should handle error gracefully, i.e. not like this...
            	System.out.println("Error processing request from " + sock.getRemoteSocketAddress());
            	e.printStackTrace();
            }
        }	
	}//End of inner class HTTPRequest
}//End of class WebServer