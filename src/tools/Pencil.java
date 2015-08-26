/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * This class creates new Pencil tool.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class Pencil extends AbstractTool {
    
    /** A GeneralPath object represents pencil drawing. */
    private GeneralPath myPath;
    
    
    /**
     * Constructs new Pencil with the start and end points.
     * 
     * @param theStartPoint the start point.
     * @param theEndPoint the end point.
     */
    public Pencil(final Point2D theStartPoint, final Point2D theEndPoint) {
        super(theStartPoint, theEndPoint);
        
        myPath = new GeneralPath();
        myPath.setWindingRule(GeneralPath.WIND_EVEN_ODD);
    }
    
    /** {@inheritDoc} */
    @Override
    public void setStartPoint(final Point2D theStartPoint) {
        myPath = new GeneralPath();
        myPath.moveTo(theStartPoint.getX(), theStartPoint.getY());
    }

    
    /** {@inheritDoc} */
    @Override
    public Shape getShape() {
    
        final Point2D endPoint = getEndPoint();
        myPath.lineTo(endPoint.getX(), endPoint.getY());
        
        return myPath;
    }

}