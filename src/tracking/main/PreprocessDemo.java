package tracking.main;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import tracking.gui.WindowManager;
import tracking.preprocessing.BackgroundSubtraction;
import tracking.preprocessing.GaussianBlur;
import tracking.preprocessing.Grayscale;
import tracking.preprocessing.HSVBind;
import tracking.preprocessing.MorphClose;
import tracking.preprocessing.MorphDilate;
import tracking.preprocessing.MorphOpen;
import tracking.preprocessing.Preprocesser;
import tracking.processing.ContourFinding;
import tracking.processing.Processer;

public class PreprocessDemo {
	
	private VideoCapture video;
	
	ArrayList<Preprocesser> preprocessors = new ArrayList<Preprocesser>();
	ArrayList<Processer> processes = new ArrayList<Processer>();
	
	public PreprocessDemo(VideoCapture video) {
		this.video = video;
		
		if(!video.isOpened()) {
			System.err.println("Failed to open video stream!");
		}
		
		
		Preprocesser morphDilate = new MorphDilate();
		morphDilate.showWindow();
		
		Preprocesser morphOpen = new MorphOpen();
		morphDilate.addPreprocesser(morphOpen, true);
		
		//Define any preprocessors
		Preprocesser backgroundSubtraction = new BackgroundSubtraction();
		//backgroundSubtraction.showWindow();
		morphOpen.addPreprocesser(backgroundSubtraction, false);
		
		
		
		
		//preprocessors.add(morphDilate);
		
		
		
		Processer contour = new ContourFinding();
		contour.addPreprocesser(morphDilate);
		
		processes.add(contour);
		
		Preprocesser gray = new Grayscale();
		Preprocesser blur = new GaussianBlur(new Size(9, 9), 2, 2);
		
		int totalFrames = (int) video.get(Videoio.CV_CAP_PROP_FRAME_COUNT);
		Mat frame = new Mat();
		for(int i = 0; i < totalFrames; i++){
			video.read(frame);
			Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2RGB);//Convert the image color so that the output looks correct
			frame = gray.process(frame);
			frame = blur.process(frame);
			processFrame(frame);
		}	
	}
	
	private void processFrame(Mat frame) {
		WindowManager.imShow("Original", CVUtil.Mat2BufferedImage(frame));
		
		for(Preprocesser process : preprocessors) {
			Mat processedImage = process.process(frame, true);
			WindowManager.imShow(process.getName(), CVUtil.Mat2BufferedImage(processedImage));
		}
		
		for(Processer process : processes) {
			Mat processedImage = process.process(frame, true);
			WindowManager.imShow(process.getName(), CVUtil.Mat2BufferedImage(processedImage));
		}
		
	}
	
}
