package Classes.Matrix.Pivots;

public class Pivot {
    private String    Name;
    private int[]     Position;
    private Double    Coeficient;
    private Double    Result;
    private boolean   IsFree;

    public Pivot(String name, int[] position, Double coeficient, Double result, boolean isFree){
        Name        = name;
        Position    = position;
        Coeficient  = coeficient;
        Result      = result;
        IsFree      = isFree;
    }
    
    public Pivot(String name, boolean isFree ){
        Name        = name;
        Position    = new int[] {-1,-1};
        Coeficient  = null;
        Result      = null;
        IsFree      = isFree;
    }

    public String   Get_Name        (){return Name;}
    public void     Set_Name        (String name){ Name = name;}
    public int[]    Get_Position    (){return Position;}
    public void     Set_Position    (int[] position){ Position = position;}
    public void     Set_PositionCol (int positionCol){ Position[1] = positionCol;}
    public void     Set_PositionRow (int positionRow){ Position[0] = positionRow;}
    public Double   Get_Coeficient  (){return Coeficient;}
    public void     Set_Coeficient  (Double coeficient){ Coeficient = coeficient;}
    public Double   Get_Result      (){return Result;}
    public void     Set_Result      (Double result){ Result = result;}
    public boolean  Get_IsFree      (){return IsFree;}
    public void     Set_IsFree      (boolean isFree){ IsFree = isFree;}

}
