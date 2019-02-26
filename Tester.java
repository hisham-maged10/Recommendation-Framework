import java.util.ArrayList;
import java.io.IOException;
public class Tester
{
	public static void main(String[] args)
	{
		fifthWeek();
	}
	public static void firstWeek()
	{
		/*DataLoader loader=new DataLoader("data","ratedmoviesfull.csv","ratings.csv");
		ArrayList<Movie> moviesData=loader.getRecordedMovies();
		System.out.println(moviesData.size()+" movies were loaded..");
		moviesData=MovieDatabase.movieFilter(new GenreFilter("Comedy"));
		System.out.println(moviesData.size()+" movies were found for comedy genre filter");
		moviesData=MovieDatabase.movieFilter(new LengthFilter(150));
		System.out.println(moviesData.size()+" movies were found for length genre filter");
		loader.directorsMethod();
		ArrayList<Rater> ratersData=loader.getRecordedRaters();
		System.out.println("Total number of raters: "+ratersData.size());
		for(Rater r:ratersData)
			System.out.println(r);
		System.out.println("Rater 193 has "+loader.getNumOfRating(193)+" ratings");
		int maxNum=0;
		ratersData=loader.raterFilter(new MaxNoRatingsFilter((maxNum=loader.getMaximumRaters())));
		System.out.println("Max Number of ratings is "+maxNum+" and "+ratersData.size()+" raters have that number of ratings and they are:-");
		for(Rater r:ratersData)
			System.out.println(r.getRaterID());
		ratersData=loader.raterFilter(new RatedMovieFilter("1798709"));
		System.out.println("Movie 1798709 was rated with "+ratersData.size()+" ratings");
		System.out.println("The number of movies that was rated is: "+loader.getUniqueMoviesCount());
		*/
		
		//for(Movie e:moviesData)
		//	System.out.println(e.getDirectors());
	}

	public static void secondWeek()
	{
	/*	DataProcessor prc=new DataProcessor();
		System.out.println("Number of raters: "+prc.getRatersCount()+"\nNumber of movies: "+MovieDatabase.get(MoviePropertySelector.SIZE,null));
		ArrayList<Rating> processedData=prc.getProcessedRatings(12);
		//System.out.println("processed ratings with minimal ratings of 49 times:-");
		System.out.println("processed ratings with minimal ratings of 12 times is of size: "+processedData.size());
		for(Rating r: processedData)
		{
			System.out.println(r.getRating()+" "+MovieDatabase.get(MoviePropertySelector.TITLE,r.getImdbID()));
		}
		String filmName="Inside Llewyn Davis";
		System.out.println("Average Rating for "+filmName+" only is: "+prc.getAverageRatingID(filmName))
		*/;
	}
	
	public static void thirdWeek()
	{
		/*DataProcessor prc=new DataProcessor();
		System.out.println("Number of raters: "+prc.getRatersCount()+"\nNumber of movies: "+MovieDatabase.get(MoviePropertySelector.SIZE,null));
		ArrayList<Rating> processedData=prc.getProcessedRatings(3,new LengthRangeFilter(90,180),new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		System.out.println("processed ratings with minimal ratings of 35 time is of size: "+processedData.size());
		for(Rating r: processedData)
		{
			System.out.println(r.getRating()+" "+MovieDatabase.get(MoviePropertySelector.TITLE,r.getImdbID())+" Time:"+MovieDatabase.get(MoviePropertySelector.LENGTH,r.getImdbID())+" "+"Directors: "+MovieDatabase.get(MoviePropertySelector.DIRECTORS,r.getImdbID()));
		}
		*/
	}

	public static void fourthWeek()
	{
		DataProcessor prc= new DataProcessor();
		// DataProcessor prc= new DataProcessor(null,null,"ratingquiz.csv");
		System.out.println("Number of raters: "+prc.getRatersCount()+"\nNumber of movies: "+prc.getMoviesCount());
		// System.out.println(prc.getWeight("15","20"));
		// ArrayList<Rating> processedData=prc.getProcessedRatingsWeighted("71",20,5);
		// ArrayList<Rating> processedData=prc.getProcessedRatingsWeighted("964",20,5,new GenreFilter("Mystery"));		
		// ArrayList<Rating> processedData=prc.getProcessedRatingsWeighted("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));		
		// ArrayList<Rating> processedData=prc.getProcessedRatingsWeighted("168",10,3,new GenreFilter("Drama"),new LengthRangeFilter(80,160));
		// ArrayList<Rating> processedData=prc.getProcessedRatingsWeighted("314",10,5,	,new LengthRangeFilter(70,200));
		/*for(Rating r:processedData)
		{
			System.out.println(r.getRating()+" "+MovieDatabase.get(MoviePropertySelector.TITLE,r.getImdbID())+" Time:"+MovieDatabase.get(MoviePropertySelector.LENGTH,r.getImdbID())+" "+"Directors: "+MovieDatabase.get(MoviePropertySelector.DIRECTORS,r.getImdbID()));
		}*/
	}
	public static void fifthWeek()
	{
		RecommendationRunner rr=new RecommendationRunner();
		rr.printRecommendationsFor("65");
	}

}