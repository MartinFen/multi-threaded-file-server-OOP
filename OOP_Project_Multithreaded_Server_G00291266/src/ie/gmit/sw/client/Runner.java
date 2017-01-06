package ie.gmit.sw.client;

import java.util.Scanner;

import ie.gmit.sw.client.UI;
import ie.gmit.sw.client.config.Context;
import ie.gmit.sw.client.config.XMLParser;

public class Runner {
	//Variables---------------------------------------------------------------------
	private static int Menuchoice = 0;
	private static Boolean flag =false;
	//static UI ui = new UI();
	static WebClient r = new WebClient();
	static Scanner scan = new Scanner(System.in);
	
	//Methods---------------------------------------------------------------------
	
	
	//Main method---------------------------------------------------------------------
	public static void main(String[] args) throws Throwable {
		
		Context ctx = new Context();
		XMLParser xmlp = new XMLParser(ctx);
		
		xmlp.init();
		System.out.println(ctx);
		
		do
		{
			System.out.println("\n1. Connect to Server"+
				"\n2. Print File Listing"+
				"\n3. Download File"+
				"\n4. Quit"+
				"\nType Option [1-4]>");
				System.out.println("\nEnter your choice: ");
				
			Menuchoice = scan.nextInt();
			switch (Menuchoice)
			{
				case 1:
						{
							
							r.client();
							flag=true;
							System.out.println("Connected\n");
							break;
						}
				case 2:
						{
							if(flag==true)
							System.out.println("Case 2\n");
							else
							System.out.println("Cant do that\n");
							break;
						}
				case 3:
						{	
							if(flag==true)
							System.out.println("Case 3\n");
							else
							System.out.println("Cant do that\n");
							break;
						}
				case 4:
						{	
							if(flag==true)
							System.out.println("Case 4\n");
							else
							System.out.println("Cant do that\n");
							break;
						}
				default:
						{
							if(Menuchoice != 1 || Menuchoice != 2 || Menuchoice !=3 || Menuchoice !=4)
							{
								System.out.println("\nNot a valid choice, Please choose a number between 1 & 4");
							}
							break;
						}
			}
			
		}
		while(Menuchoice !=4);
		scan.close();
		//ui.menu();
		

	}
}
