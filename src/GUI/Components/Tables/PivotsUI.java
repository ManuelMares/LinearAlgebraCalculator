package GUI.Components.Tables;

import Classes.Matrix.Pivots.Classes.Pivot;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;

public class PivotsUI extends Table {

    public PivotsUI(Pivots_Augmented pivots, String title){
        Set_Layout(title);        
        Set_TableParameters(pivots);    
    }
    public PivotsUI(Pivots pivots, String title){
        Set_Layout(title);
        Set_TableParameters(pivots);        
    }
    private void        Set_TableParameters(Pivots_Augmented pivots){
        categories = Get_CategoriesAugmented(true);
        content = Get_PivotsRows(pivots);
        Set_Table();
        Set_TableColorPattern();
    }
    private void        Set_TableParameters(Pivots pivots){
        categories = Get_CategoriesAugmented(false);
        content = Get_PivotsRows(pivots);
        Set_TableColorPattern();        
        Set_Table();
    }
    
    private String[]    Get_CategoriesAugmented(boolean isAugmented){
        if(isAugmented)
            return new String[] {"Name", "Position", "Coeficients", "Result", "Status"};
        else
            return new String[] {"Name", "Position", "Coeficients", "Status"};

    }
    private String[][]  Get_PivotsRows(Pivots_Augmented pivots){
        String[][] content = new String[pivots.Get_Size()][5];
        for (int index = 0; index < pivots.Get_Size(); index++) {
            content[index] = PivotTo_StringArray(pivots.Get_Pivot(index));
        }
        return content;
    }
    private String[][]  Get_PivotsRows(Pivots pivots){
        String[][] content = new String[pivots.Get_Size()][4];
        for (int index = 0; index < pivots.Get_Size(); index++) {
            content[index] = PivotTo_StringArray(pivots.Get_Pivot(index));
        }
        return content;
    }
    private String[]    PivotTo_StringArray(Pivot_Augmented pivot){
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
    private String[]    PivotTo_StringArray(Pivot pivot){
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

}
