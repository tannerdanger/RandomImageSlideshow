import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public final class SlideShow extends JPanel implements ActionListener {
    private File directory;
    private File[] usedImages;
    private File[] availableImages;
    private JLabel currentImage = null;
    final int NEXT_IMAGE_KEY = 123;
    JButton go;
    JFileChooser chooser;
    String choosertitle;
    JFrame myFrame;



    protected SlideShow() {

        currentImage = new JLabel();
        currentImage.setVisible(false);
        add(currentImage);

       // currentImage.setHorizontalAlignment(SwingConstants.CENTER);
       // currentImage.setVerticalAlignment(SwingConstants.CENTER);

        initializeFrame();
        this.addKeyListener(new KeyListen());
        start();
        setBackground(Color.BLACK);

    }

    private void initializeFrame() {

        myFrame = new JFrame("");

        myFrame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        myFrame.getContentPane().add(this,"Center");
        myFrame.setSize(this.getPreferredSize());
        myFrame.setVisible(true);
        myFrame.add(this);
        myFrame.addKeyListener(new KeyListen());

    }

    private void start() {
        go = new JButton("Choose image directory...");
        go.addActionListener(this);
        add(go);


    }

    private void nextImage() {

            PixelImage tempPixImg = null;
            Random rand = new Random();
            int nextImg = rand.nextInt(availableImages.length-1);

            System.out.println(availableImages[nextImg].toString());


            final File currImgFile = availableImages[nextImg];
            try{
                tempPixImg = PixelImage.load(currImgFile);
                currentImage.setIcon(new ImageIcon(tempPixImg));


            } catch (final IOException exception){
                exception.printStackTrace();
            }

            if(!currentImage.isVisible()) {
                currentImage.setVisible(true);
                myFrame.setPreferredSize(getPreferredSize());
            }

            if(myFrame.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
                myFrame.pack();
                myFrame.setPreferredSize(getPreferredSize());
                myFrame.pack();
            }
    }

    public void actionPerformed(ActionEvent e) {
        int result;


        chooser = new JFileChooser(".");

        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());

            //What happens when directory is selected
            directory = chooser.getSelectedFile();
            go.setVisible(false);
            availableImages = directory.listFiles();



            System.out.println("getSelectedFile() : "

                    + directory);

        } else {
            System.out.println("No Selection ");
        }
    }


    public Dimension getPreferredSize() {

        if(availableImages == null)
            return new Dimension(200, 200);

        else {
            System.out.println("X:  " + currentImage.getWidth() + "   Y:   " + currentImage.getHeight());
            return new Dimension(currentImage.getWidth() + 20, currentImage.getHeight() + 40);
            //Dimension retD = new Dimension(currentImage.getX() + 10, currentImage.getY() + 10);


           // return (currentImage.getSize().);
        }


        //return new Dimension(200, 200);
    }



    protected class KeyListen extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent theEvent){

            super.keyPressed(theEvent);

            if(theEvent.getKeyCode() == NEXT_IMAGE_KEY) {
                System.out.println("Key pressed.");
                nextImage();
            }
        }

    }
}


