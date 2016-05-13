package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.MenuModel;

public class MenuView extends View
{
	public MenuView(MenuModel source) throws IOException
	{
		mainFrame_.getContentPane().removeAll();
		source_ = source;
		play_button_ = new MyButton("Play");
		quit_button_ = new MyButton("Quit");
		JLayeredPane pane = new JLayeredPane();
		pane.setBounds(0, 0, MyFrame.WIDTH, MyFrame.HEIGHT);
		pane.setLayout(null);
		
		MenuPanel mPanel = new MenuPanel();
		mPanel.setBounds(0, 0, MyFrame.WIDTH, MyFrame.HEIGHT);
		play_button_.setBounds(PLAY_BUTTON_X, PLAY_BUTTON_Y, MyButton.WIDTH, MyButton.HEIGHT);
		quit_button_.setBounds(QUIT_BUTTON_X, QUIT_BUTTON_Y, MyButton.WIDTH, MyButton.HEIGHT);
		
		pane.add(mPanel, new Integer(0), 0);
		pane.add(play_button_, new Integer(1), 1);
		pane.add(quit_button_, new Integer(1), 2);
		mainFrame_.add(pane);		
	}
	
	public void draw()
	{	
		mainFrame_.paintAll(mainFrame_.getGraphics());
		mainFrame_.repaint();
	}
	
	public MyButton getPlayButton()
	{
		return play_button_;
	}
	
	public MyButton getQuitButton()
	{
		return quit_button_;
	}
	
	class MenuPanel extends JPanel
	{
		public MenuPanel()
		{
			
		}
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        draw(g);
	    }
		
		public void draw(Graphics g) 
		{
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawImage(source_.getBackground(), 0, 0, null);
				
			g2.setFont(font_);
			g2.setColor(Color.WHITE);
			for(int i = 0; i < source_.getMessage().length; ++i)
			{
				g2.drawString(source_.getMessage()[i], INSTRUCTION_X, INSTRUCTION_Y+i*INTERLINE);
			}

		}
		

		static final long serialVersionUID = 1L;
	}
	
	private MyButton play_button_;
	private MyButton quit_button_;

	private static final int PLAY_BUTTON_X = 100;
	private static final int PLAY_BUTTON_Y = 450;
	private static final int QUIT_BUTTON_X = 550;
	private static final int QUIT_BUTTON_Y = 450;
	private static final int INSTRUCTION_X = 40;
	private static final int INSTRUCTION_Y = FONT_SIZE+60;
	private static final int INTERLINE = FONT_SIZE + 20;
	private MenuModel source_;
}