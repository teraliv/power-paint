/*
 * TCSS 305 - Spring 2015
 * 
 * Assignment 5 - PowerPaint.
 * Alex Terikov
 */

package gui;

import actions.EllipseAction;
import actions.LineAction;
import actions.PencilAction;
import actions.RectangleAction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * The Graphical User Interface for the PowerPaint program.
 * 
 * @author Alex Terikov (teraliv@uw.edu)
 * @version May 7, 2015
 */
public class PowerPaintGUI implements PropertyChangeListener {
    
    /** Slider minimum value. */
    private static final int THICKNESS_MIN = 0;
    
    /** Slider maximum value. */
    private static final int THICKNESS_MAX = 20;
    
    /** Slider major spacing. */
    private static final int MAJOR_SPACING = 5;
    
    /** Slider minor spacing. */
    private static final int MINOR_SPACING = 1;
    
    /** Slider initial value position. */
    private static final int THICKNESS_INIT = 1;
    
    /** A drawing panel. */
    private PaintPanel myPanel;
    
    /** The window for this GUI. */
    private JFrame myFrame;
    
    /** The button that undo all shapes. */
    private JMenuItem myUndoAllButton;
    
    /** The button that undo last drawn shape. */
    private JMenuItem myUndoButton;
    
    /** The button that redo last undo shape. */
    private JMenuItem myRedoButton;
    
    /** The icon that show current color. */
    private final ColorIcon myColorIcon;
    
    /** A button group for radio buttons. */
    private final ButtonGroup myTopGroup;
    
    /** Top menu bar. */
    private final JMenu myToolsMenu;
    
    /** The image icon to show in the window title and about window. */
    private ImageIcon myImageIcon = new ImageIcon("./images/w.gif");
    
    /**
     * Constructs Graphical User Interface.
     */
    public PowerPaintGUI() {
        myColorIcon = new ColorIcon(Color.BLACK);
        myTopGroup = new ButtonGroup();
        myToolsMenu = new JMenu("Tools");
        
        setupGUI();
    }
    
    /**
     * Sets up all GUI components.
     */
    private void setupGUI() {
        myFrame = new JFrame("PowerPaint");
        final PowerPaintToolBar toolBar = new PowerPaintToolBar();
        final List<Action> toolActions = new ArrayList<Action>();
        
        myPanel = new PaintPanel();
        
        toolActions.add(new PencilAction(myPanel));
        toolActions.add(new LineAction(myPanel));
        toolActions.add(new RectangleAction(myPanel));
        toolActions.add(new EllipseAction(myPanel));
        
        // associate tool buttons with actions
        for (final Action action : toolActions) {
            createToolsMenuBarButton(action);
            toolBar.createToolBarButton(action);
        }
        
        // register this PowerPaintGUI class as listener for property changes from the panel
        myPanel.addPropertyChangeListener(this);
        
        myFrame.setIconImage(myImageIcon.getImage());
        myFrame.setJMenuBar(setupMenuBar());
        myFrame.add(myPanel, BorderLayout.CENTER);
        myFrame.add(toolBar, BorderLayout.SOUTH);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
    }
    
    /**
     * This method returns the menu bar for the panel.
     * 
     * @return returns menu bar.
     */
    private JMenuBar setupMenuBar() {
        
        final JMenuBar menuBar = new JMenuBar();
        
        final JMenu fileMenu = setupFileMenu();
        final JMenu optionsMenu = setupOptionsMenu();
        final JMenu helpMenu = setupHelpMenu();
        
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(myToolsMenu);
        menuBar.add(helpMenu);
        
        return menuBar;
    }
    
    /**
     * This method returns the file menu in the menu bar.
     * 
     * @return returns the file menu.
     */
    private JMenu setupFileMenu() {
        
        // File Menu
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // Undo all change button
        myUndoAllButton = new JMenuItem("Undo all changes");
        myUndoAllButton.setEnabled(false);
        myUndoAllButton.setMnemonic(KeyEvent.VK_U);
        fileMenu.add(myUndoAllButton);
       
        myUndoAllButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                if (theEvent.getSource() == myUndoAllButton) {
                    myPanel.clearPaintPanel(true);
                }
            }
        });
        
        fileMenu.addSeparator();
        
        // Undo button
        myUndoButton = new JMenuItem("Undo");
        myUndoButton.setMnemonic(KeyEvent.VK_N);
        myUndoButton.setAccelerator(KeyStroke.getKeyStroke(
                                    KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        
        myUndoButton.setEnabled(false);
        fileMenu.add(myUndoButton);
        
        myUndoButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (theEvent.getSource() == myUndoButton) {
                    myPanel.setUndo(true);
                }
            }
        });
        
        // Redo button
        myRedoButton = new JMenuItem("Redo");
        myRedoButton.setMnemonic(KeyEvent.VK_R);
        myRedoButton.setAccelerator(KeyStroke.getKeyStroke(
                                    KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        
        myRedoButton.setEnabled(false);
        fileMenu.add(myRedoButton);
        
        myRedoButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (theEvent.getSource() == myRedoButton) {
                    myPanel.setRedo(true);
                }
            }
        });
        
        fileMenu.addSeparator();
        
        // Exit button
        final JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        fileMenu.add(exit);
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                
                myFrame.dispose();
            }
        });
        
        return fileMenu;
    }
    
    /**
     * This method returns the option menu in the menu bar.
     * 
     * @return returns options menu.
     */
    private JMenu setupOptionsMenu() {
        
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        // Grid check box
        final JCheckBoxMenuItem gridButton = new JCheckBoxMenuItem("Grid");
        gridButton.setMnemonic(KeyEvent.VK_G);
        optionsMenu.add(gridButton);
        
        // Anonymous inner class to control check box selection.
        gridButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if ((theEvent.getSource() == gridButton) && (gridButton.isSelected())) {
                    myPanel.setGrid(true);                    
                } else {
                    myPanel.setGrid(false);
                }
            }
        });
        
        optionsMenu.addSeparator();
        
        // Thickness slider
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        optionsMenu.add(thicknessMenu);
        optionsMenu.addSeparator();
        
        final JSlider thicknessButton = new JSlider(JSlider.HORIZONTAL, 
                                          THICKNESS_MIN, THICKNESS_MAX, THICKNESS_INIT);
        thicknessMenu.add(thicknessButton);
        thicknessButton.setMajorTickSpacing(MAJOR_SPACING);
        thicknessButton.setMinorTickSpacing(MINOR_SPACING);
        thicknessButton.setPaintLabels(true);
        thicknessButton.setPaintTicks(true);
        
        // change listener for thickness slider
        thicknessButton.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int thickness = thicknessButton.getValue();
                
                if (thickness >= 0) {
                    myPanel.setThickness(thickness);   
                }
            }
        });
        
        // Color button
        final JMenuItem colorButton = new JMenuItem("Color...", myColorIcon);
        colorButton.setMnemonic(KeyEvent.VK_C);
        optionsMenu.add(colorButton);
        
        // anonymous action listener for color
        colorButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                final Color colorResult = 
                                JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                
                myColorIcon.setColor(colorResult);
                
                if (colorResult != null) {
                    myPanel.setColor(colorResult);
                }
                
            }
        });
        
        return optionsMenu;
    }
    
    /**
     * This method returns the help menu in the menu bar.
     * 
     * @return returns help menu.
     */
    private JMenu setupHelpMenu() {
        
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem aboutButton = new JMenuItem("About...");
        aboutButton.setMnemonic(KeyEvent.VK_A);
        helpMenu.add(aboutButton);
        
        aboutButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                
                if (theEvent.getSource() == aboutButton) {
                    
                    final Image image = myImageIcon.getImage();
                    final Image newImage = image.getScaledInstance(40, 30, 
                                               java.awt.Image.SCALE_SMOOTH);                 
                    myImageIcon = new ImageIcon(newImage);
                    
                    JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint \nSpring 2015", 
                                      "About", JOptionPane.INFORMATION_MESSAGE, myImageIcon);
                }
            }
        });
        
        return helpMenu;
    }
    
    /**
     * This method creates menu bar tool buttons.
     * 
     * @param theAction the Action to associate with the new button being created.
     */
    private void createToolsMenuBarButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        
        myTopGroup.add(createdButton);
        myToolsMenu.add(createdButton);
    }
    
    /**
     * This method handles property change events.
     * 
     * @param theEvent The property that handles this event.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        // Receive the state of myShapesList array in PaintPanel class
        // if the array is not empty set Undo & Undo All buttons enabled.
        if ("shapes".equals(theEvent.getPropertyName())) {
            final Boolean status = (Boolean) theEvent.getNewValue();
            
            myUndoAllButton.setEnabled(status);
            myUndoButton.setEnabled(status);
        }
        
        // The same behavior for myShapesRedoList array
        if ("shapesUndo".equals(theEvent.getPropertyName())) {
            final Boolean status = (Boolean) theEvent.getNewValue();
            
            myRedoButton.setEnabled(status);
        }
    }
    
}