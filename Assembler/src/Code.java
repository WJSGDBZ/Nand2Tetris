import org.junit.Test;

import java.awt.event.MouseAdapter;
import java.util.HashMap;

/**
 * convert instruction to binary
 */
public class Code {
    private static HashMap<String,String> comp;
    private static HashMap<String,String> dest;
    private static HashMap<String,String> jump;
    static {
        comp = new HashMap<String,String>(){{
            put("0","0101010");put("1","0111111");put("-1","0111010");put("D","0001100");put("A","0110000");
            put("!D","0001101");put("!A","0110001");put("-D","0001111");put("-A","0110011");
            put("D+1","0011111");put("A+1","0110111");put("D-1","0001110");put("A-1","0110010");
            put("D+A","0000010");put("D-A","0010011");put("A-D","0000111");put("D&A","0000000");
            put("D|A","0010101");

            put("M","1110000");put("!M","1110001");put("-M","1110011");put("M+1","1110111");
            put("M-1","1110010");put("D+M","1000010");put("D-M","1010011");
            put("M-D","1000111");put("D&M","1000000");put("D|M","1010101");
        }};
        dest = new HashMap<String,String>(){{
            put(null,"000");put("M","001");put("D","010");put("MD","011");
            put("A","100");put("AM","101");put("AD","110");put("AMD","111");
        }};
        jump = new HashMap<String,String>(){{
            put(null,"000");put("JGT","001");put("JEQ","010");put("JGE","011");
            put("JLT","100");put("JNE","101");put("JLE","110");put("JMP","111");
        }};
    }

    public String comp(String ins){
        return comp.get(ins);
    }

    public String dest(String ins){
        return dest.get(ins);
    }
    public String jump(String ins){
        return jump.get(ins);
    }

    /**
     * if 16 bits are not enough, use 0 to fill up
     * @param length 长度
     * @param number 数字
     * @return
     */
    public static String lpad(int length, String number) {
        for(int i=number.length();i<length;i++){
            number = "0"+number;
        }
        return number;
    }

    /**
     * dec to bin
     * @param number
     * @return
     */
    public static String binary(int number){
        String binary = "";
        while(number > 0){
            binary = number%2 + "" + binary;
            number /= 2;
        }
        return binary==""? "0":binary;
    }
}
