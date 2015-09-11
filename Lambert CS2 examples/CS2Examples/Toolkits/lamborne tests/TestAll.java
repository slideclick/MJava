// TestAll
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestAll{

   public static void main (String[] args){
    lamborne.Stack stack = new LinkedStack();
    Queue qu = new LinkedQueue();
    Heap heap = new ArrayHeap();
    PriorityQueue pq = new HeapPriorityQueue();
    PriorityQueue pq2 = new LinkedPriorityQueue(2);
    SortedCollection sc = new LinkedBSTSortedCollection();
    Tree tree = new LinkedTree();
  }
}

