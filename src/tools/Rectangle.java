/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class creates new Rectangle tool.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class Rectangle extends AbstractTool {
    
    /**
     * Constructs new rectangle.
     * 
     * @param theStartPoint start point.
     * @param theEndPoint end point.
     */
    public Rectangle(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    /** {@inheritDoc} */
    @Override
    public Shape getShape() {
        final Rectangle2D.Double rectangle = new Rectangle2D.Double();
        rectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        
        return rectangle;
    }
}
