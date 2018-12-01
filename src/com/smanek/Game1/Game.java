package com.smanek.Game1;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	
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

	@Override
	public void run() {
		while(running)
		{
			System.out.println("this is running..");
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
