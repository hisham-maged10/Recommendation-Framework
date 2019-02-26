public class Rating implements Comparable<Rating>
{
	/*
	* holds the rating of a specific movie linking both classes using key which is imdbID which is the movie id in imdb and contains the rating of the movie as a double
	*/
	private String imdbID;
	private double rating;

	public Rating(String imdbID, double rating)
	{
		this.imdbID=imdbID;
		this.rating=rating;	
	}

	public String getImdbID()
	{
		return this.imdbID;
	}
	
	public double getRating()
	{
		return this.rating;
	}
	
	@Override
	public String toString()
	{
		return this.imdbID+","+this.rating;
	}
	
	@Override
	public int compareTo(Rating anotherRating)
	{
		if(this.rating<anotherRating.getRating())
			return -1;
		else if(this.rating>anotherRating.getRating())
			return 1;
		else
			return 0;
	}
	@Override
	public int hashCode()
	{
		return Integer.parseInt(this.imdbID)*(int)this.rating;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o==this) return true;
		return (((Rating)o).getImdbID().equals(this.imdbID) && ((Rating)o).getRating()==this.rating);
	}
}