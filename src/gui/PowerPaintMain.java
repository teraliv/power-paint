/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package gui;

import java.awt.EventQueue;

/**
 * Runs PowerPaint by instantiating and starting the PowerPaintGUI.
 * 
 * @author Ale Terikov (teraliv@uw.com)
 * @version May 7, 2015
 *
 */
public final class PowerPaintMain {
    
    /**
     * Private constructor to prevent external instantiation.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * The starter point for the PowerPaint program.
     * 
     * @param theArgs command line arguments.
     */
    public static void main(final String... theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI(); // create the graphical user interface
            }
        });
    }

}
