package sysengineering.controller;

import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import sysengineering.model.DrawingComment;
import sysengineering.model.DrawingLine;
import sysengineering.model.DrawingLineComment;
import sysengineering.model.DrawingLineSolid;
import sysengineering.model.DrawingModel;
import sysengineering.model.DrawingRectangle;
import sysengineering.model.DrawingSquare;


public class DrawingController implements ActionListener, MouseListener, MouseMotionListener, DrawingTextFieldChangedListener{

	
	
	private DrawingModel mDrawingModel;
	private Point2D mMousePressedPoint;

	private enum DrawingMode {
		Rectangle, Line, Comment
	};

	private DrawingMode mDrawingMode;

	/**
	 * Standardkonstruktor
	 * 
	 * @param _m
	 *            the model of mvc for application
	 */
	public DrawingController(DrawingModel _m) {
		this.mDrawingModel = _m;
		this.mDrawingMode = DrawingMode.Rectangle;
	}

	/**
	 * This method returns the model of gis
	 * 
	 * @return gisModel
	 */
	public DrawingModel getEaModel() {
		return mDrawingModel;
	}

	/**
	 * This method sets the model of gis
	 * 
	 * @param gisModel
	 */
	public void setDrawingModel(DrawingModel _drawingModel) {
		this.mDrawingModel = _drawingModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Line") {
			System.out.println("Line selected!");
			this.mDrawingMode = DrawingMode.Line;
		} else if (e.getActionCommand() == "Rectangle") {
			System.out.println("Rectangle selected!");
			this.mDrawingMode = DrawingMode.Rectangle;
		} else if (e.getActionCommand() == "Comment") {
			System.out.println("Comment selected!");
			this.mDrawingMode = DrawingMode.Comment;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse clicked at: x:" + e.getX() + " y:" + e.getY());

		if (this.mDrawingMode.equals(DrawingMode.Rectangle)) {
			int offset = 70 / 2;
			Point newPoint = new Point(e.getX() - offset, e.getY() - offset);
			DrawingRectangle rec = new DrawingSquare(newPoint);
			if (!this.mDrawingModel.intersects(rec)) {
				this.mDrawingModel.addGraphics(rec);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse pressed at: x:" + e.getX() + " y:" + e.getY());
		
		if (this.mDrawingMode.equals(DrawingMode.Line) || this.mDrawingMode.equals(DrawingMode.Comment)) {
			if (this.mDrawingModel.isPointInRectangle(e.getPoint())) {
				DrawingRectangle rec = this.mDrawingModel.getDrawingRectangleForPoint(e.getPoint());
				this.mMousePressedPoint = rec.getCenter();
			} else {
				this.mMousePressedPoint = null;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse released at: x:" + e.getX() + " y:" + e.getY());
		
		if (this.mDrawingMode.equals(DrawingMode.Line) && this.mMousePressedPoint != null) {
			if (this.mDrawingModel.isPointInRectangle(e.getPoint())) {
				DrawingRectangle rec1 = this.mDrawingModel.getDrawingRectangleForPoint(mMousePressedPoint);
				DrawingRectangle rec2 = this.mDrawingModel.getDrawingRectangleForPoint(e.getPoint());
				if (rec1 != null && rec2 != null) {
					if (!(rec1 instanceof DrawingComment || rec2 instanceof DrawingComment)) {
						DrawingLine line = new DrawingLineSolid(rec1, rec2);
						if (!this.mDrawingModel.contains(line)) {
							this.mDrawingModel.addGraphics(line);
						}
					}
				}
			}
		} else if (this.mDrawingMode.equals(DrawingMode.Comment) && this.mMousePressedPoint != null) {
			int offset = 70 / 2;
			Point newPoint = new Point(e.getX() - offset - offset, e.getY() - offset);
			DrawingRectangle rec1 = this.mDrawingModel.getDrawingRectangleForPoint(mMousePressedPoint);
			if (rec1 instanceof DrawingSquare) {
				DrawingComment comm = new DrawingComment(newPoint, 70 * 2 , 70);
				
				if (!this.mDrawingModel.intersects(comm)) {
					this.mDrawingModel.addGraphics(comm);
					this.mDrawingModel.addGraphics(new DrawingLineComment(comm, rec1));
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse entered at: x:" + e.getX() + " y:" + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse exited at: x:" + e.getX() + " y:" + e.getY());

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse dragged at: x:" + e.getX() + " y:" + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse moved at: x:" + e.getX() + " y:" + e.getY());
	}

	@Override
	public void textFieldChanged(TextField textField) {
		this.mDrawingModel.setCurrentName(textField.getText());
	}

}
