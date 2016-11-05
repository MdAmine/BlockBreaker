import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends Rectangle {


	int dx = 3;
	int dy = -3;
	
	Rectangle left, right;
	Rectangle one, two,three,four;

	Paddle(int a, int b, int w, int h) {
		x = a;
		y = b;
		width = w;
		height = h;
		left = new Rectangle(a - 1, b + 1, 1, h - 2);
		right = new Rectangle(a + w, b + 1, 1, h - 2);
		
		one = new Rectangle(a, b, w/4, h);
		two = new Rectangle(a+w/4, b, w/4, h);
		three = new Rectangle(a+w/2, b, w/4, h);
		four = new Rectangle(a+(3*w/4), b, w/4, h);
	}

	public void draw(Graphics g) {
			g.fillRect(x, y, width, height);
	}

}
