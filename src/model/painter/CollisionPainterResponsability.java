package model.painter;

import java.awt.Graphics2D;

import model.movable.collision.ECollisionType;

public abstract class CollisionPainterResponsability {
	// Type de collision geree
	protected ECollisionType collisionType;

	// Prochain responsable si la classe actuelle ne permet pas de gerer la
	// collision donnee

	protected CollisionPainterResponsability successor;

	public CollisionPainterResponsability(ECollisionType collisionType) {
		this.collisionType = collisionType;
	}

	
	public void setSuccessor(CollisionPainterResponsability successor) {
		this.successor = successor; 
	}

	protected boolean isResponsible(int collisionValue) {
		return (this.collisionType.getValue() == collisionValue);
	}

	// Dessine la collision si il en est le responsable
	public abstract void drawCollision(Graphics2D crayon,int x, int y,
			int drawOffsetX, int drawOffsetY,
			int tileWidth, int tileHeight, 
			int collisionValue);
}
