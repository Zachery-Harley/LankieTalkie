package tracking.gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSlider;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import tracking.main.CVUtil;

public class WindowManager {

	private static ArrayList<Window> windows = new ArrayList<Window>();
	private static ArrayList<SliderWindow> sliderWindows = new ArrayList<SliderWindow>();
	
	public static void imShow(String windowName, Image image) {
		BufferedImage imageBuffered = FXImage2BufferedImage(image);
		imShow(windowName, imageBuffered);
	}
	
	public static void imShow(String windowName, BufferedImage image) {
		Window window = getWindow(windowName);
		if(window == null) {
			window = newWindow(windowName);
		}
		window.setImage(image);
	}
	
	private static Window getWindow(String name) {
		for(Window window : windows) {
			if(window.getName().equals(name)) {
				return window;
			}
		}
		return null;
	}
	
	private static Window newWindow(String name) {
		Window newWindow = new Window(name);
		windows.add(newWindow);
		return newWindow;
	}
	
	private static BufferedImage FXImage2BufferedImage(Image image) {
		int width = (int)Math.ceil(image.getWidth());
		int height = (int)Math.ceil(image.getHeight());
		BufferedImage imageToShow = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		SwingFXUtils.fromFXImage(image, imageToShow);
		
		return imageToShow;
	}
	
	/////////////////////////////////
	/////// Slider windows //////////
	/////////////////////////////////
	
	public static void sliderShow(String windowName, String label, int min, int max, int defaultValue) {
		SliderWindow window = getSliderWindow(windowName);
		if(window == null) {
			window = new SliderWindow(windowName);
			sliderWindows.add(window);
		}
		window.addSlider(label, min, max, defaultValue);
	}
	
	public static int getSliderValue(String windowName, String label) {
		SliderWindow window = getSliderWindow(windowName);
		if(window == null) return 0;
		return window.getSlider(label).getValue();
	}
	
	private static SliderWindow getSliderWindow(String name) {
		for(SliderWindow window : sliderWindows) {
			if(window.getName().equals(name)) {
				return window;
			}
		}
		return null;
	}
	
} 
