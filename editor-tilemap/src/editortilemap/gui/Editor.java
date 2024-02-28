package editortilemap.gui;

import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editortilemap.util.Util;

public class Editor extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JPanel panel;

	private final JPanel panelFile;
	private final JButton buttonSelectFile;

	// private final Canvas canvasFile;

	public Editor() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Util.TITLE);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		this.add(this.panel);

		this.panelFile = new JPanel();
		this.panelFile.setLayout(new BoxLayout(this.panelFile, BoxLayout.Y_AXIS));
		this.panel.add(this.panelFile);

		this.buttonSelectFile = new JButton("Select File");
		this.buttonSelectFile.setFont(Util.getFontDefault());
		this.buttonSelectFile.addActionListener((ActionListener) -> {
			JFileChooser fileTileMap = new JFileChooser();

			if (fileTileMap.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				System.out.println(fileTileMap.getSelectedFile().getAbsolutePath());
			}
		});
		this.panelFile.add(this.buttonSelectFile);

		// this.panelFile.add(new Gra)

		// this.canvasFile = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		// this.panelFile.add(this.canvasFile);
	}

	@Override
	public void paint(Graphics g) {
		g.drawRect(300, 300, 200, 200);
	}

}
