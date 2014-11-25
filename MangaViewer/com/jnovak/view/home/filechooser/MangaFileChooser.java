package jnovak.view.home.filechooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;

import jnovak.parameter.ParameterDesc;
import jnovak.parameter.Parameters;

public class MangaFileChooser extends Observable implements ActionListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private final JFileChooser	fileChooser;

	public MangaFileChooser()
	{
		super();
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setSelectedFile(new File(Parameters.getInstance().getProperties()
				.getProperty(ParameterDesc.SOURCE_FOLDER.toString())));
	}

	public JFileChooser getFileChooser()
	{
		return fileChooser;
	}

	public boolean isVisible()
	{
		return fileChooser.isVisible();
	}

	public void setVisible(final boolean bool)
	{
		fileChooser.setVisible(bool);
	}

	public void addActionListener(final ActionListener actionListener)
	{
		fileChooser.addActionListener(actionListener);
	}

	@Override
	public void actionPerformed(final ActionEvent event)
	{
		if (event.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
		{
			System.out.println("Accepted !");
			System.out.println(fileChooser.getSelectedFile());
			setChanged();
			notifyObservers(fileChooser.getSelectedFile());

		} else if (event.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
		{
			System.out.println("Cancelled !");
			fileChooser.setVisible(false);
		}
	}
}
