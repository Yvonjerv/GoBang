import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PicJpanel extends JPanel {
	MainWindow mj;

	public PicJpanel(MainWindow mj) {
		// Set the position, height and width of the panel in the form
		this.setBounds(0, 0, mj.getWidth(), mj.getHeight());
		this.mj = mj;
	}

	/**
	 * Painting component
	 */
	protected void paintComponent(Graphics g) {
		try {
			// Generated picture format
			BufferedImage bi = ImageIO.read(new File("img/bj.jpg"));
			g.drawImage(bi, 0, 0, this);// Draw an image in the front panel object
		} catch (IOException e) {
			e.getMessage();// Abnormal output
		}

		int map[][] = mj.map;// Initialization of 2D arrays

		g.setColor(Color.BLACK);// Default black

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				g.drawRect(50 * i, 50 * j, 50, 50);// 
				// 1 is white.
				if (map[i][j] == 1) {
					//15*15 chessboard, width and height are 50 pixels
					g.setColor(Color.BLUE);
					g.fillOval(50 * j, 50 * i, 50, 50);// Draw pieces
				}
				g.setColor(Color.BLACK);
				// 2 is black.
				if (map[i][j] == 2) {
					g.setColor(Color.BLACK);
					g.fillOval(50 * j, 50 * i, 50, 50);
				}
			}
		}
	}

}