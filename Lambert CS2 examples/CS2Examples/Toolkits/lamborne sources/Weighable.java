// Weighable
// (c) 1999 Ken Lambert and Martin Osborne
 
package lamborne;

/**
 * This interface, etc...
 */


public interface Weighable  extends Comparable{

    public double getWeight();
    /*
     * Returns the object's weight
     *
     * Pre:  none
     * Post: no change
     * Ret:  the object's weight
     */


    public void setWeight (double weight);
    /*
     * Sets the object's weight
     *
     * Pre:  none
     * Post: the object's weight has been set
     * Ret:  void
     */
     
}

