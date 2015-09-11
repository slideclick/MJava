// LinkedVertex
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;



/**
 * This class ...
 */
 
import java.util.*;

class LinkedVertex extends AbstractVertex implements Vertex {

    private List edgeList;  // List of edges leaving this vertex
    
    protected LinkedVertex (Object label)
    {
       super (label);
       edgeList = new LinkedList();
    }
    
    protected void addEdgeTo (LinkedVertex toVertex, Object edgeLabel)
    {
        GraphEdge edge = new GraphEdge (this, toVertex, edgeLabel);
        edgeList.add (0, edge);
    }
    
    protected void addEdgeTo (LinkedVertex toVertex, GraphSubEdge subEdge)
    {
        GraphEdge edge = new GraphEdge (this, toVertex, subEdge);
        edgeList.add (0, edge);
    }
    
    protected GraphEdge getEdgeTo (LinkedVertex toVertex)
    {
        int index = edgeList.indexOf (new GraphEdge (this, toVertex));
        if (index == -1) 
            return null;
        else
            return (GraphEdge)(edgeList.get (index));
    }

    protected Iterator incidentEdges()
    {
        return edgeList.iterator();
    }
        
    protected Iterator neighboringVertices()
    {
        return new InnerNeighboringVerticesIterator();
    }
    
    protected boolean removeEdgeTo (LinkedVertex toVertex)
    {
        return edgeList.remove (new GraphEdge (this, toVertex));
    }
    
    
//****************** InnerNeighboringVerticesEnumeration ********************

    private class InnerNeighboringVerticesIterator implements Iterator
    {
        private Iterator iter;
    
        protected InnerNeighboringVerticesIterator() 
        {
            iter = edgeList.iterator();
        }
        
        public boolean hasNext()
        {
            return iter.hasNext();
        }
        
        public Object next()
        {
            if (!iter.hasNext())
                throw new NoSuchElementException
                ("There are no more elements");            
            
            return ((GraphEdge)(iter.next())).toVertex;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }
    }
    
}

