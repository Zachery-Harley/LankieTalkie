package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import tracking.gui.WindowManager;

public class MorphDilate extends Preprocesser {

Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 4));
	
	public MorphDilate() {
		super("Morph Dilate");
	}
	
	@Override
	public void showWindow() {
		super.showWindow();
		WindowManager.sliderShow("Dilate", "size", 4, 255, 20);
	}
	
	public void updateValues() {
		int size = WindowManager.getSliderValue("Dilate", "size");
		kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(size, size));
	}

	@Override
	public Mat process(Mat input) {
		Mat output = new Mat();
		if(windowShown) updateValues();
		Imgproc.morphologyEx(input, output, Imgproc.MORPH_DILATE, kernel);
		return output;
	}
	
}
