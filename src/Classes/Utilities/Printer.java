package Classes.Utilities;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;
import Classes.Matrix.Pivots.Classes.Pivot;
import Classes.Matrix.Pivots.Classes.Pivots;

public class Printer {
    public static void     Matrix(double[][] matrix, String message){
        if(message.length() > 0)
            System.out.println(message);    
        for (double[] row : matrix) 
            Vector(row, "", "|");
            
        System.out.print("\n");  
    }
    public static void     Matrix(Matrix matrix, String message){
        if(message.length() > 0)
            System.out.println(message);  
        for (int indexRow = 0; indexRow < matrix.Get_SizeRows(); indexRow++) {
            double[] row = matrix.Get_Row(indexRow);
            Vector(row, "", "|");
        }
        System.out.print("\n");  
    }
    public static void     MatrixStatus(String header, String[] categories, String[][] content, int cellSize){
        Table(header, categories, content, cellSize);
    }
    public static void     Pivots(Pivots_Augmented pivots){
        String header = "\n\nDetailed report of variables' status...";
        String[] categories = {"Name", "Position", "Coeficients", "Result", "Status"};
        int cellSize = 12;
        String[][] content = Get_PivotsRows(pivots, cellSize);
        Table(header, categories, content, cellSize);
    }
    public static void     Pivots(Pivots pivots){
        String header = "\n\nDetailed report of variables' status...";
        String[] categories = {"Name", "Position", "Coeficients", "Status"};
        int cellSize = 12;
        String[][] content = Get_PivotsRows(pivots, cellSize);
        Table(header, categories, content, cellSize);
    }
    private static String[][]   Get_PivotsRows(Pivots_Augmented pivots, int cellSize){
        String[][] content = new String[pivots.Get_Size()][5];
        for (int index = 0; index < pivots.Get_Size(); index++) {
            content[index] = PivotTo_StringArray(pivots.Get_Pivot(index));
        }
        return content;
    }
    private static String[][]   Get_PivotsRows(Pivots pivots, int cellSize){
        String[][] content = new String[pivots.Get_Size()][4];
        for (int index = 0; index < pivots.Get_Size(); index++) {
            content[index] = PivotTo_StringArray(pivots.Get_Pivot(index));
        }
        return content;
    }
    private static String[]     PivotTo_StringArray(Pivot_Augmented pivot){
        String[] pivotString = new String[5]; 
        if(pivot != null ){
            pivotString[0] = pivot.Get_Name();
            if(!pivot.Get_IsFree()){
                int[] posit = pivot.Get_Position();
                pivotString[1] = String.format("(%d,%d)", (posit[0]+1), (posit[1]+1));
                pivotString[2] = String.format("%.2f", pivot.Get_Coeficient());
                pivotString[3] = String.format("%.2f", pivot.Get_Result());
                pivotString[4] = "Basic";
            }else{
                pivotString[1] = "Na";
                pivotString[2] = "Na";
                pivotString[3] = "Na";
                pivotString[4] = "Free";
            }
        }
        return pivotString;
    }
    private static String[]     PivotTo_StringArray(Pivot pivot){
        String[] pivotString = new String[4]; 
        if(pivot != null ){
            pivotString[0] = pivot.Get_Name();
            if(!pivot.Get_IsFree()){
                int[] posit = pivot.Get_Position();
                pivotString[1] = String.format("(%d,%d)", (posit[0]+1), (posit[1]+1));
                pivotString[2] = String.format("%.2f", pivot.Get_Coeficient());
                pivotString[3] = "Basic";
            }else{
                pivotString[1] = "Na";
                pivotString[2] = "Na";
                pivotString[3] = "Free";
            }
        }
        return pivotString;
    }
    public static void     Pivot(Pivot_Augmented pivot){
        String[] categories = {"Name:", "Position:", "Coeficient:", "Result:", "Status:"};
        String[] pivotString = PivotTo_StringArray(pivot);
        System.out.print("\n");
        for (int index = 0; index < 5; index++) {
            System.out.printf("%s %6s,   ", categories[index], pivotString[index]);
        }
        System.out.print("\n");
    }
    public static void     Pivot(Pivot pivot){
        String[] categories = {"Name:", "Position:", "Coeficient:", "Status:"};
        String[] pivotString = PivotTo_StringArray(pivot);
        System.out.print("\n");
        for (int index = 0; index < 4; index++) {
            System.out.printf("%s %6s,   ", categories[index], pivotString[index]);
        }
        System.out.print("\n");
    }



    public static void     Title(String message){
        String simbol = "/";
        Times(simbol, 150);
        System.out.printf("\n%2s%100s%48s\n", "//", message.toUpperCase(), "//");
        Times(simbol, 150);
    }
    public static void     Title2(String message){
        String divisor = "======================================================";
        System.out.printf("%s%s%s\n", divisor,  message.toUpperCase(), divisor);
    }
    public static void     Subtitle(String message){
        String divisor = "-----------------------------------------------------------------";
        System.out.printf("%s%s%s\n", divisor, message, divisor);
    }
    public static void     Subtitle2(String message){
        String divisor = "~~~~~~~~~~~~~~~~";
        System.out.printf("%s%s%s\n", divisor, message, divisor);
    }


    public static void     Table(String header, String[] categories, String[][] content, int cellSize){
        System.out.println(header);
        Table_DivisorRow(cellSize, categories.length);
        Table_row(cellSize, categories);
        Table_DivisorRow(cellSize, categories.length);
        for (String[] cellRow : content) {
            Table_row(cellSize, cellRow);
        }
        Table_DivisorRow(cellSize, categories.length);
    }
    public static void     Table_DivisorRow(int lengthCell, int numCells){
        System.out.print("|");
        for (int index = 0; index < numCells; index++) {
            Times("-", lengthCell);
            System.out.print("|");
        }
        System.out.print("\n");
    }
    public static void     Table_row(int lengthCell, String[] content){
        System.out.print("|");
        for (String element : content) {
            String format = String.format("%-" + lengthCell + "s|", element);
            System.out.print(format);
        }
        System.out.print("\n");
    }
    
    public static void     Vector(double[] row){
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(double[] row, String message){
        if(message.length() > 0)
            System.out.println(message);
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(double[] row, String message, String holdingSimbol){
        if(message.length() > 0)
            System.out.println(message);
        System.out.printf("%s", holdingSimbol);
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.printf("   %s\n", holdingSimbol);
    }
    public static void     Vector(int[] row){
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(int[] row, String message){
        if(message.length() > 0)
            System.out.println(message);
        System.out.print("{");
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.println("   }");
    }
    public static void     Vector(int[] row, String message, String holdingSimbol){
        if(message.length() > 0)
            System.out.println(message);
        System.out.printf("%s", holdingSimbol);
        for (double element : row) {
            if(Math.abs(element) >=  0.0001 )
                System.out.printf(" %6.2f", element);
            else
                System.out.printf(" %6.2f", 0.00);
        }
        System.out.printf("   %s\n", holdingSimbol);
    }
     
    public static void     Times(String string, int times){
        for (int index = 0; index < times; index++) {
            System.out.print(string);
        }
    }
}
