package mypacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyboard extends KeyAdapter
{
	public int key;
	public void keyPressed(KeyEvent e) 
    {
        key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) 
        {
            driver.start.pac.z='l';
        } 
        else if (key == KeyEvent.VK_RIGHT) 
        {
            driver.start.pac.z='r';
        }
        else if (key == KeyEvent.VK_UP) 
        {
            driver.start.pac.z='u';
        } 
        else if (key == KeyEvent.VK_DOWN) 
        {
            driver.start.pac.z='d';
        }
    }
}
