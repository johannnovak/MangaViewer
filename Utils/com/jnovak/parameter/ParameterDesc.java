package jnovak.parameter;

public enum ParameterDesc
{
	SOURCE_FOLDER("Manga source folder"),
	CHAPTER_PATTERN("Chapter pattern"),
	PAGE_PATTERN("Page pattern"),
	MOUSE_WHEEL_SPEED("Mouse Wheel Speed");

	private String	label;

	ParameterDesc(final String label)
	{
		this.label = label;
	}

	public String getLabel()
	{
		return label;
	}
}
