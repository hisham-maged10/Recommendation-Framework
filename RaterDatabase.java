import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
public class RaterDatabase{
    private static HashMap<String,Rater> raterData;

    public static void initializeData(String relativeDirectory,String raterFile){
        if(relativeDirectory==null || raterFile==null){RaterDatabase.initializeData(); return;}
        DataLoader loader=new DataLoader(relativeDirectory,null,raterFile);
        raterData=loader.getRecordedRatersMap();
    }
    private static void initializeData()
    {
        DataLoader loader=new DataLoader("data",null,"ratings.csv");
        raterData=loader.getRecordedRatersMap();
    }

    private static boolean isDataLoaded()
    {
        try{
        return !RaterDatabase.raterData.isEmpty();
        }catch(NullPointerException ex)
		{
			return false;
		}
    }

    private static void checkData()
    {
        if(!RaterDatabase.isDataLoaded())initializeData();return;
    }
    public static boolean containsID(String id)
    {
        checkData();
        return raterData.containsKey(id);
    }

    public static Object get(RaterPropertySelector selector,String id)
    {
        RaterDatabase.checkData();
        if(RaterDatabase.containsID(id) || selector == RaterPropertySelector.ARR || selector == RaterPropertySelector.SIZE)
        switch(selector)
        {
            case NUMBER_OF_RATINGS: return raterData.get(id).getNumberOfRatings();
            case RATINGS: return raterData.get(id).getRaterRatings();
            case RATER: return raterData.get(id);
            case ARR: return new ArrayList<>(RaterDatabase.raterData.values());
            case SIZE: return raterData.size();
        }
        return null;
    }
    public static double getCertainRating(String raterID,String imdbID)
    {
        checkData();
        if(RaterDatabase.containsID(raterID))
            return raterData.get(raterID).getRating(imdbID);
        else
            return -1.0;
    }
    public static ArrayList<Rater> raterFilter(RFilter... filters)
    {
        checkData();
        ArrayList<Rater> filteredArr=new ArrayList<>();
        ArrayList<Rater> recordedRaters=new ArrayList<>(raterData.values()); 
        RaterFilter f=new RaterFilter(filters);
        for(Rater r:recordedRaters)
        {
            if(f.satisfies(r))
                filteredArr.add(r);
        }
        return filteredArr;
    }
    public static void addRaterRating(String filename)
    {
        checkData();
        DataLoader tempLoader= new DataLoader("data",null,filename);
        HashMap<String,Rater> newRaters = tempLoader.getRecordedRatersMap();
        for(Map.Entry<String,Rater> e: newRaters.entrySet())
            for(Rating r:e.getValue().getRaterRatings())
                RaterDatabase.addRaterRating(e.getKey(),r.getImdbID(),r.getRating());
        

    }
    public static boolean addRaterRating(String raterID,String imdbID, double rating)
    {
        checkData();
        if(MovieDatabase.containsID(imdbID))
        {
            if(!RaterDatabase.containsID(raterID))
            {
                HashMap<String,Rating> raterRatings=new HashMap<>();
                raterRatings.put(imdbID,new Rating(imdbID,rating));
                raterData.put(raterID,new Rater(raterID,raterRatings));
                return true;
            }
            else
            {
                raterData.get(raterID).addRating(imdbID,new Rating(imdbID,rating));
                return true;
            }
        }
        else
            return false;
    }

}