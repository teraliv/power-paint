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
 * Defines behavior common to all Tool subclasses.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public interface Tool {
    
    /**
     * This method sets up the start point of the shape.
     * 
     * @param theStartPoint the start point.
     */
    void setStartPoint(final Point2D theStartPoint);
    
    /**
     * This method sets up the end point of the shape.
     * 
     * @param theEndPoint the end point.
     */
    void setEndPoint(final Point2D theEndPoint);
    
    /**
     * This query returns the start point of the shape.
     * 
     * @return the start point.
     */
    Point2D getStartPoint();
    
    /**
     * This query returns the end point of the shape.
     * 
     * @return the end point.
     */
    Point2D getEndPoint();
    
    /**
     * This query returns the drawing shape.
     * 
     * @return shape
     */
    Shape getShape();
}
