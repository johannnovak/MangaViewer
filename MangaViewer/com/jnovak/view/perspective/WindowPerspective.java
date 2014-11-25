package jnovak.view.perspective;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowPerspective implements Observer
{
	private final static Logger				LOGGER	= LoggerFactory
															.getLogger(WindowPerspective.class);
	protected final JPanel					mainPanel;
	protected final WindowPerspectiveEnum	perspectiveEnum;

	public WindowPerspective(final WindowPerspectiveEnum perspectiveEnum)
	{
		this.mainPanel = new JPanel();
		this.perspectiveEnum = perspectiveEnum;
	}

	public JPanel getPanel()
	{
		return mainPanel;
	}

	public void init()
	{
		mainPanel.setFocusable(true);
	}

	@Override
	public void update(	final Observable observable,
						final Object currentPage)
	{
	}
}
