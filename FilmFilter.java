import java.util.ArrayList;
import java.util.Arrays;
public class FilmFilter implements MovieFilter
{
	private ArrayList<MovieFilter> filters;
	
	public FilmFilter(MovieFilter... filters)
	{
		this.filters=new ArrayList<>(Arrays.asList(filters));
	}

	public void addFilter(MovieFilter filter)
	{
		this.filters.add(filter);
	}
	
	public boolean satisfies(Movie movie)
	{
		for(MovieFilter f:filters)
			if(!f.satisfies(movie))
				return false;
		return true;
	}
}