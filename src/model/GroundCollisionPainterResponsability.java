package model;

import java.awt.Graphics2D;
import java.awt.Image;

import texture.TextureFactory;

public class GroundCollisionPainterResponsability extends
CollisionPainterResponsability {

public GroundCollisionPainterResponsability(ECollisionType collisionType) {
super(collisionType);
}

@Override
public void drawCollision(Graphics2D crayon, int x, int y, int tileWidth,
	int tileHeight, int collisionValue) {
if (this.isResponsible(collisionValue)) {
	try {
		Image img = TextureFactory.getInstance().get("ground");
		crayon.drawImage(img, x * tileWidth, y * tileHeight, tileWidth,
				tileHeight, null);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
}
