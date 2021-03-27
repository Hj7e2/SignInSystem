package currency;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PictureOpaque extends JLabel{
	String file;
	int width;
	int height;
	float opaque;
	
	public float getOpaque() {
		return opaque;
	}

	public void setOpaque(float opaque) {
		this.opaque = opaque;
	}

	public PictureOpaque(String file,int x,int y,int width,int height,float opaque) {
	this.file = file;
	this.width=width;
	this.height=height;
	this.opaque=opaque;
	ImageIcon image = new ImageIcon(file);
	image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));//可以用下面三句代码来代替
	//Image img = image.getImage();
	//img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	//image.setImage(img);
	this.setIcon(image);
	this.setBounds(x, y, width, height);
	}
	
	@Override
	public void paint(Graphics g){
		Image bgimage = null;
		try {
			bgimage = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque);
	    Graphics2D g2d = (Graphics2D) g; 
	    g2d.setComposite(ac);
	    g2d.drawImage(bgimage, 0, 0, width, height, this);
	}
}
