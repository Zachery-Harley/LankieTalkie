package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;

import tracking.gui.WindowManager;
import tracking.main.CVUtil;

public class BackgroundSubtraction extends Preprocesser{

	BackgroundSubtractorMOG2 subtractor;
	private int history = 5;
	private double threshhold = 30;
	private boolean shadowDetection = false;
	
	
	public BackgroundSubtraction() {
		super("Background Subtraction");
		this.subtractor = Video.createBackgroundSubtractorMOG2(history, threshhold, shadowDetection);
	}
	
	@Override
	public void showWindow() {
		super.showWindow();
		
		WindowManager.sliderShow("BackgroundSubtraction", "History", 1, 100, 5);
		WindowManager.sliderShow("BackgroundSubtraction", "Threshhold", 1, 100, 5);
		WindowManager.sliderShow("BackgroundSubtraction", "Shadow", 0, 1, 0);
	}
	
	public void updateValues() {
		boolean changed =false;
		this.history = WindowManager.getSliderValue("BackgroundSubtraction", "History");
		this.threshhold = WindowManager.getSliderValue("BackgroundSubtraction", "Threshhold");
		if (WindowManager.getSliderValue("BackgroundSubtraction", "Shadow") > 0) {
			if(!shadowDetection) changed = true; 
			shadowDetection = true;
		} else {
			if(shadowDetection) changed = true; 
			shadowDetection = false;
		}
		
		if(changed) {
			System.out.println("Hist: " + history + ", Thresh: " + threshhold + ", Shadows: " + shadowDetection);
			this.subtractor = Video.createBackgroundSubtractorMOG2(history, threshhold, shadowDetection);
		}
	}
	
	@Override
	public Mat process(Mat intput) {
		Mat output = new Mat();
		if(windowShown) updateValues();
		this.subtractor.apply(intput, output);
		return output;
	}
	
	
	
	
}
