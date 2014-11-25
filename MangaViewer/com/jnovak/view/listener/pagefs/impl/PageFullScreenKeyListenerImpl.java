package jnovak.view.listener.pagefs.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;

import jnovak.parameter.ParameterDesc;
import jnovak.parameter.Parameters;
import jnovak.view.listener.pagefs.PageFullScreenActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageFullScreenKeyListenerImpl extends Observable implements KeyListener,
		MouseWheelListener
{

	private final static Logger	LOGGER	= LoggerFactory
												.getLogger(PageFullScreenKeyListenerImpl.class);

	@Override
	public void keyPressed(final KeyEvent key)
	{
		if (key.getKeyCode() == KeyEvent.VK_N)
		{
			LOGGER.debug("Key for next page !");
			setChanged();
			notifyObservers(PageFullScreenActions.NEXT);
		} else if (key.getKeyCode() == KeyEvent.VK_P)
		{
			LOGGER.debug("Key for previous page !");
			setChanged();
			notifyObservers(PageFullScreenActions.PREVIOUS);
		}
	}

	@Override
	public void keyReleased(final KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(final KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent event)
	{
		final int steps = event.getWheelRotation();
		final int amount = event.getUnitsToScroll()
				* Integer.parseInt(Parameters.getInstance().getProperties()
						.getProperty(ParameterDesc.MOUSE_WHEEL_SPEED.toString()));
		setChanged();
		if (steps < 0)
		{
			PageFullScreenActions.SCROLL_UP.setAmount(amount);
			notifyObservers(PageFullScreenActions.SCROLL_UP);
		} else
		{
			PageFullScreenActions.SCROLL_DOWN.setAmount(amount);
			notifyObservers(PageFullScreenActions.SCROLL_DOWN);
		}
	}
}
