import java.util.*;
/**
 * Runs the game
 * @author Casy Adjei
 *@
 */
public class Game 
{
	public static Scanner x = new Scanner(System.in);
	int luck = (int)Math.ceil((Math.random()*5));
	static String name;
	static GamePlayer player = new GamePlayer();
	GameMenu menu = new GameMenu();
	static boolean isInGodmode = false;
	/**
	 * Starts the game.
	 */
	public void startGame()
	{
		waitForInput();
		System.out.println("        \"Welcome to prison escape!\"\n\n                                    \"...\"\n");
		waitForInput("noMess");
		System.out.println("        \"Your goal is to escape the prison by solving puzzles!\"\n\n        \"...\"\n");
		waitForInput("noMess");
		System.out.println("        \"In order to progress throught the game, you must ACT and USE.\"\n\n        \"...\"\n");
		waitForInput("noMess");
		System.out.println("        \"Act performs an action within a room, with or without an item\nUSE performs an action with an item, and is accesed from the inventory\"\n\n        \"...\"\n");
		waitForInput("noMess");
		System.out.println("If you ever get confused, try exploring, LOOKing, or reading the item tooltips from the inventory. Good luck!");
		
		System.out.println("________________________\nPrisoner\nVersion 0.5\n");
		
		waitForInput();
		name = askName();
		
		if(name.equals("GODMODE"))
		{
			debugMode();
		} else 
		{
			System.out.println("\n[ this is your menu. it can be accessed at most times ]");
			System.out.println("[ it provides helpful information such as the location and time ]");
			System.out.println("[ for now however, close it ]");
			menu.DisplayMenu();
			System.out.println("[your goal is to escape the facility. To begin, exit the unlocked cell]");
			menu.DisplayGUI();
			
		}
		
		
		
		
	}
	/**
	 * Allows for the entry of debug mode.
	 *  
	 *  This mode allows you to move around the world by calling the teleportTo(int x int y) method of the GamePlayer class.
	 *   Additionally, it skips the breif intoduction.
	 */
	public void debugMode()
	{
		System.out.println("============\n" + "you have entered debug mode" + "\n============\n");
		isInGodmode = true;
		menu.DisplayGUI();
		
	}
	/**
	 * The accessor method for the static godMode atribute.
	 * @return Returns true if the user is in debug mode.
	 * 
	 * Returns false otherwise
	 */
	public static boolean getGodMode()
	{
		return isInGodmode;
	}
	
	/**
	 * Waits for the user to press enter with a prompt
	 */
	public static void waitForInput()
	{
		System.out.println("[ press ENTER to continue ]");
		x.nextLine();
	}
	
	/**
	 * Provides an alternate waitForInput without the text "[ press ENTER to continue ]"
	 * @param noMessage If the method's parameter is equal to "noMess", no prompt message will appear.
	 */
	public void waitForInput(String noMessage)
	{
		if(noMessage.equals("noMess"))
			x.nextLine();
	}
	/**
	 * Asks the user for a name
	 * @return Returns the name the user requested.
	 */
	public String askName()
	{
		String thing;
		System.out.print("What is the name of the prisoner? (One word only)  :: ");
		thing = x.next();
		return thing;
	}
	/**
	 * Accessor method for the name variable
	 * @return Returns teh value of name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Congratulates the player and ends the game
	 */
	public static void Win()
	{
		System.out.println("\n\n\n\n\nYou escaped the prison! YOU WIN!");
		System.exit(0);
	}
	
}
