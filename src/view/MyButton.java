package src.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class MyButton extends JComponent
{
	public MyButton(String title) throws IOException
	{
		super();
		title_ = title;
		loadImage();
		
		setLayout(null);
		enableInputMethods(true);   
		addMouseListener(createMouseListener());
	}
	
	public MyButton(String title, int x, int y) throws IOException
	{
		this(title);
		setBounds(x, y, WIDTH, HEIGHT);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(WIDTH, HEIGHT);
	}
	
	public Dimension getMinimumSize()
	{
		return new Dimension(WIDTH, HEIGHT);
	}
	
	public Dimension getMaximumSize()
	{
		return new Dimension(WIDTH, HEIGHT);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(images_[state_.getValue()], 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(FONT);
		g.drawString(title_, TITLE_OFFSET_X, TITLE_OFFSET_Y);
	}
	
	private MouseListener createMouseListener()
	{
		return new MyButtonMouseListener();
	}
    
    public void addActionListener(ActionListener listener)
    {
        listeners_.add(listener);
    }
    
    public String getTitle()
    {
    	return title_;
    }
    
    private void notifyListeners(MouseEvent e)
    {
        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, title_, e.getWhen(), e.getModifiers());
        synchronized(listeners_)
        {
            for (ActionListener tmp : listeners_)  
                tmp.actionPerformed(evt);
        }
    }
	
	private void loadImage() throws IOException
	{
		images_ = new BufferedImage[]{ImageIO.read(new File("resources/active.png")),
									  ImageIO.read(new File("resources/hovered.png")),
									  ImageIO.read(new File("resources/pressed.png"))};
	}
	
	private class MyButtonMouseListener implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
	    {
	    }
		
	    public void mouseEntered(MouseEvent e)
	    {
	    	mouseInside_ = true;
	    	if(state_ == ButtonState.ACTIVE)
	    		state_ = ButtonState.HOVERED;
	    }
	    public void mouseExited(MouseEvent e)
	    {
	    	mouseInside_ = false;
	        if(state_ == ButtonState.HOVERED)
	        	state_ = ButtonState.ACTIVE;
	    }
	    public void mousePressed(MouseEvent e)
	    {
	    	if(state_ == ButtonState.HOVERED)
	    		state_ = ButtonState.PRESSED;
	    }
	    public void mouseReleased(MouseEvent e)
	    {
	    	if(mouseInside_ && state_ == ButtonState.PRESSED)
	    	{
	    		state_ = ButtonState.HOVERED;
	    		notifyListeners(e);
	    	}
	    	else
	    	{
	    		state_ = ButtonState.ACTIVE;
	    	}
	   }
	};
	
	public static final int WIDTH = 166;
	public static final int HEIGHT = 53;
	private static final int FONT_SIZE = 14;
	private static final int TITLE_OFFSET_X = 20;
	private static final int TITLE_OFFSET_Y = HEIGHT/2 + FONT_SIZE/2; 
	private static final Font FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
	
	static final long serialVersionUID = 2L;
	
	enum ButtonState
	{
		ACTIVE(0),
		HOVERED(1),
		PRESSED(2),
		COUNT(3);
		
		private final int value;
		private ButtonState(int value)
		{
			this.value = value;
		}
		
		public int getValue() 
		{
	        return value;
	    }
	}
	
	private ButtonState state_ = ButtonState.ACTIVE;
	private boolean mouseInside_ = false;
	private String title_;
	
	private BufferedImage[] images_;
	
	private ArrayList<ActionListener> listeners_ = new ArrayList<ActionListener>();
}