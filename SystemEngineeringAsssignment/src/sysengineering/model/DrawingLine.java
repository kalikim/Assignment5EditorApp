package sysengineering.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;



public abstract class DrawingLine extends DrawingShapesPrimitive {

	protected Point2D start;
	protected Point2D end;

	public DrawingLine(Point2D _start, Point2D _end) {
		this.start = _start;
		this.end = _end;
	}

	@Override
	public Rectangle getBoundingBox() {
		int startX = (int) this.start.getX();
		int startY = (int) this.start.getY();
		int width = (int) (this.end.getX() - this.start.getX());
		int height = (int) (this.end.getY() - this.end.getY());

		java.awt.Rectangle rec = new java.awt.Rectangle(startX, startY, width,
				height);
		return rec;
	}

	public boolean equals(DrawingLine line) {
		boolean straight = this.start.equals(line.start) && this.end.equals(line.end);
		
		return straight;
	}
	
	public Point2D getUnitVector() {
		Point2D vector = getVector();
		Double length = getLength();
		
		Double x = vector.getX() / length;
		Double y = vector.getY() / length;
		
		return new Point2D.Double(x, y);
	}
	
	public Double getLength() {
		Point2D vector = getVector();
		return Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
	}
	
	public Point2D getVector() {
		return new Point2D.Double(end.getX() - start.getX(), end.getY() - start.getY());
	}
	
	public Point2D getCenter() {
		Point2D vector = getVector();
		return new Point2D.Double(this.start.getX() + vector.getX() * 0.5, this.start.getY() + vector.getY() * 0.5);
	}
	
	public Point2D getArrowPointOnLine(Point2D p, Double length) {

		Point2D unitVector = this.getUnitVector();

		return new Point2D.Double(p.getX() - length * unitVector.getX(), p.getY() - length * unitVector.getY());
	}
	
	public Point2D rotateAround(Point2D center, Point2D point, double angle) {
		double tempx = center.getX() + (Math.cos(Math.toRadians(angle)) * (point.getX() - center.getX()) - Math.sin(Math.toRadians(angle)) * (point.getY() - center.getY()));
		double tempy = center.getY() + (Math.sin(Math.toRadians(angle)) * (point.getX() - center.getX()) + Math.cos(Math.toRadians(angle)) * (point.getY() - center.getY()));
		
		return new Point2D.Double(tempx, tempy);
	}
	
	protected void drawArrow(Point2D p, Graphics2D g2) {
		Point2D onLine = this.getArrowPointOnLine(p, 15.0);
		Point2D arrow1 = this.rotateAround(p, onLine, 15);
		Point2D arrow2 = this.rotateAround(p, onLine, -15);
		
		g2.drawLine((int) p.getX(), (int) p.getY(), (int) arrow1.getX(), (int) arrow1.getY());
		g2.drawLine((int) p.getX(), (int) p.getY(), (int) arrow2.getX(), (int) arrow2.getY());
		g2.drawLine((int) arrow1.getX(), (int) arrow1.getY(), (int) arrow2.getX(), (int) arrow2.getY());
	}
	
	protected void drawFullArrow(Point2D p, Graphics2D g2) {
		Point2D onLine = this.getArrowPointOnLine(p, 15.0);
		Point2D arrow1 = this.rotateAround(p, onLine, 15);
		Point2D arrow2 = this.rotateAround(p, onLine, -15);
		
		int[] xArr = {(int) p.getX(), (int) arrow1.getX(), (int) arrow2.getX()};
		int[] yArr = {(int) p.getY(), (int) arrow1.getY(), (int) arrow2.getY()};
		
		g2.fillPolygon(xArr, yArr, 3);
		

	}
}
