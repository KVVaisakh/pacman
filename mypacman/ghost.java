package mypacman;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ghost 
{
	Image ghost;
	int posx,posy,color,d=0,xdir=0,ydir=0;
	char cur_dir='r';
	ghost(int x)
	{
		if(x==1)	
		{	
			ghost = new ImageIcon("images/pacman-red-png-4.png").getImage();
			posx=160;
			posy=360;
			color=1;
		}
		if(x==2)	
		{	
			ghost = new ImageIcon("images/pacman-orange-png-26.png").getImage();
			posx=360;
			posy=80;			
			color=2;
		}
		if(x==3)	
		{	
			ghost = new ImageIcon("images/pacman-blue-png-10.png").getImage();
			posx=280;
			posy=600;
			color=3;
		}
	}
	
	public void moveGhost(Graphics2D g2d) 
	{
		if(color==1)
		{
			if(posx % driver.start.BLOCK_SIZE==0 && posy%driver.start.BLOCK_SIZE==0)
			{
				xdir=driver.start.pac.posx-posx;
				ydir=driver.start.pac.posy-posy;
				d=driver.start.screenData[posy/driver.start.BLOCK_SIZE][posx/driver.start.BLOCK_SIZE];
				if(xdir>0 && (d==2 || d==8 || d==6 || d==4 || d==5 || d==11 || d==12 || d==-2 || d==-8 || d==-6 || d==-4 || d==-5 || d==-11 || d==-12))
					cur_dir='r';
				else if(ydir>0&&( (d>5 && d<13) || (d>-13 && d<-5))	)											
					cur_dir='d';
				else if(ydir<0&& (d==1 || (d>2 &&d<8) ||d==10 || d==11 || d==-1 || (d<-2 &&d>-8) ||d==-10 || d==-11) )					
					cur_dir='u';
				else if(xdir<0&& (d==2 || d==3 || d==5 || d==6 || d==9 || d==10 || d==12||d==-2 || d==-3 || d==-5 || d==-6 || d==-9 || d==-10 || d==-12))	
					cur_dir='l';
				else 
					cur_dir='p';
			}
			if(cur_dir=='r')		posx+=1;
			else if(cur_dir=='d')	posy+=1;
			else if(cur_dir=='u')	posy-=1;
			else if(cur_dir=='l')	posx-=1;
		}
		if(color==2)
		{
			if(posx % driver.start.BLOCK_SIZE==0 && posy%driver.start.BLOCK_SIZE==0)
			{
				if(driver.start.pac.cur_dir=='l' )		xdir=-1;
				else if( driver.start.pac.cur_dir=='r') xdir=1;
				ydir=driver.start.pac.posy-posy;
				d=driver.start.screenData[posy/driver.start.BLOCK_SIZE][posx/driver.start.BLOCK_SIZE];
				if(xdir>0 && (d==2 || d==8 || d==6 || d==4 || d==5 || d==11 || d==12 || d==-2 || d==-8 || d==-6 || d==-4 || d==-5 || d==-11 || d==-12))
					cur_dir='r';
				else if(ydir>0&&( (d>5 && d<13) || (d>-13 && d<-5))	)											
					cur_dir='d';
				else if(ydir<0&& (d==1 || (d>2 &&d<8) ||d==10 || d==11 || d==-1 || (d<-2 &&d>-8) ||d==-10 || d==-11) )					
					cur_dir='u';
				else if(xdir<0&& (d==2 || d==3 || d==5 || d==6 || d==9 || d==10 || d==12||d==-2 || d==-3 || d==-5 || d==-6 || d==-9 || d==-10 || d==-12))	
					cur_dir='l';
				else 
					cur_dir='p';
			}
			if(cur_dir=='r')		posx+=1;
			else if(cur_dir=='d')	posy+=1;
			else if(cur_dir=='u')	posy-=1;
			else if(cur_dir=='l')	posx-=1;
		}
		if(color==3)
		{
			if(posx % driver.start.BLOCK_SIZE==0 && posy%driver.start.BLOCK_SIZE==0)
			{
				if(driver.start.pac.cur_dir=='u' )		ydir=-1;
				else if( driver.start.pac.cur_dir=='d') ydir=1;
				xdir=driver.start.pac.posx-posx;
				d=driver.start.screenData[posy/driver.start.BLOCK_SIZE][posx/driver.start.BLOCK_SIZE];
				if(xdir>0 && (d==2 || d==8 || d==6 || d==4 || d==5 || d==11 || d==12 || d==-2 || d==-8 || d==-6 || d==-4 || d==-5 || d==-11 || d==-12))
					cur_dir='r';
				else if(ydir>0&&( (d>5 && d<13) || (d>-13 && d<-5))	)											
					cur_dir='d';
				else if(ydir<0&& (d==1 || (d>2 &&d<8) ||d==10 || d==11 || d==-1 || (d<-2 &&d>-8) ||d==-10 || d==-11) )					
					cur_dir='u';
				else if(xdir<0&& (d==2 || d==3 || d==5 || d==6 || d==9 || d==10 || d==12||d==-2 || d==-3 || d==-5 || d==-6 || d==-9 || d==-10 || d==-12))	
					cur_dir='l';
				else 
					cur_dir='p';
			}
			if(cur_dir=='r')		posx+=1;
			else if(cur_dir=='d')	posy+=1;
			else if(cur_dir=='u')	posy-=1;
			else if(cur_dir=='l')	posx-=1;
		}
		drawGhost(g2d,posx ,posy);
    }

    public void drawGhost(Graphics2D g2d, int x, int y) {

        g2d.drawImage(ghost, x, y, driver.start);
    }
}
