package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import texture.TextureFactory;

public class WallCollisionPainterResponsability extends CollisionPainterResponsability {

	public WallCollisionPainterResponsability(ECollisionType collisionType) {
		super(collisionType);
	}

	@Override
	public void drawCollision(Graphics2D crayon, int x, int y, int tileWidth, int tileHeight, int collisionValue) {
		if (this.isResponsible(collisionValue)) {
			try {
				Image img = TextureFactory.getInstance().getWall();
				crayon.drawImage(img, x * tileWidth, y * tileHeight, tileWidth, tileHeight, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
