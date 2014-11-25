package jnovak.treeview;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jnovak.parameter.ParameterDesc;
import jnovak.parameter.Parameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.InfoParser;

public class TreeView implements FileVisitor<Path>
{
	private final static Logger	LOGGER	= LoggerFactory.getLogger(TreeView.class);
	private boolean				isRootDirectory;
	private Manga				manga;
	private Chapter				lastAddedChapter;

	public TreeView()
	{
		this.isRootDirectory = true;
		InfoParser.getInstance().setChapterParseRule("u u n t *", " ");
		InfoParser.getInstance().setPageParseRule("u.u-n");
	}

	public TreeView(final Path sourceFolder) throws IOException
	{
		this.isRootDirectory = true;
		InfoParser.getInstance().setChapterParseRule(
				Parameters.getInstance().getProperties()
						.getProperty(ParameterDesc.CHAPTER_PATTERN.toString()), " ");
		InfoParser.getInstance().setPageParseRule(
				Parameters.getInstance().getProperties()
						.getProperty(ParameterDesc.CHAPTER_PATTERN.toString()));
		initMangaTreeView(sourceFolder);
	}

	public void initMangaTreeView(final Path sourceFolder) throws IOException
	{
		Files.walkFileTree(sourceFolder, this);
		LOGGER.debug(manga.toString());
	}

	@Override
	public FileVisitResult postVisitDirectory(	final Path path,
												final IOException e) throws IOException
	{
		if (path.getFileName().toString().equals(manga.getTitle()))
		{
			manga.configureChapterDependencies();
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(	final Path path,
												final BasicFileAttributes attr) throws IOException
	{
		if (isRootDirectory)
		{
			isRootDirectory = false;
			manga = new Manga(path);
		} else
			lastAddedChapter = manga.addChapter(path);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(	final Path path,
										final BasicFileAttributes attr) throws IOException
	{
		if (!attr.isDirectory())
		{
			lastAddedChapter.addPage(path);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(	final Path path,
											final IOException e) throws IOException
	{
		LOGGER.error("Visit failed");
		return FileVisitResult.TERMINATE;
	}

	public Manga getManga()
	{
		return manga;
	}
}
