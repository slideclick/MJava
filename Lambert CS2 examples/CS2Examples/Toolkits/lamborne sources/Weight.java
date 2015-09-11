// Weight
// (c) 1999 Ken Lambert and Martin Osborne

/**
 * This class provides comparable strings
 */

package lamborne;

import java.io.Serializable;

public class Weight implements Weighable, Serializable{

    public double weight;
    
    public Weight()
    {
        weight = 0.0;
    }
    
    public Weight (double weight)
    {
        this.weight = weight;
    }   
    
    public int compareTo (Object obj)
    {
        if (!(obj instanceof Weight))
            throw new IllegalArgumentException
            ("The object must be a weight");
            
        return (int)(weight - (((Weight)obj).weight));
    }
    
    public boolean equals (Object obj)
    {
        if (obj == null || ! (obj instanceof Weight))
            return false;
        else
            return weight == ((Weight)obj).weight;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    public void setWeight (double weight)
    {
        this.weight = weight;
    }

    public String toString()
    {
        return "" + weight;
    }
    
}  
