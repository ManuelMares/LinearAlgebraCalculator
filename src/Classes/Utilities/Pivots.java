package Classes.Utilities;

import Classes.Matrix.Pivots.Classes.Pivot_Augmented;

public class Pivots{
    Vector vectorMethods = new Vector();

    private String[] names;
    private int[][] positions;
    private double[] values;
    private double[] results;
    private boolean[] areFree;
    public int size;


    //CONSTRUCTORS
    public Pivots(){
        names = new String[0];
        positions = new int[0][0];
        values = new double[0];
        results = new double[0];
        areFree = new boolean[0];
        size = 0;
    }
    public Pivots(Pivots newPivot){
        names = newPivot.names;
        positions = newPivot.positions;
        values = newPivot.values;
        results = newPivot.results;
        areFree = newPivot.areFree;
        size = newPivot.size;
    }


    //ADD
    public void AddPivot(String name, int[] position, double value, double result, boolean isFree){        
        AddPivotName(name);
        AddPivotPosition(position);
        AddPivotValue(value);
        AddPivotResult(result);
        AddPivotIsFree(isFree);        
        size++;
    }
    
    public void AddFreeVariable(String name){
        int[] position = {-1, -1};        
        AddPivot(name, position, 0, 0, true);
    }
    private void AddPivotName(String newName){
        names = vectorMethods.IncreaseVector(names);
        names[names.length - 1] = newName;
    }
    private void AddPivotPosition(int[] newPosition){
        positions = vectorMethods.IncreaseVector(positions);
        positions[positions.length - 1] = newPosition;
    }
    private void AddPivotValue(double newValue){
        values = vectorMethods.IncreaseVector(values);
        values[values.length - 1] = newValue;
    }
    private void AddPivotResult(double newResult){
        results = vectorMethods.IncreaseVector(results);
        results[results.length - 1] = newResult;
    }
    private void AddPivotIsFree(boolean newIsFree){
        areFree = vectorMethods.IncreaseVector(areFree);
        areFree[areFree.length - 1] = newIsFree;
    }


    

    //GET
    public Pivots GetPivot(int indexPivot){
        Pivots newPivot = new Pivots();
        if(indexPivot>=0 && indexPivot < size){
            newPivot.AddPivot(names[indexPivot], positions[indexPivot], values[indexPivot], results[indexPivot], areFree[indexPivot]);
        }
        return newPivot;
    }

    private int FindPivotIndex(String nameGoal){
        int indexPivot = -1;
        int counter = 0;
        for (String name : names) {
            if(name.equals(nameGoal))
                indexPivot = counter;
            counter++;
        }
        return indexPivot;
    }
        
    public String GetPivot_Name(String name){       
        return name;
    }
    public String GetPivot_Name(int index){
        String name = "";
        if( index >= 0 && index < positions.length )
            name = names[index];        
        return name;
    }
    public int[] GetPivot_Position(String name){
        int[] position = {-1, -1};
        int indexPivot = FindPivotIndex(name);
        if(indexPivot >= 0)
            position = positions[indexPivot];        
        return position;
    }
    public int[] GetPivot_Position(int index){
        int[] position = {-1, -1};   
        if( index >= 0)
            position = positions[index];        
        return position;
    }
    public double GetPivot_Value(String name){
        double value = 0;
        int indexPivot = FindPivotIndex(name);
        if(indexPivot >= 0)
            value = values[indexPivot];        
        return value;
    }
    public double GetPivot_Value(int index){
        double value = 0;
        if(index >= 0 && index < (values.length -1) )
            value = values[index];        
        return value;
    }
    public double GetPivot_Result(String name){
        double result = 0;
        int indexPivot = FindPivotIndex(name);
        if(indexPivot >= 0)
            result =  results[indexPivot];
        return result;
    }    
    public double GetPivot_Result(int index){
        double result = 0;
        if(index >= 0 && index < (results.length -1))
            result =  results[index];
        return result;
    }    
    public boolean GetPivot_isFree(String name){
        int indexPivot = FindPivotIndex(name);
        if(indexPivot >= 0){
            return areFree[indexPivot];
        }
        return false;
    }
    public boolean GetPivot_isFree(int index){
        if(index >= 0){
            return areFree[index];
        }
        return false;
    }




    //SET
    public void SetPivotsToZero(){
        names = new String[0];
        positions = new int[0][0];
        values = new double[0];
        areFree = new boolean[0];
        size = 0;
    }

    public void setResult(int index, double newResult){
        results[index] = newResult;
    }


    //UPDATE
    public void UpdatePivot_Name(){}
    public void UpdatePivot_Position(){}
    public void UpdatePivot_Value(){}
    public void UpdatePivot_isFree(){}

    public void PrintPivots_CompleteStatus(){
        String topDivisor = "------------";
        System.out.println("\n\nUpdated detailed report of variables' status");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", "Name", "Position", "Coeficient", "Result" , "Status");
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
        for (int index = 0; index < names.length; index++) {
            if(areFree[index]){
                String name =names[index];
                String na ="N/A";
                String status = "Free";
                System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n", name, na, na, na, status);
            }else{
                String name =names[index];
                String position = "(" + (positions[index][0]+1) +","+ (positions[index][1]+1) + ")";
                String status = "Basic";
                System.out.printf("|%-12s|%-12s|%-12.2f|%-12.2f|%-12s|\n", name, position, values[index], results[index], status);
            }
        }        
        System.out.printf("|%-12s|%-12s|%-12s|%-12s|%-12s|\n\n\n", topDivisor, topDivisor, topDivisor, topDivisor, topDivisor);
    }


    //DELETE
    public void DeletePivot(int indexDelete){
        String[] newNames = new String[names.length - 1];
        int[][] newPositions = new int[positions.length - 1][2];
        double[] newValues = new double[values.length - 1];
        boolean[] newAreFree = new boolean[areFree.length - 1];

        int counter = 0;
        for (int index = 0; index < newNames.length; index++) {
            if(index != indexDelete){
                newNames[counter] = names[counter];
                newPositions[counter][0] = positions[counter][0];
                newPositions[counter][1] = positions[counter][1];
                newValues[counter] = values[counter];
                newAreFree[counter] = areFree[counter];
                counter++;
            }
        }

        names = newNames;
        positions = newPositions;
        values = newValues;
        areFree = newAreFree;

        size--;
    }










    
    public boolean Validate_ColumnHasPivot(int indexColumn){
        //If there is any element in positions whose value [1] (columns) is equal to the parameter, then that column has already a pivot
        for (int[] position: positions) {
            if(position[1] == indexColumn)
                return true;
        }
        return false;
    }
    
    public boolean Validate_RowHasPivot(int indexRow){
        //If there is any element in positions whose value [0] (rows) is equal to the parameter, then that row has already a pivot
        for (int[] position: positions) {
            if(position[0] == indexRow)
                return true;
        }
        return false;
    }
    public int Get_Size() {
        return 0;
    }
    public Pivot_Augmented Get_Pivot(int index) {
        return null;
    }

}