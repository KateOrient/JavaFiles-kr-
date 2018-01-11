package JavaFiles;

import java.util.*;
import java.util.regex.*;

public class Class{
    public String access;
    public String name;
    public List<String> staticFields;
    public List<String> finalFields;
    public List<String> fields;
    public List<String> staticMethods;
    public List<String> finalMethods;
    public List<String> methods;

    public Class(){
        staticFields = new ArrayList<>();
        finalFields = new ArrayList<>();
        fields = new ArrayList<>();
        staticMethods = new ArrayList<>();
        finalMethods = new ArrayList<>();
        methods = new ArrayList<>();
    }

    public void setAccess (String access){
        this.access = access;
    }

    public boolean hasMain(){
        for(String item:staticMethods){
            if(item.matches("public +static +void +main(.|\\s)*")){
                return true;
            }
        }
        return false;
    }

    public void load(String code){
        if(code.startsWith("public")){
            access = "public";
        }
        Pattern pattern = Pattern.compile("(?<=class ).+(?=\\s*\\{)");
        Matcher matcher = pattern.matcher(code);
        if(matcher.find()){
            name = matcher.group();
        }
        pattern = Pattern.compile("(public|private|protected) +static +[a-zA-Z<>_0-9]+ +[a-zA-Z0-9_]+.*;");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            staticFields.add(matcher.group());
        }
        pattern = Pattern.compile("(public|private|protected) +final +[a-zA-Z<>_0-9]+ +[a-zA-Z0-9_]+.*;");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            finalFields.add(matcher.group());
        }
        pattern = Pattern.compile("(public|private|protected)(?! +(static|final)) +[a-zA-Z<>_0-9]+ +[a-zA-Z0-9_]+.*;");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            fields.add(matcher.group());
        }
        pattern = Pattern.compile("(@Override\\s*)?(public|private|protected) +static +[a-zA-Z<>_]+ *[a-zA-Z<>_]* *\\(.*\\) *[\\n\\r]?[{]{1}[^{}]*([^{}]*[{]{1}[^{}]*}[^{}]*)*}");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            staticMethods.add(matcher.group());
        }
        pattern = Pattern.compile("(@Override\\s*)?(public|private|protected) +final +(?!class)[a-zA-Z<>_]+ *[a-zA-Z<>_]* *\\(.*\\) *[\\n\\r]?[{]{1}[^{}]*([^{}]*[{]{1}[^{}]*}[^{}]*)*}");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            finalMethods.add(matcher.group());
        }
        pattern = Pattern.compile("(@Override\\s*)?(public|private|protected) +(?!class)[a-zA-Z<>_]+ *[a-zA-Z<>_]* *\\(.*\\) *[\\n\\r]?[{]{1}[^{}]*([^{}]*[{]{1}[^{}]*}[^{}]*)*}");
        matcher = pattern.matcher(code);
        while(matcher.find()){
            methods.add(matcher.group());
        }
    }
}
