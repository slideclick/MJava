// Edge interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;



/**
 * This class ...
 */
 
public interface Edge {

    public void clearMark();
    public int compareTo (Object that) ;
    //public String debugString();
    public boolean equals (Object obj);
    public Vertex getFromVertex();
    public Object getLabel();
    public Vertex getToVertex();
    public double getWeight();
    public int hashCode();
    public boolean isMarked(); 
    public void setLabel (Object label);
    public void setMark();
    public void setWeight (double weight);
    public String toString();

}

