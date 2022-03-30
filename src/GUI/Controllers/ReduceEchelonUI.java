package GUI.Controllers;

import GUI.Components.Containers.*;
import GUI.Components.Tables.*;
import GUI.Components.Text.*;
import Classes.Matrix.Pivots.Classes.Pivot_Augmented;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;
import Classes.Utilities.Colors;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReduceEchelonUI {    
   private SectionScrollUI    UI;
   private SectionVerticalUI  UI_NewSolutionTry;
   private boolean            NewSolutionTry;
   private Color              BgColorSection;
   private Color              BgColordivisor;
   private Color              colorText;
   int[] size = {1000, 295};


    public ReduceEchelonUI(){        
        BgColorSection =  Colors.gray1; 
        BgColordivisor =  Colors.gray2;  
        colorText    =  Colors.black;    
        UI= new SectionScrollUI(size);
        UI.Set_BackgroundColor(BgColorSection);
    }
    public SectionScrollUI Get_UI(){
        return UI;
    }

    public void Set_NewSolutionTry(boolean status){
        //This two methods don't produce any output
        //if(!NewSolutionTry && !status)
        //if(NewSolutionTry && status)
           
        if(!NewSolutionTry && status)
            Create_NewSolutionTry();

        if(NewSolutionTry && !status)
            Add_NewSolutionTry();
    }

    public void Create_NewSolutionTry(){        
        BgColorSection =  Colors.white; 
        BgColordivisor =  Colors.gray1;
        NewSolutionTry = true;

        UI_NewSolutionTry = new SectionVerticalUI ();
        UI_NewSolutionTry.Set_BackgroundColor(BgColorSection);
        UI_NewSolutionTry.setLayout(new BoxLayout(UI_NewSolutionTry, BoxLayout.PAGE_AXIS));
        UI_NewSolutionTry.setBorder(new EmptyBorder(0, 100, 0, 0));

        Subtitle1UI subtitle = new Subtitle1UI("Extra steps", colorText, BgColorSection);
        UI_NewSolutionTry.add(subtitle);
        Subtitle2UI subtitle2 = new Subtitle2UI("The position of at least one pivot has change", colorText, BgColorSection);
        UI_NewSolutionTry.add(subtitle2);  
        Subtitle2UI subtitle3 = new Subtitle2UI("A new set of iterations will be executed.", colorText, BgColorSection);
        UI_NewSolutionTry.add(subtitle3);       
    }
    public void Add_NewSolutionTry(){       
        BgColorSection =  Colors.gray1; 
        BgColordivisor =  Colors.gray2;
        NewSolutionTry = false;
        UI.Add_Component(UI_NewSolutionTry);        
    }

    public void Print_Pivots(Pivots_Augmented  pivots, String message){
        PivotsUI pivotsUI = new PivotsUI(pivots, message);
        Add_Component(pivotsUI);
    }
    public void Delete_RepeatedRow(double[][] matrix){
        Division division = new Division(BgColordivisor);        
        Add_Component(division);
        Subtitle2UI subtitle = new Subtitle2UI("At least one repeated row has been detected and has been deleted:\n", colorText, Colors.gray1);
        Add_Component(subtitle);
        
        MatrixUI matrixUI = new MatrixUI(matrix, "New matrix:");
        Add_Component(matrixUI);
    }

    public void Print_Matrix(double[][] matrix, String message){
        MatrixUI matrixUI = new MatrixUI(matrix, message);
        Add_Component(matrixUI);
    }

    public void Print_PivotHasTurnIntoZero(int indicePivot){
        Division division = new Division();        
        Add_Component(division);
        Subtitle2UI subtitle = new Subtitle2UI("Step " + indicePivot, colorText, BgColorSection);  
        Add_Component(subtitle);
        TextUI text = new TextUI("Pivot "+ indicePivot + " has turn into a zero, so no operation is executed in this step \n", colorText, BgColorSection);
        Add_Component(text);
    }

    public void Print_RowOperation(int step, int index_RowToCancel, int index_Row, double factorToCancel){               
        TextUI text = new TextUI(String.format("%d)R%d = R%d + (%.2f)*R%d.\n", step, index_RowToCancel, index_RowToCancel, factorToCancel, index_Row), colorText, BgColorSection);
        Add_Component(text);
    }
    public void Print_Step_ClearColumn(Pivot_Augmented pivot, double[] unitarianRow){     
        int[] position = pivot.Get_Position();
        char stepNumber = pivot.Get_Name().charAt(1);
        
        Division division = new Division();        
        Add_Component(division);
        Subtitle2UI subtitle = new Subtitle2UI("Step " + stepNumber, colorText, BgColorSection);          
        Add_Component(subtitle);

        Print_Pivot(pivot);
        TextUI text = new TextUI(String.format("Dividing row %d by %.2f to get the unitarian row:\n", (position[0] + 1), pivot.Get_Coeficient() ), colorText, BgColorSection);
        Add_Component(text);

        Print_Vector(unitarianRow);
    }
    public void Print_RowOperationsWillBeExecuted(){
        TextUI text2 = new TextUI("\nNow this unitarian row will be use to clean the pivot's column. The operations are:", colorText, BgColorSection);
        Add_Component(text2);
    }
    public void Print_NoRowOperationsExecuted(){
        //TextUI text2 = new TextUI("\nNo row operations are needed in this step", colorText, BgColorSection);
        //Add_Component(text2);
    }
    public void Print_Pivot(Pivot_Augmented pivot){
        String name = pivot.Get_Name();
        int[] position = pivot.Get_Position();
        double coeficient = pivot.Get_Coeficient();
        double result = pivot.Get_Result();
        String isFree = pivot.Get_IsFree() ? "Free": "Basic";

        TextUI text = new TextUI("\nPivot: ", colorText, BgColorSection);
        TextUI text2 = new TextUI(String.format("Name: %s,  Position: (%d,%d),  Coeficient: %.2f,  Result: %.2f,  Status: %s", 
                                             name,  (position[0]+1), (position[1]+1), coeficient,   result,     isFree),
                                             colorText, BgColorSection);

        Add_Component(text);
        Add_Component(text2);
    }
    public void Print_Vector(double[] unitarianRow){
        String vector = "{ ";
        for (double element : unitarianRow) {
            if(element == 0)
                vector +="0.00  ";
            else
                vector += String.format("%.2f  ", element);
        }
        vector += "}";

        
        TextUI text = new TextUI(vector, colorText, BgColorSection);
        Add_Component(text);
    }


    private void Add_Component(TextUI text){
        if(NewSolutionTry)
            UI_NewSolutionTry.Add_Component(text);
        else
            UI.Add_Component(text);
    }
    
    private void Add_Component(Subtitle2UI subtitle){
        if(NewSolutionTry)
            UI_NewSolutionTry.Add_Component(subtitle);
        else
            UI.Add_Component(subtitle);
    }
    private void Add_Component(MatrixUI matrix){
        matrix.Set_TableColor(BgColorSection);
        if(NewSolutionTry){
            UI_NewSolutionTry.Add_Component(matrix);
        }
        else
            UI.Add_Component(matrix);
    }
        
    private void Add_Component(PivotsUI pivots){
        if(NewSolutionTry){
            pivots.Set_TableColor((Colors.white));;
            UI_NewSolutionTry.Add_Component(pivots);
        }
        else
            UI.Add_Component(pivots);
    }
     
    private void Add_Component(Division division){
        if(NewSolutionTry){
            division.Set_Color(Colors.gray1);
            UI_NewSolutionTry.Add_Component(division);
        }
        else{
            division.Set_Color(Colors.gray2);
            UI.Add_Component(division);
        }
    }



    public static ConclusionUI Print_Conclusion(String header, String[] categories, String[][] content){
        ConclusionUI conclusion = new ConclusionUI(header, categories, content);
        return conclusion;
    }
}
