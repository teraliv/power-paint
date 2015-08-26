/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * This abstract class represent default behavior for tool subclasses.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public abstract class AbstractTool implements Tool {
    
    /** The start point of the shape. */
    private Point2D myStartPoint;
    
    /** The end point of the shape. */
    private Point2D myEndPoint;
    
    /**
     * Initialize the instance fields.
     * 
     * @param theStartPoint the start point of the shape.
     * @param theEndPoint the end point of the shape.
     */
    protected AbstractTool(final Point2D theStartPoint, final Point2D theEndPoint) {
        myStartPoint = theStartPoint;
        myEndPoint = theEndPoint;
    }
    
    /** {@inheritDoc} */
    @Override
    public void setStartPoint(final Point2D theStartPoint) {
        myStartPoint = theStartPoint;
        
    }

    /** {@inheritDoc} */
    @Override
    public void setEndPoint(final Point2D theEndPoint) {
        myEndPoint = theEndPoint;
        
    }

    /** {@inheritDoc} */
    @Override
    public Point2D getStartPoint() {
        return myStartPoint;
    }
    
    /** {@inheritDoc} */
    @Override
    public Point2D getEndPoint() {
        return myEndPoint;
    }
    
    /** {@inheritDoc} */
    @Override
    public abstract Shape getShape();

}
