public final class YearAfterFilter implements MovieFilter
{
	private int year;
	public YearAfterFilter(int year)
	{
		this.year=year;
	}
	
	public boolean satisfies(Movie movie)
	{
		return movie.getYear()>=this.year;
	}
}