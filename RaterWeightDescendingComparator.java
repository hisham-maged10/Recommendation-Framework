import java.util.Comparator;
public class RaterWeightDescendingComparator implements Comparator<RaterWeight>
{
    public RaterWeightDescendingComparator()
    {

    }
    public int compare(RaterWeight w1,RaterWeight w2)
    {
        return w2.compareTo(w1);
    }
}