public class Movie
{
	/*
	*8 fields to store imdbID,title,year,genres (can be multiple genres and will be separated with commas),director (can be multiple and separated by commas)
	*,countries filmed in (can bed multiple countries and will be separated by commas)
	*, movie length in minutes, poster url of imdb and N/A if doesn't exist
	*/
	private String imdbID;
	private String movieTitle;
	private int year;
	private String genres;
	private String directors;
	private String countries;
	private int movieLength;
	private String posterURL;
	
	public Movie(String imdbID,String movieTitle,int year,String countries,String genres,String directors,int movieLength,String posterURL)
	{
		this.imdbID=imdbID;
		this.movieTitle=movieTitle;
		this.year=year;
		this.genres=genres;
		this.directors=directors;
		this.countries=countries;
		this.movieLength=movieLength;
		this.posterURL=posterURL==null?"N/A":posterURL;
	}	
	
	public Movie(String imdbID,String movieTitle,int year,String genres,String directors,String countries,int movieLength)
	{
		this(imdbID,movieTitle,year,genres,directors,countries,movieLength,null);
	}
	
	public String getImdbID()
	{
		return this.imdbID;
	}

	public String getMovieTitle()
	{
		return this.movieTitle;
	}
	
	public int getYear()
	{
		return this.year;
	}

	public String getGenres()
	{
		return this.genres;
	}

	public String getDirectors()
	{
		return this.directors;
	}

	public String getCountries()
	{
		return this.countries;
	}
	
	public int getMovieLength()
	{
		return this.movieLength;
	}

	public String getPosterURL()
	{
		return this.posterURL;
	}
	@Override
	public String toString()
	{
		return this.imdbID+","+this.movieTitle+","+this.year+","+this.genres+","+this.directors+","+this.countries+","+this.movieLength+","+this.posterURL;
	}
	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}
}