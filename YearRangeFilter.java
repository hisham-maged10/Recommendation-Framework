public final class YearRangeFilter implements MovieFilter
{
	private int minYear,maxYear;
	public YearRangeFilter(int minYear,int maxYear)
	{
		this.minYear=minYear;
		this.maxYear=maxYear;
	}
	
	public boolean satisfies(Movie movie)
	{
		int year=movie.getYear();
		return (year>=this.minYear && year<=this.maxYear);
	}
}