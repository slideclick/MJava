// GraphSubEdge
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.io.Serializable;

/**
 * This class ...
 */
 
public class GraphSubEdge implements Serializable {

    protected Object  label;      // Label associated with this edge
    protected boolean mark;       // Indicates if edge is marked
    
    protected GraphSubEdge (Object label)
    {
        this.label = label;
        mark = false;
    }
    
}
