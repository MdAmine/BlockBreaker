import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener {
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> balls = new ArrayList<Block>();
	ArrayList<Block> powerUp = new ArrayList<Block>();

	Paddle paddle;
	Thread t;
	Animate animate;

	BlockBreakerPanel() {

		paddle = new Paddle(175, 560, 100, 15);

		addKeyListener(this);
		setFocusable(true);

		balls.add(new Block(200, 400, 10, 10));

		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 0, 50, 20));
		}
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 25, 50, 20));
		}
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 50, 50, 20));
		}
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 75, 50, 20));
		}
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 100, 50, 20));
		}
		for (int i = 0; i < 9; i++) {
			blocks.add(new Block((i * 55 + 2), 125, 50, 20));
		}

		Random rand = new Random();

		for (int i = 0; i < 15; i++) {
			blocks.get(rand.nextInt(32)).powerUp = true;
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Block b : blocks) {
			b.draw(g);
		}
		for (Block b : balls) {
			b.draw(g);
		}
		for (Block p : powerUp) {
			p.draw(g);
		}
		paddle.draw(g);

	}

	public void update() {
		for (Block p : powerUp) {
			p.y += 1;
			if (p.intersects(paddle) && !p.destroyed) {
				p.destroyed = true;
				balls.add(new Block(paddle.dx + 100, 400, 10, 10));
			}
		}

		for (Block ba : balls) {
			ba.x += ba.dx;
			if (ba.x > (getWidth() - 15) && ba.dx > 0 || ba.x < 0)
				ba.dx *= -1;
			
			if (ba.y < 0 || ba.intersects(paddle.one) && ba.dx>0){
				ba.dy *= -1;
				ba.dx *= -1;
			}
			else if (ba.y < 0 || ba.intersects(paddle.four) && ba.dx<0){
				ba.dy *= -1;
				ba.dx *= -1;
			}
			else if(ba.y < 0 || ba.intersects(paddle)){
				ba.dy *= -1;
			}
				
			ba.y += ba.dy;

			for (Block b : blocks) {
				if ((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed) {
					ba.dx *= -1;
					b.destroyed = true;
					if (b.powerUp) {
						powerUp.add(new Block(b.x, b.y, 25, 19));
					}
				}

				else if (b.intersects(ba) && !b.destroyed) {
					b.destroyed = true;
					ba.dy *= -1;
					if (b.powerUp) {
						powerUp.add(new Block(b.x, b.y, 25, 19));
					}
				}
			}
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			animate = new Animate(this);
			t = new Thread(animate);
			t.start();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= 40;
			paddle.one.x -= 40;
			paddle.two.x -= 40;
			paddle.three.x -= 40;
			paddle.four.x -= 40;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 40;
			paddle.one.x += 40;
			paddle.two.x += 40;
			paddle.three.x += 40;
			paddle.four.x += 40;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
