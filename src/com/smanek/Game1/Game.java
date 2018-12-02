package com.smanek.Game1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // created an image to be rendered
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); // converting the image to raster and fetching each pixel in integer form (enables access to the image being rendered).
	
	public Game()
	{
		Dimension size = new Dimension(WIDTH*scale, HEIGHT*scale);
		setPreferredSize(size);
		
		frame = new JFrame();
	}
	
	public synchronized void start()
	{
		thread = new Thread(this, "Display");
		running = true;
		thread.start();
	}
	
	public void update()
	{}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) 
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.dispose(); // disposes buffer after calculation
		bs.show(); // render the buffer that has been calculated
	}

	@Override
	public void run() {
		while(running)
		{
			update();
			render();
		}
	}
	
	public synchronized void stop()
	{
		running = false;
		try 
		{
			thread.join();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("the game");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}
