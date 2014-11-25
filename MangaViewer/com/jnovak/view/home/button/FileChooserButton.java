package jnovak.view.home.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import jnovak.view.home.filechooser.MangaFileChooser;

public class FileChooserButton extends JButton implements ActionListener
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	private final MangaFileChooser	fileChooser;

	public FileChooserButton(final String label, final MangaFileChooser fileChooser)
	{
		super(label);
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(final ActionEvent arg0)
	{
		fileChooser.setVisible(!fileChooser.isVisible());
	}

}
