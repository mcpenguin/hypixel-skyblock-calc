package minion;

import java.util.*;

public class MinionContainer {
	
	private static double k = 1.04772;
	private static double r = 0.88458;
	
	private static int[] type0 = {64, 192, 192, 384, 384, 576, 576, 768, 768, 960, 960};
	private static int[] type1 = {128, 256, 256, 384, 384, 576, 576, 768, 768, 960, 960};
	private static int[] type2 = {192, 320, 320, 448, 448, 576, 576, 768, 768, 960, 960};
	private static int[] type3 = {640, 640, 640, 704, 704, 768, 768, 832, 832, 896, 960};

	private String itemName;
	private ArrayList<Minion> minionList;
	private float noOfItemsForEnch;
	private int storageType;
	private float proportionOfItemsInDrops;
	private double baseProductionTime;
	
	public MinionContainer(String itemName, float noOfItemsForEnch, int storageType,
			float proportionOfItemsInDrops, double baseProductionTime) {
		super();
		this.itemName = itemName;
		this.noOfItemsForEnch = noOfItemsForEnch;
		this.storageType = storageType;
		this.proportionOfItemsInDrops = proportionOfItemsInDrops;
		this.baseProductionTime = baseProductionTime;
		this.minionList = new ArrayList<Minion>(22);
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ArrayList<Minion> getMinionList() {
		return minionList;
	}

	public void addMinion(Minion minion) {
		minionList.add(minion);
	}

	public float getNoOfItemsForEnch() {
		return noOfItemsForEnch;
	}

	public void setNoOfItemsForEnch(float noOfItemsForEnch) {
		this.noOfItemsForEnch = noOfItemsForEnch;
	}

	public int getStorageType() {
		return storageType;
	}

	public void setStorageType(int storageType) {
		this.storageType = storageType;
	}

	public float getProportionOfItemsInDrops() {
		return proportionOfItemsInDrops;
	}

	public void setProportionOfItemsInDrops(float proportionOfItemsInDrops) {
		this.proportionOfItemsInDrops = proportionOfItemsInDrops;
	}
	
	public double getBaseProductionTime() {
		return baseProductionTime;
	}

	public void setBaseProductionTime(double baseProductionTime) {
		this.baseProductionTime = baseProductionTime;
	}

	public void setMinionList(ArrayList<Minion> minionList) {
		this.minionList = minionList;
	}

	public double getMinionRate(Minion minion) {
		int l = minion.getLevel();
		double n = 0;
		if (l % 2 == 0)
			n = l/2-1;
		else
			n = (l-1)/2;
		
		double productionTime = 0;
		if (n != 0)
			productionTime = Math.round(k*baseProductionTime*Math.pow(r, n)); //in seconds
		else
			productionTime = baseProductionTime;
		
		double rate = 1800/productionTime; //in items per hour
		
		return rate;
	}
	
	public float getCollectiveMinionRate() {
		float total = 0;
		for (Minion m: minionList) {
			total+=getMinionRate(m);
		}
		return total;
	}
	
	public double getMinionStorage(Minion m) {
		String storageChest = m.getStorage().toUpperCase();
		int l = m.getLevel();
		
		int Ns = 0;
		switch (storageChest) {
			case "N":
				Ns = 0;
				break;
			case "S":
				Ns = 3;
				break;
			case "M":
				Ns = 9;
				break;
			case "L":
				Ns = 15;
				break;
		}
		
		int No = 0;
		switch (storageType) {
			case 0:
				No = type0[l-1];
				break;
			case 1:
				No = type1[l-1];
				break;
			case 2:
				No = type2[l-1];
				break;
			case 3:
				No = type3[l-1];
				break;
		}
		
		
		return (Ns*64 + No) * proportionOfItemsInDrops;
		
	}
	
	public double getCombinedMinionStorage() {
		int total = 0;
		for (Minion m: minionList) {
			total+=getMinionStorage(m);
		}
		return total;
	}
	
	public double getTimeBeforeFull(Minion m) {
		return getMinionStorage(m)/getMinionRate(m);
	}
	
	public double getMaxTimeBeforeFull() {
		double maxTime = 0;
		for (Minion m: minionList) {
			if (getTimeBeforeFull(m) > maxTime)
				maxTime = getTimeBeforeFull(m);
		}
		return maxTime;
	}
	
	public String toString() {
		String s1 = "Rate of production: " + getCollectiveMinionRate() + " items/hour";
		String s2 = "Maximum combined storage: " + getCombinedMinionStorage() + " " +
		itemName + "s, or " + getCombinedMinionStorage()/noOfItemsForEnch +
				" enchanted " + itemName + "s";
		String s3 = "Duration of minion until full: " + getMaxTimeBeforeFull() + " hours";
		return s1 + "\n" + s2 + "\n" + s3;
	}
	

}
