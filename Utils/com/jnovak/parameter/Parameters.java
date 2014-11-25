package jnovak.parameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parameters
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	private final static Logger		LOGGER				= LoggerFactory.getLogger(Parameters.class);
	private final static Parameters	parameters			= new Parameters();
	private final Properties		properties;

	private Parameters()
	{
		properties = new Properties();
	}

	public void loadPropertiesFromFile(final String path) throws IOException
	{
		final InputStream in = new FileInputStream(path);
		properties.load(in);
		LOGGER.debug((properties.keySet()).toString());
	}

	public static Parameters getInstance()
	{
		return parameters;
	}

	public Properties getProperties()
	{
		return properties;
	}
}
