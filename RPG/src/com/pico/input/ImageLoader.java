package com.pico.input;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.pico.objects.characters.player.DIR;
import com.pico.prefs.PREFS;

public class ImageLoader {
	public static HashMap<String, Image> storedImages = new HashMap<String, Image>();
	public static BufferedImage new_image;
	private static GraphicsConfiguration gfx_config = GraphicsEnvironment.
            getLocalGraphicsEnvironment().getDefaultScreenDevice().
            getDefaultConfiguration();
	public static Graphics2D g2d;

	public static ArrayList<BufferedImage> loadImages(String filenames){
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		String[] files = filenames.split(",");
		for (String file : files) {
			images.add((BufferedImage)loadImage(file));
		}
		return images;
				
	}
	public static Image loadImage(String filename){
		
		if(!storedImages.containsKey(filename) || PREFS.MINMEMMODE){
			
			try {
			File imgFile = new File("img/"+filename);
			if(PREFS.MINMEMMODE){
				return toCompatibleImage(ImageIO.read(imgFile));
			}
				storedImages.put(filename, toCompatibleImage(ImageIO.read(imgFile)));
			} catch (IOException e) {
				System.out.println("Could not load image "+filename);
				e.printStackTrace();
				return null;
			}
		}
			return storedImages.get(filename);
	}
	private static Image toCompatibleImage(BufferedImage image)
	{
	     if (image.getColorModel().equals(gfx_config.getColorModel()))
	                return image;
	         new_image = gfx_config.createCompatibleImage(
	                        image.getWidth(), image.getHeight(), image.getTransparency());
	        g2d = (Graphics2D) new_image.getGraphics();
	        g2d.drawImage(image, 0, 0, null);
	        return new_image; 
	}
	public static Image[][] splitImage(String name, int width, int rows, int cols) {
		Image[][] returnArr = new Image[rows][cols];
		Image baseImage = loadImage(name);
		int imgWidth = (int)((double)baseImage.getWidth(null)/(double)cols);
		int imgHeight = (int)((double)baseImage.getHeight(null)/(double)rows);
		double mult = (double)(imgHeight/(double)imgWidth);
		int height = (int)(width * mult);
		
		for(int i = 0; i< rows; i++){
			for(int j = 0; j<cols; j++){
				returnArr[i][j] = 
						getScaledImage( 
								baseImage, 
								j*imgWidth, 
								i*imgHeight,
								(j+1)*imgWidth, 
								(i+1)*imgHeight, 
								width, 
								height
								);
			}
		}
		return returnArr;
		
	}
	public static Image getScaledImage(Image baseImage, int sx, int sy, int dx, int dy, int width, int height){
		new_image = gfx_config.createCompatibleImage(
				width, height, ((BufferedImage) baseImage).getTransparency());
		Graphics2D imgGffx = new_image.createGraphics();
		
		imgGffx.drawImage(baseImage,0,0,width,height,sx,sy,dx,dy,null);
		return new_image;
	}
	
	public static BufferedImage loadImage(String _imgName, int width) {
		return (BufferedImage) toCompatibleImage(loadImage(_imgName),width);
	}
	private static Image toCompatibleImage(Image baseImage, int width) {
		int baseWidth  = baseImage.getWidth(null);
		int baseHeight = baseImage.getHeight(null);
		double mult =  (double)baseHeight/(double)baseWidth;
		
		int height = (int) (width * mult);
	

        // image is not optimized, so create a new image that is
         new_image = gfx_config.createCompatibleImage(
                        width, height, ((BufferedImage) baseImage).getTransparency());

        // get the graphics context of the new image to draw the old image on
        g2d = (Graphics2D) new_image.getGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage(baseImage, 0, 0,width, height, 0, 0, baseWidth, baseHeight, null);
        //g2d.dispose();
        //image.getGraphics().dispose();
        //image.flush();


        // return the new optimized image
        return new_image; 

	}
	
}
