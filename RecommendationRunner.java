import java.util.ArrayList;
import java.util.Random;
public class RecommendationRunner implements Recommender{

    @SuppressWarnings("unchecked")
    public ArrayList<String> getItemsToRate(){
        Random rand=new Random();
        ArrayList<String> result=new ArrayList<>();
        ArrayList<Movie> movies=MovieDatabase.movieFilter(new YearAfterFilter(2014));
        for(int i=0,n=movies.size();i<10;i++)
        {
            result.add(movies.get(rand.nextInt(n)).getImdbID());
        }
        return result;
    }

    public void printRecommendationsFor(String webRaterID){
        DataProcessor prc= new DataProcessor();
        ArrayList<Rating> movies=prc.getProcessedRatingsWeighted(webRaterID,10,3);
        if(movies.size() == 0){
            System.out.println("No Movies were Found");
        }
        else{
        System.out.println("<html>");
            System.out.println("<table align=\"center\" border=\"1\">");
        
                System.out.println("<tr>");
                    System.out.println("<th>Movie</th>");
                    System.out.println("<th>Year</th>");
                    System.out.println("<th>Length</th>");
                    System.out.println("<th>Country</th>");
                    System.out.println("<th>Weighted Rating</th>");
                System.out.println("</tr>");
                
                for(int i=0;i<10;i++)
                {
                System.out.println("<tr>");
                    
                    
                    System.out.println("<td>");
                        
                        System.out.println("<h3>"+MovieDatabase.get(MoviePropertySelector.TITLE,movies.get(i).getImdbID())+"</h3>");
                        System.out.print("<img src=\"");
                        System.out.print(MovieDatabase.get(MoviePropertySelector.POSTER,movies.get(i).getImdbID()));
                        System.out.println("\"/>");
                        
                    System.out.println("</td>");

                    System.out.println("<td>");
                        
                        System.out.println(MovieDatabase.get(MoviePropertySelector.YEAR,movies.get(i).getImdbID()));
                        
                    System.out.println("</td>");

                    System.out.println("<td>");
                        
                        System.out.println(MovieDatabase.get(MoviePropertySelector.LENGTH,movies.get(i).getImdbID()));
                        
                    System.out.println("</td>");

                    System.out.println("<td>");
                        
                        System.out.println(MovieDatabase.get(MoviePropertySelector.COUNTRIES,movies.get(i).getImdbID()));
                        
                    System.out.println("</td>");

                    System.out.println("<td>");
                        
                        System.out.println(movies.get(i).getRating());
                        
                    System.out.println("</td>");
                System.out.println("</tr>");
                }
        
            System.out.println("</table>");
        System.out.println("</html>");
        }
    }
}