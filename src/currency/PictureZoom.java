package currency;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PictureZoom extends JLabel{
	public PictureZoom(String file,int x,int y,int width,int height) {
	ImageIcon image = new ImageIcon(file);
	image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));//可以用下面三句代码来代替
	//Image img = image.getImage();
	//img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	//image.setImage(img);
	this.setIcon(image);
	this.setBounds(x, y, width, height);
	}
	
}
