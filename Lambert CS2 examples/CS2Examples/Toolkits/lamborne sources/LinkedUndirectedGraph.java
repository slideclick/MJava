// LinkedUndirectedGraph
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

/**
 * This class ...
 */
 
import java.util.*;

public class LinkedUndirectedGraph extends LinkedDirectedGraph 
                                   implements Graph, Cloneable {

    public LinkedUndirectedGraph()
    {
        super();
    }    

    public LinkedUndirectedGraph (Collection col)
    {                                                   
        super (col);
    }

    public LinkedUndirectedGraph (Iterator iter)
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
             
        GraphSubEdge subEdge = new GraphSubEdge (edgeLabel);
        fromVertex.addEdgeTo (toVertex  , subEdge);
        toVertex  .addEdgeTo (fromVertex, subEdge);
        
        modCount++;
        edgeCount++;
    }
    
    public Iterator edges()
    {
        return new InnerEdgeIterator();    
    }
    
    public boolean removeEdge (Object fromLabel, Object toLabel)  
    {
        LinkedVertex fromVertex, toVertex;

        if (fromLabel == null || toLabel == null)
            throw new IllegalArgumentException
            ("fromLabel or toLabel is null");

        fromVertex = (LinkedVertex)(getVertex (fromLabel));
        toVertex   = (LinkedVertex)(getVertex (toLabel));

        if (fromVertex == null || toVertex == null)
            throw new IllegalArgumentException
            ("Vertices with fromLabel or toLabel not in graph");
        
        boolean edgeRemovedFlg = fromVertex.removeEdgeTo (toVertex);
        if (edgeRemovedFlg){
            toVertex.removeEdgeTo (fromVertex);
            modCount++;
            edgeCount--;
        }
            
        return edgeRemovedFlg;
    }
                                       
    public boolean removeVertex (Object label)
    {
        int numEdgesRemoved = 0;

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
                numEdgesRemoved++;
                iter.next();
            }
        }
        
        // Examine all vertices
        Iterator iter = vertices();
        while (iter.hasNext()){
            nextVertex = (LinkedVertex)(iter.next());
            if (nextVertex.removeEdgeTo (removedVertex)) 
                numEdgesRemoved++;
        }

        edgeCount -= numEdgesRemoved / 2;
        size--;
        modCount++;
        
        return true;
    }

//************************ InnerEdgeIterator **************************

    private class InnerEdgeIterator implements Iterator
    {
        private Iterator    iter;
        private GraphEdge   nextEdge;
        private Set         edgesToSkip;
        
        protected InnerEdgeIterator()
        {
            edgesToSkip = new HashSet();
            iter = directedEdges();
            nextEdge = null;
            getNextUnmarkedEdge();                 
        }
        
        public boolean hasNext()
        {
            return nextEdge != null;
        }
        
        public Object next()
        {
            if (nextEdge == null)
                throw new NoSuchElementException
                ("There are no more elements");            
            
            GraphEdge returnedEdge = nextEdge;
            getNextUnmarkedEdge();
            return returnedEdge;
        }   
        
        private void getNextUnmarkedEdge()
        {
            while (iter.hasNext()){
                nextEdge = (GraphEdge)(iter.next());
                if (edgesToSkip.contains (nextEdge))
                    nextEdge = null;
                else{
                    GraphEdge reversedEdge = 
                        new GraphEdge (nextEdge.toVertex, 
                                       nextEdge.fromVertex, "");
                    edgesToSkip.add (reversedEdge);
                    break;
                }
            }
        }   

        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }

    }  
                  
}



