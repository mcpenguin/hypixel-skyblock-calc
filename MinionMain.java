package minion;

import java.util.*;

public class MinionMain {
	
	public static Scanner kbd = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.print("Item name: ");
		String itemName = kbd.nextLine();
		
		System.out.print("Number of raw items to make enchanted: ");
		Float noOfItemsForEnch = kbd.nextFloat();
		
		System.out.print("Proportion of item in drops (from 0 to 1): ");
		Float proportionOfItemsInDrops = kbd.nextFloat();
		
		System.out.print("Time taken to harvest one item (adjusted accordingly): ");
		Double baseProductionTime = kbd.nextDouble();
		System.out.println();
		
		System.out.println("Storage type: ");
		System.out.println("Type 1: Wheat, Mushroom, Cow, Sheep, Spiders, Cave Spiders");
		System.out.println("Type 2: Chicken, Rabbit");
		System.out.println("Type 3: Fishing");
		System.out.println("Type 0: Anything else");
		System.out.print("Type your answer here: ");
		int storageType = kbd.nextInt();
		
		for (int i = 0; i<=30; i++)
			System.out.print("-");
		System.out.println();
		
		//minionList
		MinionContainer minionContainer = new MinionContainer(itemName, noOfItemsForEnch, storageType, proportionOfItemsInDrops, baseProductionTime);
		
		System.out.println("Here you will list your minions. Please use the following format: ");
		System.out.println("LEVEL (1-11), STORAGE (N(one), S(mall), M(edium), L(arge)");
		System.out.println("When you have finished listing down, type 'DONE'. ");
		System.out.println();
		kbd.nextLine();
		
		String minionLine = kbd.nextLine();
		int minionLevel = 0;
		String minionStorage = "";
		
		while (!minionLine.toLowerCase().equals("done")) {
			String[] minionChar = minionLine.split(" ");
			minionLevel = Integer.valueOf(minionChar[0]);
			minionStorage = minionChar[1];
			
			Minion m = new Minion(minionLevel, minionStorage);
			minionContainer.addMinion(m);
			
			minionLine = kbd.nextLine();
		}
		
		System.out.println();
		
		// debugging
//		for (Minion m: minionContainer.getMinionList()) {
//			System.out.println(m.getStorage());
//			System.out.println(minionContainer.getMinionStorage(m));
//		}
		
		System.out.println();
		System.out.println(minionContainer);
		

	}

}
