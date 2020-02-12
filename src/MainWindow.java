import javax.swing.JFrame;

public class MainWindow extends JFrame {
	PicJpanel pj;// panel
	int map[][] = new int[16][16]; // Use 2D arrays as chessboard
	// 1 is white, 2 is black. Black and white alternate, default to black.
	int flag = 2; 
	int winer = 0; // Provide winners.

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public MainWindow() {
		// Set the size, width and height of the form in pixels
		this.setSize(756, 780);
		//Set the main thread to shut down automatically when the form closes
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		//Set the position of the form in the middle of the display
		this.setLocationRelativeTo(null);
		this.setResizable(false);// Setting Form Fixed Size Invariant
		// Instead of enabling layout manager, change to manual layout.
		this.setLayout(null);
		this.setTitle("Gobang games.");

		pj = new PicJpanel(this);//Add panels to forms
		//Add a mouse listen event to the panel
		pj.addMouseListener(new MyMouse(this));
		this.add(pj);

		this.setVisible(true);// No Hidden Display
	}

	public static void main(String[] args) {
		new MainWindow();
	}
}
