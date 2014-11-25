package utils;

import org.apache.commons.lang3.StringUtils;

public class InfoParser
{
	private static InfoParser	infoParser	= new InfoParser();
	private String				chapterToken;
	private String[]			chapterRules;
	private String				pageToken;
	private String[]			pageRules;

	private InfoParser()
	{
		chapterToken = "";
		pageToken = "";
	}

	public static InfoParser getInstance()
	{
		return infoParser;
	}

	public void setChapterParseRule(final String example,
									final String token)
	{
		// TODO virer token en splittant les u n * ...
		chapterToken += token.toCharArray()[0];
		for (int i = 1; i < token.toCharArray().length; ++i)
			chapterToken += "|" + token.toCharArray()[i];
		chapterRules = example.split(chapterToken);
	}

	public String[] parseChapter(String stringToParse)
	{
		final String[] infos = { "", "" };

		stringToParse = StringUtils.normalizeSpace(stringToParse);
		final String[] stringSplit = stringToParse.split(chapterToken);
		boolean star = false;

		for (int i = 0; i < stringSplit.length; ++i)
		{
			if (star)
			{
				infos[1] += stringSplit[i] + chapterToken;
			} else
			{
				if (chapterRules[i].equals("n"))
				{
					infos[0] = stringSplit[i];
				} else if (chapterRules[i].equals("t"))
				{
					infos[1] += stringSplit[i] + chapterToken;
				} else if (chapterRules[i].equals("*"))
				{
					star = true;
					--i;
				}
			}
		}
		infos[1] = StringUtils.chop(infos[1]);
		return infos;
	}

	public void setPageParseRule(final String example)
	{
		pageToken = "\\W";
		pageRules = example.split(pageToken);
	}

	public int parsePage(String stringToParse)
	{
		String info = null;
		boolean stop = false;
		int i = 0;

		stringToParse = StringUtils.normalizeSpace(stringToParse);
		final String[] stringSplit = stringToParse.split(pageToken);
		while (!stop && i < stringSplit.length)
		{
			if (chapterRules[i].equals("n"))
			{
				info = stringSplit[i];
				stop = true;
			}
			++i;
		}

		return Integer.parseInt(info);
	}
}
