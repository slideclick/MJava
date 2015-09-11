public class ExpressionTree extends Object {

    private BTNode root;

//===================================================================
// Static accept method
//===================================================================

    public static void accept(boolean condition, String msg)
    {
       if (! condition)
          throw new RuntimeException(msg);
    } 
    
//===================================================================
// Constructors
//===================================================================

    public ExpressionTree()
    {
        ExpressionTree.accept 
          (false, "Trying to instantiate an empty tree"); 
    }
    
    public ExpressionTree(Token token)
    {
        ExpressionTree.accept 
          (token.type == Token.INT, 
          "Trying to instantiate a one node tree with noninteger = " + token);

        root = new BTNode (token);
    }
    
    public ExpressionTree(Token token, ExpressionTree leftTree, 
                                       ExpressionTree rightTree)
    {
        ExpressionTree.accept
          (token != null, "Trying to instantiate a tree with a null root");
        ExpressionTree.accept
          (token.isOperator(),
          "Trying to instantiate a tree with root operator" + " = " + token);
        ExpressionTree.accept
          (leftTree != null, 
          "Trying to instantiate a tree with left subtree null");
        ExpressionTree.accept
          (rightTree != null,
          "Trying to instantiate a tree with right subtree null");

        root = new BTNode (token, leftTree.root, rightTree.root);
    }
    
//===================================================================
// To string methods
//===================================================================

 
    public String toString()
    {
        return toString (root);
    }
    
    private String toString (BTNode curNode)
    {
        if (curNode == null) return "";
        String str = curNode.value.toString();
        if (curNode.left == null && curNode.right == null) return str;
        return str +
               "(" +
               toString (curNode.left) + 
               "," + 
               toString (curNode.right) +
                ")";
    }  
    
//...................................................................

    public String infix(){
        return infix (root);
    }
    
    private String infix (BTNode node){
        String str = "";
        if (node == null) 
            return "";
        else{
            if (node.left != null) str += "(";    
            str += infix (node.left) +
                   node.value.toString() +
                   infix (node.right);
            if (node.right != null) str += ")";
        }
        return str;
    }       
    
//...................................................................

    public String prefix(){
        return prefix (root);
    }
    
    private String prefix (BTNode node){
        String str;
        if (node == null) 
            return "";
        else    
            return node.value.toString() +
                   prefix (node.left) +
                   prefix (node.right);
    }       
    
//...................................................................

    public String postfix(){
        return postfix (root);
    }
    
    private String postfix (BTNode node){
        String str;
        if (node == null) 
            return "";
        else    
            return postfix (node.left) +
                   postfix (node.right) +
                   node.value.toString();
    }       
    
//===================================================================
// evaluate method
//===================================================================

    public int evaluate(){
        return evaluate (root);
    }
    
    private int evaluate(BTNode node){
        Token token = (Token) node.value;
        if (token.type == Token.INT)
            return token.value;
        else{
            int leftOperand = evaluate (node.left);
            int rightOperand = evaluate (node.right);
            return computeValue (token, leftOperand, rightOperand);
        }
    }
    
    private int computeValue(Token op, int value1, int value2){
      int result = 0;
      switch (op.type){
         case Token.PLUS:
            result = value1 + value2;
            break;
         case Token.MINUS:
            result = value1 - value2;
            break;
         case Token.MUL:
            result = value1 * value2;
            break;
         case Token.DIV:
            ExpressionTree.accept
              (value2 != 0, "Trying to divide by zero in an expression tree");             
            result = value1 / value2;
            break;
      }
      return result;
   }
        
//===================================================================
//============================ Inner Classes ========================

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



