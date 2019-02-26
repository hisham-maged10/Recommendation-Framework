import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collections;
public class DataLoader
{
	private HashMap<String,Movie> recordedMovies;
	//private ArrayList<Rater> recordedRaters;
	private HashMap<String,Rater> recordedRaters;
	private int uniqueMoviesCount;
	private RecordParser moviesParser;
	private RecordParser ratingsParser;
	public DataLoader(String relativeDirectory,String moviesFile,String ratingsFile)
	{
		this.recordedMovies=(moviesFile==null)?null:loadMovies(relativeDirectory+"/"+moviesFile);
		this.recordedRaters=(ratingsFile==null)?null:loadRaters(relativeDirectory+"/"+ratingsFile);
	}
	public ArrayList<Movie> getRecordedMovies()
	{
		return new ArrayList<>(this.recordedMovies.values());
	}
	public HashMap<String,Movie> getRecordedMoviesMap()
	{
		return this.recordedMovies;
	}
	public ArrayList<Rater> getRecordedRaters()
	{
		return new ArrayList<>(this.recordedRaters.values());
	}
	public HashMap<String,Rater> getRecordedRatersMap()
	{
		return this.recordedRaters;
	}
	public int getUniqueMoviesCount()
	{
		return this.uniqueMoviesCount;
	}
	public HashMap<String,Movie> loadMovies(String filePath)
	{
		HashMap<String,Movie> movies=new HashMap<>();
		RecordParser parser = new RecordParser(new File(filePath));
		this.moviesParser=parser;
		ArrayList<ArrayList<String>> info=getInfo(parser.getHeaders(),parser);
		this.uniqueMoviesCount=numOfMoviesRated(info.get(0));
		String tempImdbID=null;
		for(int i=0,n=info.get(i).size();i<n;i++)
			movies.put((tempImdbID=info.get(0).get(i)),new Movie(tempImdbID,info.get(1).get(i),Integer.parseInt(info.get(2).get(i)),
					     info.get(3).get(i),info.get(4).get(i),info.get(5).get(i),
					     Integer.parseInt(info.get(6).get(i)),info.get(7).get(i)));
		return movies;
	}
	public void directorsMethod()
	{
		ArrayList<Movie> recordedMovies=new ArrayList<>(this.recordedMovies.values());
		HashMap<String,Integer> directorsMap=new HashMap<>();
		String[] directors=null;
		for(Movie m:recordedMovies)
		{
			directors=m.getDirectors().replace('\"',' ').trim().split(",");	
			for(String e:directors)
				if(!directorsMap.containsKey(e))
					directorsMap.put(e,1);
				else
					directorsMap.put(e,directorsMap.get(e)+1);
		}
		int max=0,temp;
		String mD="";
		for(String e:directorsMap.keySet())
		{
			System.out.println("Director: "+e+" made "+(temp=directorsMap.get(e))+" Movies");
			if(temp>max)
			{
				max=temp;
				mD=e;
			}
		}
		System.out.println("The Max number of movies directed by one director is: "+max+" by: "+mD);
	}
	private ArrayList<ArrayList<String>> getInfo(String[] headers,RecordParser parser)
	{
		ArrayList<ArrayList<String>> info=new ArrayList<>();
		for(String e:headers)
			info.add(parser.getItemsByHeader(e));
		return info;
	}
	public HashMap<String,Rater> loadRaters(String filepath)
	{
		HashMap<String,Rater> raters=new HashMap<>();
		RecordParser parser=new RecordParser(new File(filepath));
		this.ratingsParser=parser;
		ArrayList<ArrayList<String>> info=getInfo(parser.getHeaders(),parser);
		HashSet<String> tempSet=new HashSet<>(info.get(0));
		ArrayList<String> raterIDs=new ArrayList<>(tempSet);
		tempSet.clear(); Collections.sort(raterIDs,new RaterIDComparator());
		HashMap<String, Rating> raterRatings=null;
		ArrayList<String> tempArr=info.get(0);
		String tempImdbID=null;
		int pos=0;
		for(String r:raterIDs)
		{
			raterRatings=new HashMap<String, Rating>();
			for(int i=pos,n=tempArr.size();i<n;i++)
			{
				if(!tempArr.get(i).equalsIgnoreCase(r))
				{
					pos=i; break;
				}
				else
					raterRatings.put((tempImdbID=info.get(1).get(i))
							,new Rating(info.get(1).get(i),Double.parseDouble(info.get(2).get(i))));
				
			}
			
			raters.put(r,new Rater(r,raterRatings));
			
		}
		return raters;
	}

	public int numOfMoviesRated(ArrayList<String> info)
	{
		HashSet<String> tempSet=new HashSet<>(info);
		ArrayList<String> uniqueMovies=new ArrayList<>(tempSet);
		return uniqueMovies.size();
	}
	public int getNumOfRating(int raterID)
	{
		ArrayList<String> info=this.ratingsParser.getItemsByHeader("rater_id");
		int count=0;
		for(String e:info)
			if(Integer.parseInt(e.trim())==raterID)
				count++;		
		return count;
	}
	public int getMaximumRaters()
	{
		int max=0;
		int temp=0;
		ArrayList<Rater> recordedRaters = new ArrayList<>(this.recordedRaters.values());
		for(Rater r:recordedRaters)
			if((temp=r.getNumberOfRatings())>max)
				max=temp;
		return max;
	}

	

}