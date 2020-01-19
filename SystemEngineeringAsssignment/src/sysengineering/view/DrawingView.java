package sysengineering.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import sysengineering.controller.DrawingController;
import sysengineering.model.DrawingDataObserver;
import sysengineering.model.DrawingShapesPrimitive;



public class DrawingView implements DrawingDataObserver{

	private DrawingController mDrawingController;
	private Frame mMainFrame;
	
	private ComponentsPanel mComponentsPanel;
	private DrawingPanel mDrawingPanel;
	
	public DrawingView(DrawingController _eaController) {
		mDrawingController = _eaController;
		this.drawWindow();
	}
	
	/**
	 * This method draws the main-frame to screen
	 */
	private void drawWindow() {
		mMainFrame = new Frame("System Engineering Assignment 5");
		mMainFrame.setSize(600, 400);
		mMainFrame.setMinimumSize(new Dimension(300, 200));
		mMainFrame.setMaximumSize(new Dimension(1920, 1920));
		//mMainFrame.addComponentListener(mGisController);
		
		mMainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mMainFrame.setVisible(false);
				System.exit(0);
			}
		});
		
		this.mComponentsPanel = ComponentsPanel.getSharedInstance(mDrawingController);
		
		mMainFrame.add(this.mComponentsPanel, BorderLayout.NORTH);
		
		this.mDrawingPanel = DrawingPanel.getSharedInstance(mDrawingController);
		
		mMainFrame.add(this.mDrawingPanel, BorderLayout.CENTER);
		
		mMainFrame.setVisible(true);
	}

	@Override
	public void update(LinkedList<DrawingShapesPrimitive> _data) {
		// TODO Auto-generated method stub
		this.mDrawingPanel.repaint();
		
	}
	
}
