package mypacman;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 

public class board extends JPanel implements ActionListener
{  
	
	public final int BLOCK_SIZE = 40;
	private final Color dotColor = new Color(192, 192, 0);
	private final Color blockColor = new Color(50, 50, 192);
	private final Color recColor = new Color(0, 0, 192);
	public int score=0;
	public int lives=4;
	public boolean inGame=true;
	public keyboard key=new keyboard();
	ghost ghost1=new ghost(1);
	ghost ghost2=new ghost(2);
	ghost ghost3=new ghost(3);
	man pac=new man();
	Image pacman;
	Timer timer = new Timer(1000, this);
	public int screenData[][] = 
		{
			{8,2,2,2,2,9,0,8,2,2,12,2,2,9},
			{7,0,0,0,0,7,0,7,0,0,7,0,0,7},
			{7,0,8,2,2,10,0,7,0,8,5,9,0,7},
			{7,0,7,0,0,7,0,7,0,7,0,7,0,7},
			{11,2,6,2,2,3,0,4,2,5,12,3,0,7},
			{7,0,7,0,0,0,0,0,0,0,7,0,0,7},
			{1,0,11,2,2,2,12,2,2,12,5,2,2,3},
			{0,0,7,0,0,0,7,0,0,7,0,0,0,0},
			{8,2,10,0,0,0,7,0,0,7,0,0,0,0},
			{7,0,11,2,2,2,5,2,2,5,12,2,9,0},
			{7,0,7,0,0,0,0,0,0,0,7,0,7,0},
			{7,0,4,12,2,9,0,8,2,12,3,0,7,0},
			{7,0,0,7,0,7,0,7,0,7,0,0,4,9},
			{11,2,2,3,0,7,0,7,0,4,9,0,0,7},
			{7,0,0,0,0,7,0,7,0,0,7,0,0,7},
			{4,2,2,2,2,3,0,4,2,2,5,2,2,3}
		};
	
	board()
	{  
		JFrame f=new JFrame("Pacman");
		
		f.setSize(820,700);  
		
		f.addMouseListener(new MouseAdapter() {
		     @Override
		     public void mousePressed(MouseEvent e) {
		        System.out.println((e.getX()-7)+" "+(e.getY()-30));
		     }
		  });
		
		f.add(this);
		addKeyListener(key);
		timer.start();
		

        setFocusable(true);
        pacman = new ImageIcon("images/pacman-png-7.png").getImage();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		setBackground(Color.black);
        drawing(g);
    }
	
	private void drawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        drawMaze(g2d);
        drawScore(g2d);
		if(score>132)
		{
			g2d.setColor(new Color(0,255,0));
			g2d.drawString("YOU WON", 200,300);
		}
		else if (inGame)  playGame(g2d);
        else
        {
        	g2d.setColor(new Color(255,0,0));
            g2d.drawString("GAME OVER", 200,300);
        }
        
    }
	
	private void playGame(Graphics2D g2d) 
	{
		ghost1.moveGhost(g2d);
        ghost2.moveGhost(g2d);
        ghost3.moveGhost(g2d);
        pac.moveman(g2d);
        try {
				Thread.sleep(2);
			} 
        catch (InterruptedException e) 
        	{
				e.printStackTrace();
			}
            repaint();
    }
	
	
	private void drawMaze(Graphics2D g2d) 
	{
        int i,j;
        for (i = 0; i < 14; i++)
        {
            for (j = 0; j < 16; j++) 
            {     
                if (screenData[j][i]==0) 
                { 
                    g2d.setColor(blockColor);
                    g2d.fillRect(i*BLOCK_SIZE,j*BLOCK_SIZE,40,40);
                    g2d.setColor(recColor);
                    g2d.setStroke(new BasicStroke(5));
                    if(i>0)		if(screenData[j][i-1]!=0)	g2d.drawLine(i*BLOCK_SIZE,j*BLOCK_SIZE,i*BLOCK_SIZE,(j+1)*BLOCK_SIZE);
                    if(j>0)		if(screenData[j-1][i]!=0)	g2d.drawLine(i*BLOCK_SIZE,j*BLOCK_SIZE,(i+1)*BLOCK_SIZE,j*BLOCK_SIZE);
                    if(j<15)	if(screenData[j+1][i]!=0)	g2d.drawLine(i*BLOCK_SIZE,(j+1)*BLOCK_SIZE,(i+1)*BLOCK_SIZE,(j+1)*BLOCK_SIZE);
                    if(i<13)	if(screenData[j][i+1]!=0)	g2d.drawLine((i+1)*BLOCK_SIZE,j*BLOCK_SIZE,(i+1)*BLOCK_SIZE,(j+1)*BLOCK_SIZE);
                }
                else if(screenData[j][i]>0)
                {
                	g2d.setColor(dotColor);
                	g2d.fillRect(i*BLOCK_SIZE+BLOCK_SIZE/2,j*BLOCK_SIZE+BLOCK_SIZE/2,3,3);
                }
            }
        }
        g2d.setColor(recColor);
        g2d.drawLine(14*BLOCK_SIZE,0,14*BLOCK_SIZE,16*BLOCK_SIZE);
        g2d.drawLine(0,16*BLOCK_SIZE,14*BLOCK_SIZE,16*BLOCK_SIZE);
    }
	
	private void drawScore(Graphics2D g) {

        int i;
        String s;

        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.setColor(new Color(220, 10, 0));
        s = "Score: " + score;
        g.drawString(s,14*BLOCK_SIZE+20 , 50);
        s = "lives: ";
        g.setColor(new Color(10, 220, 20));
        g.drawString(s,14*BLOCK_SIZE+20 , 90);
        for (i = 0; i < lives; i++) {
            g.drawImage(pacman,14*BLOCK_SIZE+ i * 65, 100, this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
	
}  