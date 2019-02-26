public final class RaterFilter implements RFilter
{
	private RFilter[] filters;
	
	public RaterFilter(RFilter... filters)
	{
		this.filters=filters;	
	}
	
	public boolean satisfies(Rater rater)
	{
		for(RFilter f:filters)
			if(!f.satisfies(rater))
				return false;
		return true;
	}
}