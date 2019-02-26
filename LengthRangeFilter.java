public class LengthRangeFilter implements MovieFilter
{
	private int minLength,maxLength;
	public LengthRangeFilter(int minLength,int maxLength)
	{
		this.minLength=minLength;
		this.maxLength=maxLength;
	}
	
	public boolean satisfies(Movie m)
	{
		int movieLength=m.getMovieLength();
		return (movieLength>=this.minLength && movieLength<=this.maxLength);
	}
}