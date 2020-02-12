import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * Mouse Operations Class
 */
class MyMouse extends MouseAdapter {
	MainWindow mj;

	public MyMouse(MainWindow mj) {
		super();// Calling the Construction Method of the Parent MouseAdapte
		this.mj = mj;// Initialization
	}

	/**
	 * Four directions of winning or losing are judged: left or right, up or down,
	 * left trapezoid , right trapezoid. Ten pieces in one direction of a chess
	 * piece are judged to satisfy five pieces'continuity.
	 */
	public boolean isWin(int x, int y, int map[][]) {
		// left or right
		int num = 1;
		for (int i = 0; i < 14; i++) {
			if (map[x][i] != 0) {
				if (map[x][i] == map[x][i + 1]) {
					num++;
					if (num >= 5) {
						return true;
					}
				} else {
					num = 1;
				}
			}
		}

		// up or down
		num = 1;
		for (int i = 0; i < 14; i++) {
			if (map[i][y] != 0) {
				if (map[i][y] == map[i + 1][y]) {
					num++;
					if (num >= 5) {
						return true;
					}
				} else {
					num = 1;
				}
			}
		}

		num = 1;
		// right trapezoid x-1 j+1
		for (int i = 0; i < map.length * 2 - 1; i++) {
			for (int j = 1; j < map.length; j++) {
				if (((i - j) >= 0) && ((i - j) < map.length)) {
					if (map[j][i - j] != 0) {
						if (map[j][i - j] == map[j - 1][i - j + 1]) {
							num++;
							if (num >= 5) {
								return true;
							}
						} else {
							num = 1;
						}
					}
				}
			}
		}

		num = 1;
		// left trapezoid x+1 y+1
		for (int i = -map.length; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				if (((i + j) >= 0) && ((i + j) < map.length)) {
					if (map[j][j + i] != 0) {
						if (map[j][i + j] == map[j + 1][i + j + 1]) {
							num++;
							if (num >= 5) {
								return true;
							}
						} else {
							num = 1;
						}
					}
				}
			}
		}
		return false;
	}

	public void mousePressed(MouseEvent e) {
		// Reset
		if (mj.winer != 0) {
			mj.setMap(new int[16][16]);
			mj.winer = 0;
			mj.pj.repaint();
			return;
		}
		int map[][] = mj.map;
		// Get the coordinates of the position under the mouse point
		Point p = e.getPoint();
		// The map value changes after clicking
		int x = (int) p.getX() / 50;
		int y = (int) p.getY() / 50;
		// Pixel position under mouse point
		System.out.println("Mouse click:\tx=" + p.getX() + "\ty=" + p.getY());
		// The row position of the array
		System.out.println("Location in the array:\tx=" + x + "\ty=" + y);

		// Change the color of the chess pieces to alternate black and white
		if (map[y][x] == 0) {
			map[y][x] = mj.flag;
		}

		// Change order
		if (mj.flag == 1) { // If it's white, change it to black.
			mj.flag = 2;
		} else if (mj.flag == 2) {
			mj.flag = 1;
		}

		MyMouse mouse = new MyMouse(mj);
		if (mouse.isWin(y, x, map)) {// Judgement of winning or losing
			if (map[y][x] == 1) {
				mj.winer = 1;
				mj.pj.repaint();
				// Pop up the winning message
				JOptionPane.showMessageDialog(mj, "Blue wins!");
				mj = null;
			}
			if (map[y][x] == 2) {
				mj.winer = 2;
				mj.pj.repaint();
				JOptionPane.showMessageDialog(mj, "Black wins!");
				mj = null;
			}
		}

		// Adjust Array Replotting
		mj.setMap(map);
		mj.pj.repaint();
	}
}
