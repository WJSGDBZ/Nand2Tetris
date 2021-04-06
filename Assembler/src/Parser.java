import org.junit.Test;

import java.util.HashMap;

/**
 * read the file ,get the different commands in it and
 * breaks them into parts
 */
public class Parser {
    private String type;// type of instruction
    private String comp;// compute part
    private String dest;// destination part
    private String jump;// jump part
    private String address;// binary addr
    private int length = 0;// instruction length
    private int varLength = 16;

    // only check label
    public void firstPass(String instruction,SymbolTable table){
        String ins = instruction.replace(" ","");// remove space
        if(ins.length() == 0){ // ignore space
            return;
        }

        char sign = ins.charAt(0);
        if(sign == '('){
            String label = "";
            for(int i=1;i<ins.length()-1;i++){
                label+=ins.charAt(i);
            }
            System.out.println("label= "+label+" addr="+length);
            //put label and next instruction
            table.put(label,length);
        }else if(sign !='/'){
            length++;
        }
    }

    //parse instruction and check var
    public boolean parse(String instruction, SymbolTable table){
        String ins = instruction.replace(" ",""); // remove space
        if(ins.length() == 0) return false; // ignore space
        System.out.println("当前指令为："+ins);
        char sign = ins.charAt(0);
        //A-instruction
        if(sign == '@') {
            type = "A";
            initA();
            if(Character.isDigit(ins.charAt(1))){//address or data
                int addr = 0;
                for(int i=1;i<ins.length();i++){
                    addr = addr*10 + (int)ins.charAt(i) - (int)('0');
                }
                String binary = "";
                while(addr > 0){
                    binary = addr%2 + "" + binary;
                    addr /= 2;
                }
                address = binary==""?"0":binary;
            }else{ //var
                String s = ins.substring(1,ins.length());
                int varAddr = table.get(s);
                if(varAddr == -1){
                    table.put(s,varLength);
                    varLength++;
                }
                address = s;
            }
        } else if(sign!='(' && sign!='/'){//C-instruction
            type = "C";
            initC();
            int equal = ins.indexOf('=');
            int sem = ins.indexOf(';');
            int note = ins.indexOf('/');
            if(equal != -1){
                if(sem != -1){
                    comp = ins.substring(equal+1,sem);
                }else{
                    if(note != -1){
                        comp = ins.substring(equal+1,note);
                    }else{
                        comp = ins.substring(equal+1,ins.length());
                    }
                    comp = comp.trim();
                }
                dest = ins.substring(0,equal);
            }
            if(sem != -1){
                if(note != -1){
                    jump = ins.substring(sem+1,note);
                }else{
                    jump = ins.substring(sem+1,ins.length());
                }
                jump = jump.trim();
                if(equal == -1){
                    comp = ins.substring(0,sem);
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public void initA(){
        address = "";
    }

    public void initC(){
        comp = "";
        dest = null;
        jump = null;
    }
    public String getType() {
        return type;
    }

    public String getComp() {
        return comp;
    }

    public String getDest() {
        return dest;
    }

    public String getJump() {
        return jump;
    }

    public String getAddress() {
        return address;
    }

    public int getLength() {
        return length;
    }

    public int getVarLength() {
        return varLength;
    }
}
