//===========================================================================
// WORD COUNT PROGRAM
//===========================================================================
// This program will input any text file and output a table of word counts
// containing each word in the document and the number of times that each
// word appears. Maximum number of words is 500.
//---------------------------------------------------------------------------
//package Program1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class main {
        public static void main(String[] args) {

            // Variable Declarations
            // -- keyboard input
            Scanner keyboard_input = new Scanner(System.in);

            // -- file input
            boolean file_found = false;
            String file_name = "";
            Scanner file_input = null;

            // --line buffer
            // (Index [0] = current char position, index [1] = buffer contents)
            String[] line_buffer = new String[2];

            // -- word count table (as parallel arrays)
            String[] word_list = new String[500];
            int[] word_counts = new int[500];


            // File Processing
            // -- open file
            while (!file_found) {
                try {
                    // get file name
                    System.out.println("Enter file name (including .txt): ");
                    file_name = keyboard_input.nextLine();

                    // -- open file for reading
                    file_input = new Scanner(new File(file_name));
                    file_found = true;
                } catch (FileNotFoundException e) {
                    System.out.println("File " + file_name + " not found.");
                    System.out.println("Please re-enter");
                }
            }

            // -- process file
            processFile(word_list, word_counts, file_input);

            // Display Word Counts
            displayTable(word_list, word_counts);
        }


        // Supporting Methods

        public static boolean isLetter(char c)
        //---------------------------------------------------------------------------
        // INPUT:       c
        // PRECOND:     None.
        // OUTPUT:      <function value>
        // POSTCOND:    If c between 'a' ... 'z' or 'A' ... 'Z', returns true,
        //              else, returns false.
        //---------------------------------------------------------------------------
        {
            return 'a' <= c && c <= 'z' ||
                    'A' <= c && c <= 'Z';
        }


        public static boolean endOfBuffer(String[] buffer)
        //---------------------------------------------------------------------------
        // INPUT:       buffer
        // PRECOND:     buffer[0] and buffer[1] not equal to empty string.
        //              buffer[0] contains only digit characters.
        //              int(buffer[0]) < len(buffer[1])
        //
        // OUTPUT:      <function value>
        // POSTCOND:    If at end of buffer, returns true, else returns false.
        //---------------------------------------------------------------------------
        {
            int curr_pos = Integer.valueOf(buffer[0]);

            return curr_pos == buffer[1].length();
        }

        public static String scanWord(String[] buffer)
        //---------------------------------------------------------------------------
        // INPUT:       buffer
        // PRECOND:     buffer[0] and buffer[1] not equal to empty string.
        //              buffer[0] contains only digit characters.
        //              int(buffer[0]) < len(buffer[1])
        //
        // OUTPUT:      <function value>, buffer
        // POSTCOND:    Returns next word in buffer as function value.
        //              int(buffer[0]) at first letter of next word, if another letter
        //              found, else at end of line character.
        //---------------------------------------------------------------------------
        {
            String next_word = "";
            int curr_pos = Integer.valueOf(buffer[0]);

            // skip any initial non-letters
            while (!endOfBuffer(buffer) && !isLetter(buffer[1].charAt(curr_pos))) {

                // incr current position (in buffer also)
                curr_pos = curr_pos + 1;
                buffer[0] = String.valueOf(Integer.valueOf(curr_pos));
            }

            // scan all letters in next word
            while (!endOfBuffer(buffer) && isLetter(buffer[1].charAt(curr_pos))) {

                // append current letter to word building
                next_word = next_word + buffer[1].charAt(curr_pos);

                // incr current position (in buffer also)
                curr_pos = curr_pos + 1;
                buffer[0] = String.valueOf(Integer.valueOf(curr_pos));
            }


            // return word scanned
            return next_word;
        }

        public static LineBuffer readLine(Scanner in_file)
        //---------------------------------------------------------------------------
        // INPUT:       in_file
        // PRECOND:     open(in_file) and not at end of file
        //
        // OUTPUT:      <function value>
        // POSTCOND:    Returns next line from in_file and returns as a line buffer
        //              (i.e., index[0] = 0, index[1] contains line)
        //---------------------------------------------------------------------------
        {
            /*
            String[] buffer = new String[2];
            buffer[0] = "0";
            buffer[1] = in_file.nextLine();

            return buffer;
             */
            LineBuffer buffer = new LineBuffer(in_file.nextLine());
            return buffer;
        }


        public static void updateTable(String word, String[] words, int[] counts)
        //---------------------------------------------------------------------------
        // INPUT:       word, words, counts
        // PRECOND:     len(words) > 0, len(counts) = len(words).
        //
        // OUTPUT:      words, counts
        // POSTCOND:    If word equal to words[i], counts[i] incremented by one.
        //              Otherwise, adds word to words and sets corresponding element
        //              in counts to one.
        //---------------------------------------------------------------------------
        {
            int i = 0;
            boolean found = false;

            while (!found) {
                if (words[i] != null && words[i].equals(word)) {
                    counts[i] = counts[i] + 1;
                    found = true;
                } else if (words[i] == null) {
                    words[i] = word;
                    counts[i] = 1;
                    found = true;
                } else
                    i = i + 1;

            }
        }

        public static void displayTable(String[] words, int[] counts)
        //---------------------------------------------------------------------------
        // INPUT:       words, counts
        // PRECOND:     len(words) > 0, len(counts) = len(words).
        //
        // OUTPUT:      None
        // POSTCOND:    Displays corresponding words and counts on screen.
        //---------------------------------------------------------------------------
        {
            int i = 0;
            while (words[i] != null) {
                //System.out.printf(format("%12s", words[i]), format("%3d", counts[i]));
                System.out.printf("%12s", words[i]);
                System.out.printf("%3d", counts[i]);
                System.out.println();
                i = i + 1;
            }
        }


        public static void processFile(String[] words, int[] counts, Scanner in_file)
        //---------------------------------------------------------------------------
        // INPUT:       words, counts, in_file
        // PRECOND:     len(words) > 0, len(counts) = len(words).
        //
        // OUTPUT:      words, counts
        // POSTCOND:    First k elements of words contain the k unique words in file.
        //              First k elements of counts contains the corresponding number
        //              of occurrences of each word in words.
        //---------------------------------------------------------------------------
        {
            String next_word;
            LineBuffer buffer;

            while (!endOfFile(in_file)) {
                buffer = readLine(in_file);

                while (!buffer.endOfBuffer()) {
                    next_word = buffer.scanWord();
                    updateTable(next_word, words, counts);
                }
            }
        }


        public static boolean endOfFile(Scanner in_file)
        //---------------------------------------------------------------------------
        // INPUT:       in_file
        // PRECOND:     open(in_file)
        //
        // OUTPUT:      Boolean
        // POSTCOND:    Returns true if at end of file, otherwise, returns false.
        //--------------------------------------------------------------------------
        {
            return !in_file.hasNext();
        }

    }
