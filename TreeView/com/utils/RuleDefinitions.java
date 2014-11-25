package utils;

public enum RuleDefinitions
{
	USELESS('s'),
	NUMBER('n'),
	TITLE('t');

	private char	charCode;

	private RuleDefinitions(final char charCode)
	{
		this.charCode = charCode;
	}

	public char getCharCode()
	{
		return charCode;
	}
}