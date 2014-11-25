package jnovak.treeview;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.InfoParser;

public class Chapter
{
	private final int			chapterNumber;
	private final String		chapterTitle;
	private final List<Page>	pages;
	private final Path			path;
	private Chapter				nextChapter;

	public Chapter(final Path path)
	{
		final String[] chapterInfos = InfoParser.getInstance().parseChapter(
				path.getFileName().toString());
		this.chapterNumber = Integer.parseInt(chapterInfos[0]);
		this.chapterTitle = chapterInfos[1];
		this.pages = new ArrayList<Page>();
		this.path = path;
	}

	public void addPage(final Path path)

	{
		boolean stop = false;
		int i = 0;
		final Page page = new Page(path);
		while (!stop && i < pages.size())
		{
			if (pages.get(i).compareTo(page) < 1)
				++i;
			else
				stop = true;
		}
		pages.add(i, page);
	}

	public void configurePageDependencies()
	{
		int i = 0;

		pages.get(i).setPreviousPage(null);
		pages.get(i).setNextPage(pages.get(i + 1));

		for (i = 1; i < pages.size() - 1; ++i)
		{
			pages.get(i).setNextPage(pages.get(i + 1));
			pages.get(i).setPreviousPage(pages.get(i - 1));
		}
		pages.get(i).setPreviousPage(pages.get(i - 1));
		pages.get(i).setNextPage(nextChapter == null ? null : nextChapter.getPages().get(0));
	}

	public int getNumber()
	{
		return chapterNumber;
	}

	public String getTitle()
	{
		return chapterTitle;
	}

	public List<Page> getPages()
	{
		return pages;
	}

	public void setNextChapter(final Chapter nextChapter)
	{
		this.nextChapter = nextChapter;
	}

	@Override
	public String toString()
	{
		return "Chapter " + chapterNumber + "\t: \"" + chapterTitle + "\"";
	}

	public int compareTo(final Chapter chapter)
	{
		if (chapterNumber < chapter.getNumber())
		{
			return -1;
		} else if (chapterNumber == chapter.getNumber())
		{
			return 0;
		} else
		{
			return 1;
		}
	}
}
