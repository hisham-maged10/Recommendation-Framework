public final class PosterFilter implements MovieFilter
{
	private String url="n/a";
	public boolean satisfies(Movie m)
	{
		return m.getPosterURL().equalsIgnoreCase(url);
	}
}