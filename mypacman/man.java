package mypacman;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class man 
{
	public int posx,posy,d=0;
	public char z,cur_dir='r';
	Image pacman;
	man()
	{
		posx=240;
		posy=360;
		z='r';
	}
	public void moveman(Graphics2D g2d) 
	{
		if(posx==driver.start.ghost1.posx && posy==driver.start.ghost1.posy || posx==driver.start.ghost2.posx && posy==driver.start.ghost2.posy || posx==driver.start.ghost3.posx && posy==driver.start.ghost3.posy)
		{
			if(driver.start.lives>0)			
			{
				driver.start.lives--;
				posx=240;
				posy=360;
				driver.start.ghost1.posx=160;
				driver.start.ghost1.posy=360;
				driver.start.ghost2.posx=360;
				driver.start.ghost2.posy=80;
				driver.start.ghost3.posx=280;
				driver.start.ghost3.posy=600;
				z='r';
			}
			else
			{
				driver.start.inGame=false;
			}
		}
		if(posx % driver.start.BLOCK_SIZE==0 && posy%driver.start.BLOCK_SIZE==0)
		{
			d=driver.start.screenData[posy/driver.start.BLOCK_SIZE][posx/driver.start.BLOCK_SIZE];
			if(z=='r'&& (d==2 || d==8 || d==6 || d==4 || d==5 || d==11 || d==12 || d==-2 || d==-8 || d==-6 || d==-4 || d==-5 || d==-11 || d==-12))		
				cur_dir='r';
			else if(z=='d'&&( (d>5 && d<13) || (d>-13 && d<-5))	)											
				cur_dir='d';
			else if(z=='u'&& (d==1 || (d>2 &&d<8) ||d==10 || d==11 || d==-1 || (d<-2 &&d>-8) ||d==-10 || d==-11) )					
				cur_dir='u';
			else if(z=='l'&& (d==2 || d==3 || d==5 || d==6 || d==9 || d==10 || d==12||d==-2 || d==-3 || d==-5 || d==-6 || d==-9 || d==-10 || d==-12))	
				cur_dir='l';
			else 																		
				cur_dir='p';
		}
		if(cur_dir=='r')		posx+=1;
		else if(cur_dir=='d')	posy+=1;
		else if(cur_dir=='u')	posy-=1;
		else if(cur_dir=='l')	posx-=1;
		drawman(g2d,posx ,posy,z);
	}
	public void drawman(Graphics2D g2d, int x, int y,int z) 
	{
		if(z=='l')	pacman = new ImageIcon("images/pacmanleft.png").getImage();
		if(z=='r')	pacman = new ImageIcon("images/pacmanright.png").getImage();
		if(z=='u')	pacman = new ImageIcon("images/pacmanup.png").getImage();
		if(z=='d')	pacman = new ImageIcon("images/pacmandown.png").getImage();
		g2d.drawImage(pacman, x, y, driver.start);
		if(x % driver.start.BLOCK_SIZE==0 && y%driver.start.BLOCK_SIZE==0)
		{
			if(driver.start.screenData[y/driver.start.BLOCK_SIZE][x/driver.start.BLOCK_SIZE]>0)
			{
				driver.start.screenData[y/driver.start.BLOCK_SIZE][x/driver.start.BLOCK_SIZE]=-driver.start.screenData[y/driver.start.BLOCK_SIZE][x/driver.start.BLOCK_SIZE];
				driver.start.score++;
			}
		}
    }
}
