package model.item;

public abstract class Item {
	private String id;
	private String name;
	private boolean collected;

	public Item(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		collected = false;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setCollected() {
		collected = true;
	}

	public boolean isCollected() {
		return collected;
	}

	public abstract String getTexture();

	public abstract Item clone();
}
