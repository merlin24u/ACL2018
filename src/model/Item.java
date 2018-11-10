package model;

public abstract class Item {
	protected String id;
	protected String name;
	
	public Item(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String getTexture();
	
	public abstract Item clone();
}
