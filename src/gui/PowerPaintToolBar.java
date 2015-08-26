/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package gui;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * JToolBar for the PowerPaint.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class PowerPaintToolBar extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 6246624583948874599L;
    
    /** A button group for radio buttons. */
    private final ButtonGroup myButtonGroup;
    
    /**
     * Constructs tool bar for PowerPaint.
     */
    public PowerPaintToolBar() {
        super();
        
        myButtonGroup = new ButtonGroup();
    }
    
    /**
     * Create a JToggleButton for the PowerPaint tool bar.
     * 
     * @param theAction to associate with the created JToggleButton.
     */
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        
        myButtonGroup.add(toggleButton);
        add(toggleButton);
    }
}
