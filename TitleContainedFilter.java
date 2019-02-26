public final class TitleContainedFilter implements MovieFilter
{
	private String title;
	public TitleContainedFilter(String title)
	{
		this.title=title;
	}
	
	public boolean satisfies(Movie m)
	{
		return m.getMovieTitle().toLowerCase().contains(this.title.toLowerCase());
	}
}