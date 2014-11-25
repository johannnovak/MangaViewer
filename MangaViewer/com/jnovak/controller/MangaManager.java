package jnovak.controller;

import java.util.Observable;

import jnovak.treeview.Chapter;
import jnovak.treeview.Page;
import jnovak.treeview.TreeView;

public class MangaManager extends Observable
{
	private Chapter	currentChapter;
	private Page	currentPage;

	public MangaManager()
	{
	}

	public void init(final TreeView treeView)
	{
		currentChapter = treeView.getManga().getChapters().get(0);
		currentPage = currentChapter.getPages().get(0);
	}

	public Page getCurrentPage()
	{
		return currentPage;
	}
}
