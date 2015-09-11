import java.util.*;

class LinkedVertexPT {

    protected String  label;     // Label associated with this vertex
    protected boolean mark;      // Indicates if the vertex is marked
    private   List    edgeList;  // List of edges leaving this vertex
    
// ====================== public methods ==========================

    public void clearMark()
    {
        mark = false;
    }
    
    public String getLabel() 
    {
        return label;
    }
    
    public boolean isMarked() 
    {
        return mark;
    }
    
    public void setLabel (String lbl, LinkedGraphPT g)
    {
        g.vertices.remove (label);
        g.vertices.put (lbl, this);
        label = lbl;
    }           

    public void setMark() 
    {
        mark = true;
    }
    
    public String toString()
    {
        return label.toString();
    }

// ===================== protected methods ========================

    protected LinkedVertexPT (String label)
    {
       this.label = label;
       mark = false;
       edgeList = new LinkedList();
    }
    
    protected void addEdgeTo (LinkedVertexPT toVertex, double weight)
    {
        LinkedEdgePT edge = new LinkedEdgePT (this, toVertex, weight);
        edgeList.add (0, edge);
        toVertex.edgeList.add (0,edge);
    }
    
    protected LinkedEdgePT getEdgeTo (LinkedVertexPT toVertex)
    {
        int index = edgeList.indexOf (new LinkedEdgePT (this, toVertex));
        if (index == -1) 
            return null;
        else
            return (LinkedEdgePT)(edgeList.get (index));
    }

    protected Iterator incidentEdges()
    {
        return edgeList.iterator();
    }
        
    protected Iterator neighboringVertices()
    {
        return new InnerNeighboringVerticesIterator(this);
    }
    
    protected boolean removeEdgeTo (LinkedVertexPT toVertex)
    {
        LinkedEdgePT edge = new LinkedEdgePT (this, toVertex);
        toVertex.edgeList.remove  (edge);
        return edgeList.remove (edge);
    }
    
    
//****************** InnerNeighboringVerticesEnumeration ********************

    private class InnerNeighboringVerticesIterator implements Iterator
    {
        private Iterator iter;
        private LinkedVertexPT thisVertex;
    
        protected InnerNeighboringVerticesIterator(LinkedVertexPT v) 
        {
            iter = edgeList.iterator();
            thisVertex = v;
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
            
            return ((LinkedEdgePT)(iter.next())).getOtherVertex (thisVertex);
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }
    }
    
}

