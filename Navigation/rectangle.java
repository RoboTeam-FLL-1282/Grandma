package Navigation;

import java.awt.Graphics2D;

public class rectangle {

	int x1;
	int y1;
	int x2;
	int y2;
	private int width;
	private int height;
	
	public rectangle() {}
	
	public rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		width = x2 - x1;
		height = y2 - y1;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawLine(x1, y1, x1 + width, y1);
		g2d.drawLine(x2, y2 - height, x1 + width, y2);
		g2d.drawLine(x2 - width, y2, x2, y2);
		g2d.drawLine(x1, y1, x1, y1 + height);
	}
	
	public void setBounds(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		width = x2 - x1;
		height = y2 - y1;
	}
	
}
