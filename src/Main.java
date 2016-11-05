import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame fenetre = new JFrame("Block Breaker");

		BlockBreakerPanel panel = new BlockBreakerPanel();

		fenetre.getContentPane().add(panel);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(500, 680);
		fenetre.setResizable(false);
		fenetre.setVisible(true);

	}
}
