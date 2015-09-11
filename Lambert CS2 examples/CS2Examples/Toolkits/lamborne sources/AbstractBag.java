// AbstractBag
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractBag extends AbstractContainer {

    public AbstractBag()
    {
        super();
    }
    
    public boolean equals (Object other)
    {
        AbstractBag otherBag;

        if (other == this) return true;
        if (!(other instanceof AbstractBag)) return false;

        otherBag = (AbstractBag)other;
        if (size() != otherBag.size()) return false;

        Iterator thisIter = iterator();
        while (thisIter.hasNext()){
            Object next = thisIter.next();
            if (getCount (next) != otherBag.getCount (next))
                return false;
        }

        return true;
    }
    
    public abstract int getCount (Object item);
}
