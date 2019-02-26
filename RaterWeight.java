public class RaterWeight implements Comparable<RaterWeight>
{
	/*
	* holds the rating of a specific movie linking both classes using key which is imdbID which is the movie id in imdb and contains the rating of the movie as a double
	*/
	private String raterID;
	private double weight;

	public RaterWeight(String raterID, double weight)
	{
		this.raterID=raterID;
		this.weight=weight;	
	}

	public String getRaterID()
	{
		return this.raterID;
	}
	
	public double getWeight()
	{
		return this.weight;
	}
	
	@Override
	public String toString()
	{
		return this.raterID+","+this.weight;
	}
	
	@Override
	public int compareTo(RaterWeight anotherWeight)
	{
		if(this.weight<anotherWeight.getWeight())
			return -1;
		else if(this.weight>anotherWeight.getWeight())
			return 1;
		else
			return this.raterID.compareTo(anotherWeight.getRaterID());
	}
	@Override
	public int hashCode()
	{
		return Integer.parseInt(this.raterID)*(int)this.weight;
	}
	@Override
	public boolean equals(Object o)
	{
		if(o==this) return true;
		return (((RaterWeight)o).getRaterID().equals(this.raterID) && ((RaterWeight)o).getWeight()==this.weight);
	}
}