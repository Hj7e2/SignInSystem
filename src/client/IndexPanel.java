package client;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import currency.PictureOpaque;
import currency.PictureZoom;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class IndexPanel extends JPanel {
	
	Calendar cal;
	private JLabel labelyear;
	private JLabel labeltime;
	private JLabel labelweek;
	JSlider slider;
	
	public JSlider getSlider() {
		return slider;
	}
	
	

	public IndexPanel() {
		setOpaque(false);
		setLayout(null);
		
		labeltime = new JLabel("10:53");
		labeltime.setForeground(new Color(255, 105, 180));
		labeltime.setFont(new Font("宋体", Font.BOLD, 32));
		labeltime.setBounds(355, 50, 100, 37);
		add(labeltime);
		

		labelyear = new JLabel("2020年7月1日");
		labelyear.setForeground(new Color(65, 105, 225));
		labelyear.setFont(new Font("宋体", Font.PLAIN, 14));
		labelyear.setBounds(355, 86, 100, 18);
		add(labelyear);
		
		labelweek = new JLabel("周三");
		labelweek.setFont(new Font("宋体", Font.PLAIN, 14));
		labelweek.setForeground(new Color(255, 99, 71));
		labelweek.setBounds(388, 106, 48, 18);
		add(labelweek);

		PictureOpaque kuang = new PictureOpaque("lib\\框.png", 325, 13, 150, 137,0.6f);
		add(kuang);

//		PictureZoom zhuangshi = new PictureZoom("D:\\信息\\eclipse_workpace\\疫情打卡请假系统\\lib\\装饰1.png", 32, 13, 111, 137);
//		add(zhuangshi);
		
        slider=new JSlider(0,100);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
//        slider.setPaintLabels(true); //添加数字  
//        slider.setPaintTicks(true);//添加刻度
		slider.setOpaque(false);
//		slider.setBackground(Color.WHITE);
		slider.setBounds(325, 540, 150, 37);
		add(slider);
//		 设置滑块样式
		slider.setUI(new  javax.swing.plaf.metal.MetalSliderUI(){
			    	@Override
			    	public void paintThumb(Graphics g) {
		                //绘制指示物
			    		Graphics2D g2d = (Graphics2D) g;
			    		g2d.setColor(new Color(0x44DAFF));//滑块颜色
			    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			    		g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width,
		                        thumbRect.height);//修改为圆形
		                //也可以帖图(利用鼠标事件转换image即可体现不同状态)
		                //g2d.drawImage(image, thumbRect.x, thumbRect.y, thumbRect.width,thumbRect.height,null);
		 
			    	}
			    	public void paintTrack(Graphics g) {
		                //绘制刻度的轨迹
			    		int cy,cw;
			    		Rectangle trackBounds = trackRect;
			    		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			    			cy = (trackBounds.height/2) - 2;
			    			cw = trackBounds.width;
			    			Graphics2D g2 = (Graphics2D) g;
//			    			g2.setPaint(new Color(255,255,255));
			    			g2.setPaint(new GradientPaint(0, 0, Color.white, cw, 0,
			    					Color.black, true));//滑条颜色
			    			 			
			    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    					RenderingHints.VALUE_ANTIALIAS_ON);
			    			g2.translate(trackBounds.x, trackBounds.y + cy);
			    			g2.fillRect(0, -cy + 5, cw, cy);
			    			
			    			int trackLeft = 0;
			    			int trackRight = 0;
			    			trackRight = trackRect.width - 1;
		 
			    			int middleOfThumb = 0;
			    			int fillLeft = 0;
			    			int fillRight = 0;
			    			//换算坐标
			    			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
			    			middleOfThumb -= trackRect.x;
			    			
			    			if (!drawInverted()) {
			    				fillLeft = !slider.isEnabled() ? trackLeft : trackLeft + 1;
			    				fillRight = middleOfThumb;
			    				} else {
			    				fillLeft = middleOfThumb;
			    				fillRight = !slider.isEnabled() ? trackRight - 1
			    				: trackRight - 2;
			    				}
			    			//设定渐变,在这里从红色变为红色,则没有渐变,滑块划过的地方自动变成红色
			    			g2.setPaint(new GradientPaint(0, 0, Color.white, cw, 0,
			    					Color.black, true));
			    			g2.fillRect(0, -cy + 5, fillRight - fillLeft, cy);
			    					
			    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    			RenderingHints.VALUE_ANTIALIAS_OFF);
			    			g2.translate(-trackBounds.x, -(trackBounds.y + cy));   					
			    		}
			    		else {
		    				super.paintTrack(g);
		    				}
			    	}
			    
		});
		setTime();	
	}
	
	private String addZero(int num) {
		return num<10?"0"+Integer.toString(num):Integer.toString(num);
	}
	
	private String getWeek() {
		String week = "";
		Date today = new Date();
		cal.setTime(today);
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday == 1) {
			week = "周日";
		} else if (weekday == 2) {
			week = "周一";
		} else if (weekday == 3) {
			week = "周二";
		} else if (weekday == 4) {
			week = "周三";
		} else if (weekday == 5) {
			week = "周四";
		} else if (weekday == 6) {
			week = "周五";
		} else if (weekday == 7) {
			week = "周六";
		}
		return week;
	}

	public void setTime() {
		//创建Calendar对象
		cal = Calendar.getInstance();

		//用Calendar类提供的方法获取年、月、日、时、分
		int year  =cal.get(Calendar.YEAR);   //年
		int month =cal.get(Calendar.MONTH)+1;  //月  默认是从0开始  即1月获取到的是0
		int day   =cal.get(Calendar.DAY_OF_MONTH);  //日，即一个月中的第几天
		int hour  =cal.get(Calendar.HOUR_OF_DAY);  //小时
		int minute=cal.get(Calendar.MINUTE);   //分

		labeltime.setText(addZero(hour)+":"+addZero(minute));
		labelyear.setText(year+"年"+addZero(month)+"月"+addZero(day)+"日");
		labelweek.setText(getWeek());
	}

}
