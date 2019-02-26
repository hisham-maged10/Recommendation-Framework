import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class DataProcessor
{
	public DataProcessor()
	{
		this(null,null,null);
	}
	public DataProcessor(String dir,String movieFile,String raterfile)
	{
		initialize(dir==null?"data":dir,movieFile,raterfile);
	}
	private void initialize(String dir,String movieFile,String raterFile)
	{
		MovieDatabase.initializeData(dir,movieFile);
		RaterDatabase.initializeData(dir,raterFile);
	}
	public int getRatersCount()
	{
		return (Integer)RaterDatabase.get(RaterPropertySelector.SIZE,null);
	}
	public int getMoviesCount()
	{
		return (Integer)MovieDatabase.get(MoviePropertySelector.SIZE,null);
	}
	private double getAverageRatingID(String id,int minRatingsCount)
	{
		double sum=0.0;
		int ratersCount=0;
		ArrayList<Rater> filteredRaters=RaterDatabase.raterFilter(new RatedMovieFilter(id));
		if((ratersCount=filteredRaters.size())<minRatingsCount)
			return 0.0;
		else{
			for(Rater r:filteredRaters)
			{
				sum+=r.getRating(id);
			}
		return sum/ratersCount;
		}
	}
	public double getAverageRatingID(String title)
	{
		String id=MovieDatabase.getIdUsingTitle(title);
		if(id.equals("NO SUCH TITLE!"))
			return 0.0;
		else
			return this.getAverageRatingID(id,1);

	}
	@SuppressWarnings("unchecked")
	private ArrayList<Rating> processRatings(int minRatingsCount,MovieFilter... filters)
	{
		ArrayList<Rating> processedRatings=new ArrayList<>();
		double ratingTemp;
		String imdbIDTemp;
		ArrayList<Movie> loadedMovies=filters.length==0?((ArrayList<Movie>)MovieDatabase.get(MoviePropertySelector.ARR,null)):MovieDatabase.movieFilter(filters);
		for(Movie m: loadedMovies)
		{
			if((ratingTemp=getAverageRatingID((imdbIDTemp=m.getImdbID()),minRatingsCount))==0.0)
				continue;
			else
				processedRatings.add(new Rating(imdbIDTemp,ratingTemp));

		} 
		return processedRatings;
	}
	public ArrayList<Rating> getProcessedRatings(int minRatingsCount,MovieFilter... filters)
	{
		ArrayList<Rating> processedData=this.processRatings(minRatingsCount,filters);
		Collections.sort(processedData);
		return processedData;
	}

	public ArrayList<Rating> getProcessedRatingsWeighted(String raterID,int numSimilarRaters,int minimalRaters,MovieFilter... filters)
	{
		ArrayList<Rating> processedData=this.getSimilarRatings(raterID,numSimilarRaters,minimalRaters,filters);
		return processedData;
	}


	private double getWeight(Rater r1,Rater r2)
	{
		HashMap<String,Rating> r1Ratings=r1.getRaterMap();
		HashMap<String,Rating> r2Ratings=r2.getRaterMap();
		double product=0.0;
		for(Map.Entry<String,Rating> r1Entry : r1Ratings.entrySet())
		{
			if(r2Ratings.containsKey(r1Entry.getKey()))
				product+=((r1Entry.getValue().getRating()-5.0)*(r2Ratings.get(r1Entry.getKey()).getRating()-5.0));
				//product+=((r1Entry.getValue()-2.5)*(r2Ratings.get(r1Entry.getKey())-2.5));
			else
				continue;
		}
		return product==0.0?-1.0:product;
	}
	private double getWeight(String r1,String r2)
	{
		HashMap<String,Rating> r1Ratings=((Rater)RaterDatabase.get(RaterPropertySelector.RATER,r1)).getRaterMap();
		HashMap<String,Rating> r2Ratings=((Rater)RaterDatabase.get(RaterPropertySelector.RATER,r2)).getRaterMap();
		double product=0.0;
		for(Map.Entry<String,Rating> r1Entry : r1Ratings.entrySet())
		{
			if(r2Ratings.containsKey(r1Entry.getKey()))
				product+=((r1Entry.getValue().getRating()-5.0)*(r2Ratings.get(r1Entry.getKey()).getRating()-5.0));
				//product+=((r1Entry.getValue()-2.5)*(r2Ratings.get(r1Entry.getKey())-2.5));
			else
				continue;
		}
		return product==0.0?-1.0:product;
	}
	@SuppressWarnings("unchecked")
	private LinkedHashMap<String,Double> getSimilarities(String raterID,int numSimilarRaters)
	{
		ArrayList<RaterWeight> similarRatings = new ArrayList<>();
		//TreeMap<String,Double> similarRatings = new TreeMap<>(new StringDescComparator());
		ArrayList<Rater> raters=(ArrayList<Rater>)RaterDatabase.get(RaterPropertySelector.ARR,null);
		Rater r1=(Rater)RaterDatabase.get(RaterPropertySelector.RATER,raterID);
		double tempProduct;
		String tempID;
		if(r1==null) return new LinkedHashMap<>();
		for(Rater r : raters)
		{
			if((tempID=r.getRaterID()).equals(raterID))
				continue;
			else{
				if((tempProduct=this.getWeight(r1,r))>0.0)
					//similarRatings.put(tempID,tempProduct);
					similarRatings.add(new RaterWeight(tempID,tempProduct));
				else
					continue;
			}
		}
		Collections.sort(similarRatings,new DescComparator<RaterWeight>());
		return getSortedLinkedHashMap(similarRatings.subList(0,numSimilarRaters));
	}
	private LinkedHashMap<String,Double> getSortedLinkedHashMap(List<RaterWeight> weightsList)
	{
		LinkedHashMap<String,Double> weights=new LinkedHashMap<>();
		for(RaterWeight rw:weightsList)
		{
			weights.put(rw.getRaterID(), rw.getWeight());
		}
		return weights;
	}
	@SuppressWarnings("unchecked")
	private ArrayList<Rating> getSimilarRatings(String raterID,int numSimilarRaters,int minimalRaters,MovieFilter... filters)
	{
		LinkedHashMap<String,Double> raterWeight= this.getSimilarities(raterID,numSimilarRaters);
		ArrayList<Rating> ret = new ArrayList<>();
		ArrayList<Movie> moviesList=(filters.length==0)?(ArrayList<Movie>)MovieDatabase.get(MoviePropertySelector.ARR,null):MovieDatabase.movieFilter(filters);
		ArrayList<Rater> filteredRater=null;
		String tempImdbID=null;
		double newRating=0.0;
		String tempRaterID;
		int counter;
		for(Movie m: moviesList)
		{
			filteredRater=RaterDatabase.raterFilter(new RatedMovieFilter((tempImdbID=m.getImdbID())));
			if(filteredRater.size()<minimalRaters)continue;
			counter=0;
			newRating=0.0;
			for(Rater r:filteredRater)
			{
				if(raterWeight.containsKey((tempRaterID=r.getRaterID())))
				{
					newRating+=r.getRating(tempImdbID)*raterWeight.get(tempRaterID);
					counter++;
				}
			}
			if(counter<minimalRaters)continue;
			else
			ret.add(new Rating(tempImdbID,newRating/counter));
		}
		Collections.sort(ret,Collections.reverseOrder());
		return ret;
	}














}
