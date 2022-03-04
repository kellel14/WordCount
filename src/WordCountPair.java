public class WordCountPair {
    // Instance Variables
    private String word;
    private int count;

    public WordCountPair(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    //increment count by 1
    //add a method

    public void incrCount() {

        count = count + 1;
    }
}
