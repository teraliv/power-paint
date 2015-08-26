/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class creates new Ellipse tool.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class Ellipse extends AbstractTool {
    
    /**
     * Constructs new Ellipse with the start and end points.
     * 
     * @param theStartPoint the start point.
     * @param theEndPoint the end point.
     */
    public Ellipse(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    /** {@inheritDoc} */
    @Override
    public Shape getShape() {
        final Ellipse2D.Double ellipse = new Ellipse2D.Double();
        ellipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        
        return ellipse;
    }

}
