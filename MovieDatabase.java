import java.util.HashMap;
import java.util.ArrayList;
public class MovieDatabase
{
	private static HashMap<String,Movie> movieData;
	
	public static void initializeData(String relativeDirectory,String movieFile)
	{
		if(relativeDirectory==null || movieFile==null){ MovieDatabase.initializeData();return;}
		DataLoader loader=new DataLoader(relativeDirectory,movieFile,null);
		movieData=loader.getRecordedMoviesMap();
			
	}
	private static void initializeData()
	{
		DataLoader loader=new DataLoader("data","ratedmoviesfull.csv",null);
		movieData=loader.getRecordedMoviesMap();
	}
	
	private static boolean isDataLoaded()
	{
		try{
		return !movieData.isEmpty();
		}catch(NullPointerException ex)
		{
			return false;
		}
	}	
	
	private static void checkData()
	{
		if(!MovieDatabase.isDataLoaded())initializeData();
	}
	public static boolean containsID(String imdbID)
	{
		MovieDatabase.checkData();
		return movieData.containsKey(imdbID);
	}
	
	public static String getIdUsingTitle(String title)
	{
		ArrayList<Movie> moviesArr=new ArrayList<>(movieData.values());
		for(Movie m:moviesArr)
			if(m.getMovieTitle().equalsIgnoreCase(title) || m.getMovieTitle().equalsIgnoreCase("\""+title+"\""))
				return m.getImdbID();
		return "NO SUCH TITLE!";
		
	}
	
	public static Object get(MoviePropertySelector selector,String imdbID)
	{
		MovieDatabase.checkData();
		if(MovieDatabase.containsID(imdbID) || selector==MoviePropertySelector.SIZE || selector==MoviePropertySelector.ARR)
		switch(selector)
		{
			case YEAR:return movieData.get(imdbID).getYear();
			case TITLE:return movieData.get(imdbID).getMovieTitle();
			case MOVIE:return movieData.get(imdbID);
			case POSTER:return movieData.get(imdbID).getPosterURL();
			case LENGTH:return movieData.get(imdbID).getMovieLength();
			case COUNTRIES:return movieData.get(imdbID).getCountries();
			case GENRES:return movieData.get(imdbID).getGenres();
			case DIRECTORS:return movieData.get(imdbID).getDirectors();
			case SIZE:return movieData.size();
			case ARR:return new ArrayList<Movie>(movieData.values());
			default:return null;
		}
		return null;
	}

	public static ArrayList<Movie> movieFilter(MovieFilter... filters)
	{
		MovieDatabase.checkData();
		ArrayList<Movie> recordedMovies=new ArrayList<>(movieData.values());
		ArrayList<Movie> filteredArr=new ArrayList<>();
		FilmFilter f=new FilmFilter(filters);
		for(Movie m:recordedMovies)
			if(f.satisfies(m))
				filteredArr.add(m);
		return filteredArr;
	}
	
}