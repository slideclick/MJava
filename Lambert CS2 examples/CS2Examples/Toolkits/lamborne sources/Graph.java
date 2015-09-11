// Graph interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Graph {
    public void       addEdge (Object fromLabel, Object  toLabel, Object edgeLabel);
    public void       addVertex (Object label);
    public void       clear ();
    public void       clearEdgeMarks ();
    public void       clearVertexMarks ();
    public Object     clone ();
    public Collection collectionView ();
    public boolean    containsEdge (Object fromLabel, Object toLabel);
    public boolean    containsVertex (Object label);
    public Iterator   edges ();
    public boolean    equals (Object other);
    public Edge       getEdge (Object fromLabel, Object toLabel);
    public Vertex     getVertex (Object label);
    public int        hashCode ();
    public Iterator   incidentEdges (Vertex vertex);
    public boolean    isEmpty ();
    public Iterator   iterator ();
    public Iterator   neighboringVertices (Vertex vertex);
    public boolean    removeEdge (Object fromLabel, Object toLabel);
    public boolean    removeVertex (Object label);
    public int        size ();
    public int        sizeEdges ();
    public int        sizeVertices ();
    public Object[]   toArray (); 
    public String     toString ();
    public Iterator   vertices ();
}
