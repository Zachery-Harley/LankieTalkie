package tracking.preprocessing;

import java.util.ArrayList;

import org.opencv.core.Mat;

import tracking.gui.WindowManager;
import tracking.main.CVUtil;

public abstract class Preprocesser {
	
	protected boolean windowShown = false;
	private String name;
	private boolean showBeforeOutput = false;
	private ArrayList<Preprocesser> preprocessing = new ArrayList<Preprocesser>();
	
	public Preprocesser(String name) {
		this.name = name;
	}
	
	public abstract Mat process(Mat input);
	
	public Mat process(Mat input, boolean runAll) {
		Mat output;
		if(!runAll) output = process(input);
		else {
			for(Preprocesser process : preprocessing) {
				input = process.process(input, true);
			}
		}
		output = process(input);
		if(this.showBeforeOutput) WindowManager.imShow(name, CVUtil.Mat2BufferedImage(output));
		return output;
	}
	
	public void showWindow() {
		this.windowShown = true;
	}
	
	/**
	 * Add a preprocessor that will be run prior to running this process. By
	 * setting showOutput to be true then the added preprocess will be displayed;
	 * @param preprocessor
	 * @param showOutput
	 */
	public void addPreprocesser(Preprocesser preprocessor, boolean showOutput) {
		preprocessor.setShowBeforeOutput(true);
		this.preprocessing.add(preprocessor);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setShowBeforeOutput(boolean show) {
		this.showBeforeOutput = show;
	}
	
}
