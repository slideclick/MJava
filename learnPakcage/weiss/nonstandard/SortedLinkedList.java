package weiss.nonstandard;

// SortedLinkedList class
//
// CONSTRUCTION: with no initializer
// Access is via LinkedListIterator class
//
// ******************PUBLIC OPERATIONS*********************
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// LinkedListIterator zeroth( )
//                        --> Return position to prior to first
// LinkedListIterator first( )
//                        --> Return first position
// void insert( x )       --> Insert x
// void insert( x, p )    --> Insert x (ignore p)
// void remove( x )       --> Remove x
// LinkedListIterator find( x )
//                        --> Return position that views x
// LinkedListIterator findPrevious( x )
//                        --> Return position prior to x
// ******************ERRORS********************************
// No special errors

/**
 * Linked list implementation of the list
 *    using a header node.
 * Access to the list is via LinkedListIterator.
 * @author Mark Allen Weiss
 * @see LinkedListIterator
 */
public class SortedLinkedList extends LinkedList
{
    /**
     * Insert after p.
     * @param x the item to insert.
     * @param p this parameter is ignored.
     */
    public void insert( Object x, LinkedListIterator p )
    {
        insert( (Comparable) x );
    }
    
    /**
     * Insert in sorted order.
     * @param x the item to insert.
     */
    public void insert( Comparable x )
    {
        LinkedListIterator prev = zeroth( );
        LinkedListIterator curr = first( );

        while( curr.isValid( ) && x.compareTo( curr.retrieve( ) ) > 0 )
        {
            prev.advance( );
            curr.advance( );
        }

        super.insert( x, prev );
    }
    
    public static void main( String [ ] args )
    {
        LinkedList         theList = new SortedLinkedList( );
        LinkedListIterator theItr;
        int i;

        theItr = theList.zeroth( );
        printList( theList );

        for( i = 0; i < 10; i++ )
        {
            theList.insert( new Integer( (i*7)%10 ), theItr );
            printList( theList );
            theItr.advance( );
        }

        for( i = 0; i < 10; i += 2 )
            theList.remove( new Integer( i ) );

        for( i = 0; i < 10; i++ )
            if( ( i % 2 == 0 ) == ( theList.find( new Integer( i ) ).isValid( ) ) )
                System.out.println( "Find fails!" );

        System.out.println( "Finished deletions" );
        printList( theList );
    }

}
