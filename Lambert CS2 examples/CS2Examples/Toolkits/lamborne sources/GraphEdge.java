// GraphEdge
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.io.Serializable;

/**
 * This class ...
 */
 
public class GraphEdge implements Edge, Serializable {

    protected GraphSubEdge  subEdge;     // Contains label and mark associated
                                         // with this edge
    protected Vertex        fromVertex;  // First vertex for edge
    protected Vertex        toVertex;    // Second vertex for edge
    
    protected GraphEdge (Vertex from, Vertex to)
    {
        subEdge = null;
        fromVertex = from;
        toVertex = to;
    }

    protected GraphEdge (Vertex from, Vertex to, Object label)          
    {
        fromVertex = from;
        toVertex = to;
        subEdge = new GraphSubEdge (label); 
    }
    
    protected GraphEdge (Vertex from, Vertex to, GraphSubEdge subEdge)          
    {
        fromVertex = from;
        toVertex = to;
        this.subEdge = subEdge; 
    }
    
    public void clearMark()
    {
        subEdge.mark = false;
    }
    
    public int compareTo (Object that)
    {
        if (!(that instanceof GraphEdge))
            throw new IllegalArgumentException
            ("Can compare this edge only to another edge");
        Object thisLabel = this.getLabel(),
               thatLabel = ((GraphEdge)that).getLabel();
        if (!(thisLabel instanceof Comparable 
           && thatLabel instanceof Comparable))
            throw new IllegalArgumentException
            ("One or both edges doesn't have a comparable label");
          
        Comparable thisComp = (Comparable)thisLabel;
        Comparable thatComp = (Comparable)thatLabel;
          
        return thisComp.compareTo (thatComp);
    }

    public String debugString()
    {
        return "" + fromVertex + ":" + toVertex   + ":"
                  + getLabel() + ":" + isMarked();
    }
    
    public boolean equals (Object obj)
    {
        if (!(obj instanceof GraphEdge))
            return false;
            
        GraphEdge edge = (GraphEdge) obj;
        return fromVertex == edge.fromVertex && toVertex == edge.toVertex;
    }
    
    public Vertex getFromVertex()
    {
        return fromVertex;
    }
    
    public Object getLabel()
    {
        return subEdge.label;
    }
    
    public Vertex getToVertex()
    {
        return toVertex;
    }
    
    public double getWeight()
    {
        Object thisLabel = this.getLabel();
        if (!(thisLabel instanceof Weighable))
            throw new IllegalArgumentException
            ("The edge must have a weighable label");
            
        return ((Weighable)thisLabel).getWeight();
    }

    public int hashCode()
    {
        return fromVertex.hashCode() + toVertex.hashCode();
    }
    
    public boolean isMarked() 
    {
        return subEdge.mark;
    }
    
    public void setLabel (Object label)
    {
        if (label == null )
            throw new IllegalArgumentException
            ("Label must be non null");
        subEdge.label = label;
    }     
          
    public void setMark()
    {
        subEdge.mark = true;
    }
    
    public void setWeight (double weight)
    {
        Object thisLabel = this.getLabel();
        if (!(thisLabel instanceof Weighable))
            throw new IllegalArgumentException
            ("The edge must have a weighable label");
            
        ((Weighable)thisLabel).setWeight (weight);
    }
    
    public String toString()
    {
        return "" + fromVertex + ":" + toVertex   + ":" + getLabel();
    }

}

