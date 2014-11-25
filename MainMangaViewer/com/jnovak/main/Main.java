package jnovak.main;

import java.io.IOException;

import jnovak.controller.MainManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{
	private final static Logger	LOGGER	= LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) throws IOException
	{
		LOGGER.info("Lancement du main");
		final MainManager manager = new MainManager();
	}
}
