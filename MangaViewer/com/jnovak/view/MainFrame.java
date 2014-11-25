package jnovak.view;

import java.io.IOException;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jnovak.controller.MangaManager;
import jnovak.view.listener.pagefs.impl.PageFullScreenKeyListenerImpl;
import jnovak.view.perspective.WindowPerspectiveEnum;
import jnovak.view.perspective.WindowPerspectiveManager;
import jnovak.view.perspective.home.WindowPerspectiveHome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainFrame
{
	private final static Logger					LOGGER	= LoggerFactory.getLogger(MainFrame.class);
	private final JFrame						frame;
	private final JPanel						framePanel;
	private final WindowPerspectiveManager		windowPerspectiveManager;
	private final PageFullScreenKeyListenerImpl	pageFullScreenKeyListener;

	public MainFrame(final MangaManager MangaManager) throws IOException
	{
		frame = new JFrame();
		framePanel = new JPanel();
		windowPerspectiveManager = new WindowPerspectiveManager();
		windowPerspectiveManager.init();
		pageFullScreenKeyListener = new PageFullScreenKeyListenerImpl();
	}

	public void init(final String title)
	{
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);

		frame.setContentPane(framePanel);
		framePanel.add(windowPerspectiveManager.get(WindowPerspectiveEnum.HOME).getPanel());

		pageFullScreenKeyListener.addObserver(windowPerspectiveManager
				.get(WindowPerspectiveEnum.PAGE_FULLSCREEN));

		frame.setVisible(true);
		((WindowPerspectiveHome) windowPerspectiveManager.getCurrentPerspective()).getHomePanel()
				.getFileChooserButton().requestFocus();
	}

	public void addObserverForFileChooser(final Observer obs)
	{
		((WindowPerspectiveHome) windowPerspectiveManager.get(WindowPerspectiveEnum.HOME))
				.getFileChooser().addObserver(obs);
	}

	public void changePerspectiveTo(final WindowPerspectiveEnum newWindowPerspective)
	{
		framePanel.removeAll();
		framePanel.add(windowPerspectiveManager.get(newWindowPerspective).getPanel());
		// frame.revalidate();
		// frame.repaint();
		frame.pack();
		framePanel.requestFocusInWindow();
		framePanel.addKeyListener(pageFullScreenKeyListener);
		framePanel.addMouseWheelListener(pageFullScreenKeyListener);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public WindowPerspectiveManager getWindowPerspectiveManager()
	{
		return windowPerspectiveManager;
	}
}
