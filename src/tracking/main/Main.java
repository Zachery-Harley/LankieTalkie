package tracking.main;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tracking.gui.WindowManager;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		String path = Main.class.getResource("/cat.jpg").toString();
		Image image = new Image(path);
		
		startOpenCV();
	}
	
	private void startOpenCV() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		VideoCapture video = new VideoCapture();
		
		if(video.open("resources/trainsLowFPS.avi")) {
			PreprocessDemo preprocess = new PreprocessDemo(video);
		} else {
			System.out.println("Failed to open video");
		}
	}
	
	public static void simpleWait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
