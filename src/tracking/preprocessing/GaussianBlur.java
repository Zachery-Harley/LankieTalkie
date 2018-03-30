package tracking.preprocessing;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GaussianBlur extends Preprocesser{

	private Size ksize;
	private int sigmaX;
	private int sigmaY;
	
	public GaussianBlur(Size size, int sigmaX, int sigmaY) {
		super("GaussionBlur");
		this.ksize = size;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
	}

	@Override
	public Mat process(Mat input) {
		Mat output = new Mat();
		Imgproc.GaussianBlur(input, output, ksize, sigmaX, sigmaY);
		return output;
	}

}
