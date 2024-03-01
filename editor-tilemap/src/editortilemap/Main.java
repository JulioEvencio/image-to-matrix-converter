package editortilemap;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Thread(new Editor(5, 5, 64)).start();
		});
	}

}
