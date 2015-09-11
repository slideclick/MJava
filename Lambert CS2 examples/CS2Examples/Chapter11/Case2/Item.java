// Item
// (c) 2000 Ken Lambert and Martin Osborne

public class Item extends Object{

    public String name, description;

    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String toString()
    {
        String desc = description.replace ('\n', ' ');
        return name + " >>> " + desc;
    }
    
    public boolean equals (Object obj)
    {
        if (obj == null || !(obj instanceof Item))
            return false;
        else{
            Item item = (Item)obj;
            return name.equals (item.name);
        }
    }
}
