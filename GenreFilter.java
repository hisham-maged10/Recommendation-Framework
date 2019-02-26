public final class GenreFilter implements MovieFilter
{
	private String genre;
	public GenreFilter(String genre)
	{
		this.genre=genre;
	}	

	public boolean satisfies(Movie movie)
	{
		return movie.getGenres().toLowerCase().contains(this.genre.toLowerCase());
	}
}