import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {

	int dx = 3;
	int dy = -3;
	boolean destroyed = false;
	boolean powerUp = false;
	Rectangle left, right;

	Block(int a, int b, int w, int h) {
		x = a;
		y = b;
		width = w;
		height = h;
		left = new Rectangle(a - 1, b + 1, 1, h - 2);
		right = new Rectangle(a + w, b + 1, 1, h - 2);
	}

	public void draw(Graphics g) {
		if (!destroyed)
			g.fillRect(x, y, width, height);
	}
}
