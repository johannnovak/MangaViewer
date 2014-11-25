package jnovak.view.perspective.pagefs;

import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jnovak.treeview.Page;
import jnovak.view.listener.pagefs.PageFullScreenActions;
import jnovak.view.perspective.WindowPerspective;
import jnovak.view.perspective.WindowPerspectiveEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowPerspectivePageFullscreen extends WindowPerspective
{
	private final static Logger	LOGGER	= LoggerFactory
												.getLogger(WindowPerspectivePageFullscreen.class);
	private Page				currentPage;
	private final JLabel		label;

	public WindowPerspectivePageFullscreen(final WindowPerspectiveEnum perspectiveEnum)
	{
		super(perspectiveEnum);
		label = new JLabel();
	}

	public void setCurrentPage(final Page currentPage)
	{
		this.currentPage = currentPage;
	}

	public Page getCurrentPage()
	{
		return currentPage;
	}

	@Override
	public void init()
	{
		super.init();
		mainPanel.add(label);
	}

	@Override
	public JPanel getPanel()
	{
		updateImageWithNewPage();
		return super.getPanel();
	}

	private void updateImageWithNewPage()
	{
		LOGGER.info(currentPage.toString());
		label.setIcon(new ImageIcon(currentPage.getPath().toString()));

	}

	@Override
	public void update(	final Observable observable,
						final Object receivedAction)
	{
		super.update(observable, currentPage);
		final PageFullScreenActions action = (PageFullScreenActions) receivedAction;
		switch (action)
		{
			case NEXT:
				if (currentPage.getNextPage() != null)
				{
					currentPage = currentPage.getNextPage();
					updateImageWithNewPage();
					updateFrame();
				} else
				{
					LOGGER.debug("Cannot go to next page : last page already");
				}
				break;
			case PREVIOUS:
				if (currentPage.getPreviousPage() != null)
				{
					currentPage = currentPage.getPreviousPage();
					updateImageWithNewPage();
					updateFrame();
				} else
				{
					LOGGER.info("Cannot go to previous page : this is the first page.");
				}
				break;
			case ZOOM:
				break;
			case DEZOOM:
				break;
			case SCROLL_DOWN:
				moveUp();
				break;
			case SCROLL_UP:
				moveDown();
				break;
		}
	}

	private void moveUp()
	{
		LOGGER.debug("Scroll Down => move up");

		if (allowedToMoveUp())
			label.setLocation(label.getX(),
					label.getY() - PageFullScreenActions.SCROLL_DOWN.getAmount());
		else
			label.setLocation(
					label.getX(),
					((JFrame) SwingUtilities.getWindowAncestor(mainPanel)).getHeight()
							- label.getHeight() - PageFullScreenActions.SCROLL_DOWN.getAmount());
	}

	private boolean allowedToMoveUp()
	{
		return (label.getY() + label.getHeight() - PageFullScreenActions.SCROLL_DOWN.getAmount() > ((JFrame) SwingUtilities
				.getWindowAncestor(mainPanel)).getHeight() ? true : false);
	}

	private void moveDown()
	{
		LOGGER.debug("Scroll up => move down");

		if (allowedToMoveDown())
			label.setLocation(label.getX(),
					label.getY() - PageFullScreenActions.SCROLL_UP.getAmount());
		else
			label.setLocation(label.getX(), 0);
	}

	private boolean allowedToMoveDown()
	{
		return (label.getY() - PageFullScreenActions.SCROLL_UP.getAmount() < 0 ? true : false);
	}

	private boolean allowedToScrollRight()
	{
		return false;
	}

	private boolean allowedToScrollLeft()
	{
		return false;
	}

	private void updateFrame()
	{
		SwingUtilities.getWindowAncestor(mainPanel).validate();
		SwingUtilities.getWindowAncestor(mainPanel).repaint();
		SwingUtilities.getWindowAncestor(mainPanel).pack();
	}
}
