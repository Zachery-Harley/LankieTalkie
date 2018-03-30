package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import tracking.gui.WindowManager;
import tracking.main.CVUtil;

public class MorphOpen extends Preprocesser{

	Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(4, 4));
	
	public MorphOpen() {
		super("Morph Open");
	}

	@Override
	public Mat process(Mat input) {
		Mat output = new Mat();
		Imgproc.morphologyEx(input, output, Imgproc.MORPH_OPEN, kernel);
		return output;
	}
	
	

}
