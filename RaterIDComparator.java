import java.util.Comparator;
public final class RaterIDComparator implements Comparator<String>
{
	public int compare(String s1,String s2)
	{
		int x=Integer.parseInt(s1),y=Integer.parseInt(s2);
		if(x<y)
			return -1;
		if(x>y)
			return 1;
		else
			return 0;
	}
}