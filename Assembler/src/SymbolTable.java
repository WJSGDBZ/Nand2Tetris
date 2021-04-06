import java.util.HashMap;

/**
 * record Label and Var when it appear first
 * and pre-define Symbol
 */
public class SymbolTable {
    private HashMap<String,Integer> table;

    public void init(){
        table = new HashMap<String,Integer>(){{
            //pre-define
            // R0-R15
            for(int i=0;i<16;i++){
                String key = "R"+i;
                put(key,i);
            }

            put("SCREEN",16384);
            put("KBD",24576);
            put("SP",0);
            put("LCL",1);
            put("ARG",2);
            put("THIS",3);
            put("THAT",4);
        }};
    }

    public int get(String str){
        int ret = -1;
        if(table.containsKey(str))
            ret = table.get(str);

        return ret;
    }

    public void put(String key,int value){
        table.put(key,value);
    }

    public HashMap<String, Integer> getTable() {
        return table;
    }
}
