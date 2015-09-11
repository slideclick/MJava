// Tree interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;
import java.io.Serializable;

public class LinkedTree extends AbstractTree
                                implements Tree, Cloneable {

// =============================== Tree ============================

    private TreeNode root;
    
    public LinkedTree()
    {
        super();
        root = null;
    }
    
    public void clear()
    {
        super.clear();
        root =null;
    }
    
    public Object clone()
    {
        throw new UnsupportedOperationException
        ("This is left as an exercise");
    }
    
    public String hierarchicalList()
    {
        return hierarchicalList (root, 0);
    }
    
    private String hierarchicalList (TreeNode curNode, int level)
    {
        if (curNode == null) return "";
        String str = "";
        for (int i = 0; i < level; i++) str += " ";
        str += curNode.value.toString();
        if (curNode.child != null)
            str += "\n" + hierarchicalList (curNode.child, level + 4);
        if (curNode.sibling != null)
            str += "\n" + hierarchicalList (curNode.sibling, level);
        return str;
    }  
    
    public Iterator iterator()
    // The iterator returns items in preorder
    {
        Queue qu = new LinkedQueue();
        preorderTraverse (root, qu); 
        return qu.iterator(); 
    }
    
    private void preorderTraverse (TreeNode curNode, Queue qu)
    /*
     * Creates a queue of items from an preorder traversal of the tree
     *
     * Pre:  none
     * Post: no change
     * Ret:  a queue containing the results of an inorder traversal
     *       of the tree 
     */
    {
        if (curNode == null) return;
        qu.enqueue (curNode.value);
        
        if (curNode.child != null)
            preorderTraverse (curNode.child, qu);
        if (curNode.sibling != null)
            preorderTraverse (curNode.sibling, qu);
        return;
    }  

    public int size()
    {
        if (size == -1)
            size = size (root);
        return size;
    }
    
    private int size (TreeNode node)
    {
        if (node == null) 
            return 0;
        else
            return 1 + size (node.child) + size (node.sibling);
    }
    
    public String toString()
    {
        return toString (root);
    }
    
    private String toString (TreeNode curNode)
    {
        if (curNode == null) return "";
        String str = curNode.value.toString();
        if (curNode.child != null)
            str += "(" + toString (curNode.child) + ")";
        if (curNode.sibling != null)
            str += "," + toString (curNode.sibling);
        return str;
    }  
    
    public TreeIterator treeIterator()
    {
        return new InnerTreeIter();
    }
    
// ============================== TreeNode ===========================

    private class TreeNode implements Serializable {

        private Object value;        // Value stored in this node
        private TreeNode child;      // Reference to first child
        private TreeNode sibling;    // Reference to right sibling
    
        private TreeNode()
        {
            value = null;
            child = null;
            sibling = null;
        }

        private TreeNode(Object value)
        {
            this.value = value;
            child = null;
            sibling = null;
        }

        private TreeNode(Object value, TreeNode child, TreeNode sibling)
        {
            this.value = value;
            this.child = child;
            this.sibling = sibling;
        }
    }

// ============================== InnerTreeIter ============================

    private class InnerTreeIter implements TreeIterator{
    
        private TreeNode currentNode;
        private lamborne.Stack parents;
        
        private InnerTreeIter()
        {
            currentNode = null;
            parents = new LinkedStack();
        }
        
        public boolean addRoot (Object item)
        {
            if (item == null)
                throw new IllegalArgumentException 
                ("Trying to add null item to tree");
            if (!isEmpty())
                throw new IllegalStateException
                ("Trying to add a root when tree is not empty");

            root = new TreeNode (item);
            currentNode = root;
            size = 1;
            return true;
        }
        
        public boolean addFirstChild (Object item)
        {
            if (item == null)
                throw new IllegalArgumentException 
                ("Trying to add null item to tree");
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Trying to add when no node is current");

            TreeNode newChild = new TreeNode (item, null, currentNode.child);
            currentNode.child = newChild;
            parents.push (currentNode);
            currentNode = newChild;
            size++;
            return true;
        }

        public boolean addRightSibling (Object item)
        {
            if (item == null)
                throw new IllegalArgumentException 
                ("Trying to add null item to tree");
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Trying to add when no node is current");
            if (currentNode == root)
                throw new IllegalStateException
                ("Trying to insert a sibling for the root");

            TreeNode newSibling = new TreeNode (item, null, currentNode.sibling);
            currentNode.sibling = newSibling;
            currentNode = newSibling;                  
            size++;
            return true;
        }
        
        public Object getCurrent()
        {
            if (!hasCurrentPosition())
                return null;
            else
                return currentNode.value;
        }
        
        public Object getFirstChild()
        // WARNING: currentNodes and parents change even if there is no
        // first child
        {
            if (currentNode == null) return null;
        
            parents.push (currentNode);
            currentNode = currentNode.child;
            if (currentNode == null)
                return null;
            else
                return currentNode.value;
        }        
              
        public Object getParent()
        {
            if (parents.isEmpty()) return null;
            
            currentNode = (TreeNode)(parents.pop());
            return currentNode.value;
        }
        
        public Object getRightSibling()
        {
            if (currentNode == null) return null;
        
            currentNode = currentNode.sibling;
            if (currentNode == null)
                return null;
            else
                return currentNode.value;
        }

        public Object getRoot()
        {
            if (isEmpty()) 
                return null;
            else{
                parents.clear();
                currentNode = root;
                return root.value;
            }
        }
        
        public boolean hasChild ()
        {
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Checking on child when there is no current position");
            return currentNode.child != null;
        }
        
        public boolean hasCurrentPosition ()
        {
            return currentNode != null;
        }
        
        public boolean hasParent()
        {
            return (parents.size() != 0);
        }
    
        public boolean hasRightSibling ()
        {
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Checking on right sibling when " +
                 "there is no current position"); 
            return currentNode.sibling != null;
        }
        
        public boolean moveTo (int index[])
        // Warning: This must be coded carefully to avoid the throwing
        // of exceptions.
         
        // If move succeeds currentNode is set to indicated node
        // else currentNode is set to null
        {
            if (isEmpty()){
                return false;
            }
            getRoot();
            for (int i = 0; i < index.length; i++){
                int childIndex = index[i];
                if (childIndex != 0){
                    if (!hasChild()){
                        currentNode = null;
                        return false;
                    }
                    getFirstChild();
                }
                for (int j = 2; j <= childIndex; j++){
                    if (!hasRightSibling()){
                        currentNode = null;
                        return false;
                    }
                    getRightSibling();
                }
            }
            return true;
        } 
        
        public boolean moveTo (Object item)
        // Warning: This code must be careful to avoid the throwing
        // of exceptions.

        // If move succeeds currentNode is set to indicated node
        // else currentNode is set to null
        {
            throw new UnsupportedOperationException
            ("This is left as an exercise");
        }
        
        public Object removeCurrent()
        {
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Trying to remove when no node is current");
                          
            Object returnItem = currentNode.value;      // Remember the item we
                                                                // are removing
                                                                 
            if (currentNode == root)                    // Remove the root node
                clear();             
                                     
            else {                                     // Remove a nonroot node
        
                size = -1;         // We don't know the number of currentNode's
                                  // descendants so we must invalidate the size
                              
                TreeNode parentNode = (TreeNode)(parents.peek());        // Get 
                                                        // currentNode's parent
           
                if (parentNode.child == currentNode)// There is no left sibling
                    parentNode.child = currentNode.sibling;  // so parent needs 
                                     // to point at currentNode's right sibling
                                              
                else{                                // There is a left sibling
                                 // Set priorNode to currentNode's left sibling 
                    TreeNode priorNode = parentNode.child;
                    while (priorNode.sibling != currentNode) 
                        priorNode = priorNode.sibling;
                    priorNode.sibling = currentNode.sibling; // and it needs to
                                       // points at currentNode's right sibling
                }
                                              
                if (currentNode.sibling == null)    // currentNode had no right
                    currentNode = null;      // sibling so there is no longer a 
                                                                 //current node
                
                else                   // currentNode has a right sibling so it
                    currentNode = currentNode.sibling;       // becomes current                        
            }
            return returnItem;
        }
        
        public Object setCurrent (Object item)
        {
            if (item == null)
                throw new IllegalArgumentException 
                ("Trying to set null item into tree");
            if (!hasCurrentPosition())
                throw new IllegalStateException
                ("Trying to set when no node is current");
            
            Object returnItem = currentNode.value;
            currentNode.value = item;
            return returnItem;
        }
               
    }
    
}
