package sysengineering.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;



public abstract class DrawingRectangle extends DrawingShapesPrimitive {

	protected Point2D start;
	protected int width;
	protected int height;

	public DrawingRectangle(Point2D start) {
		this(start, 70);
	}
	
	public DrawingRectangle(Point2D start, int square) {
		this(start, square, square);
	}
	
	public DrawingRectangle(Point2D start , int width, int height) {
		this.start = start;
		this.width = width;
		this.height = height;
	}

	public Point2D getStart() {
		return start;
	}

	public Point2D getCenter() {
		return new Point2D.Double(this.start.getX() + this.width / 2, this.start.getY() + this.height / 2);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(new Point((int) Math.round(this.start.getX()), (int) Math.round(this.start.getY())), new Dimension(this.width, this.height));
	}

	public boolean contains(Point2D point) {
		return this.getBoundingBox().contains(point);
	}

	public boolean intersectsWith(DrawingRectangle rectangle) {
		Point2D p1 = rectangle.start;
		Point2D p2 = new Point2D.Double(rectangle.start.getX() + rectangle.width, rectangle.start.getY());
		Point2D p3 = new Point2D.Double(rectangle.start.getX() + rectangle.width, rectangle.start.getY() + rectangle.height);
		Point2D p4 = new Point2D.Double(rectangle.start.getX(), rectangle.start.getY() + rectangle.height);
		
		Point2D[] points = {p1, p2, p3, p4};
		
		boolean intersects = false;
		
		for(Point2D p: points) {
			if(this.contains(p)) {
				intersects = true;
				break;
			}
		}
		return intersects;
	}
}
