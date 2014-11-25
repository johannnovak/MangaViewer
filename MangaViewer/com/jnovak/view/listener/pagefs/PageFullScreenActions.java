package jnovak.view.listener.pagefs;

public enum PageFullScreenActions
{
	NEXT(0),
	PREVIOUS(0),
	ZOOM(0),
	DEZOOM(0),
	SCROLL_UP(0),
	SCROLL_DOWN(0);

	private int	amount;

	PageFullScreenActions(final int amount)
	{
		this.amount = amount;
	}

	public void setAmount(final int amount)
	{
		this.amount = amount;
	}

	public int getAmount()
	{
		return amount;
	}
}
