public class LinkedEdgePT {

    private double           weight;       
    private boolean          mark;               
    private LinkedVertexPT   vertex1;   
    private LinkedVertexPT   vertex2;   
    
    protected LinkedEdgePT (LinkedVertexPT from, LinkedVertexPT to)
    {
        vertex1 = from;
        vertex2 = to;
        weight = 0;
        mark = false;
    }

    protected LinkedEdgePT (LinkedVertexPT from, LinkedVertexPT to, 
	                         double weight)          
    {
        vertex1 = from;
        vertex2 = to;
        this.weight = weight; 
        mark = false;
    }
    
    public void clearMark()
    {
        mark = false;
    }
    
    public boolean equals (Object obj)
    {
        if (!(obj instanceof LinkedEdgePT))
            return false;
            
        LinkedEdgePT edge = (LinkedEdgePT) obj;
        return (vertex1 == edge.vertex1 && vertex2 == edge.vertex2) ||
               (vertex1 == edge.vertex2 && vertex2 == edge.vertex1);
    }
    
    public LinkedVertexPT getOtherVertex (LinkedVertexPT thisVertex)
    {
        if (thisVertex == null || thisVertex == vertex2)
            return vertex1;
        else
            return vertex2;
    }
    
    public double getWeight()
    {
        return weight;
    }
    

    public boolean isMarked() 
    {
        return mark;
    }
    
    public void setMark()
    {
        mark = true;
    }
    
    public void setWeight (double weight)
    {
        this.weight = weight;
    }     
          
    public String toString()
    {
        return "" + vertex1 + ":" + vertex2   + ":" + weight;
    }

}

