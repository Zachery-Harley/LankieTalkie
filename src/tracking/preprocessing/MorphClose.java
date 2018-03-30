package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MorphClose extends Preprocesser{

	private int kernalSize = 12;
	
	public MorphClose() {
		super("Morph Close");
	}

	@Override
	public Mat process(Mat input) {
		Mat output = new Mat();
		Size kernalS = new Size(2 * kernalSize + 1, 2 * kernalSize + 1);
		//The element can be either CROSS, RECT or ELLIPSE
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, kernalS);
		Imgproc.morphologyEx(input, output, Imgproc.MORPH_CLOSE, kernel);
		return output;
	}
	
	
}
