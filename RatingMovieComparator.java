import java.util.Comparator;
public final class RatingMovieComparator implements Comparator<Rating>
{
	public int compare(Rating r1,Rating r2)
	{
		int movieID1=Integer.parseInt(r1.getImdbID().trim()),movieID2=Integer.parseInt(r2.getImdbID().trim());
		if(movieID1<movieID2)
			return -1;
		else if(movieID1>movieID2)
			return 1;
		else
			return 0;

	}

}