package sysengineering.model;

import java.awt.Graphics;

public abstract class DrawingShapesPrimitive  {
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	abstract public java.awt.Rectangle getBoundingBox();



	abstract public void draw(Graphics g/*, int x, int y*/);



}
