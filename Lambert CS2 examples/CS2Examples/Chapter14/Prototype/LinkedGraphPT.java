import java.util.*;

public class LinkedGraphPT {

    protected int vertexCount;
    protected int edgeCount; 
    protected Map vertices;   

    public LinkedGraphPT()
    {
        vertexCount = 0;
        edgeCount = 0;
        vertices = new HashMap();
    }    

    public LinkedGraphPT (Iterator iter)
    {
        vertexCount = 0;
        edgeCount = 0;
        vertices = new HashMap();
        while (iter.hasNext()){
            String str = (String)(iter.next());
            addVertex (str);
        }
    }    

    public void addEdge 
        (String fromLabel, String toLabel,double weight)
    {
        LinkedVertexPT fromVertex, toVertex;
        
        fromVertex = getVertex (fromLabel);
        toVertex   = getVertex (toLabel);
        fromVertex.addEdgeTo (toVertex, weight);
        edgeCount++;
    }
    
    public void addVertex (String label)
    {
        vertices.put (label, new LinkedVertexPT (label));
        vertexCount++;
    }
    
    public void clear()
    {
        vertexCount = 0;
        edgeCount = 0;
        vertices = new HashMap();
    }
    
    public void clearEdgeMarks()
    {
        LinkedEdgePT edge;
        Iterator iter = edges();
        while (iter.hasNext()){
            edge = (LinkedEdgePT)(iter.next());
            edge.clearMark();
        }
    }
    
    public void clearVertexMarks()
    {
        LinkedVertexPT vertex;
        Iterator iter = vertices();
        while (iter.hasNext()){
            vertex = (LinkedVertexPT)(iter.next());
            vertex.clearMark();
        }
    }
    
    public boolean containsEdge (String fromLabel, String toLabel)
    {
        return getEdge (fromLabel, toLabel) != null;
    }
    
    public boolean containsVertex (Object label)
    {
        return vertices.containsKey (label);
    }
    
    public Iterator edges()
    {
        return new InnerEdgeIterator();
    }
    
    public LinkedEdgePT getEdge (String fromLabel, String toLabel)
    {
        LinkedVertexPT fromVertex, toVertex;            

        fromVertex = (LinkedVertexPT)(vertices.get (fromLabel));
        toVertex   = (LinkedVertexPT)(vertices.get (toLabel));
        return fromVertex.getEdgeTo (toVertex);
    }
    
    public LinkedVertexPT getVertex (String label)
    {
        return (LinkedVertexPT)(vertices.get(label));
    }
    
    public Iterator incidentEdges (LinkedVertexPT vertex)
    {
        return vertex.incidentEdges();
    }
    
    public boolean isEmpty()
    {
        return vertexCount == 0;
    }

    public Iterator neighboringVertices (LinkedVertexPT vertex)
    {
        return vertex.neighboringVertices();
    }
    
    public boolean removeEdge (String fromLabel, String toLabel)  
    {
        LinkedVertexPT fromVertex, toVertex;
        boolean edgeRemovedFlg; 
        fromVertex = (LinkedVertexPT)(getVertex (fromLabel));     
        toVertex   = (LinkedVertexPT)(getVertex (toLabel));     
        edgeRemovedFlg = fromVertex.removeEdgeTo (toVertex);
        
        if (edgeRemovedFlg) 
            edgeCount--;
            
        return edgeRemovedFlg;
    }
    
    public boolean removeVertex (String label)
    {
        LinkedVertexPT removedVertex, nextVertex;

        removedVertex = (LinkedVertexPT)(vertices.remove (label));
        
        if (removedVertex == null) 
            return false;
        
        // Examine all vertices
        Iterator iter = vertices();
        while (iter.hasNext()){
            nextVertex = (LinkedVertexPT)(iter.next());
            if (nextVertex.removeEdgeTo (removedVertex)) 
                edgeCount--;
        }
        vertexCount--;
        
        return true;
    }

    public int sizeEdges()
    {
        return edgeCount;
    }
    
    public int sizeVertices()
    {
        return vertexCount;
    }
    
    public String toString()
    {
        Iterator iter;
        String str;
        
        iter = vertices();
        str = sizeVertices() + " Vertices: ";
        while (iter.hasNext())
            str += " " + iter.next();
        str += "\n";
        iter = edges();
        str += sizeEdges() + " Edges: ";
        while (iter.hasNext())
            str += " " + iter.next();
        
        return str;
    }
    
    public Iterator vertices()
    {
        return vertices.values().iterator();
    }

//==================================================================

    private class InnerEdgeIterator implements Iterator
    { 
        private Iterator vertexIter;
        private Iterator edgeIter;
        private Object   nextEdge;
        private Set      edgesToSkip;
        
        private InnerEdgeIterator()
        {
            vertexIter = vertices();
            getFirstEdgeFromNextVertex();
            edgesToSkip = new HashSet();
        }
        
        private void getFirstEdgeFromNextVertex()
        {
            nextEdge = null;
            while (vertexIter.hasNext()){
                LinkedVertexPT vertex = (LinkedVertexPT)(vertexIter.next());
                edgeIter = incidentEdges (vertex);
                if (edgeIter.hasNext()){
                    nextEdge = edgeIter.next();
                    break;
                }
            }
        }
        
        public boolean hasNext()
        {
            return nextEdge != null;
        }
        
        public Object next()
        {
            edgesToSkip.add (nextEdge);
            Object returnedEdge = nextEdge;
            do {
                getNextEdge();
            }while (nextEdge != null && edgesToSkip.contains (nextEdge));
            
            return returnedEdge;
        }
        
        private void getNextEdge()
        {
            if (edgeIter.hasNext())
                nextEdge = edgeIter.next();
            else 
                getFirstEdgeFromNextVertex();
        }    

        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }

    }

}

