package Classes.Matrix.Pivots.Classes;

public class Pivot {
    private String    Name;
    private int[]     Position;
    private Double    Coeficient;
    private boolean   IsFree;

    public Pivot(String name, int[] position, Double coeficient, boolean isFree){
        Name        = name;
        Position    = position;
        Coeficient  = coeficient;
        IsFree      = isFree;
    }
    
    public Pivot(String name, boolean isFree ){
        Name        = name;
        Position    = new int[] {-1,-1};
        Coeficient  = null;
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
    public boolean  Get_IsFree      (){return IsFree;}
    public void     Set_IsFree      (boolean isFree){ IsFree = isFree;}

}
