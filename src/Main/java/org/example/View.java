package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class View extends JFrame {

	protected ViewMenuBar menuBar;
	protected ViewTopBar topBar;
	protected BufferedImage img;
	protected Rectangle rect[];

	private static int WINDOW_W = 600;
	private static int WINDOW_H = 650;

	public View(ViewMenuBar menuBar, ViewTopBar topBar, int lvl){
		super();

		this.menuBar = menuBar;
		this.topBar = topBar;

		try {
			img = ImageIO.read(new File("img/ugly.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		initRect(lvl);
		setJMenuBar(this.menuBar);
		setTitle("Puzzle");
		setSize(WINDOW_W, WINDOW_H);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setFocusable(true);
	}

	public void initRect(int lvl){
		int i, j, index, rectSize = WINDOW_W / lvl;

		rect = new Rectangle[lvl * lvl];

		for(i = 0; i < rect.length; i++)
			rect[i] = new Rectangle();

		for(j = 0; j < lvl; j++)
		{
			for(i = 0; i < lvl; i++)
			{
				index = i * lvl + j;

				rect[index].x = i * rectSize;
				rect[index].y = j * rectSize;
				rect[index].width = rect[index].x + rectSize;
				rect[index].height = rect[index].y + rectSize;
			}
		}
	}

	public BufferedImage getImg()
	{
		return img;
	}

	public Rectangle[] getRect()
	{
		return rect;
	}

	public void setKeyListener(KeyListener keyListener)
	{
		this.addKeyListener(keyListener);
	}
}
