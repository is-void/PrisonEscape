import java.util.Scanner;
/**
 * Controls most of the visible aspects of the game, as well as dealing with the majority of the user input
 * @author Casy Adjei
 *
 */
public class GameMenu
{
	
	Scanner x = new Scanner(System.in);
	
	/**
	 * Constuctor for the GameMenu class. Initializes inventory
	 */
	public GameMenu()
	{		
		for(int a = 0; a < 7; a++)
		{
			Game.player.setInventorySlot("[EMPTY]", a);
		}	
	}
	
	/**
	 * Adds an item to the inventory based off of player location and input
	 */
	public void PickItem()
	{
		if(Game.player.getRoom() == "Courtyard" || Game.player.getRoom() == "Unlocked Storage" || Game.player.getRoom() == "Unlocked Guard Meeting Room" || Game.player.getRoom() == "Library")
		{
			System.out.print(Game.player.getRoomItemsList());
			System.out.print("Type the item number you would like to take --> ");
			int index = x.nextInt();
			if(!Game.player.getIndividualRoomItems(index).equals("[EMPTY]") && !Game.player.getIndividualRoomItems(index).equals(""))
			{
				Game.player.setInventorySlot(Game.player.getIndividualRoomItems(index), Game.player.getItemIndexOf("[EMPTY]")); 
				System.out.println("You got the " + Game.player.getIndividualRoomItems(index) + ".");
				Game.player.removeItemFromRoom(index);
			}
			else
			{
				System.out.println("You can't get this!");
			}
		} else
		{
			System.out.println("There are no items in this room!\n");
		}
	}
	/**
	 * Changes the player location based off of their input and current location. 
	 * Respects boundries of the map.
	 */
	public void Move()
	{
		boolean canMoveNorth = true;
		boolean canMoveSouth = true;
		boolean canMoveEast = true;
		boolean canMoveWest = true;
		boolean moved = false;
		
		String northRoom = Game.player.checkRoom(Game.player.getLocationX(), Game.player.getLocationY()+1);
		String southRoom = Game.player.checkRoom(Game.player.getLocationX(), Game.player.getLocationY()-1);
		String eastRoom =  Game.player.checkRoom(Game.player.getLocationX()+1, Game.player.getLocationY());
		String westRoom = Game.player.checkRoom(Game.player.getLocationX()-1, Game.player.getLocationY());
		
		System.out.println("\n_______________________________________________________________________________________________");
		//North
		if(northRoom != "Wall" && northRoom != "Out of bounds!" && northRoom != "Cell" && northRoom != "Solidary Confinement")
		{
			
			if(northRoom == "Hallway" || northRoom == "Corridor")
			{
				System.out.println("To your north is a passage.");
			}else if(northRoom == "Backup Generator"  || northRoom == "Guard Meeting Room" || northRoom == "Weapons" || northRoom == "Generator" || northRoom == "Emergency Button")
			{
				System.out.println("To your north is a locked room" + "(" + northRoom + ")");
				canMoveNorth = false;
			} else
			{
				System.out.println("To your north is the " + northRoom);
			}
		}
		else
		{
			System.out.println("To your north is a wall.");
			canMoveNorth = false;
		}
		//South
			if(southRoom != "Wall" && southRoom != "Out of bounds!" && southRoom != "Solidary Confinement" && southRoom != "Courtyard" && southRoom != "Cell")
			{
				if(southRoom != "Hallway" && southRoom != "Corridor" && southRoom != "Dirt Path" && southRoom != "Lazer Wall" && southRoom != "Storage")
				{
					System.out.println("To your south is the " + southRoom);
				}else if(southRoom == "Lazer Wall")
				{
					System.out.println("To your south is a lazer wall: you cannot pass here yet");
					canMoveEast = false;
				} else
				{
					System.out.println("To your south is a passage");
				
				}
				if(southRoom == "Storage")
				{
				System.out.println("To your south is the locked storage room. The lock on the door looks flimsy. Maybe some rock or hammer might break it?");
				canMoveSouth = false;
				}
			
			} else 
			{
				System.out.println("To your south is a wall.");
				canMoveSouth = false;
			}
		//East
		if(eastRoom != "Wall" && eastRoom != "Out of bounds!" && eastRoom != "Guard Meeting Room" && eastRoom != "Weapons" && eastRoom != "Generator" && eastRoom != "Emergency Button" && eastRoom != "Backup Generator" && eastRoom != "Cell" && eastRoom != "Your Cell" && eastRoom != "Library")
		{
			if(eastRoom == "Lazer Wall")
			{
				System.out.println("To your east is a lazer wall: you cannot pass here yet");
				canMoveEast = false;
			} else if(eastRoom == "Hallway" || eastRoom == "Corridor" || eastRoom == "Dirt Path")
			{
				System.out.println("To your east is a passage");
			} else
			{
				System.out.println("To your east is the " + eastRoom);
			}
			
		} else if(eastRoom == "Exit")
		{
			System.out.println("To your east is the Exit, however it is locked and guarded.");
			canMoveEast = false;
		} else
		{
			System.out.println("To your east is a wall.");
			canMoveEast = false;
		}
			
		//West
		if(westRoom != "Wall" && westRoom != "Out of bounds!" && westRoom != "Guard Meeting Room" && westRoom != "Weapons" && westRoom != "Generator" && westRoom != "Emergency Button" && westRoom != "Backup Generator" && westRoom != "Cell" && westRoom != "Your Cell" && westRoom != "Library" && westRoom != "Solidary Confinement")
		{
			if(westRoom == "Hallway" || westRoom == "Corridor" || westRoom == "Dirt Path")
			{
				System.out.println("To your west is a passage");
			} else
			{
				System.out.println("To your west is the " + westRoom);
			}
		} else
		{
			System.out.println("To your west is a wall.");
			canMoveWest = false;
		}		
		
		//Ask For player input on where to move
		while(!moved)
		{
			System.out.print("Movement Options - ");
			if(canMoveNorth == true)
				System.out.print("Head north to " + northRoom + ": N ");
			if(canMoveEast == true)
				System.out.print("Head east to " + eastRoom + ": E ");
			if(canMoveSouth == true)
				System.out.print("Head south to " + southRoom + ": S ");
			if(canMoveWest == true)
				System.out.println("Head west to " + westRoom + ": W");
			
			switch(x.next())
			{
				case "N":
					if(canMoveNorth == true)
					{
						moved = true;
						Game.player.moveY(1);
						break;
					}
					else
						System.out.println("You cannot move in that direction!");
				
				case "S":
					if(canMoveSouth == true)
					{
						moved = true;
						Game.player.moveY(-1);
						break;
					}
					else
						System.out.println("You cannot move in that direction!");
					
				case "E":
					if(canMoveEast == true)
					{
						moved = true;
						Game.player.moveX(1);
						break;
					}
					else
						System.out.println("You cannot move in that direction!");
				
				case "W":
					if(canMoveWest == true)
					{
						moved = true;
						Game.player.moveX(-1);
						break;
					} else
						System.out.println("You cannot move in that direction!");
			}
		}
		DisplayGUI();
	}
	
		
	/**
	 * Displays the basic player menu.
	 * Shows the user some important infomation such as location
	 */
	public void DisplayMenu()
	{
		System.out.println("_______________________________________________________________________________________________");
		System.out.println("Menu");
		System.out.println("Prisoner ID     - 92320-A");
		System.out.println("Name            - " + Game.name);
		System.out.println("Location        - " + Game.player.getLocationX() + ", " + Game.player.getLocationY() + " (" + Game.player.getRoom() + ")");
		System.out.println("Time            - " + "June 8th, 3:00 PM");
		System.out.println("_______________________________________________________________________________________________");
		MenuOptions();
		
	}
	/**
	 * Displays the game's user interface. 
	 * Additionally, it takes the user's input and sends the player to a method that corresponds.
	 * Finally, it also detects when the conditions are met to end the game and calls the method WinGame()
	 */
	public void DisplayGUI()
	{
		if(Game.player.getRoom() == "Open Exit")
		{
			Game.Win();
		}
		if(Game.getGodMode() == true)
		{
			System.out.println("_______________________________________________________________________________________________");
			System.out.println("You are in [" + Game.player.getRoom() + "]");
			System.out.println("What would you like to do?");
			System.out.println("Choices - Open your inventory: INV " + "Look Around: LOOK " +  "Move to a diffrent area: MOVE " + "Open menu: MENU " + "Perform an action: ACT " + "Obtain an item: GET " + "Teleport (godmode only): TP");
			
			String choice = x.next();
			switch(choice)
			{
				case("INV"):
					DisplayInventory();		
					break;
				case("LOOK"):
					Look();
					break;
				case("MOVE"):
					Move();
					break;
				case("MENU"):
					DisplayMenu();	
					break;
				case("ACT"):
					Act();
					break;
				case("GET"):
					PickItem();
					break;
				case("TP"):
					System.out.println("Choose your teleport location --> X Y");
					Game.player.teleportTo(x.nextInt(), x.nextInt());
					break;
			}
			if(choice != "MOVE")
			{
				DisplayGUI();
			}
		} else
		{
			System.out.println("_______________________________________________________________________________________________");
			System.out.println("You are in [" + Game.player.getRoom() + "]");
			System.out.println("What would you like to do?");
			System.out.println("Choices - Open your inventory: INV " + "Look Around: LOOK " +  "Move to a diffrent area: MOVE " + "Open menu: MENU " + "Perform an action: ACT " + "Obtain an item: GET");
			
			String choice = x.next();
			switch(choice)
			{
				case("INV"):
					DisplayInventory();		
					break;
				case("LOOK"):
					Look();
					break;
				case("MOVE"):
					Move();
					break;
				case("MENU"):
					DisplayMenu();	
					break;
				case("ACT"):
					Act();
					break;
				case("GET"):
					PickItem();
					break;
					
			}
			if(choice != "MOVE")
			{
				DisplayGUI();
			}
		}
		
	}
	
	/**
	 * Displays the basic menu options
	 */
	public void MenuOptions()
	{
		System.out.println("Menu Options - Exit Menu: 0 " + "Open Inventory : 1 ");
		int choice = x.nextInt();
		switch(choice)
		{
			case(0):
				break;
			case(1):
				DisplayInventory();
				break;
			default:
				break;
				
				
		}
		
	}
	/**
	 * Provides details to the player about their location and gives the user some hints.
	 */
	public void Look()
	{
		System.out.println("_______________________________________________________________________________________________");
		switch(Game.player.getRoom())
		{
		
			case("Unlocked Guard Meeting Room"):
				System.out.println("The spacious room had many chairs and a table. \nIt seem like someone was just in the room, and left in a hurry.\nThere is a keycard sitting on the table.");
				break;
			case("Unlocked Storage"):
				System.out.println("The small closet contained some boxes and various tools. \nTucked away in the corner, there was a toolbox with a various tools inside, including a lockpick");
				break;
			case("Unlocked Weapons"):
				System.out.println("The room contained vairious weapons hanging from a translucent vault.\nLeaning against the vault however, was an exposed baton.");
				break;
			case("Unlocked Generator"):
				System.out.println("The generator sat in the middle of the room. \nIt was surrounded by cobwebs and dust, that seemed to suggest this room was rarely visted.\nIn the center of the generator, there was a large vent, big enough to stick an object into it.");
				break;
				
			case("Unlocked Emergency Button"):
				System.out.println("The small room contained only one object. A large red buton inside of a transluncent container titled: \"OPEN EXIT\".\nThe container is too firm to break with your bare hands, maybe a hammer or an axe could break it?");
				break;
				
			case("Unlocked Backup Generator"):
				System.out.println("The smallish generator was very protected. It seemed way too sturdy to potentially damage.\nOutside of an explosion, you couldn't think of a single way to damage it.");
				break;
			
			case("Library"):
				System.out.println("Tall library walls surround your vision. \nIn the center of the walls, there is a small table containg some arts and craft supplies.");
				break;
				
			case("Hallway"):
				System.out.println("You look down the long corridor.\n At one end there is the cafeteria, at the other there is the exit.\nYou notice that all of the locked rooms, execpt one door to the west, use a keycard.");
				break;
			
			case("Cafe"):
				System.out.println("There are a bunch of tables with plastic utensils and napkins.\nAt one end, there is a set of white doors that lead to the kitchen.");
				break;
			
			case("Your Cell"):
				System.out.println("The claustrophobic cell only houses a toilet, a small shelf with some books, and a bed.\nThere is a small opening for food next to a red button.");
				break;
			
			case("Corridor"):
				System.out.println("The corridor opens to the exit. \nThere are security cameras and motion dectoors dotted all over the passage.");
				break;
				
			case("Open Exit"):
				System.out.println("The door to your freedom.");
				break;
	
			case("Kitchen"):
				System.out.println("This room has various cooking supplies, a sink, and some cups.\nThere is some food, but it looks pretty disgusting.");
				break;
			
			case("Courtyard"):
				System.out.println("The large, grassy area has a couple of weight racks and a soccer field. \nThere is also a small tree with some crab apples growing from them.");
				break;
			
			case("Dirt Path"):
				System.out.println("The dirt path leads away from the courtyard and toward a small tower.\nThe tower seems to control the prisons security systems.");
				break;
				
			case("Security"):
				System.out.println("Inside the small room there seems to be a button that unlocks the exit.\nHowever, it is encased in a glass container, mabye some form of weapon could break the container?");
				break;
			
		}	
		System.out.println("_______________________________________________________________________________________________");
	}
	/**
	 * Allows the player to perform actions when certain conditions are met.
	 */
	public void Act()
	{
		if(Game.player.getRoom() != "Kitchen" && Game.player.getRoom() != "Security")
		{
			System.out.println("You cannot perform an action here!");
			DisplayGUI();
		} else if(Game.player.getRoom() == "Kitchen")
		{
			if(Game.player.checkInventory("Crabapple"))
			{
				System.out.println("You decided to blend the Crabapple");
				System.out.println("You got Crabapple Juice!");
				Game.player.setInventorySlot("Crabapple Juice", Game.player.getItemIndexOf("Crabapple"));
			}
		} else if(Game.player.getRoom() == "Security")
		{
			if(Game.player.getCanPressButton() == true)
			{
				System.out.println("You pressed the button and unlocked the emergency button!");
				Game.player.useItem(999);
				
			}
		} 
		
	}
	
	/**
	 * Displays the appropiate menu based off of menuType
	 * @param menuType The type of menu to be recived
	 */
	public void MenuOptions(String menuType)
	{
		if(menuType.equals("inventory"))
		{
			System.out.println("Inventory Options - Open Menu: 0 " + "Close Inventory : 1 " + "Select Item : 2 ");
			int choice = x.nextInt();
			switch(choice)
			{
				case(0):
					DisplayMenu();
					break;
				case(1):
					break;
				case(2):
					SelectItem();
					break;
				default:
					break;
					
					
			}
		}
		
	}
	/**
	 * Displays the inventory
	 */
	public void DisplayInventory()
	{
		System.out.println("_______________________________________________________________________________________________");
		System.out.println("Inventory ");
		for(int a = 0; a < 7; a++)
			System.out.println("Slot " + (a + 1) + " -- " +  Game.player.getInventorySlot(a) + " - " + Game.player.getInventoryInfo(a));
		System.out.println("_______________________________________________________________________________________________");
		MenuOptions("inventory");
	}
	/**
	 * Selects an item from the inventory
	 */
	public void SelectItem()
	{
		System.out.print("What item would you like to select? --> ");
		int item = x.nextInt() - 1 ;
		System.out.println("What would you like to do with " + Game.player.getInventorySlot(item) + "?");
		System.out.println("Item Options - " + "Use: 0 " + "Check Item: 1 " + "Exit Menu: 2");
		switch(x.nextInt())
		{
			case(0):
				Game.player.useItem(item);
				DisplayInventory();
				break;
				
			case(1):
				System.out.println(Game.player.getInventoryInfo(item));
				DisplayInventory();
				break;
			
			default:
				DisplayInventory();
				break;
				
			
		}
		
		
			
	}
}
