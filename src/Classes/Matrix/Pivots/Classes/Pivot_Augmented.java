package Classes.Matrix.Pivots.Classes;

public class Pivot_Augmented extends Pivot {
    private String    Name;
    private int[]     Position;
    private Double    Coeficient;
    private Double    Result;
    private boolean   IsFree;

    public Pivot_Augmented(String name, int[] position, Double coeficient, Double result, boolean isFree){
        super(name, position, coeficient, isFree);
        Result      = result;
    }
    
    public Pivot_Augmented(String name, boolean isFree ){
        super(name, isFree);
        Result      = null;
    }

    public Double   Get_Result      (){return Result;}
    public void     Set_Result      (Double result){ Result = result;}

}
