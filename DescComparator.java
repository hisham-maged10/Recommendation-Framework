import java.util.Comparator;
public class DescComparator<T extends Comparable<T>> implements Comparator<T>
{
    public int compare(T s1,T s2)
    {
        return s2.compareTo(s1);
    }
}