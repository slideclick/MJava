// LinkedBSTSortedCollection
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;
import java.io.*;

public class LinkedBSTSortedCollection extends AbstractSortedCollection 
                         implements SortedCollection, Cloneable {

    private BTNode root;           // The root node of the tree

    public LinkedBSTSortedCollection()
    {
        super();
        root = null;
    }
    
    public LinkedBSTSortedCollection (Collection col, boolean addUnique)    
    {
        super();
        root = null;
      
        if (col == null)
            throw new IllegalArgumentException
            ("Trying to instantiate using a null collection");

        Iterator iter = col.iterator();
        while (iter.hasNext()){
            Object next = iter.next();
            if (addUnique && contains (next)){
                continue;
            }else{
                add (next);
            }
        }
        modCount = 0;
    }
    
    public boolean add (Object item)   
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException
            ("Cannot add a non-comparable item");
        
        BTNode currentNode, parent;
        int comparison = 0;
        modCount++;
        
        if (size == 0){
            root = new BTNode (item);
            size++;
            return true;
        }
        
        parent = null;
        currentNode = root;
        while (currentNode != null){
            comparison = 
              ((Comparable)(currentNode.value)).compareTo ((Comparable)item);
            parent = currentNode;
            if (comparison > 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        
        if (comparison > 0)
            parent.left = new BTNode (item);
        else
            parent.right = new BTNode (item);
            
        size++;
        return true;
    }
    
    public void clear ()    
    {
        size = 0;
        modCount++;
        root = null;
    }
    
    public Object clone ()    
    {
        return new LinkedBSTSortedCollection (collectionView(), false);
    }
    
    public String debugString()
    {
        return debugString (root);
    }
    
    private String debugString (BTNode curNode)
    {
        if (curNode == null) return "";
        String str = curNode.value.toString();
        if (curNode.left == null && curNode.right == null) return str;
        return str +
               "(" +
               debugString (curNode.left) + 
               "," + 
               debugString (curNode.right) +
                ")";
    }  
    
    public Object  get (Object item)   
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException
            ("Trying to get a non-comparable item from collection");
        
        BTNode currentNode;
        int comparison;
        
        currentNode = root;
        while (currentNode != null){
            comparison = 
              ((Comparable)(currentNode.value)).compareTo ((Comparable)item);
            if (comparison == 0)
                return currentNode.value;
            else if (comparison > 0)
                currentNode = currentNode.left;
            else 
                currentNode = currentNode.right;
        }
        return null;
    }
    
    public Object get (int i) 
    {
        if (i < 0 || i >= size)
            throw new IllegalArgumentException
            ("Index with value " + i + " out of range 0 to " + (size - 1));
        
        Counter count = new Counter (i + 1);
        Basket basket = new Basket();
        get (root, count,  basket);
        return basket.value;
    }
    
    private void get (BTNode currentNode, Counter count, Basket basket)
    {
        if (currentNode == null) 
            return;
            
        get (currentNode.left, count, basket);
        count.value--;
        if (count.value == 0)
            basket.value = currentNode.value;
        else if (count.value > 0)
            get (currentNode.right, count, basket); 
        return;
    }
        
    public Iterator iterator()
    // The iterator returns the items in ascending order
    {
        Queue q = new LinkedQueue();
        inorderTraverse (root, q); 
        return q.iterator(); 
    }
    
    private void inorderTraverse (BTNode currentNode, Queue q)
    /*
     * Creates a queue of items from an inorder traversal of the tree
     *
     * Pre:  none
     * Post: no change
     * Ret:  a queue containing the results of an inorder traversal
     *       of the tree (the items in sorted order)
     */
    {
        if (currentNode == null) return;
        inorderTraverse (currentNode.left, q);
        q.enqueue (currentNode.value);
        inorderTraverse (currentNode.right, q);
    } 
    
    public boolean remove (Object item)    
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException
            ("Trying to remove a non-comparable item");
        
        BTNode currentNode, parent, newChild, preRoot;
        Object itemRemoved = null;
        int comparison = 0;
        char direction;

        if (isEmpty())
            return false;
        
        // Attempt to locate the node containing the item
        preRoot = new BTNode (null, root, null);
        parent = preRoot;
        direction = 'L';
        currentNode = root;
        while (currentNode != null){
            comparison = 
              ((Comparable)(currentNode.value)).compareTo ((Comparable)item);
            if (comparison == 0){
                itemRemoved= currentNode.value;
                break;
            }
            parent = currentNode;
            if (comparison > 0){
                direction = 'L';
                currentNode = currentNode.left;
            }else{
                direction = 'R';
                currentNode = currentNode.right;
            }
        }
        
        // Return false if the item is absent
        if (comparison != 0) return false;
        

        // The item is present, so remove its node

        // Case 1: The node has a left and a right child
        //         Replace the node's value with the max value in the
        //         left subtree
        //         Delete the max node in the left subtree
        if (currentNode.left != null && currentNode.right != null)
            liftMaxInLeftSubtreeToTop (currentNode);
            
        else{
            // Case 2: the node has no left child        
            if (currentNode.left == null)
                newChild = currentNode.right;
       
            // Case 3: the node has no right child
            else 
                newChild = currentNode.left;
            
            // Case 2 & 3: tie the parent to the new child
            if (direction == 'L')
                parent.left = newChild;
            else 
                parent.right = newChild;
        }

        // All cases: Reset the root (if it hasn't changed no harm done)
        //            Decrement the collection's size counter
        //            Return true
        root = preRoot.left;
        size--;
        modCount++;
        return true;
    }
    
    private void liftMaxInLeftSubtreeToTop (BTNode top)
    {
    // Replace top's value with the max value in the left subtree
    // and delete the max node in the left subtree
    //
    // Pre:  top has a left child
    // Post: the maximum node in top's left subtree has been removed
    // Post: top.value = max value in top's left subtree
        
        BTNode parent = top;
        BTNode currentNode = top.left;
        while (currentNode.right != null){
            parent = currentNode;
            currentNode = currentNode.right;
        }
        top.value = currentNode.value;
        if (parent == top)
            top.left = currentNode.left;
        else
            parent.right = currentNode.left;
    }
    
// ======================= Inner Classes ======================

    public class Basket extends Object {

        private Object value;
    
        private Basket(){
            value = null;
        }
    
        private Basket (Object obj){
            value = obj;
        }
    }

    private class BTNode implements Serializable {

        private Object value;    // Value stored in this node
        private BTNode left;     // Reference to left child
        private BTNode right;    // Reference to right child
    
        private BTNode()
        {
            value = null;
            left = null;
            right = null;
        }

        private BTNode(Object value)
        {
            this.value = value;
            left = null;
            right = null;
        }

        private BTNode(Object value, BTNode left, BTNode right)
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private class Counter extends Object {

        private int value;
    
        private Counter()
        {
            value = 0;
        }
    
        private Counter (int i)
        {
            value = i;
        }
    
        public String toString()
        {
            return "" + value;
        }
    } 
}



