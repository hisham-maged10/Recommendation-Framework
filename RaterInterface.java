import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public interface RaterInterface
{
	public void addRating(String imdbID,Rating rating);
	public String getRaterID();
	public int getNumberOfRatings();
	public double getRating(String imdbID);
	public ArrayList<Rating> getRaterRatings();
	public HashSet<Rating> getRaterRatings(boolean x);
	public boolean hasRating(String imdbID);
	public int hashCode();
	public String toString();
	public boolean equals(Object o);
}