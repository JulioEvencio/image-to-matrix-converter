package editortilemap;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import editortilemap.util.Util;

public class Editor extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = 640;
	private final int HEIGHT = 480;

	private final JFrame frame;

	private final BufferedImage renderer;

	public Editor() {
		this.frame = new JFrame();

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle(Util.TITLE);
		this.frame.setSize(this.WIDTH, this.HEIGHT);
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

		this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
		this.frame.add(this);

		this.renderer = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);
	}

	private void tick() {
		// Code
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, this.WIDTH, this.HEIGHT);

		// Code

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.WIDTH, this.HEIGHT);

		graphics.drawImage(this.renderer, 0, 0, this.WIDTH, this.HEIGHT, null);

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();

		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		while (true) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();
			}
		}
	}

}
