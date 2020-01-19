package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;



public class DrawingSquare extends DrawingRectangle {
	
	public DrawingSquare(Point2D start) {
		super(start, 70);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);

		Stroke s = new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 1.0f }, // Dash pattern
				0.0f);
		g2.setStroke(s);

		int x = (int) this.start.getX();
		int y = (int) this.start.getY();
				
		g2.setColor(Color.gray);
		g2.fillRect(x, y, this.width, this.height);
		g2.setColor(Color.black);
		
		g2.drawRect(x, y, this.width, this.height);
		g2.drawString(this.getName(), x + 5, y - 5);
	}

}
