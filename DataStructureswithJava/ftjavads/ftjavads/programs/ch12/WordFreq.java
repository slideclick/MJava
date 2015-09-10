// maintains a word and its frequency of occurrence
public class WordFreq implements Comparable<WordFreq>
{
	private String word;
	private int frequency;			// number of times word found

	// creates object with word and set frequency = 1
	public WordFreq(String word)
	{
		this.word = word;
		frequency = 1;
	}

	// add 1 to the frequency
	public void increment()
	{ frequency++; }

	// compare the word values in executing object and item
	public boolean equals (Object obj)
	{ return word.equals(((WordFreq)obj).word); }

	// compare the word values in executing object and item
	public int compareTo(WordFreq item)
	{ 
		return word.compareTo(item.word);	
	}

	// output an object in the format:  word (frequency)
	public String toString()
	{ return word + " (" + frequency + ")"; }
}
