package model.painter;

import java.awt.Graphics2D;
import java.awt.Image;

import model.movable.collision.ECollisionType;
import texture.TextureFactory;

public class GroundCollisionPainterResponsability extends
CollisionPainterResponsability {

public GroundCollisionPainterResponsability(ECollisionType collisionType) {
super(collisionType);
}

@Override
public void drawCollision(Graphics2D crayon, int x, int y, 
	int drawOffsetX, int drawOffsetY,
	int tileWidth, int tileHeight,
	int collisionValue) {
if (this.isResponsible(collisionValue)) {
	try {
		Image img = TextureFactory.getInstance().get("ground");
		crayon.drawImage(img, drawOffsetX+x * tileWidth, drawOffsetY+y * tileHeight, tileWidth,
				tileHeight, null);
	} catch (Exception e) {
		e.printStackTrace();
	}
}else if(successor != null) {
	successor.drawCollision(crayon, x, y, drawOffsetX, drawOffsetY, tileWidth, tileHeight, collisionValue);
}
}
}
