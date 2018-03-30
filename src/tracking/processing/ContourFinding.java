package tracking.processing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


public class ContourFinding extends Processer{

	List<MatOfPoint> contours = new LinkedList<MatOfPoint>();
	Mat hierarchy = new Mat();
	public int threshhold = 1;
	
	public ContourFinding() {
		super("Contour Finding");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Mat process(Mat input) {
		Mat output = input.clone();
		Imgproc.cvtColor(output, output, Imgproc.COLOR_GRAY2RGB);
		Imgproc.findContours(input, contours, hierarchy, Imgproc.RETR_EXTERNAL,  Imgproc.CHAIN_APPROX_SIMPLE);
		while(contours.size() > 0) {
			MatOfPoint contour = contours.remove(0);
			Rect roi = Imgproc.boundingRect(contour);
			Scalar color = new Scalar(0, 125, 0, 255);
			Imgproc.rectangle(output, new Point(roi.x + roi.width, roi.y + roi.height), new Point(roi.x, roi.y), color, 5);
		}
		return output;
	}

}
