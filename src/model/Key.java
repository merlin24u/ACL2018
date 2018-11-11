package model;

public class Key extends Item {

	public Key(String id, String name) {
		super(id, name);
	}

	@Override
	public Item clone() {
		return new Key(super.getId(), super.getName());
	}

	@Override
	public String getTexture() {
		return "key";
	}

}
