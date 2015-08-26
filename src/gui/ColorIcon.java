/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * This class creates new IconImage object with the give color.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class ColorIcon implements Icon {
    
    /** The height of the icon. */
    private static final int HEIGHT = 14;
    
    /** The width of the icon. */
    private static final int WIDTH = 14;
    
    /** The color of the icon. */
    private Color myColor;
    
    /**
     * Constructs new icon with the given color.
     * 
     * @param theColor the color of the icon.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    public void paintIcon(final Component theComponent, 
                          final Graphics theGraphics, final int theX, final int theY) {
        
        theGraphics.setColor(myColor);
        theGraphics.fillRect(theX, theY, WIDTH, HEIGHT);
        
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawRect(theX, theY, WIDTH, HEIGHT);
    }
    
    /** 
     * This query sets the color of the icon.
     * 
     * @param theColor the color if the icon.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

}
