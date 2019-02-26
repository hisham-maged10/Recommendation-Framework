public final class LengthFilter implements MovieFilter
{
	private int length;
	public LengthFilter(int length)
	{
		this.length=length;
	}
	
	public boolean satisfies(Movie movie)
	{
		return movie.getMovieLength()>=this.length;
	}

}