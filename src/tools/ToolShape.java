/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * This class creates new shape object with the give shape, thickness, and color.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class ToolShape {
    
    /** The shape to draw. */
    private final Shape myShape;
    
    /** The thickness size of the shape. */
    private final int myThickness;
    
    /** The color of the shape. */
    private final Color myColor;
    
    /**
     * Constructs a new drawing shape object with the given shape, thickness, and color. 
     * 
     * @param theShape the drawing shape.
     * @param theThickness the thickness of this shape.
     * @param theColor the color of this shape.
     */
    public ToolShape(final Shape theShape, final int theThickness, final Color theColor) {
        myShape = theShape;
        myThickness = theThickness;
        myColor = theColor;
    }
    
    /**
     * This query returns the drawing shape.
     * 
     * @return  returns the current shape.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * This query returns the thickness of the shape.
     * 
     * @return returns the thickness for this shape.
     */
    public int getThickness() {
        return myThickness;
    }
    
    /**
     * This query returns the color of the shape.
     * 
     * @return returns the color for this shape.
     */
    public Color getColor() {
        return myColor;
    }

}
