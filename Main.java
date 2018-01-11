import JavaFiles.JavaFiles;

import java.io.IOException;

public class Main{
    public static void main (String[] args)throws IOException{
        JavaFiles jf = new JavaFiles();
        jf.load("input1.txt");
        jf.printLexeme("output1.txt");
        jf.printLexemeMentions("output2.txt");
    }
}
