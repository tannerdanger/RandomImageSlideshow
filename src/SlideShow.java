import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public final class SlideShow extends JFrame {
    protected ImagePanel leftPanel;
    protected ImagePanel midPanel;
    protected ImagePanel rightPanel;
    private JPanel basePanel;

    protected SlideShow(){

        createFrame();
        addPanels();

    }

    private void createFrame() {


        addWindowListener(
                new WindowAdapter() {
                    /**
                     * Invoked when a window is in the process of being closed.
                     * The close operation can be overridden at this point.
                     *
                     * @param e
                     */
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        System.exit(0);
                    }
                }
        );


        setSize(this.getPreferredSize());
        setVisible(true);
        addKeyListener(new KeyListen());
        setResizable(false);

    }
    private void addPanels() {
       // getContentPane().setLayout(new BorderLayout());
       // getContentPane().add(basePanel, BorderLayout.CENTER);
        basePanel = new JPanel(new GridLayout(0, 3));
        add(basePanel, BorderLayout.CENTER);
        leftPanel = new ImagePanel('Q');
        midPanel = new ImagePanel('W');
        rightPanel = new ImagePanel('E');


        basePanel.add(leftPanel);
        basePanel.add(midPanel);
        basePanel.add(rightPanel);

//        tempL.add(leftPanel, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 1500);
    }

    protected class KeyListen extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent theEvent){

            super.keyPressed(theEvent);

            if(theEvent.getKeyChar() == 'q' || theEvent.getKeyChar() == 'Q') {

                leftPanel.nextImage();

            }else if(theEvent.getKeyChar() == 'w' || theEvent.getKeyChar() == 'W') {

                midPanel.nextImage();

            }else if(theEvent.getKeyChar() == 'e' || theEvent.getKeyChar() == 'E') {

                rightPanel.nextImage();
            }

        }

    }
}


