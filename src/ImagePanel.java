import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class ImagePanel extends JPanel implements ActionListener {

	private JButton selectDir;
	private JLabel currentImage;
	private File[] availableImages;
	private JFileChooser chooser;


	protected ImagePanel(char theHotkey){
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		//setSize(new Dimension(200, 500));

		selectDir = new JButton("Select Directory for '" + theHotkey+ "' ");
		currentImage = new JLabel();
		currentImage.setVisible(false);

		selectDir.addActionListener(this);
		add(selectDir, BorderLayout.NORTH);
		add(currentImage, BorderLayout.CENTER);

	}

	protected void nextImage(){
		PixelImage tempPixImg;
		Random rand = new Random();
		int nextImg = rand.nextInt(availableImages.length);

		File tempFile = availableImages[nextImg];
		try{
			tempPixImg = PixelImage.load(tempFile);
			currentImage.setIcon(new ImageIcon(tempPixImg));
		}catch (final IOException exception){
			exception.printStackTrace();
		}
		if(!currentImage.isVisible()){
			currentImage.setVisible(true);
		}
	}


	/**
	 * Invoked when an action occurs.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser(".");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			availableImages = chooser.getSelectedFile().listFiles();
		}

		selectDir.setVisible(false);

	}
}
