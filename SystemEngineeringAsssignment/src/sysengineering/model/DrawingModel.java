package sysengineering.model;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.LinkedList;



public class DrawingModel {

	// observer
		private LinkedList<DrawingDataObserver> mObservers;

		// data-model
		private LinkedList<DrawingShapesPrimitive> mGraphicPrimitivs;

		// singleton
		private static DrawingModel sharedInstance;

		private String mCurrentName;

		public static DrawingModel getSharedInstance() {
			if (DrawingModel.sharedInstance != null) {
				return DrawingModel.sharedInstance;
			} else {
				DrawingModel.sharedInstance = new DrawingModel();
				return DrawingModel.sharedInstance;
			}
		}

		private DrawingModel() {
			this.mObservers = new LinkedList<DrawingDataObserver>();
			this.mGraphicPrimitivs = new LinkedList<DrawingShapesPrimitive>();
			this.mCurrentName = "name";
		}

		/**
		 * This method adds an observer to the observer list observer gets called if
		 * data-model gets updated
		 * 
		 * @param _observer
		 *            data observer
		 */
		public void addObserver(DrawingDataObserver _observer) {
			this.mObservers.add(_observer);
		}

		/**
		 * This method adds a new rectangle to the data-model
		 * 
		 * @param _p
		 */
		public void addGraphics(DrawingShapesPrimitive _p) {
			_p.setName(mCurrentName);
			this.mGraphicPrimitivs.add(_p);
			for (int i = 0; i < this.mObservers.size(); i++) {
				this.mObservers.get(i).update(mGraphicPrimitivs);
			}
		}

		public boolean isPointInRectangle(Point2D point) {
			boolean isInRectangle = false;
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.contains(point)) {
						isInRectangle = true;
						break;
					}
				}
			}
			return isInRectangle;
		}

		public DrawingRectangle getDrawingRectangleForPoint(Point2D point) {
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.contains(point)) {
						return rec;
					}
				}
			}
			return null;
		}

		public void repaint(Graphics g) {
			for (int i = 0; i < this.mGraphicPrimitivs.size(); i++) {
				DrawingShapesPrimitive gp = this.mGraphicPrimitivs.get(i);
				if (gp instanceof DrawingLine) {
					gp.draw(g);
				}
			}
			for (int i = 0; i < this.mGraphicPrimitivs.size(); i++) {
				DrawingShapesPrimitive gp = this.mGraphicPrimitivs.get(i);
				if (gp instanceof DrawingRectangle) {
					gp.draw(g);
				}
			}
		}

		public boolean intersects(DrawingRectangle rectangle) {
			boolean intersects = false;
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingRectangle) {
					DrawingRectangle rec = (DrawingRectangle) gp;
					if (rec.intersectsWith(rectangle)) {
						intersects = true;
						break;
					}
				}
			}
			return intersects;
		}

		public boolean contains(DrawingLine line) {
			boolean contains = false;
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingLine) {
					DrawingLine l = (DrawingLine) gp;
					if (l.equals(line)) {
						contains = true;
						break;
					}
				}
			}
			return contains;
		}

		public int getNumberOfDrawingRectangles() {
			int count = 0;
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingRectangle) {
					count++;
				}
			}
			return count;
		}

		public int getNumberOfDrawingLines() {
			int count = 0;
			for (DrawingShapesPrimitive gp : this.mGraphicPrimitivs) {
				if (gp instanceof DrawingLine) {
					count++;
				}
			}
			return count;
		}

		public void setCurrentName(String _name) {
			this.mCurrentName = _name;
		}
}
