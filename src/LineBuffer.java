public class LineBuffer {

    // Instance Variables
    String line;
    int current_char;
    // Constructor
//---------------------------------------------------------------------------
    // Initializes a newly created LineBuffer object to contain the line of
// text passed, with the current character set to 0.
    //---------------------------------------------------------------------------
    public LineBuffer(String line) {
        this.line = line;
        current_char = 0;
    }
    // Operators
    public boolean endOfBuffer()
    //---------------------------------------------------------------------------
    // Returns true if current character of line buffer is at the end of the
// buffer, otherwise, returns false.
    //---------------------------------------------------------------------------
    {
        /**
         *
         * revert to original work code here
         *int current_char = Integer.valueOf(line);
         *         return current_char == line.length();
         */

        //if(current_char[0])

        return current_char >= line.length();
    }
    public String scanWord()
    //---------------------------------------------------------------------------
    // Returns the next word scanner in the line buffer.
    //---------------------------------------------------------------------------
    {


        String next_word = "";
        while(!isLetter()){
            current_char++;

        }
        while(isLetter()){
            next_word += line.charAt(current_char);
            current_char++;
        }
        return next_word;
        //return scanWord(line);
    }
    // Private Supporting Methods
    private boolean isLetter()
    //---------------------------------------------------------------------------
    // Returns true if current character of buffer is between 'a' ... 'z' or
// 'A' ... 'Z', otherwise, returns false.
    //---------------------------------------------------------------------------
    {

        // quick way, this checks between the alphabet
        return 'a' <= current_char && current_char <= 'z' ||
                'A' <= current_char && current_char <= 'Z';
    }
}