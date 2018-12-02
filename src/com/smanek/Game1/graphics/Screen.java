package com.smanek.Game1.graphics;

public class Screen {
	private int WIDTH, HEIGHT;
	public int[] pixels;

	private int time = 0;
	private int counter = 0;

	public Screen(int Width, int Height) {
		this.HEIGHT = Height;
		this.WIDTH = Width;
		pixels = new int[Width * Height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {

		counter++;
		if (counter % 2 == 0) {
			time++;
		}

		for (int y = 0; y < this.HEIGHT; y++) {
			for (int x = 0; x < this.WIDTH; x++)
				if (x % 10 == 0 && y % 10 == 0) {
					try {
						pixels[time + 50 * this.WIDTH] = 0xFF00FF;
					} catch (ArrayIndexOutOfBoundsException e) {
						time = 0;
					}
				}
		}

	}

}
