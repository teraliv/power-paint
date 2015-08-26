/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package actions;

import gui.PaintPanel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tools.Ellipse;
import tools.Tool;

/**
 * The Action that occurs when the Ellipse tool is selected.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class EllipseAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -2127311033567909957L;

    /** The initial point of the tool. */
    private static final Point2D.Double INITIAL_POINT = new Point2D.Double(0, 0);
    
    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    
    /** The tool that represents the Ellipse. */
    private final Tool myTool;
    
    /**
     * Construct an Action of the Ellipse tool.
     * 
     * @param thePanel a JPanel to associate with this Action.
     */
    public EllipseAction(final PaintPanel thePanel) {
        super("Ellipse", new ImageIcon("./images/ellipse_bw.gif"));
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "A Ellipse");
        
        myPanel = thePanel;
        myTool = new Ellipse(INITIAL_POINT, INITIAL_POINT);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myTool);
        myPanel.repaint();
    }
    
    /** 
     * This query returns the Ellipse tool.
     * 
     * @return returns the Ellipse tool.
     */
    public Tool getTool() {
        return myTool;
    }
    
}
