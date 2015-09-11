// AbstractStack
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractStack extends AbstractContainer {

    public AbstractStack()
    {
        super();
    }
    
    public boolean equals (Object other)
    {
        AbstractStack otherColl;

        if (other == this) return true;
        if (!(other instanceof AbstractStack)) return false;

        otherColl = (AbstractStack)other;
        if (size() != otherColl.size()) return false;

        Iterator thisIter = iterator();
        Iterator otherIter = otherColl.iterator();
        while (thisIter.hasNext()){
            if (!thisIter.next().equals (otherIter.next()))
                return false;
        }

        return true;
    }
}
