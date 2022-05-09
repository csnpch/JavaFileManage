public class Main {
    
    public static void main(String[] args) {
        
        //? Create a new file
        ManageFile productFile = new ManageFile("product");
        // ManageFile sellerFile = new ManageFile("customer");
                                                              // custom type file         
        // ManageFile sellerFile = new ManageFile("seller", "txt");
    
        //? product
        String[] dataArr = {"1", "Hu", "hu", "hu"};
        productFile.writeDataByArr(dataArr);

        productFile.writeDataByStr("2, Ha, ha, ha");
        productFile.writeDataByStr("3, He, he, he");
        productFile.writeDataByStr("4, Ho, ho, ho");
        // productFile.readFile();
        // productFile.reportData();
        // productFile.clearFile();

        
        //? Example Get Data Product
        String[][] dataProduct = productFile.getData();
        
        for (int r = 0; r < dataProduct.length; r++) {
            for (int c = 0; c < dataProduct[r].length; c++) {
                System.out.print(dataProduct[r][c] + " ");
            }
            System.out.println();
        }


        //? edit data to product
        dataProduct[0][1] = "HuHaHe";        
        //? updateDataInFile 
        productFile.writeDataNewToFile(dataProduct);


        //? customer
        // customerFile.writeDataByStr("So, la, su");
        // customerFile.writeDataByStr("Su, la, so");
        // customerFile.readFile();
    
            
    }

}