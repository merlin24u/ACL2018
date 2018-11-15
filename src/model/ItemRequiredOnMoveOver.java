package model;

import java.awt.Point;
import java.util.ArrayList;

public class ItemRequiredOnMoveOver extends OnMoveOver {
	private ArrayList<Item> itemsRequired;
	private boolean removeRequirements;

	public ItemRequiredOnMoveOver(Map map, Point position, boolean isVisible, boolean isActivated,
			boolean isPersistingAfterActivation, ArrayList<Item> itemsRequired, boolean removeRequirements) {
		super(map, position, isVisible, isActivated, isPersistingAfterActivation);
		this.itemsRequired = itemsRequired;
		this.removeRequirements = removeRequirements;
	}

	@Override
	protected boolean hasRequirements(Character character) {
		if (character.isType("Player")) {
			ArrayList<Item> playerItems = new ArrayList<Item>();
			for (Item i : itemsRequired) {
				Item itemFound = ((Pacman) character).getItem(i.getId());
				if (itemFound == null)
					return false;
				else
					playerItems.add(itemFound);
			}

			if (removeRequirements) {
				for (Item i : playerItems) {
					((Pacman) character).removeItem(i);
				}
			}
			return true;
		}
		return false;
	}

}
