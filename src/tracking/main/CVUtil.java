package tracking.main;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;

public class CVUtil{
	
	public static BufferedImage Mat2BufferedImage(Mat image) {
		BufferedImage output;
		byte[] data = new byte[image.width() * image.height() * (int)image.elemSize()];
		int type;
		image.get(0, 0, data);
		
		if(image.channels() == 1)
			type = BufferedImage.TYPE_BYTE_GRAY;
		else
			type = BufferedImage.TYPE_3BYTE_BGR;
		
		output = new BufferedImage(image.width(), image.height(), type);
		output.getRaster().setDataElements(0, 0, image.width(), image.height(), data);
		return output;
	}
	
}
