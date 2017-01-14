package ie.gmit.sw.client;

import java.io.*; //We need the Java IO library to read from the socket's input stream and write to its output stream
import java.net.*; //Sockets are packaged in the java.net library
import java.util.Scanner;

import ie.gmit.sw.client.config.Context;

public class WebClient 
{
	//Variables--------------------------------------------------------------------------------------------------------------------------
	private Context ctx;
	private String response;
	private boolean isConnected=false;
	
	//Constructor--------------------------------------------------------------------------------------------------------------------------
	public WebClient(Context c)
	{
		super();
		this.ctx = c;
	}
	
	//Methods--------------------------------------------------------------------------------------------------------------------------
	public void listFiles() throws Throwable
	{
		new Thread(new Runnable()
		{
			public void run() 
			{  	
				try 
				{ 
						Socket s = new Socket(ctx.getHost(), Integer.parseInt(ctx.getPort())); //Connect to the server
						//send message to server
						String request ="2";
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						out.writeObject(request); 
						out.flush(); 
						
						//Read response from server
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());
						response = (String) in.readObject(); 
						System.out.println(response);
						s.close(); //Tidy up
				} catch (Exception e) 
				{ 
						System.out.println("Error: " + e.getMessage());
				}//End of try /catch
			}//End of run(). The thread will now die...sob..sob...;)
		}).start(); //Start the thread
	}
		
	public void downloadFiles() throws Throwable
		{
			new Thread(new Runnable()
			{
				public void run() 
				{  	
					try 
					{ 
						Socket s = new Socket(ctx.getHost(), Integer.parseInt(ctx.getPort())); //Connect to the server
						//send message to server
						String request ="3";
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						out.writeObject(request); 
						out.flush(); 
						
						//Read response from server
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());	
						int temp = Integer.parseInt(response);
						for (int i = 0; i < temp; i++) 
						{
							response = (String) in.readObject(); 
							System.out.println(response);
						}
						
						//Get the name of the thread (worker) doing this runnable (job)
						String threadName = Thread.currentThread().getName(); 
 	 					System.out.println("client-->" + threadName+" "+response);
						//System.out.println(threadName + " just woke up and closing socket...");
						
						s.close(); //Tidy up
					} catch (Exception e) 
					{ 
						System.out.println("Error: " + e.getMessage());
					}//End of try /catch
				}//End of run(). The thread will now die...sob..sob...;)
			}).start(); //Start the thread
		}
		
	public void connected()
	{
		setConnected(true);
	}

	//Accessors--------------------------------------------------------------------------------------------------------------------------
	public boolean isConnected() {
		return isConnected;
	}
		
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
}//End of class
