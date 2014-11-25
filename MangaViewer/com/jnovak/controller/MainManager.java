package jnovak.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Observable;
import java.util.Observer;

import jnovak.treeview.TreeView;
import jnovak.view.MainFrame;
import jnovak.view.home.filechooser.MangaFileChooser;
import jnovak.view.perspective.WindowPerspectiveEnum;
import jnovak.view.perspective.pagefs.WindowPerspectivePageFullscreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainManager implements Observer
{
	private final static Logger	LOGGER	= LoggerFactory.getLogger(MainManager.class);
	private final TreeView		treeView;
	private final MainFrame		mainFrame;
	private final MangaManager	mangaManager;

	public MainManager() throws IOException
	{
		treeView = new TreeView();
		mangaManager = new MangaManager();
		mainFrame = new MainFrame(mangaManager);

		mainFrame.addObserverForFileChooser(this);
		mangaManager.addObserver(mainFrame.getWindowPerspectiveManager().get(
				WindowPerspectiveEnum.PAGE_FULLSCREEN));
		((WindowPerspectivePageFullscreen) mainFrame.getWindowPerspectiveManager().get(
				WindowPerspectiveEnum.PAGE_FULLSCREEN)).setCurrentPage(mangaManager
				.getCurrentPage());

		mainFrame.init("MangaViewer");
	}

	@Override
	public void update(	final Observable observable,
						final Object file)
	{
		LOGGER.info("UPDATE !");
		if (observable instanceof MangaFileChooser)
		{
			LOGGER.info("Received file : " + file);
			try
			{
				final Path path = ((File) file).toPath();
				LOGGER.info("TreeView initializing");
				treeView.initMangaTreeView(path);
				LOGGER.info("TreeView OK");

				LOGGER.info("MangaManager initializing");
				mangaManager.init(treeView);
				LOGGER.info("MangaManager OK");

				((WindowPerspectivePageFullscreen) mainFrame.getWindowPerspectiveManager().get(
						WindowPerspectiveEnum.PAGE_FULLSCREEN)).setCurrentPage(mangaManager
						.getCurrentPage());

				LOGGER.info("Changing perspective");
				mainFrame.changePerspectiveTo(WindowPerspectiveEnum.PAGE_FULLSCREEN);
			} catch (final IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
