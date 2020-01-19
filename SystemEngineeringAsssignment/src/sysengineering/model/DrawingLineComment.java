package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;



public class DrawingLineComment extends DrawingLine {

	public DrawingLineComment(DrawingComment rec1, DrawingRectangle rec2) {
		super(rec2.getCenter(), rec1.getCenter());
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);

		this.drawArrow(this.getCenter(), g2);

		Stroke s = new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f);
		g2.setStroke(s);

		g2.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());

	}

}
