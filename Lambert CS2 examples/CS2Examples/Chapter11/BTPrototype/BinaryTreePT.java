import java.util.*;
import lamborne.*;

public class BinaryTreePT extends Object implements Cloneable{

    private BTNode root;
    private int    size;             
                                           
//===================================================================
// Constructors
//===================================================================

    public BinaryTreePT()
    {
        root =null;
        size = 0;
    }
    
    public BinaryTreePT(Object item)    
    {
        size = 1;
        root = new BTNode (item);
    }

    public BinaryTreePT(Object item, BinaryTreePT leftTree, 
                                     BinaryTreePT rightTree)
    {
        int    leftSize = 0, 
               rightSize = 0;
        BTNode leftNode = null, 
               rightNode = null;
        
        if (leftTree != null){
            leftSize = leftTree.size();
            leftNode = leftTree.root;
        }
            
        if (rightTree != null){
            rightSize = rightTree.size();
            rightNode = rightTree.root;
        }

        root = new BTNode (item, leftNode, rightNode);
        size = 1 + leftSize + rightSize;        
    }
    
//===================================================================
// Supporting methods isEmpty and size
//===================================================================
 
    public boolean isEmpty()
    {
        return root == null;
    }
 
    public int size()
    {
        return size;
    }
        
//===================================================================
// General method clone
//===================================================================

    public Object clone()
    {
        BinaryTreePT cloneTree = new BinaryTreePT();
        cloneTree.root = clone(root);
        cloneTree.size = size;
        return cloneTree;
    }

    private BTNode clone(BTNode source)
    {
        if (source == null)
           return null;
        else{
           BTNode target = new BTNode(source.value, 
                                      clone(source.left),
                                      clone(source.right));
           return target;
        }
     }
    
//===================================================================
// General method equals
//===================================================================

    public boolean equals(Object other)
    {
        if(!(other instanceof BinaryTreePT))
            return false;
        else
            return equals(root, ((BinaryTreePT)other).root);
    }

    private boolean equals(BTNode node1, BTNode node2)
    {
        if (node1 == null && node2 == null)
            return true;
        else if ((node1 == null && node2 != null) ||
                 (node1 != null && node2 == null))
            return false;
        else if (node1.value.equals(node2.value))
            return equals(node1.left, node2.left) &&
                   equals(node1.right, node2.right);
        else
            return false;
    }
            
//===================================================================
// General methods for iterators
//===================================================================

    public Iterator iterator()
    {
        return iteratorPreorder();
    }
    
    public Iterator iteratorPreorder()
    {
        Queue nodes = new LinkedQueue();
        iteratorPreorder (root, nodes); 
        return nodes.iterator(); 
    }
    
    private void iteratorPreorder (BTNode currentNode, Queue nodes)
    {
        if (currentNode == null) return;
        nodes.enqueue (currentNode.value);
        iteratorPreorder (currentNode.left, nodes);
        iteratorPreorder (currentNode.right, nodes);
    } 

//...................................................................
  
    public Iterator iteratorInorder()
    {
        Queue nodes = new LinkedQueue();
        iteratorInorder (root, nodes); 
        return nodes.iterator(); 
    }
    private void iteratorInorder (BTNode currentNode, Queue nodes)
    {
        if (currentNode == null) return;
        iteratorInorder (currentNode.left, nodes);
        nodes.enqueue (currentNode.value);
        iteratorInorder (currentNode.right, nodes);
    } 
  
//...................................................................

    public Iterator iteratorPostorder()
    {
        Queue nodes = new LinkedQueue();
        iteratorPostorder (root, nodes); 
        return nodes.iterator(); 
    }
    
    private void iteratorPostorder (BTNode currentNode, Queue nodes)
    {
        if (currentNode == null) return;
        iteratorPostorder (currentNode.left, nodes);
        iteratorPostorder (currentNode.right, nodes);
        nodes.enqueue (currentNode.value);
    } 
  
//...................................................................
  
    public Iterator iteratorLevelorder()
    {
        Queue nodes = new LinkedQueue();
        Queue levelsQu = new LinkedQueue();
        
        if (!isEmpty()){ 
            levelsQu.enqueue (root);
            iteratorLevelorder (levelsQu, nodes);
        }
        return nodes.iterator();
    }
    
    private void iteratorLevelorder(Queue levelsQu, Queue nodes)
    {
        BTNode node;
        
        if (levelsQu.isEmpty()) return;
        
        node = (BTNode)(levelsQu.dequeue());
        nodes.enqueue (node.value);
        if (node.left  != null) levelsQu.enqueue (node.left);
        if (node.right != null) levelsQu.enqueue (node.right);
        iteratorLevelorder (levelsQu, nodes);
    }

//===================================================================
//General methods for displaying tree as string
//===================================================================

    public String toString()
    {
        return toString(root);
    }

    private String toString(BTNode node)
    {
        String str = "";
        if (node != null){
            str += node.value.toString();
            if (node.left != null || node.right != null)
                str += "(" + toString(node.left) + "," +
                             toString(node.right) + ")";
        }
        return str;
    }

    public String toStringPreorder()
    {
        return toString(iteratorPreorder());
    }

    public String toStringInorder()
    {
        return toString(iteratorInorder());
    }

    public String toStringPostorder()
    {
        return toString(iteratorPostorder());
    }

    public String toStringLevelorder()
    {
        return toString(iteratorLevelorder());
    }

    private String toString(Iterator iter)
    {
        String str = "";
        while (iter.hasNext())
            str += iter.next().toString();
        return str;
    }

// ============================ Inner Classes =========================

    private class BTNode {

        public Object value;    // Value stored in this node
        public BTNode left;     // Reference to left child
        public BTNode right;    // Reference to right child
    
        public BTNode()
        {
            value = null;
            left = null;
            right = null;
        }

        public BTNode(Object value)
        {
            this.value = value;
            left = null;
            right = null;
        }

        public BTNode(Object value, BTNode left, BTNode right)
        {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}