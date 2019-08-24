package mypacman;

import java.awt.*;  
import java.awt.event.*;

public class Mouse extends Frame implements MouseMotionListener
{  
    Mouse()
    {  
        addMouseMotionListener(this);           
        setSize(300,300);  
        setLayout(null);  
        setVisible(true);  
    }  
    public void mouseDragged(MouseEvent e) 
    { 
    	System.out.print("\n"+e.getX()+" "+e.getY()); 
    }  
    public void mouseMoved(MouseEvent e) {}  
  
}  