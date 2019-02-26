import java.util.HashSet;
public final class RatedMovieFilter implements RFilter
{
	private String movieID;
	public RatedMovieFilter(String movieID)
	{
		this.movieID=movieID;
	}
	
	public boolean satisfies(Rater r)
	{
		HashSet<Rating> temp=r.getRaterRatings(true);
		for(Rating e:temp)
			if(Integer.parseInt(e.getImdbID().trim())==Integer.parseInt(this.movieID.trim()))
				return true;
		return false;
	}
}