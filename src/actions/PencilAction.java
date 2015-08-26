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

import tools.Pencil;
import tools.Tool;

/**
 * The Action that occurs when the Pencil tool is selected.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class PencilAction extends AbstractAction {

    /** A generated serialization ID.  */
    private static final long serialVersionUID = 5240338990448297612L;

    /** The JPanel to associate with this Action. */
    private final PaintPanel myPanel;
    
    /** The tool that represents the Pencil. */
    private final Tool myTool;
    
    /**
     * Construct an Action of the Pencil tool.
     * 
     * @param thePanel a JPanel to associate with this Action.
     */
    public PencilAction(final PaintPanel thePanel) {
        super("Pencil", new ImageIcon("./images/pencil_bw.gif"));
        
        myPanel = thePanel;
        myTool = new Pencil(new Point(0, 0), new Point(0, 0));
        
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "A Pencil");
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myTool);
        myPanel.repaint();
    }
    
    /** 
     * This query returns the Pencil tool.
     * 
     * @return returns the Pencil tool.
     */
    public Tool getTool() {
        return myTool;
    }
}
