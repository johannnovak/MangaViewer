package jnovak.treeview;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Manga
{
	private final static Logger	LOGGER	= LoggerFactory.getLogger(Manga.class);
	private final String		mangaTitle;
	private final List<Chapter>	chapters;

	public Manga(final Path path)
	{
		this.mangaTitle = path.getFileName().toString();
		chapters = new ArrayList<Chapter>();
	}

	public List<Chapter> getChapters()
	{
		return chapters;
	}

	public Chapter addChapter(final Path path)
	{
		boolean stop = false;
		int i = 0;
		final Chapter chapter = new Chapter(path);
		LOGGER.debug("Adding Chapter");
		while (!stop && i < chapters.size())
		{
			if (chapters.get(i).compareTo(chapter) < 1)
				++i;
			else
				stop = true;
		}
		chapters.add(i, chapter);
		return chapter;
	}

	public void configureChapterDependencies()
	{
		for (int i = 0; i < chapters.size() - 1; ++i)
		{
			chapters.get(i).setNextChapter(chapters.get(i + 1));
			chapters.get(i).configurePageDependencies();
		}
		chapters.get(chapters.size() - 1).setNextChapter(null);
		chapters.get(chapters.size() - 1).configurePageDependencies();
	}

	public String getTitle()
	{
		return mangaTitle;
	}

	@Override
	public String toString()
	{
		String disp = "";
		disp += "Manga : " + mangaTitle;
		disp += "\nChapter nb : " + chapters.size() + "\n";
		for (final Chapter chapter : chapters)
		{
			disp += chapter + "\n";
			for (final Page page : chapter.getPages())
				disp += page + "\n";
		}
		return disp;
	}
}
