package main;

import java.awt.Graphics2D;

public interface Drawable {
	
	void update();
	
	void draw(Graphics2D g2);
	
	void reset();

}
