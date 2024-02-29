package editortilemap.util;

import java.awt.Font;

import javax.swing.JMenuItem;

public class Util {

	public static final String TITLE = "Editor Tilemap";

	public static Font getFontDefault() {
		return new Font("Sans Serif", Font.BOLD, 25);
	}

	public static JMenuItem createJMenuItem(String text, Event event) {
		JMenuItem menuItem = new JMenuItem(text);

		menuItem.addActionListener((ActionEvent) -> {
			event.onEvent();
		});

		return menuItem;
	}

}
