package model;

public class Key extends Item {

	public Key(String id, String name) {
		super(id, name);
	}

	@Override
	public Item clone() {
		return new Key(this.id, this.name);
	}

	@Override
	public String getTexture() {
		return "res/key.png";
	}

}
