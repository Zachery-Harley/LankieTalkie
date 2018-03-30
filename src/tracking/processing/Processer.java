package tracking.processing;

import java.util.ArrayList;

import org.opencv.core.Mat;

import tracking.preprocessing.Preprocesser;

public abstract class Processer {
	
	private String name;
	private ArrayList<Preprocesser> preprocessing = new ArrayList<Preprocesser>();
	
	public Processer(String name) {
		this.name = name;
	}
	
	public abstract Mat process(Mat input);
	
	public Mat process(Mat input, boolean runAll) {
		if(!runAll) return process(input);
		
		for(Preprocesser process : preprocessing) {
			input = process.process(input, true);
		}
		
		return process(input);
	}
	
	public void addPreprocesser(Preprocesser preprocessor) {
		this.preprocessing.add(preprocessor);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
