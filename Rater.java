import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
public class Rater implements RaterInterface
{
	private String raterID;
	private HashMap<String,Rating> raterRatings;
	
	public Rater(String raterID,HashMap<String,Rating> raterRatings)
	{
		this.raterID=raterID;
		this.raterRatings=raterRatings;
	}
	
	public void addRating(String imdbID,Rating rating)
	{
		if(!raterRatings.containsKey(imdbID))
			raterRatings.put(imdbID,rating);
		else
			return;
	}
	
	public String getRaterID()
	{
		return this.raterID;
	}
	
	public HashMap<String,Rating> getRaterMap()
	{
		return this.raterRatings;
	}
	public int getNumberOfRatings()
	{
		return raterRatings.size();
	}
	public boolean hasRating(String imdbID)
	{
		return this.raterRatings.containsKey(imdbID);
	}
	public double getRating(String imdbID)
	{
		if(this.hasRating(imdbID))
			return this.raterRatings.get(imdbID).getRating();
		else
			return 0.0;
	}
	public ArrayList<Rating> getRaterRatings()
	{
		return new ArrayList<>(raterRatings.values());
	}
	
	public HashSet<Rating> getRaterRatings(boolean x)
	{
		return new HashSet<>(this.raterRatings.values());
	}
	@Override
	public int hashCode()
	{
		return raterRatings.hashCode();
	}

	@Override
	public String toString()
	{
		return "Rater ID: "+this.raterID+" and has "+this.getNumberOfRatings()+" Ratings";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o==this) return true;
		return this.raterID.equals(((Rater)o).getRaterID());
	}
	
	
}