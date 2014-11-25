package jnovak.view.perspective.home;

import jnovak.view.home.HomePanel;
import jnovak.view.home.filechooser.MangaFileChooser;
import jnovak.view.parameter.panel.ParametersPanel;
import jnovak.view.perspective.WindowPerspective;
import jnovak.view.perspective.WindowPerspectiveEnum;

public class WindowPerspectiveHome extends WindowPerspective
{
	private final ParametersPanel	parametersPanel;
	private final HomePanel			homePanel;

	public WindowPerspectiveHome(final WindowPerspectiveEnum perspectiveEnum)
	{
		super(perspectiveEnum);
		parametersPanel = new ParametersPanel();
		homePanel = new HomePanel();
	}

	@Override
	public void init()
	{
		super.init();
		homePanel.init(mainPanel, parametersPanel);
		parametersPanel.init(mainPanel, homePanel);
	}

	public MangaFileChooser getFileChooser()
	{
		return homePanel.getFileChooser();
	}

	public HomePanel getHomePanel()
	{
		return homePanel;
	}
}
