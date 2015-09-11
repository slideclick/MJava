// AbstractVertex
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.io.Serializable;

/**
 * This class ...
 */
 
abstract public class AbstractVertex implements Serializable {

    protected Object        label;     // Label associated with this vertex
    protected boolean       mark;      // Indicates if the vertex is marked
    
    protected AbstractVertex(Object label)
    {
       this.label = label;
       mark = false;
    }

    public void clearMark()
    {
        mark = false;
    }
    
    public String debugString()
    {
        return "" + label + ":" + mark;
    }

    public Object getLabel() 
    {
        return label;
    }
    
    public boolean isMarked() 
    {
        return mark;
    }
    
    public void setLabel (Object label, Graph g)
    {
        if (label == null || g == null)
            throw new IllegalArgumentException
            ("Label and graph must be non null");
        Vertex vert = g.getVertex (label);
        if (vert != null && vert != this)
            throw new IllegalArgumentException
            ("The graph contains another vertex with the specified label");
        
        ((AbstractGraph)g).vertices.remove (this.label);
        ((AbstractGraph)g).vertices.put (label, this);
        this.label = label;
    }           

    public void setMark() 
    {
        mark = true;
    }
    
    public String toString()
    {
        return label.toString();
    }

}

