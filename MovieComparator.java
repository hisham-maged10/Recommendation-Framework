import java.util.Comparator;
public final class MovieComparator implements Comparator<Movie>
{
	public int compare(Movie m1,Movie m2)
	{
		int movieID1=Integer.parseInt(m1.getImdbID()),movieID2=Integer.parseInt(m2.getImdbID());
		if(movieID1<movieID2)
			return -1;
		else if(movieID1>movieID2)
			return 1;
		else
			return 0;
	}
}