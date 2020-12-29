package minion;

public class Minion {
	
	private int level;
	private String storage;
	
	public Minion(int level, String storage) {
		setLevel(level);
		setStorage(storage);
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}
	
	

}
