package editortilemap.util;

import java.awt.Color;
import java.awt.Graphics;

public class Rect {

	private int x;
	private int y;

	private int width;
	private int height;

	private char symbol;

	private Color color;

	public Rect(int x, int y, int width, int height, char symbol, Color color) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.symbol = symbol;

		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean wasClicked(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect(this.x, this.y, this.width, this.height);

		render.setColor(Color.BLACK);
		render.drawRect(this.x, this.y, this.width, this.height);
	}

}
