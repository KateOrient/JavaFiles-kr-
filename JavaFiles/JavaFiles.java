package JavaFiles;

import java.util.*;
import java.io.*;

public class JavaFiles{
    private List<JavaFile> list;

    public JavaFiles(){
        list = new ArrayList<JavaFile>();
    }

    public void load(String fileName)throws IOException{
        Scanner scanner = new Scanner(new File(fileName)).useDelimiter("\\s*;\\s*");
        JavaFile tmp;
        while(scanner.hasNext()){
            tmp =new JavaFile(scanner.next());
            tmp.load();
            list.add(tmp);

        }
    }

    public void printLexeme(String fileName)throws IOException{
        Scanner scanner;
        List<String> lexemes = new ArrayList<>();
        for(JavaFile item:list){
            scanner = new Scanner(new File(item.getAdress())).useDelimiter("\\s*[{}]{1}\\s*");
            while(scanner.hasNext()){
                lexemes.add(scanner.next());
            }
            scanner.close();
        }
        PrintStream ps =new PrintStream(fileName);
        Iterator it = lexemes.iterator();
        while (it.hasNext()){
            ps.println(it.next());
            ps.println();
        }
        ps.close();
    }

    public void printLexemeMentions(String fileName)throws IOException{
        Scanner scanner;
        Map<String,Integer> lexemes = new TreeMap<>();
        String tmp;

        for(JavaFile item:list){
            scanner = new Scanner(new File(item.getAdress())).useDelimiter("\\s*[{}]{1}\\s*");

            while(scanner.hasNext()){
                if(lexemes.containsKey((tmp = scanner.next()))){
                    lexemes.replace(tmp,lexemes.get(tmp)+1);
                }
                else{
                    lexemes.put(tmp,1);
                }
            }
            scanner.close();
        }

        PrintStream ps = new PrintStream(fileName);
        lexemes.forEach((s,i)->{ps.println(s+" + "+i);ps.println();});
        ps.close();
    }

}
