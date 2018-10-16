package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WallCollisionPainterResponsability extends CollisionPainterResponsability{


	public WallCollisionPainterResponsability(ECollisionType collisionType) {
		super(collisionType);
	}

	@Override
	public void drawCollision(Graphics2D crayon, int x, int y, int tileWidth, int tileHeight, int collisionValue) {
		if(this.isResponsible(collisionValue)) {
			crayon.setColor(Color.black);
			crayon.fillRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
		}else {
			if(this.successor != null) {
				this.successor.drawCollision(crayon, tileWidth, tileHeight, x, y, collisionValue);
			}
		}
	}
}
