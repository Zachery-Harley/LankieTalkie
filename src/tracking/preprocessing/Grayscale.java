package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Grayscale extends Preprocesser{

	public Grayscale() {
		super("Grayscale");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mat process(Mat intput) {
		Mat output = new Mat();
		Imgproc.cvtColor(intput, output, Imgproc.COLOR_BGR2GRAY);
		return output;
	}

}
