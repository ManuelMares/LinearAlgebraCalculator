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

public class TransposeUI {    
   private SectionScrollUI    UI;
   private boolean            NewSolutionTry;
   private Color              BgColorSection;
   private Color              BgColordivisor;
   private Color              colorText;
   int[] size = {1000, 295};


    public TransposeUI(){        
        BgColorSection =  Colors.gray1; 
        BgColordivisor =  Colors.gray2;  
        colorText    =  Colors.black;    
        UI= new SectionScrollUI(size);
        UI.Set_BackgroundColor(BgColorSection);
    }
    public SectionScrollUI Get_UI(){
        return UI;
    }


    public void Print_Matrix(double[][] matrix, String message){
        MatrixUI matrixUI = new MatrixUI(matrix, message);
        Add_Component(matrixUI);
    }


    private void Add_Component(MatrixUI matrix){
        UI.Add_Component(matrix);
    }
        
}
