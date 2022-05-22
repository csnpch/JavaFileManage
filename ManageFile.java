import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Base64;


public class ManageFile {
    

    private String[][] data;
    private int indexData = 0;
    private String fileName = new String(""); 
    private String typeName = new String("md");
    private String pathFolder = new String("data");

    
    public ManageFile() {
        if (this.fileName.isEmpty()) {
            System.out.println("\n\nParameter file name is empty!\n\n");
        }
    }


    public ManageFile(String name) {
        this.fileName = name;
        checkDirectory();
        createFile(); 
    }


    public ManageFile(String name, String type) { 
        this.fileName = name;
        this.typeName = type;
        checkDirectory();
        createFile(); 
    }
    
    
    public ManageFile(String name, String type, String pathFolder) { 
        this.fileName = name;
        this.typeName = type;
        this.pathFolder = pathFolder;
        checkDirectory();
        createFile(); 
    }


    public void checkDirectory() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }


    public String[][] getData() {
        readFile();
        return this.data;
    }


    public void setSizeData(int row, int col) {
        this.data = new String[row][col];
        System.out.println("Array size data in file : " + row + " x " + col);
    }


    public void clearData() {
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                this.data[i][j] = "";
            }
        }
    }


    public void clearFile() {

        File file = new File("./" + this.pathFolder + "/" + this.fileName + "." + this.typeName);
        try {
            file.delete();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        
    }

    
    public void pushDataToVariable(String[] val) {

        for (int i = 0; i < val.length; i++) {
            this.data[this.indexData][i] = val[i];
        }
        this.indexData++;

    }


    public void reportData() {
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[i].length; j++) {
                System.out.print(this.data[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void readFile() {

        try {

            if (this.data != null) {
                clearData();
            }
        
            File myObj = new File("./" + this.pathFolder + "/"+ this.fileName + "." + this.typeName);
            Scanner myReaderForCount = new Scanner(myObj);
            Scanner myReaderForData = new Scanner(myObj);

            int unitRows = 0;
            int unitCols = 0;
            while (myReaderForCount.hasNextLine()) {
                String data = myReaderForCount.nextLine();
                data = getDecoded(data);
                unitRows++;
                unitCols = data.split(",").length;
                data = data.replaceAll("\\s+", "");
            }
            setSizeData(unitRows, unitCols);
            
            //- It bin 
            while (myReaderForData.hasNextLine()) {
                String data = myReaderForData.nextLine();
                data = getDecoded(data);
                data = data.replaceAll("\\s+", "");
                if (data.split(",").length > 0) {
                    pushDataToVariable(data.split(","));
                }
            }

            myReaderForCount.close();
            myReaderForData.close();
        
        } catch (FileNotFoundException e) {
            
            createFile();
            readFile();

        }
    }


    public void createFile() {
        try {

            File myObj = new File("./" + this.pathFolder + "/" + this.fileName + "." + this.typeName);
            
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println(this.fileName + " - File already exists.");
            }

        } catch (IOException ex) {
            
            System.out.println("An error occurred.");
            // ex.printStackTrace();
        
        }
    }


    public void writeDataByArr(String[] data) {
        
        String line = "";
        for (int i = 0; i < data.length; i++) {
            line += data[i] + ",";
        }
        line = line.substring(0, line.length() - 1);
        writeDataByStr(line);

    }


    public void writeDataByStr(String val) {
        try {

            val = getEncoded(val);
            
            File myObj = new File("./" + this.pathFolder + "/" + this.fileName + "." + this.typeName);
            FileWriter myWriter = new FileWriter(myObj, true);
            BufferedWriter buffer = new BufferedWriter(myWriter);
            buffer.write(val);
            buffer.newLine();
            buffer.close();
            myWriter.close();
        }
        catch (IOException ex) {
            System.out.println("An error occurred.");
        }
    }


    public String getEncoded(String val) {
        // return val;
        try {
            byte[] data = val.getBytes("UTF-8");
            String encoded = Base64.getEncoder().encodeToString(data);
            return encoded;
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            return "";
        }
    }


    public String getDecoded(String val) {
        // return val;
        try {
            byte[] data = val.getBytes("UTF-8");
            String decoded = new String(Base64.getDecoder().decode(data), "UTF-8");
            return decoded;
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            return val;
        }
    }

    
    public void writeDataNewToFile(String[][] data) {
        
        clearFile();

        for (int r = 0; r < data.length; r++) {

            String line = "";
            for (int c = 0; c < data[r].length; c++) {
                if (data[r][c].equals("")) {
                    line += data[r][c] + ",";
                }
            }
            line = line.substring(0, line.length() - 1);
            writeDataByStr(line);
        }

    } 

    
}
