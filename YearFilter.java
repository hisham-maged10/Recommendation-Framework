public final class YearFilter implements MovieFilter
{
	private int year;
	public YearFilter(int year)
	{
		this.year=year;
	}
	
	public boolean satisfies(Movie movie)
	{
		return movie.getYear()==this.year;
	}
}