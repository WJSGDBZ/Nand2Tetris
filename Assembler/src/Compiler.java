import java.io.*;

/**
 * convert .asm to .hdl
 */
public class Compiler {
    static String path = "";

    public static void main(String[] args) {
        String filename = "";
        Compiler cpr = new Compiler();
        cpr.compiler(path+filename);
    }

    public void compiler(String filename){
        Parser parser = new Parser();
        Code code = new Code();
        FileInputStream fis = null;
        BufferedReader br = null;
        SymbolTable table = new SymbolTable();
        table.init();
        BufferedWriter bw = null;
        FileWriter fw = null;
        firstPass(filename+".asm",table);//check label

        try {
            fis = new FileInputStream(filename+".asm");
            File file = new File(filename+".hack");
            br = new BufferedReader(new InputStreamReader(fis));
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            if (!file.exists()) {
                file.createNewFile();
            }
            String str = null;
            while((str = br.readLine()) != null){
                System.out.println("read:" + str);
                String binaryCode = "";
                if(parser.parse(str,table)){
                    String type = parser.getType();
                    if(type == "A"){
                        String address = parser.getAddress();
                        if(Character.isDigit(address.charAt(0))){
                            binaryCode = code.lpad(16,address);
                        }else{
                            int i = table.get(address);
                            String binary = code.binary(i);
                            binaryCode = code.lpad(16,binary);
                        }
                    }else if(type == "C"){
                        String c = parser.getComp();
                        String d = parser.getDest();
                        String j = parser.getJump();
                        System.out.println("part:" + " comp="+c+" dest="+d+" jump="+j);
                        String cc = code.comp(c);
                        String dd = code.dest(d);
                        String jj = code.jump(j);

                        binaryCode = "111" + cc + dd + jj;
                    }
                    System.out.println("write:" +binaryCode);
                    bw.write(binaryCode+"\n");
                    if(binaryCode.contains("null"))
                        throw new RuntimeException(String.valueOf(br).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
                if(fis != null)
                    fis.close();
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void firstPass(String filename,SymbolTable table){
        Parser parser = new Parser();
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filename);
            br = new BufferedReader(new InputStreamReader(fis));
            String str = null;
            while((str = br.readLine()) != null){
                parser.firstPass(str,table);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
