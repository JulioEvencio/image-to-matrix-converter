package editortilemap;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import editortilemap.util.Rect;
import editortilemap.util.Util;

public class Editor extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private final int WIDTH;
	private final int HEIGHT;

	private final int RECT_SIZE_BASE;

	private final JFrame frame;

	private final BufferedImage renderer;

	private char currentSymbol;
	private Color currentColor;

	private final char[][] tilemap;
	private final List<Rect> rects;

	public Editor(int width, int height, int rectSizeBase) {
		this.WIDTH = width;
		this.HEIGHT = height;

		this.RECT_SIZE_BASE = rectSizeBase;

		this.frame = new JFrame();

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle(Util.TITLE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EmptyBorder(0, 10, 0, 10));
		frame.setJMenuBar(menuBar);

		JMenu menuGame = new JMenu("File");
		menuBar.add(menuGame);

		menuGame.add(Util.createJMenuItem("New File", () -> System.out.println("New File")));
		menuGame.add(Util.createJMenuItem("Save File", () -> System.out.println("Save File")));
		menuGame.add(Util.createJMenuItem("Load File", () -> System.out.println("Load File")));
		menuGame.add(Util.createJMenuItem("Quit", () -> Editor.exit()));

		this.setPreferredSize(new Dimension(this.WIDTH * this.RECT_SIZE_BASE, this.HEIGHT * this.RECT_SIZE_BASE));
		this.frame.add(this);

		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

		this.renderer = new BufferedImage(this.WIDTH * this.RECT_SIZE_BASE, this.HEIGHT * this.RECT_SIZE_BASE,
				BufferedImage.TYPE_INT_RGB);

		this.currentSymbol = '0';
		this.currentColor = Color.WHITE;

		this.tilemap = new char[this.WIDTH][this.HEIGHT];
		this.rects = new ArrayList<>();

		for (int i = 0; i < this.WIDTH; i++) {
			for (int j = 0; j < this.HEIGHT; j++) {
				this.tilemap[i][j] = this.currentSymbol;
				this.rects.add(new Rect(this.RECT_SIZE_BASE * i, this.RECT_SIZE_BASE * j, this.RECT_SIZE_BASE,
						this.RECT_SIZE_BASE, this.currentSymbol, this.currentColor));
			}
		}
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

		render.setColor(Color.WHITE);
		render.fillRect(0, 0, this.WIDTH * this.RECT_SIZE_BASE, this.HEIGHT * this.RECT_SIZE_BASE);

		render.setColor(Color.BLACK);

		for (Rect rect : rects) {
			rect.render(render);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.WIDTH * this.RECT_SIZE_BASE, this.HEIGHT * this.RECT_SIZE_BASE);

		graphics.drawImage(this.renderer, 0, 0, this.WIDTH * this.RECT_SIZE_BASE, this.HEIGHT * this.RECT_SIZE_BASE,
				null);

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

	public static void exit() {
		System.exit(0);
	}

	public static void exitWithError(String message, Exception error) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
		Editor.exit();
	}

}
