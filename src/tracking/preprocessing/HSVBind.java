package tracking.preprocessing;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import tracking.gui.WindowManager;

/**
 * This will return a range from an HSV image.
 * @author Zachery Harley
 *
 */
public class HSVBind extends Preprocesser{

	Scalar maxRange;
	Scalar minRange = new Scalar(0,0,0);
	
	public HSVBind() {
		super("HSVBind");
		maxRange = new Scalar(255, 225, 225);
	}
	
	public HSVBind(boolean hue, boolean saturation, boolean value) {
		super("HSVBind");
		int hueMax = 255;
		int satMax = 255;
		int valMax = 255;
		if(!hue) hueMax = 0;
		if(!saturation) satMax = 0;
		if(!value) valMax = 0;
		maxRange = new Scalar(hueMax, satMax, valMax);
	}

	public void showWindow() {
		WindowManager.sliderShow("HSV", "HueMax", 0, 255, 255);
		WindowManager.sliderShow("HSV", "HueMin", 0, 255, 0);
		WindowManager.sliderShow("HSV", "SatMax", 0, 255, 255);
		WindowManager.sliderShow("HSV", "SatMin", 0, 255, 0);
		WindowManager.sliderShow("HSV", "ValMax", 0, 255, 255);
		WindowManager.sliderShow("HSV", "ValMin", 0, 255, 0);
		windowShown = true;
	}
	
	private void updateValues() {
		int minHue = WindowManager.getSliderValue("HSV", "HueMin");
		int maxHue = WindowManager.getSliderValue("HSV", "HueMax");
		int minSat = WindowManager.getSliderValue("HSV", "SatMin");
		int maxSat = WindowManager.getSliderValue("HSV", "SatMax");
		int minVal = WindowManager.getSliderValue("HSV", "ValMin");
		int maxVal = WindowManager.getSliderValue("HSV", "ValMax");
		this.maxRange = new Scalar(maxHue, maxSat, maxVal);
		this.minRange = new Scalar(minHue, minSat, minVal);
	}
	
	@Override
	public Mat process(Mat intput) {
		Mat hsv = new Mat();
		Imgproc.cvtColor(intput, hsv, Imgproc.COLOR_BGR2HSV);
		if(this.windowShown) updateValues();
		Core.inRange(hsv, minRange, maxRange, hsv);
		return hsv;
	}
	
	
}
