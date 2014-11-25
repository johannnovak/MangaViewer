package jnovak.view.parameter.composedObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ParameterField
{
	private final JLabel	label;
	private final JTextArea	textArea;
	private String			defaultText;

	public ParameterField(final String label, final String defaultText)
	{
		this.label = new JLabel(label);
		this.defaultText = defaultText;
		this.textArea = new JTextArea(this.defaultText);

		final Border rounded = new LineBorder(Color.black);
		final Border empty = new EmptyBorder(5, 5, 5, 5);
		final Border border = new CompoundBorder(rounded, empty);
		final Insets inset = new Insets(10, 10, 10, 10);
		final Font font = new Font("Calibri", Font.CENTER_BASELINE, 15);

		textArea.setEditable(true);
		textArea.setBackground(Color.white);
		textArea.setBorder(border);
		textArea.setFont(font);
		textArea.setColumns(20);
		textArea.setLineWrap(true);
	}

	public JLabel getLabel()
	{
		return label;
	}

	public JTextArea getTextArea()
	{
		return textArea;
	}

	public void resetTextArea()
	{
		textArea.setText(defaultText);
	}

	public void saveTextArea()
	{
		defaultText = textArea.getText();
	}
}
