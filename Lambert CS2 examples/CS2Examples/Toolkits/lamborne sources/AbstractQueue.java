// AbstractQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractQueue extends AbstractContainer {

    public AbstractQueue()
    {
        super();
    }
/*    
    public boolean equals (Object other)
    {
        AbstractQueue otherColl;

        if (other == this) return true;
        if (!(other instanceof AbstractQueue)) return false;

        otherColl = (AbstractQueue)other;
        if (size() != otherColl.size()) return false;

        Iterator thisIter = iterator();
        Iterator otherIter = otherColl.iterator();
        while (thisIter.hasNext()){
            if (!thisIter.next().equals (otherIter.next()))
                return false;
        }

        return true;
    }
*/

    public boolean equals (Object other)
    {
        Queue otherColl;

        if (other == this) return true;
        if (!(other instanceof Queue)) return false;

        otherColl = (Queue)other;
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
