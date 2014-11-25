package jnovak.view.perspective;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jnovak.parameter.Parameters;
import jnovak.view.perspective.home.WindowPerspectiveHome;
import jnovak.view.perspective.pagefs.WindowPerspectivePageFullscreen;
import jnovak.view.perspective.tree.WindowPerspectiveTreeExplorer;
import jnovak.view.perspective.treepage.WindowPerspectiveTreeAndPage;

public class WindowPerspectiveManager
{
	private final Map<WindowPerspectiveEnum, WindowPerspective>	perspectiveMap;
	private final WindowPerspective								currentPerspective;

	public WindowPerspectiveManager() throws IOException
	{
		Parameters.getInstance().loadPropertiesFromFile("resources/parameters.properties");

		perspectiveMap = new HashMap<WindowPerspectiveEnum, WindowPerspective>();
		perspectiveMap.put(WindowPerspectiveEnum.HOME, new WindowPerspectiveHome(
				WindowPerspectiveEnum.HOME));
		perspectiveMap.put(WindowPerspectiveEnum.TREE_EXPLORER, new WindowPerspectiveTreeExplorer(
				WindowPerspectiveEnum.TREE_EXPLORER));
		perspectiveMap.put(WindowPerspectiveEnum.TREE_AND_PAGE, new WindowPerspectiveTreeAndPage(
				WindowPerspectiveEnum.TREE_AND_PAGE));
		perspectiveMap.put(WindowPerspectiveEnum.PAGE_FULLSCREEN,
				new WindowPerspectivePageFullscreen(WindowPerspectiveEnum.PAGE_FULLSCREEN));
		currentPerspective = perspectiveMap.get(WindowPerspectiveEnum.HOME);
	}

	public void init()
	{
		final Iterator<WindowPerspectiveEnum> iterator = perspectiveMap.keySet().iterator();
		while (iterator.hasNext())
		{
			final WindowPerspectiveEnum perspectiveEnum = iterator.next();
			perspectiveMap.get(perspectiveEnum).init();
		}
	}

	public WindowPerspective getCurrentPerspective()
	{
		return currentPerspective;
	}

	public WindowPerspective get(final WindowPerspectiveEnum perspectiveEnum)
	{
		return perspectiveMap.get(perspectiveEnum);
	}
}
