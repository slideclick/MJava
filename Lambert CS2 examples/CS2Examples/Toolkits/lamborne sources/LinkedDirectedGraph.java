// LinkedDirectedGraph
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public class LinkedDirectedGraph extends AbstractGraph 
                                 implements Graph, Cloneable {


    public LinkedDirectedGraph()
    {
        super();
    }    

    public LinkedDirectedGraph (Collection col)
    {                                                   
        super (col);
    }

    public LinkedDirectedGraph (Iterator iter)
    {                                                   
        super (iter);
    }

    public void addEdge 
        (Object fromLabel, Object toLabel,Object edgeLabel)
    {
        LinkedVertex fromVertex, toVertex;

        if (fromLabel == null || toLabel == null || edgeLabel == null)
            throw new IllegalArgumentException
            ("One or more labels is null");
        if (fromLabel.equals (toLabel))
            throw new IllegalArgumentException
            ("fromLabel equals toLabel");
             
        fromVertex = (LinkedVertex)(getVertex (fromLabel));
        toVertex   = (LinkedVertex)(getVertex (toLabel));
             
        if (fromVertex == null || toVertex == null)
            throw new IllegalArgumentException
            ("Vertices with fromLabel or toLabel not in graph");
        if (containsEdge (fromLabel, toLabel))
            throw new IllegalArgumentException
            ("Edge already in graph");
        
        fromVertex.addEdgeTo (toVertex, edgeLabel);

        modCount++;
        edgeCount++;
    }
    
    public void addVertex (Object label)
    {
        if (label == null)
            throw new IllegalArgumentException
            ("Adding a vertex with a null label");
        if (vertices.containsKey (label))
            throw new IllegalArgumentException
            ("Vertex with this label already in graph");
            
        vertices.put (label, new LinkedVertex (label));
        size++;
        modCount++;
    }
    
    public Object clone(){
        throw new UnsupportedOperationException("clone left as an exercise");
    }
    
    public Iterator edges()
    {
        return new InnerEdgeIterator();
    }
    
            // Needed by subclass
            protected Iterator directedEdges()
            {
                return new InnerEdgeIterator();
            }
    
    public Edge getEdge (Object fromLabel, Object toLabel)
    {
        LinkedVertex fromVertex, toVertex;            
        
        if (fromLabel == null || toLabel == null)
            throw new IllegalArgumentException
            ("fromLabel or toLabel is null");

        fromVertex = (LinkedVertex)(vertices.get (fromLabel));
        toVertex   = (LinkedVertex)(vertices.get (toLabel));

        if (fromVertex == null || toVertex == null)
            throw new IllegalArgumentException
            ("Vertices with fromLabel or toLabel not in graph");

        return fromVertex.getEdgeTo (toVertex);
    }
    
    public Iterator incidentEdges (Vertex vertex)
    {
        if (vertex == null )
            throw new IllegalArgumentException 
            ("Vertex must not be null");
        LinkedVertex lv = (LinkedVertex)vertex;
        if (!containsVertex (lv.label))
            throw new IllegalArgumentException 
            ("Vertex not in graph");
             
        return lv.incidentEdges();
    }
    
    public Iterator neighboringVertices (Vertex vertex)
    {
        if (vertex == null )
            throw new IllegalArgumentException 
            ("Vertex must not be null");
        LinkedVertex lv = (LinkedVertex)vertex;
        if (!containsVertex (lv.label))
            throw new IllegalArgumentException 
            ("Vertex not in graph");
            
        return lv.neighboringVertices();
    }
    
    public boolean removeEdge (Object fromLabel, Object toLabel)  
    {
        LinkedVertex fromVertex, toVertex;
        boolean edgeRemovedFlg; 

        if (fromLabel == null || toLabel == null)
            throw new IllegalArgumentException
            ("fromLabel or toLabel is null");
             
        fromVertex = (LinkedVertex)(getVertex (fromLabel));     
        toVertex   = (LinkedVertex)(getVertex (toLabel));     
             
        if (fromVertex == null || toVertex == null)
            throw new IllegalArgumentException
            ("Vertices with fromLabel or toLabel not in graph");

        
        edgeRemovedFlg = fromVertex.removeEdgeTo (toVertex);
        
        if (edgeRemovedFlg){ 
            modCount++;
            edgeCount--;
        }
            
        return edgeRemovedFlg;
    }
    
    public boolean removeVertex (Object label)
    {
        if (label == null)
            throw new IllegalArgumentException
            ("Removing a vertex with a null label");

        LinkedVertex removedVertex, nextVertex;

        removedVertex = (LinkedVertex)(vertices.remove (label));
        
        if (removedVertex == null) 
            return false;
        else{
            Iterator iter = removedVertex.neighboringVertices();
            while (iter.hasNext()){
                edgeCount--;
                iter.next();
            }
        }
        
        // Examine all vertices
        Iterator iter = vertices();
        while (iter.hasNext()){
            nextVertex = (LinkedVertex)(iter.next());
            if (nextVertex.removeEdgeTo (removedVertex)) 
                edgeCount--;
        }

        size--;
        modCount++;
        
        return true;
    }

//==================================================================

    private class InnerEdgeIterator implements Iterator
    { 
        private Iterator vertexIter;
        private Iterator edgeIter;
        private Object nextEdge;
        private int expectedModCount = modCount;
        
        private InnerEdgeIterator()
        {
            vertexIter = vertices();
            getFirstEdgeFromNextVertex();
        }
        
        private void getFirstEdgeFromNextVertex()
        {
            nextEdge = null;
            while (vertexIter.hasNext()){
                LinkedVertex vertex = (LinkedVertex)(vertexIter.next());
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
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (nextEdge == null)
                throw new NoSuchElementException
                ("There are no more elements");            
            
            Object returnedEdge = nextEdge;
            getNextEdge();
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

