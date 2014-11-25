package jnovak.view.parameter.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jnovak.parameter.ParameterDesc;
import jnovak.parameter.Parameters;
import jnovak.view.home.HomePanel;
import jnovak.view.parameter.composedObjects.ParameterField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParametersPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long							serialVersionUID	= 1L;
	private final static Logger							LOGGER				= LoggerFactory
																					.getLogger(ParametersPanel.class);
	private final JButton								backButton;
	private final JButton								saveButton;
	private final JLabel								labelHeader;
	private final Map<ParameterDesc, ParameterField>	parametersMap;
	private final JPanel								headerPanel;
	private final JPanel								parametersPanel;
	private final JPanel								footerPanel;

	private boolean										saved;

	public ParametersPanel()
	{
		super();
		headerPanel = new JPanel();
		parametersPanel = new JPanel();
		footerPanel = new JPanel();

		backButton = new JButton("Back to home");
		saveButton = new JButton("Save");

		labelHeader = new JLabel("Parameters", SwingConstants.CENTER);

		parametersMap = new HashMap<ParameterDesc, ParameterField>();

		for (final ParameterDesc desc : ParameterDesc.values())
			parametersMap.put(desc, new ParameterField(desc.getLabel(), Parameters.getInstance()
					.getProperties().getProperty(desc.toString())));

		saved = false;
	}

	public void init(	final JPanel mainPanel,
						final HomePanel homePanel)
	{
		backButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent arg0)
			{
				mainPanel.removeAll();
				mainPanel.add(homePanel);
				mainPanel.getParent().revalidate();
				mainPanel.getParent().repaint();
				if (!saved)
					for (final ParameterDesc label : parametersMap.keySet())
						parametersMap.get(label).resetTextArea();
			}
		});

		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent arg0)
			{
				for (final ParameterDesc label : parametersMap.keySet())
				{
					parametersMap.get(label).saveTextArea();
					Parameters
							.getInstance()
							.getProperties()
							.setProperty(label.toString(),
									parametersMap.get(label).getTextArea().getText());
				}
				try
				{
					Parameters
							.getInstance()
							.getProperties()
							.store(new FileOutputStream(new File("resources/parameters.properties")),
									"");
				} catch (final IOException e)
				{
					LOGGER.error("Error when storing the property file", e);
				}
				saved = false;
			}
		});

		this.setPreferredSize(new Dimension(1000, 1000));
		this.setLayout(new GridLayout(4, 1));
		parametersPanel.setLayout(new GridBagLayout());
		headerPanel.setPreferredSize(new Dimension(400, 50));
		footerPanel.setPreferredSize(new Dimension(400, 50));

		final Font fontHeader = new Font("Calibri", Font.BOLD, 24);
		labelHeader.setFont(fontHeader);
		labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
		// headerPanel.setBackground(Color.orange);
		// headerPanel.setOpaque(true);
		headerPanel.add(labelHeader);

		final GridBagConstraints gbc = new GridBagConstraints();

		parametersPanel.setPreferredSize(new Dimension(400, 400));

		gbc.insets = new Insets(10, 10, 0, 0);

		for (int i = 0; i < ParameterDesc.values().length; ++i)
		{
			gbc.gridx = 0;
			gbc.gridy = i;
			parametersPanel.add(parametersMap.get(ParameterDesc.values()[i]).getLabel(), gbc);

			gbc.gridx = 1;
			gbc.gridy = i;
			parametersPanel.add(parametersMap.get(ParameterDesc.values()[i]).getTextArea(), gbc);
		}

		footerPanel.add(backButton);
		footerPanel.add(saveButton);
		// footerPanel.setBackground(Color.gray);
		// footerPanel.setOpaque(true);

		this.add(headerPanel);
		this.add(parametersPanel);
		this.add(footerPanel);

	}
}
