// Vertex interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Vertex {

    public void    clearMark ();
    public Object  getLabel ();
    public boolean isMarked ();
    public void setLabel (Object label, Graph g);
    public void    setMark ();
    public String  toString ();

}

