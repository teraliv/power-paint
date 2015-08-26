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

import tools.Line;
import tools.Tool;

/**
 * The Action that occurs when the Line tool is selected.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class LineAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 4757602148100534862L;

    /** The initial point of the tool. */
    private static final Point2D.Double INITIAL_POINT = new Point2D.Double(0, 0);
    
    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    
    /** The tool that represents the Line. */
    private final Tool myTool;
    
   /**
     * Construct an Action of the Line tool.
     * 
     * @param thePanel a JPanel to associate with this Action.
     */
    public LineAction(final PaintPanel thePanel) {
        super("Line", new ImageIcon("./images/line_bw.gif"));
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "A Line");
        
        myPanel = thePanel;
        myTool = new Line(INITIAL_POINT, INITIAL_POINT);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myTool);
        myPanel.repaint();
    }
    
    /** 
     * This query returns the Line tool.
     * 
     * @return returns the Line tool.
     */
    public Tool getTool() {
        return myTool;
    }
}
