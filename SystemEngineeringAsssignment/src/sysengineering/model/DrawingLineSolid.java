package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;



public class DrawingLineSolid extends DrawingLine{

	public DrawingLineSolid(DrawingRectangle rec1, DrawingRectangle rec2) {
		super(rec1.getCenter(), rec2.getCenter());
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
		
		g2.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
		
		this.drawFullArrow(this.getCenter(), g2);
		
		g2.drawString(this.getName(), (int) this.getCenter().getX(), (int) this.getCenter().getY());
	}

	
}
