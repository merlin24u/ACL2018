package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class CollisionPainterResponsability {
	// Type de collision gérée
	protected ECollisionType collisionType;
	
	// Prochain responsable si la classe actuelle ne permet pas de gérer la collision donnée
	protected CollisionPainterResponsability successor;
	
	public CollisionPainterResponsability(ECollisionType collisionType)
	{
		this.collisionType = collisionType;
	}
	
	
	public void setSuccessor(CollisionPainterResponsability successor) 
	{
		this.successor = successor;
	}
	
	protected boolean isResponsible(int collisionValue) {
		return (this.collisionType.getValue() == collisionValue);
	}
	
	// Dessine la collision si il en est le responsable
	public abstract void drawCollision(Graphics2D crayon, int tileWidth, int tileHeight, int x, int y, int collisionValue);
}
