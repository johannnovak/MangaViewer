package jnovak.view.home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import jnovak.view.home.button.FileChooserButton;
import jnovak.view.home.filechooser.MangaFileChooser;
import jnovak.view.parameter.panel.ParametersPanel;

public class HomePanel extends JPanel
{
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	private final FileChooserButton	fileChooserButton;
	private final MangaFileChooser	fileChooser;
	private final JButton			parameterButton;

	public HomePanel()
	{
		super();

		fileChooser = new MangaFileChooser();
		fileChooserButton = new FileChooserButton("Choisissez le dossier root du manga : ",
				fileChooser);
		parameterButton = new JButton("Paramètres");

	}

	public void init(	final JPanel mainPanel,
						final ParametersPanel parametersPanel)
	{
		final JPanel headerMenuPanel = new JPanel();
		headerMenuPanel.setPreferredSize(new Dimension(400, 50));
		headerMenuPanel.setSize(new Dimension(400, 50));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1000, 500));
		fileChooserButton.setPreferredSize(new Dimension(400, 50));
		fileChooserButton.addActionListener(fileChooserButton);
		fileChooser.setVisible(false);
		fileChooser.addActionListener(fileChooser);
		parameterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent arg0)
			{
				mainPanel.removeAll();
				mainPanel.add(parametersPanel);
				mainPanel.getParent().revalidate();
				mainPanel.getParent().repaint();
			}
		});
		headerMenuPanel.add(fileChooserButton);
		headerMenuPanel.add(parameterButton);
		this.add(headerMenuPanel, BorderLayout.PAGE_START);
		this.add(fileChooser.getFileChooser(), BorderLayout.CENTER);
		mainPanel.add(this);
	}

	public FileChooserButton getFileChooserButton()
	{
		return fileChooserButton;
	}

	public MangaFileChooser getFileChooser()
	{
		return fileChooser;
	}
}
