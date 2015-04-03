package display;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	public static Image loadImage(String filename){
		
		Image img = null;
		try {
		File imgFile = new File(filename);
		
		
			img = ImageIO.read(imgFile);
		} catch (IOException e) {
			System.out.println("Could not load image "+filename);
			e.printStackTrace();
		}
		return img ;
	}

	public static Image[] split(int numOfImages, String filename) {
		Image mainImage = null;
		Image [] splitImages = new Image[numOfImages];
		try {
		File imgFile = new File(filename);
		
		
			mainImage = ImageIO.read(imgFile);
			int imageWidth = mainImage.getWidth(null)/numOfImages;
			int imageHeight = mainImage.getHeight(null);
			
			for(int i = 0; i< numOfImages; i++){
				BufferedImage splitImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
				splitImg.createGraphics().drawImage(mainImage,0,0,imageWidth,imageHeight,i*imageWidth,0,(i+1)*imageWidth,imageHeight,null);
				splitImages[i] = splitImg;
			}
		} catch (IOException e) {
			System.out.println("Could not load image "+filename);
			e.printStackTrace();
		}
		return splitImages;
	}

}
