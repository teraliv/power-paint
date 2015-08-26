/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


/**
 * This class creates new Line tool.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class Line extends AbstractTool {
    
  /**
     * Constructs new Line with the start and end points.
     * 
     * @param theStartPoint the start point.
     * @param theEndPoint the end point.
     */
    public Line(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    /** {@inheritDoc} */
    @Override
    public Shape getShape() {
        
        final Line2D.Double line = new Line2D.Double(getStartPoint(), getEndPoint());
        
        return line;
    }

}