package JavaFiles;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class JavaFile{
    private String adress;
    List<String> imports;
    String packageName;
    List<Class> classes;

    public JavaFile(String adress){
        this.adress = adress;
        imports = new ArrayList<>();
        classes = new ArrayList<>();
    }

    public String getAdress (){
        return adress;
    }

    public void load()throws IOException{
        Scanner scanner = new Scanner(new File(adress)).useDelimiter("\n");
        StringBuilder tmp = new StringBuilder();
        Class tmpClass;
        while (scanner.hasNext()){
            tmp.append(scanner.next()).append("\n");
        }
        String code = tmp.toString();
        Pattern pattern = Pattern.compile("import .+;");
        Matcher matcher=pattern.matcher(code);
        while(matcher.find()){
            imports.add(matcher.group());
        }
        pattern = Pattern.compile("package .+;");
        matcher = pattern.matcher(code);
        if(matcher.find()){
            packageName = matcher.group();
        }
        pattern = Pattern.compile("(public )?class [a-zA-Z0-9]+ *[\\n\\r]?[{]{1}[^{}]*([^{}]*[{]{1}[^{}]*}[^{}]*)*}");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            tmpClass = new Class();
            String str = matcher.group();
            tmpClass.load(str);
            classes.add(tmpClass);
        }
    }
}
