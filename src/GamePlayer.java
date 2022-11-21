/**
 * Controls the majority of the in-game world, as well as dealing with player values.
 * This includes the inventory, player states, rooms, and items.
 * @author Casy Adjei
 *
 */
public class GamePlayer
{
	int locationX = 0;
	int locationY = 0;
	
	
	String[] inventory = new String[7];
	String[] y2 = {"Wall", "Wall", "Guard Meeting Room", "Weapons", "Generator", "Emergency Button", "Backup Generator", "Library", "Library", "Wall"};
	String[] y1 = {"Wall", "Hallway", "Hallway", "Hallway", "Hallway", "Hallway", "Hallway","Hallway", "Hallway", "Wall"};
	String[] y0 = {"Wall", "Cafe", "Cafe", "Solidary Confinement", "Cell", "Your Cell", "Cell", "Cell", "Corridor", "Exit"};
	String[] yn1 = {"Kitchen", "Cafe", "Cafe", "Courtyard", "Courtyard", "Courtyard", "Courtyard", "Courtyard", "Courtyard", "Wall"}; 
	String[] yn2 = {"Storage", "Wall", "Wall", "Wall", "Wall", "Wall", "Wall", "Dirt Path", "Lazer Wall", "Security"};
	
	String[] weaponsItems = {"Baton"};
	String[] libraryItems = {"Ruler"};
	String[] cafeItems = {"Plastic Fork"};
	String[] storageItems = {"Lockpick"};
	String[] guardMeetingRoomItems = {"Keycard"};
	String[] courtyardItems = {"Crabapple", "Rock"};
	
	boolean canPressButton = false;
	boolean generatorWorking = true;
	
	/**
	 * The accessor method for locationX
	 * @return The x coordinate of the player, represented by locationX.
	 */
	public int getLocationX()
	{
		return locationX;
	}
	
	/**
	 * The accessor method for locationY.
	 * @return The y coordinate of the player, represented by locationY.
	 */
	public int getLocationY()
	{
		return locationY;
	}
	
	/**
	 * Returns the value of canPressButton
	 * @return The value of canPressButton, which is true if the player completes all the nessasary tasks prior to attempting to press the button.
	 */
	public boolean getCanPressButton()
	{
		return canPressButton;
	}
	/**
	 * Translates the locationX and locationY values into room names.
	 * @return The room that corresponds to the current locationX and locationY values.
	 */
	public String getRoom()
	{
		if(locationY == 2)
		{
			return y2[locationX + 5];
		}
		else if(locationY == 1)
		{
			return y1[locationX + 5];
		}
		else if(locationY == 0)
		{
			return y0[locationX + 5];
		}
		else if(locationY == -1)
		{
			return yn1[locationX + 5];
		}
		else if(locationY == -2)
		{
			return yn2[locationX + 5];
		}
		else
		{
			return "Out of bounds!";
		}
	}
	
	/**
	 * Converts the raw coordinates of the room into a String. 
	 * Checks to make sure the values are in bound
	 * @param x The x location of the room.
	 * @param y The y location of the room.
	 * @return The room that corresponds to the x and y parameters.
	 */
	public String checkRoom(int x, int y)
	{
		if(y == 2 && (x <= 4  && x >= -5))
		{
			return y2[x + 5];
		}
		else if(y == 1 && (x <= 4  && x >= -5))
		{
			return y1[x + 5];
		}
		else if(y == 0 && (x <= 4  && x >= -5))
		{
			return y0[x + 5];
		}
		else if(y == -1 && (x <= 4  && x >= -5))
		{
			return yn1[x + 5];
		}
		else if(y == -2 && (x <= 4  && x >= -5))
		{
			return yn2[x + 5];
		}
		else
		{
			return "Out of bounds!";
		}
	}
	/**
	 * Changes the x location by changeX.
	 * @param changeX The amount the x location should be incremented by.
	 */
	public void moveX(int changeX)
	{
		locationX += changeX;
	}
	
	/**
	 * Changes the y location by changeY
	 * @param changeY The amount the y location should be incremented by.
	 */
	public void moveY(int changeY)
	{
		locationY += changeY;
	}
	
	/**
	 * Instanly moves the player to the corresponding x and y positions
	 * @param x The x location that the player will move to.
	 * @param y The y locaiton that the player will move to
	 */
	public void teleportTo(int x, int y)
	{
		locationX = x;
		locationY = y;
		System.out.println("You have teleported to " + getRoom());
	}
	
	/**
	 * 
	 * @param slot The specific slot that the method should attempt to find the item of.
	 * @return The inventory item that the method should return.
	 */
	public String getInventorySlot(int slot)
	{
		if(slot >= 0 && slot < inventory.length)
			return inventory[slot];
		else
			return inventory[0];
	}
	
	/**
	 * Sets the specified slot to the specified item.
	 * @param item The item that will be put in the inventory.
	 * @param slot The index the item will be at.
	 */
	public void setInventorySlot(String item, int slot)
	{
		inventory[slot] = item;
	}
	
	/** 
	 * Returns the inventory info that corresponds to the inventory slot and item.
	 * @param slot The specifc inventory slot to get info from.
	 * @return The inventory infomation that corresponds to the slot. 
	 */
	public String getInventoryInfo(int slot)
	{
		switch(inventory[slot])
		{
			
			case("Keycard"):
				return "can open mechanical doors";
			case("Baton"):
				return "might be useful for breaking things";
			case("Crabapple"):
				return "pretty sour, but really juicy.";
			case("Crabapple Juice"):
				return "could probably damage electronics. it also tastes terrible!{will dissapear after 1 use}";
			case("Rock"):
				return "a large chunk or rock. solid enough to break a small object{will dissapear after 1 use}";
			case("Lockpick"):
				return "a small metal construct that could open a non mechanically locked door{will dissapear after 1 use}";
			case("Ruler"):
			{
				return "a thin bar that can fit between small spaces. {will dissapear after 1 use}";
			}
			default:
				return "there is no item";
			
		
		}
	}
	/**
	 * Checks the inventory to see if a specific item is present.
	 * @param item The item the method is checking the inventory for.
	 * @return If the item is present or not.
	 * returns true if the item is in the inventory, returns false otherwise.
	 */
	public boolean checkInventory(String item)
	{
		for(int s = 0; s < inventory.length; s++)
		{
			if(inventory[s].equals(item))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Uses the item, depending on which room you are in, and if any conditions are met.
	 * @param item The index of the item you are using.
	 */
	public void useItem(int item)
	{
		if(getRoom().equals("Kitchen"))
		{
			if(inventory[item] == "Rock")
			{
				System.out.println("You smashed the lock and gained access to the storage room!");
				dropItem(item);
				yn2[0] = "Unlocked Storage";
			} else if (inventory[item] == "Crabapple")
			{
				System.out.println("Try ACTing instead.");
			}
		}
		
		if(getRoom().equals("Hallway")) 
		{
			if(locationY == 1 && locationX == -3)
			{
				if(inventory[item] == "Lockpick")
				{
					System.out.println("You unlocked the guard meeting room!");
					dropItem(item);
					y2[2] = "Unlocked Guard Meeting Room";
				} else 
				{
					System.out.println("Maybe try USEing a lockpick to open the door.");
				}
			}
			if(locationY == 1 && locationX == -1)
			{
				if(inventory[item] == "Keycard")
				{
					System.out.println("You unlocked the generator room!");
					y2[4] = "Unlocked Generator"; 
				} else
				{
					System.out.println("You need a keycard to open this door.");
				}
				
			}
			if(locationY == 1 && locationX == -2)
			{
				if(inventory[item] == "Keycard")
				{
					System.out.println("You unlocked the weapons room!");
					y2[3] = "Unlocked Weapons";
				} else
				{
					System.out.println("You need a keycard to open this door");
				}
			}
			if(locationY == 1 && locationX == 0)
			{
				if(inventory[item] == "Keycard")
				{
					System.out.println("You unlocked the emergency button room");
					y2[5] = "Unlocked Emergency Button";
				} else
				{
					System.out.println("You need a keycard to open this door");
				}
			}
			if(locationY == 1 && locationX == 1)
			{
				if(inventory[item] == "Keycard")
				{
					System.out.println("You unlocked the backup generator");
					y2[6] = "Unlocked Backup Generator";
				} else
				{
					System.out.println("You need a keycard to open this door");
				}
			}
		}
		
		if(getRoom().equals("Unlocked Generator"))
		{
			if(inventory[item] == "Ruler")
			{
				System.out.println("You broke the generator by jamming it's fans!");
				generatorWorking = false;
				dropItem(item);
			} else if(inventory[item] == "Crabapple Juice")
			{
				System.out.println("A good idea, but there is a better way to destroy the generator.");			}
		}
		if(getRoom().contentEquals("Unlocked Backup Generator"))
		{
			if(inventory[item] == "Crabapple Juice")
			{
				if(generatorWorking == false)
				{
					System.out.println("You broke the backup generator by jamming it's fans!\nMost of the prison no longer has power!");
					System.out.println("The Lazer Wall is no longer powered!");
					dropItem(item);
					y2[6] = "Dirt Path";
				} else
				{
					System.out.println("You need to break the normal generator first!");
				}
			} else if(generatorWorking == true)
			{
				System.out.println("You need an item to break the generator");
			}
			
			
		}
		
		if(getRoom().equals("Security"))
		{
			if(inventory[item] == "Baton")
			{
				System.out.println("You broke the glass! You can ACT and press the button !");
				canPressButton = true;
				
			} else if(item == 999)
			{
				y2[5] = "Unlocked Emergency Button";
			}
			{
				System.out.println("Maybe try USEing some weapon to break the glass");
			}
		}
		
		if(getRoom().equals("Unlocked Emergency Button"))
		{
			if(inventory[item] == "Baton")
			{
				System.out.println("You smashed the glass! All you have to do now is press the button and get out!");
				y0[9] = "Open Exit";
			}
		}
	}
	
	/**
	 * Accessor method for the canBreakButton boolean. 
	 * @return The value of canBreakButton.
	 */
	public boolean canBreakButton()
	{
		return canPressButton;
	}
	
	/**
	 * Removes the selected item from the inventory.
	 * @param itemIndex The index of the item that will be removed.
	 */
	public void dropItem(int itemIndex)
	{
		inventory[itemIndex] = "[EMPTY]";
	}

	/**
	 * Returns the index of the item.
	 * @param item The item to search for.
	 * @return The index of the specified item.
	 */
	public int getItemIndexOf(String item)
	{
		for(int s = 0; s < inventory.length; s++)
		{
			if(inventory[s].equals(item))
			{
				return s;
			}
		}
		return -10;
	}
	
	/**
	 * Removes an item from the current room.
	 * @param index The index of the item that will be removed.
	 */
	public void removeItemFromRoom(int index)
	{
		switch(getRoom())
		{
			case("Courtyard"):
				courtyardItems[index] = "[EMPTY]";
				break;
			case("Unlocked Storage"):
				storageItems[index] = "[EMPTY]";
				break;
			case("Unlocked Weapons"):
				weaponsItems[index] = "[EMPTY]";
				break;
			case("Unlocked Guard Meeting Room"):
				guardMeetingRoomItems[index] = "[EMPTY]";
				break;
		}
	}
	/**
	 * Returns a list of all of the item in the room.
	 * @return A numbered list of all the items.
	 */
	public String getRoomItemsList()
	{
		String output = "";
		String[] roomItems = null;
		switch(getRoom())
		{
			case("Courtyard"):
				roomItems = courtyardItems;
				break;
			case("Unlocked Storage"):
				roomItems = storageItems;
				break;
			case("Unlocked Weapons"):
				roomItems = weaponsItems;
				break;
			case("Unlocked Guard Meeting Room"):
				roomItems = guardMeetingRoomItems;
				break;
			case("Library"):
				roomItems = libraryItems;
				break;
		}
		for(int index = 0; index < roomItems.length; index++)
		{
			output += index + " - " + roomItems[index] + "\n";
		}

		return output;
	}
	
	/**
	 * The specific items from each room in a non list form.
	 * @param index
	 * The index of the item that is wished to be return.
	 * @return The item that corresponds to the room.
	 */
	public String getIndividualRoomItems(int index) 
	{
		switch(getRoom())
		{
			case("Courtyard"):
				if(index <= courtyardItems.length)
					return courtyardItems[index];
				break;
				
			case("Unlocked Storage"):
				if(index <= storageItems.length)
					return storageItems[index];
				break;
				
			case("Unlocked Weapons"):
				if(index <= weaponsItems.length)
					return weaponsItems[index];
				break;
			case("Unlocked Guard Meeting Room"):
				if(index <= guardMeetingRoomItems.length)
					return guardMeetingRoomItems[index];
				break;
			case("Library"):
				if(index <= libraryItems.length)
					return libraryItems[index];
					break;
		}
		return "";
	}
	
}
