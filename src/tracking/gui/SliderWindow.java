package tracking.gui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class SliderWindow {
	
	private String name = "";
	private JFrame frame;
	private ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	
	
	public SliderWindow(String name) {
		this.name = name;
		this.frame = new JFrame(name);
		this.frame.setVisible(true);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
	}
	
	/**
	 * Get a slider with the given label, if no slider with a matching label
	 * is found then null is returned
	 * @param label The name of the slider wanted
	 * @return 
	 */
	public JSlider getSlider(String label) {
		for(JSlider slider : sliders) {
			if(slider.getName().equals(label)) return slider;
		}
		return null;
	}
	
	public void addSlider(String label, int min, int max, int defaultValue) {
		JSlider slider = getSlider(label);
		if(slider != null) {
			System.err.println("Slider: " + label + " already exsists!");
		} else {
			slider = new JSlider(min, max, defaultValue);
			slider.setName(label);
			slider.setBorder(BorderFactory.createTitledBorder(label));
			slider.setMajorTickSpacing(1);
			slider.setPaintTicks(true);
			sliders.add(slider);
			frame.getContentPane().add(slider);
			frame.pack();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
