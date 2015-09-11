import java.io.*;
import java.util.*;
import lamborne.*;
import ioutil.Format;

public class ConcordanceModel extends Object{
    
    private static int CAPACITY = 3;
    
    private List     wordBags;
    private Object   commonWords[];
    
    public ConcordanceModel()
    {
        wordBags    = new LinkedList();
        commonWords = null;
    }

    public void addFile (String dirName, String fileName)
    {
        System.out.println ("Adding Directory " + dirName + 
		                      " File " + fileName);
        
       try{
            // Open the file and establish a tokenizer on it
            File file = new File (dirName, fileName);
            FileInputStream stream = new FileInputStream (file);
            InputStreamReader isReader = new InputStreamReader (stream);
            BufferedReader bufReader = new BufferedReader (isReader);
            StreamTokenizer tokenizer = new StreamTokenizer (bufReader);
            
            // Declare quote and period to be whitespace
            tokenizer.whitespaceChars ((int)'"', (int)'"');
            tokenizer.whitespaceChars ((int)'.', (int)'.');
        
            // Read the words from the file and put each one in a set and
            // in a bag
            Bag bag = new HashBag(CAPACITY);
            Set set = new HashSet();
            String word = "";
        
            tokenizer.nextToken();
            while (tokenizer.ttype != StreamTokenizer.TT_EOF){
                if (tokenizer.ttype == StreamTokenizer.TT_WORD){
                    word = tokenizer.sval;
                    bag.add (word);
                    set.add (word);
                }
                tokenizer.nextToken();
            }
            
            // Add the bag to the end of the list of bags
            wordBags.add (bag);
            
            // We no longer know which words are common to all files
            commonWords = null;
            

        }catch(IOException e){
            System.out.println ("Error in file input:\n" + e);
        }    
    }

    public void removeFile (int index)
    {
        wordBags.remove (index);
        
        // We no longer know which words are common to all files
        commonWords = null;
    }

    public String computeStatistics (int index)
    {
        int maxWordWidth;
        int wordsPerLine = 3;
        int sortedArrayLength = 0;
        Set commonSet, tempSet, uniqueSet;
        Bag tempBag;
        Object uniqueWords[] = null, theseWords[] = null;
        int i;
        String str = "";

        // Determine maxWordWidth
        // Needed so that we can format user output in a readable format
        maxWordWidth = 1;
        tempBag = (Bag)(wordBags.get(index));
        Iterator iter = tempBag.iterator();
        while (iter.hasNext()){
            String word = (String)(iter.next());
            if (word.length() > maxWordWidth)
                maxWordWidth = word.length();
        }
        maxWordWidth += 2;
        
        // Determine the words common to all files
        if (commonWords == null){
            tempBag = (Bag)(wordBags.get(0));
            commonSet = new HashSet (tempBag.collectionView());
            for (i = 1; i < wordBags.size(); i++){
                tempBag =  (Bag)(wordBags.get(i));
                tempSet = new HashSet (tempBag.collectionView());
                commonSet.retainAll (tempSet);
            }
            sortedArrayLength = commonSet.size();
            if (sortedArrayLength != 0)
                commonWords = AbstractHeap.sort ((Collection)commonSet);
            else
                commonWords = new Object[0];
        }
        str += "Words Common to All Files: " + commonWords.length + "\n";
        for (i = 0; i < commonWords.length; i++){
            if (i%wordsPerLine == 0) str += "\n";
            str += 
              Format.justify ('l', commonWords[i].toString(), maxWordWidth);
        }
        str += "\n";

        // Determine the words unique to this file
        tempBag = (Bag)(wordBags.get(index));
        uniqueSet = new HashSet (tempBag.collectionView());
        for (i = 0; i < wordBags.size(); i++){
            if (i == index) continue;
            tempBag = (Bag)(wordBags.get(i));
            tempSet = new HashSet (tempBag.collectionView());
            uniqueSet.removeAll (tempSet);
        }
        sortedArrayLength = uniqueSet.size();
        if (sortedArrayLength != 0)
            uniqueWords = AbstractHeap.sort ((Collection)uniqueSet);
        str += "\nWords Unique to This File: " + sortedArrayLength + "\n";
        for (i = 0; i < sortedArrayLength; i++){
            if (i%wordsPerLine == 0) str += "\n";
            str += 
              Format.justify ('l', uniqueWords[i].toString(), maxWordWidth);
        }
        str += "\n";

       // Determine the words and frequencies for this file
        tempBag = (Bag)(wordBags.get(index));
        tempSet = new HashSet (tempBag.collectionView());
        str += "\nWords and Frequencies for This File: " 
            +  tempSet.size() + "/" + tempBag.size() + "\n\n";
        sortedArrayLength = tempSet.size();
        if (sortedArrayLength != 0)
            theseWords = AbstractHeap.sort (tempBag.collectionView());
        for (i = 0; i < sortedArrayLength; i++)
            str += 
              Format.justify ('l', theseWords[i].toString(), maxWordWidth)
              + ", "
              + tempBag.getCount (theseWords[i]) + "\n";
        
        // Return the string
        return str;
    }
}


