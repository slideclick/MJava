package weiss.nonstandard;

class BinaryNodeWithSize extends BinaryNode
{
    BinaryNodeWithSize( Comparable x )
    {
        super( x );
        size = 0;
    }
    
    int size;
}

// BinarySearchTreeWithRank class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// Comparable findKth( k )--> Return kth smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// IllegalArgumentException thrown if k is out of bounds


/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTreeWithRank extends BinarySearchTree
{
    /**
     * Find the kth smallest item in the tree.
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the tree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    public Comparable findKth( int k )
    {
        return findKth( k, root ).element;
    }
    
    /**
     * Internal method to find kth smallest item in a subtree.
     * @param k the desired rank (1 is the smallest item).
     * @return the node containing the kth smallest item in the subtree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    protected BinaryNode findKth( int k, BinaryNode t )
    {
        if( t == null )
            throw new IllegalArgumentException( );
        int leftSize = ( t.left != null ) ? ((BinaryNodeWithSize) t.left).size : 0;

        if( k <= leftSize )
            return findKth( k, t.left );
        if( k == leftSize + 1 )
            return t;
        return findKth( k - leftSize - 1, t.right );
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param tt the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert( Comparable x, BinaryNode tt )
    {
        BinaryNodeWithSize t = (BinaryNodeWithSize) tt;
        
        if( t == null )
            t = new BinaryNodeWithSize( x );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right );
        else
            throw new DuplicateItemException( x.toString( ) );
        t.size++;
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove( Comparable x, BinaryNode tt )
    {
    
        BinaryNodeWithSize t = (BinaryNodeWithSize) tt;
        
        if( t == null )
            throw new ItemNotFoundException( x.toString( ) );
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        }
        else
            return ( t.left != null ) ? t.left : t.right;
        
        t.size--;    
        return t;
    }
    
    
    /**
     * Internal method to remove the smallest item from a subtree,
     *     adjusting size fields as appropriate.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundExcetion if the subtree is empty.
     */
    protected BinaryNode removeMin( BinaryNode tt )
    {
        BinaryNodeWithSize t = (BinaryNodeWithSize) tt;
        
        if( t == null )
            throw new ItemNotFoundException( );
        if( t.left == null )
            return t.right;
            
        t.left = removeMin( t.left );
        t.size--;
        return t;
    }
    
        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTreeWithRank t = new BinarySearchTreeWithRank( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( new Integer( i ) );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( new Integer( i ) );

        if( ((Integer)(t.findMin( ))).intValue( ) != 2 ||
            ((Integer)(t.findMax( ))).intValue( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( ((Integer)(t.find( new Integer( i ) ))).intValue( ) != i )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.find( new Integer( i ) ) != null )
                System.out.println( "Find error2!" );
        }
        
        for( int i = 2; i < NUMS; i+= 2 )
            if( ((Integer)(t.findKth( i / 2 ))).intValue( ) != i )
                System.out.println( "FindKth error!" );
    }
}
