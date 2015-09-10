package weiss.util;

/**
 * Balanced search tree implementation of the Map.
 */
public class TreeMap extends MapImpl
{
    /**
     * Construct an empty TreeMap with default comparator.
     */
    public TreeMap( )
    {
        super( new TreeSet( ) );
    }
    
    /**
     * Construct a TreeMap using comparator.
     * @param cmp the comparator.
     */
    public TreeMap( Comparator comparator )
    {
        super( new TreeSet( ) );
        cmp = comparator;
    }
        
    /**
     * Construct a TreeMap with same key/value pairs
     * and comparator as another map..
     * @param other the other map.
     */
    public TreeMap( Map other )
    {
        super( other );
    }
    
    /**
     * Gets the comparator; returns null if default.
     * @return the comparator or if null if default is used.
     */
    public Comparator comparator( )
    {
        if( cmp == Collections.DEFAULT_COMPARATOR )
            return null;
        else
            return cmp;    
    }
    
    protected Map.Entry makePair( Object key, Object value )
    {
        return new Pair( key, value );
    }
    
    protected Set makeEmptyKeySet( )
    {
        return new TreeSet( ((TreeSet)getSet( ) ).comparator( ) );
    }
    
    protected Set clonePairSet( Set pairSet )
    {
        return new TreeSet( pairSet );
    }
    
    private final class Pair implements Map.Entry, Comparable
    {
        public Pair( Object k, Object v )
        {
            key = k;
            value = v;
        }
        
        public Object getKey( )
        {
            return key;
        }
        
        public Object getValue( )
        {
            return value;
        }
                
        public int compareTo( Object other )
        {
            return cmp.compare( getKey( ), ((Map.Entry) other).getKey( ) );
        }
        
        private Object key;
        private Object value;
    }
    
    private Comparator cmp = Collections.DEFAULT_COMPARATOR;
}
