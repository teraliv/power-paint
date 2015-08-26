/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package actions;

import gui.PaintPanel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tools.Rectangle;
import tools.Tool;

/**
 * The Action that occurs when the Rectangle tool is selected.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class RectangleAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 5912393075059647325L;

    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    
    /** The tool that represents the Rectangle. */
    private final Tool myTool;
    
    /**
     * Construct an Action of the Rectangle tool.
     * 
     * @param thePanel a JPanel to associate with this Action.
     */
    public RectangleAction(final PaintPanel thePanel) {
        super("Rectangle", new ImageIcon("./images/rectangle_bw.gif"));
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "A Rectangle");
        
        myPanel = thePanel;
        myTool = new Rectangle(new Point(0, 0), new Point(0, 0));
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myTool);
        myPanel.repaint();
    }
    
    /** 
     * This query returns the Rectangle tool.
     * 
     * @return returns the Rectangle tool.
     */
    public Tool getTool() {
        return myTool;
    }
}
