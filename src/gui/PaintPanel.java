/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.JPanel;

import tools.Pencil;
import tools.Tool;
import tools.ToolShape;


/**
 * This class creates a drawing panel on which graphics will be painted.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class PaintPanel extends JPanel {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 5875388237036053485L;

    /** The default size of the drawing panel. */
    private static final Dimension PANEL_DEFAULT_SIZE = new Dimension(400, 200);
    
    /** The initial thickness size. */
    private static final int THICKNESS_DEFAULT_SIZE = 1;
    
    /** The default size of grid. */
    private static final int GRID_SIZE = 10;
    
    /** The initial point of the tool. */
    private static final Point INITIAL_POINT = new Point(0, 0);
    
    /** The drawing tool that represents current tool. */
    private Tool myTool;
    
    /** The start point of the tool. */
    private Point myStartPosition;
    
    /** The end point of the tool. */
    private Point myEndPosition;
    
    /** The color of the current tool to draw on the paint panel. */
    private Color myColor;
    
    /** The default value for the grid. */
    private boolean myGridSelection;
    
    /** The thickness size of current tool. */
    private int myThickness;
    
    /** The dragging status of the current tool. */
    private boolean myDraggingStatus;
    
    /** A collection of drawn shapes. */
    private final Deque<ToolShape> myShapesList;
    
    /** A collection of drawn shapes for Redo action.  */
    private final Deque<ToolShape> myShapesRedoList;
    
    /**
     * Constructs a paint panel, and initializes all the fields.
     */
    public PaintPanel() {
        super();
        
        myDraggingStatus = false;
        myTool = new Pencil(INITIAL_POINT, INITIAL_POINT);
        
        myShapesList = new LinkedList<ToolShape>();
        myShapesRedoList = new LinkedList<ToolShape>();
        
        myThickness = THICKNESS_DEFAULT_SIZE;
        myGridSelection = false;
        
        setPreferredSize(PANEL_DEFAULT_SIZE);
        setBackground(Color.WHITE);
        
        setupListeners();
    }
    
    /**
     * This method sets up listeners and cursor for the paint panel.
     */
    private void setupListeners() {
        addMouseListener(new DrawingListener());
        addMouseMotionListener(new DrawingListener());
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    /**
     * This method sets the current drawing tool.
     *  
     * @param theTool the drawing tool.
     */
    public void setCurrentTool(final Tool theTool) {
        myTool = theTool;
    }
    
    /**
     * This method sets the current color of the tool.
     * 
     * @param theColor the color of the drawing tool.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * This method sets the current thickness size of drawing tool.
     * 
     * @param theThickness the thickness size of the drawing tool.
     */
    public void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    /**
     * This method sets the value to show or hide the grid.
     *  
     * @param theSelection the status of the grid.
     */
    public void setGrid(final boolean theSelection) {
        myGridSelection = theSelection;
    }
    
    /**
     * This methods clear the paint panel by removing all shapes from collections.
     * 
     * @param theSelection the selection status.
     */
    public void clearPaintPanel(final boolean theSelection) {
        if (theSelection) {
            myShapesList.clear();
            myShapesRedoList.clear();
            
            repaint();
        }
    }
    
    /**
     * This method undo last drawn shape.
     *  
     * @param theSelection the selection status.
     */
    public void setUndo(final boolean theSelection) {
        if (theSelection) {
            myShapesRedoList.addLast(myShapesList.getLast());
            myShapesList.removeLast();
            
            repaint();
        } 
    }
    
    /**
     * This method redo the last undo shape.
     * 
     * @param theSelection the selection status.
     */
    public void setRedo(final boolean theSelection) {
        if (theSelection) {
            myShapesList.addLast(myShapesRedoList.getLast());
            myShapesRedoList.removeLast();
            
            repaint();   
        }
    }
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        /* 
         * Here we listen for property changes associated with
         * ArrayList of Shapes. If the ArrayList is not empty 
         * fire the signal to PowerPaintGUI class and set enable
         * "Undo All Changes", "Undo", and "Redo" buttons.
         */
        firePropertyChange("shapes", null, !myShapesList.isEmpty());
        firePropertyChange("shapesUndo", null, !myShapesRedoList.isEmpty());
        
        
        // Here we draw all shapes on the paint panel using Deque
        for (final ToolShape shape : myShapesList) {
            g2d.setStroke(new BasicStroke(shape.getThickness()));
            g2d.setColor(shape.getColor());
            g2d.draw(shape.getShape());
        }
        
        // Here we draw current shape
        if (myDraggingStatus) {
            
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.setColor(myColor);
            
            if (myThickness != 0) {
                g2d.draw(myTool.getShape());
            }
            
        }
        
        // Draw grid if selected
        if (myGridSelection) {
            
            for (int i = 0; i < getHeight(); i++) {
                g2d.setStroke(new BasicStroke(1));
                g2d.setColor(Color.GRAY);
                
                final Line2D.Double horizontalLine = new 
                                Line2D.Double(0, i * GRID_SIZE, getWidth(), i * GRID_SIZE);
                final Line2D.Double verticalLine = new 
                                Line2D.Double(i * GRID_SIZE, 0, i * GRID_SIZE, getWidth());
                
                g2d.draw(horizontalLine);
                g2d.draw(verticalLine);
                
                repaint();
            }
        }
    }
    
    /**
     * Inner class that creates mouse events to draw on a paint panel.
     * 
     * @author Alex Terikov (teraliv@uw.edu)
     * @version May 7, 2015
     */
    class DrawingListener extends MouseAdapter {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            
            myDraggingStatus = true;
            myStartPosition = theEvent.getPoint();
            myEndPosition = myStartPosition;
            myTool.setStartPoint(myStartPosition);
            myTool.setEndPoint(myEndPosition);
            repaint();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            
            myEndPosition = theEvent.getPoint();
            myTool.setEndPoint(myEndPosition);
            
            if (myDraggingStatus && myThickness != 0) {
                myDraggingStatus = false;
                myShapesList.addLast(new ToolShape(myTool.getShape(), myThickness, myColor));
            }
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {

            myDraggingStatus = true;
            myEndPosition = theEvent.getPoint();
            myTool.setEndPoint(myEndPosition); 
            repaint();
        }
    }
}
