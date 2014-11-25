package jnovak.treeview;

import java.nio.file.Path;

import utils.InfoParser;

public class Page
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private final int			pageNumber;
	private final Path			path;
	private Page				nextPage;
	private Page				previousPage;

	public Page(final Path path)
	{
		final int pageInfo = InfoParser.getInstance().parsePage(path.getFileName().toString());
		this.pageNumber = pageInfo;
		this.path = path;
	}

	public int getPageNumber()
	{
		return pageNumber;
	}

	public Page getNextPage()
	{
		return nextPage;
	}

	public Page getPreviousPage()
	{
		return previousPage;
	}

	public Path getPath()
	{
		return path;
	}

	public void setNextPage(final Page nextPage)
	{
		this.nextPage = nextPage;
	}

	public void setPreviousPage(final Page previousPage)
	{
		this.previousPage = previousPage;
	}

	@Override
	public String toString()
	{
		return "Page number " + pageNumber;
	}

	public int compareTo(final Page page)
	{
		if (pageNumber < page.getPageNumber())
		{
			return -1;
		} else if (pageNumber == page.getPageNumber())
		{
			return 0;
		} else
		{
			return 1;
		}
	}
}
