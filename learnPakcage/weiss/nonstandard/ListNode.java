package weiss.nonstandard;

// Basic node stored in a linked list
// Note that this class is not accessible outside
// of package weiss.nonstandard

class ListNode
{
      // Constructors
    public ListNode( Object theElement )
    {
        this( theElement, null );
    }

    public ListNode( Object theElement, ListNode n )
    {
        element = theElement;
        next    = n;
    }

    public Object   element;
    public ListNode next;
}
