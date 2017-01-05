package ie.gmit.sw.client;

import ie.gmit.sw.client.config.Context;
import ie.gmit.sw.client.config.XMLParser;

public class Runner {
	
	//private static int Menuchoice;

	public static void main(String[] args) throws Throwable {
		
		Context ctx = new Context();
		XMLParser xmlp = new XMLParser(ctx);
		xmlp.init();
		
		System.out.println(ctx);
		
		/*Scanner scan = new Scanner(System.in);
		do
		{	
			//Change the sysout to a menu method
			//MainMenu();
			System.out.println("Please choose a number between 1 & 5");
			System.out.println("\nEnter your choice: ");
			Menuchoice = scan.nextInt();
			switch (Menuchoice)
			{
				case 1:
						{
							System.out.println("Case 1\n");
							System.out.println(ctx);
							break;
						}
				case 2:
						{
							System.out.println("Case 2\n");
							break;
						}
				case 3:
						{	
							System.out.println("Case 3\n");
							break;
						}
				case 4:
						{	
							System.out.println("Case 4\n");
							break;
						}
				case 5:
						{
							System.out.println("Goodbye\n");
							break;
						}
				default:
						{
							if(Menuchoice != 1 || Menuchoice != 2 || Menuchoice !=3 || Menuchoice !=4 )
							{
								System.out.println("\nNot a valid choice, Please choose a number between 1 & 5");
							}
							break;
						}
			}
		}
		while(Menuchoice !=5);
		scan.close();
		//scocket code her i assume?
*/	}

}
