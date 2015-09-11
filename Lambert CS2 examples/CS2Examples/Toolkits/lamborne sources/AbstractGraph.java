// AbstractGraph
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractGraph extends AbstractContainer {

    protected int edgeCount; 
    protected Map vertices;   
    
    public AbstractGraph()
    {
        super();
        edgeCount = 0;
        vertices = new HashMap();
    }
    
    public AbstractGraph (Collection col)
    {
        super();
        edgeCount = 0;
        vertices = new HashMap();
      
        if (col == null)
            throw new IllegalArgumentException("Collection is null");
               
        appendIterator(col.iterator());
    }

    public AbstractGraph (Iterator iter)
    {
        super();
        edgeCount = 0;
        vertices = new HashMap();
      
        if (iter == null)
            throw new IllegalArgumentException("Iterator is null");
               
        appendIterator(iter);
    }


    private void appendIterator (Iterator iter)
    {
        while (iter.hasNext())
            addVertex (iter.next());
    }
    
    public abstract void addVertex (Object label);
    
    public void clear()
    {
        super.clear();
        edgeCount = 0;
        vertices = new HashMap();
    }
       
    public void clearEdgeMarks()
    {
        Edge edge;
        Iterator iter = edges();
        while (iter.hasNext()){
            edge = (Edge)(iter.next());
            edge.clearMark();
        }
    }
    
    public void clearVertexMarks()
    {
        Vertex vertex;
        Iterator iter = vertices();
        while (iter.hasNext()){
            vertex = (Vertex)(iter.next());
            vertex.clearMark();
        }
    }
    
    public boolean containsEdge (Object fromLabel, Object toLabel)
    {
        if (fromLabel == null || toLabel == null)
            throw new IllegalArgumentException
            ("fromLabel or toLabel is null");
        if (!containsVertex (fromLabel) || !containsVertex (toLabel))
            throw new IllegalArgumentException
            ("Vertices with fromLabel or toLabel not in graph");
             
        return getEdge (fromLabel, toLabel) != null;
    }
    
    public boolean containsVertex (Object label)
    {
        if (label == null)
            throw new IllegalArgumentException
            ("Cannot test containment using a null label");
        return vertices.containsKey (label);
    }
    
    public boolean equals (Object obj){
        throw new UnsupportedOperationException("equals left as an exercise");
    }
    
    public abstract Edge getEdge (Object fromLabel, Object toLabel);

    public Vertex getVertex (Object label)
    {
        if (label == null)
            throw new IllegalArgumentException
            ("Cannot get a vertex using a null label");
        return (Vertex)(vertices.get(label));
    }
    
    public abstract Iterator edges();
    
    public Iterator iterator()
    {
        return vertices.keySet().iterator();
    }
    
    public int sizeEdges()
    {
        return edgeCount;
    }
    
    public int sizeVertices()
    {
        return size;
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
}

    



