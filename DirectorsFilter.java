public final class DirectorsFilter implements MovieFilter
{
	private String[] directors;
	public DirectorsFilter(String directors)
	{
		this.directors=directors.toLowerCase().split(",");
	}

	public boolean satisfies(Movie m)
	{
		String[] movieDirectors=m.getDirectors().toLowerCase().split(",");
		for(int i=0;i<movieDirectors.length;i++)
			movieDirectors[i]=movieDirectors[i].trim().replace('\"','\u0000');
		for(String e:directors)
			for(String ee:movieDirectors)
				if(ee.trim().equalsIgnoreCase(e))
					return true;
		return false;
	}		


}