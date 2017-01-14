package ie.gmit.sw.client;

import java.util.Scanner;

import ie.gmit.sw.client.config.Context;
import ie.gmit.sw.client.config.XMLParser;

public class Runner {
	//Variables---------------------------------------------------------------------
	private static int Menuchoice = 0;

	//Accesssors---------------------------------------------------------------------
	public static int getMenuchoice() {
			return Menuchoice;
		}

	public void setMenuchoice(int menuchoice) {
			Menuchoice = menuchoice;
		}
	
	
	//Main method---------------------------------------------------------------------
	public static void main(String[] args) throws Throwable {
		
		Context ctx = new Context();//instance of contex class
		XMLParser xmlp = new XMLParser(ctx);//instance of parser class
		xmlp.init();//parser object call init method to parse xml file
		System.out.println(ctx);
		UI ui = new UI();//instance of UI class
		WebClient webclient = new WebClient(ctx);//instance of webClient class
		Scanner scan = new Scanner(System.in);
		do
		{
			ui.menu();//prints menu to console
			Menuchoice = scan.nextInt();
			switch (Menuchoice)
			{
				case 1:
						{
							webclient.connected();//makes a the apperance of a connection
							System.out.println("Connected\n");
							break;
						}
				case 2:
						{
							if(webclient.isConnected()==true)
							webclient.listFiles();//does an actual connection between server and client
							else
							System.out.println("Cant do that\n");
							break;
						}
				case 3:
						{	
							if(webclient.isConnected()==true)
							webclient.downloadFiles();//does an actual connection between server and client
							else
							System.out.println("Cant do that\n");
							break;
						}
				case 4:
						{	
							System.out.println("Goodbye 4\n");//exits the loop(ends the program)
							break;
						}
				default:
						{
							if(getMenuchoice() != 1 || getMenuchoice()  != 2 || getMenuchoice() !=3 || getMenuchoice() !=4)
							{
								System.out.println("\nNot a valid choice, Please choose a number between 1 & 4");
							}
							break;
						}
			}	
		}
		while(getMenuchoice() !=4);
		scan.close();
	}
}
