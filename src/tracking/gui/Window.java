package tracking.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.embed.swing.SwingFXUtils;

public class Window {

	private String name = "";
	private JFrame frame;
	private JLabel image;
	
	public Window(String name) {
		this.name = name;
		this.frame = new JFrame(name);
		this.frame.setVisible(true);
		frame.getContentPane().setLayout(new FlowLayout());
		this.image = new JLabel();
		frame.getContentPane().add(this.image);
	}
	
	public void setImage(BufferedImage image) {
		Image img = scaleImage(image);
		
		this.image.setIcon(new ImageIcon(img));
		this.frame.pack();
		if(!this.frame.isVisible())
			this.frame.setVisible(true);
	}
	
	private Image scaleImage(BufferedImage input) {
		double widthD = input.getWidth();
		double heightD = input.getHeight();
		double scale = widthD / 400;
		int width = (int)Math.ceil(widthD / scale);
		int height = (int)Math.ceil(heightD / scale);
		
		Image img = input.getScaledInstance(width, height, Image.SCALE_FAST);
		return img;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Hide the window
	 */
	public void hide() {
		this.frame.setVisible(false);
	}
	
	
	
	
	
}
