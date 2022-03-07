public class WordCountTable {

    // Instance Variables
    private WordCountPair[] word_counts;
    private int current;
    // Constructor
//---------------------------------------------------------------------------
    // Creates a word count table of specified size.
    //---------------------------------------------------------------------------
    public WordCountTable(int size) {
        word_counts = new WordCountPair[size];
        current = 0;
    }
    // Operators
    public void add(String word)
    //---------------------------------------------------------------------------
    // If word not in table, then adds WordCountPair to table for word.
// Otherwise, increments the word count for the word in table.
    //---------------------------------------------------------------------------
    {
        for(int i = 0; i < word_counts.length; i++){
            if( word_counts[i].getWord() == word ){
                 word_counts[i].incrCount();
            }

        }


    }
    // Iterator Methods
// These iterator methods hasNext, nextWord and reset are used by the client
// code to iterate over all of the WordCountPair objects in the wordcount table
// one-by-one (much like the hasNext and next methods of the Scanner class).
//
// Instance variable current is reserved for use by these methods only
// (not to be used by any other methods).
    public boolean hasNext()
    //---------------------------------------------------------------------------
    // Resets true if there is another WordCountPair to iterate over in the table.
// Otherwise, returns false.
    //---------------------------------------------------------------------------
    {
        //while(WordCountPair)
        if(current < word_counts.length){
            return true;
        }
        else {
            return false;
        }

    }
    public WordCountPair nextWordCountPair()
    //---------------------------------------------------------------------------
    // Returns the next WordCountPair object in the wordcount table.
    //---------------------------------------------------------------------------
    {
        //
        WordCountPair otherWordCount = word_counts[current]; //returns the word count
        current++; //adds or goes to the next one
        return otherWordCount; //returns this object

    }
    public void reset()
    //---------------------------------------------------------------------------
    // Resets to first WordCountPair of the table for iterating over.
    //---------------------------------------------------------------------------
    {
        current = 0;
    }
}
