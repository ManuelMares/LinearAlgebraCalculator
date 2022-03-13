package Classes.Matrix.Pivots.Classes;

import Classes.Matrix.Pivots.GetPivots;
import Classes.Matrix.Pivots.GetPivotsRecursion_Augmented;
import Classes.Matrix.Pivots.GetPivots_Augmented;
import Classes.Utilities.Vector;

public class Pivots_Augmented{
    Vector vectorMethods = new Vector();
    private Pivot_Augmented[]  ArrayPivots;
    private int      Size;
    private boolean  StepByStep;



    //CONSTRUCTOR
    public Pivots_Augmented(){
        ArrayPivots = new Pivot_Augmented[0];
        StepByStep = true;
        Size = 0;
    }
    
    
    public void     Update_Pivots(double[][] matrix){
        for (Pivot_Augmented pivot : ArrayPivots) {
            if(pivot != null && !pivot.Get_IsFree()){
                int[] position = pivot.Get_Position();
                Double newCoeficient = matrix[position[0]][position[1]];
                Double newResult = matrix[position[0]][matrix[0].length - 1];
    
                pivot.Set_Coeficient(newCoeficient);
                pivot.Set_Result(newResult);
            }
        }
    }
    public void     Add_BasicPivot(Pivot_Augmented pivot){
        Pivot_Augmented[] newArray = new Pivot_Augmented[Size + 1];
        for (int index =0; index < Size; index++)
            newArray[index] = ArrayPivots[index];
        newArray[Size] = pivot;
        ArrayPivots = newArray;
        Size++;
    }
    public void     Add_FreePivot(){
        Pivot_Augmented[] newArray = new Pivot_Augmented[Size + 1];
        for (int index =0; index < Size; index++)
            newArray[index] = ArrayPivots[index];
        //No arguments means that the new pivot is a free variable
        String newName = "X" + (Size + 1);
        Pivot_Augmented newPivot = new Pivot_Augmented(newName, true);
        newArray[Size] = newPivot;
        ArrayPivots = newArray;
        Size++;
    }
    public void     Delete_Pivot(int indexToDelete){
        Pivot_Augmented[] newArray = new Pivot_Augmented[Size -1];
        for (int index =0; index < Size; index++){
            if(index != indexToDelete)
                newArray[index] = ArrayPivots[index];
        }
        ArrayPivots = newArray;
        Size--;
    }
    public Pivot_Augmented    Get_Pivot(int index){
        return ArrayPivots[index];
    }
    public Pivot_Augmented    Get_Pivot(String name){
        int index = Character.getNumericValue(name.charAt(1)) - 1;
        return Get_Pivot(index);
    }
    public void     Set_Pivot(Pivot_Augmented pivot, int index){
        ArrayPivots[index] = pivot;
    }
    public int      Get_Size(){
        return Size;
    }
    public void     Set_StepByStep(boolean status){
        StepByStep = status;
    }

    public boolean  Check_RowHasPivot(int indexRow){
        if(ArrayPivots != null ){
            for (Pivot_Augmented pivot : ArrayPivots) {
                if(pivot.Get_Position()[0] == indexRow)
                return true;
            }
        }
        return false;
    }
    public int      Count_FreeVariables(){
        int counterVar = 0;
        if(ArrayPivots != null & Size > 0){
            for (Pivot_Augmented pivot : ArrayPivots) 
                if(pivot.Get_IsFree())
                    counterVar++;
        }
        return counterVar;
    }
    public int      Count_BasicVariables(){
        int counterVar = 0;
        if(ArrayPivots != null & Size > 0){
            for (Pivot_Augmented pivot : ArrayPivots) 
                if(pivot != null && !pivot.Get_IsFree())
                    counterVar++;
        }
        return counterVar;
    }

    public Pivots_Augmented   Get_PivotsRecursion_Augmented(double[][] matrix){
        return GetPivotsRecursion_Augmented.Main(matrix, StepByStep);
    }    
    public Pivots_Augmented   Get_Pivots_Augmented(double[][] matrix){
        return GetPivots_Augmented.Main(matrix, StepByStep);
    }   
    public Pivots_Augmented   Get_Pivots(double[][] matrix){
        return GetPivots_Augmented.Main(matrix, StepByStep);
    }

    public Boolean  check_LinearlyIndependence(double[][] matrix){
        if(Count_FreeVariables() > 0 )
            return true;
        return true;
    }

}