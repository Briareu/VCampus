package VCampusClient.src.main.java.seu.list.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JComponent;

public class VerifyCode extends JComponent implements MouseListener{

	private String codes;
	private int width = 40,height = 30;
	private int codesLength=4;
	private Random random = new Random();
	
	public VerifyCode() {
		width = this.codesLength*16+(this.codesLength-1)*10;
		setPreferredSize(new Dimension(width,height));
		setSize(width,height);
		this.addMouseListener(this);
	    setToolTipText("点击可更换验证码");
	}
	
	//得到生成的验证码
	public int getCodesLength() {
		return codesLength;
	}
	
	//设置验证码长度
	public void setCodesLength(int codeLength) {
		if(codesLength<4) {
			this.codesLength = 4;
		} else {
			this.codesLength = codeLength;
		}
	}
	
	public String getCode() {
		return codes;
	}
	//让验证码产生随机的颜色
	public Color getRandColor(int min, int max) {
		if(min > 255)
			min=255;
		if(max > 255)
			max=255;
		int red = random.nextInt(max-min)+min;
		int green = random.nextInt(max-min)+min;
		int blue = random.nextInt(max-min)+min;
		return new Color (red,green,blue);
	}
	//设置验证码具体的数字或字母
	protected String generateCode() {
		char[] codes = new char[this.codesLength];
		for(int i=0,len=codes.length;i<len;i++) {
			if(random.nextBoolean()) {
				codes[i] = (char)(random.nextInt(10)+48);
			}else {
				codes[i] = (char)(random.nextInt(26)+97);
			}
		}
		this.codes = new String(codes);
		return this.codes;
		
	}
	
    protected void paintComponent(Graphics g) 
    {  
	    super.paintComponent(g);  
	     if(this.codes == null || this.codes.length() != this.codesLength) 
	     {  //判断生成的验证码是否为空或超出长度
	           this.codes = generateCode();  
	     }  
	       width = this.codesLength * 16 + (this.codesLength - 1) * 10;  
	       super.setSize(width, height);  //接口使用，验证码字体大小
	       super.setPreferredSize(new Dimension(width, height));//接口使用，验证码背景大小  
	       Font mFont = new Font("Arial", Font.BOLD | Font.ITALIC, 25);  //设置字体和字体大小
	       g.setFont(mFont);  //设置对象
	       
	              //绘制出验证码的背景的矩形轮廓  
	      Graphics2D g2d = (Graphics2D) g;  
	      g2d.setColor(getRandColor(200, 250));  
	      g2d.fillRect(0, 0, width, height);  
	      g2d.setColor(getRandColor(180, 200));  
	      g2d.drawRect(0, 0, width - 1, height - 1);  
	      
	              //绘制出验证码背景的线  
	      int i = 0, len = 150;  
	      for (; i < len; i++)
	      { 
	    	  int x = random.nextInt(width - 1);  
	          int y = random.nextInt(height - 1);  
	          int x1 = random.nextInt(width - 10) + 10;  
	          int y1 = random.nextInt(height - 4) + 4;  
	          g2d.setColor(getRandColor(180, 200));  
              g2d.drawLine(x, y, x1, y1);  
	      }  
	            
	             //绘制出验证码的具体字母  
	    i = 0; len = this.codesLength;  
	    FontMetrics fm = g2d.getFontMetrics();  
	    int base = (height - fm.getHeight())/2 + fm.getAscent();  
	    for(;i<len;i++) 
	    {  
	     int b = random.nextBoolean() ? 1 : -1;  
	     g2d.rotate(random.nextInt(10)*0.01*b);  
	     g2d.setColor(getRandColor(20, 130));  
	     g2d.drawString(codes.charAt(i)+"", 16 * i + 10, base);  
	    }  
	} 
	      
	//下一个验证码  
    public void nextCode() {  
	    generateCode();  
	     repaint();  
	}  
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		nextCode();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
