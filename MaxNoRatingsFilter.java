public final class MaxNoRatingsFilter implements RFilter
{
	private int needed;
	public MaxNoRatingsFilter(int needed)
	{
		this.needed=needed;
	}
	
	public boolean satisfies(Rater r)
	{
		return r.getNumberOfRatings()==this.needed;
	}
}