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

public class MultiplicationUI {    
   private SectionScrollUI    UI;
   private boolean            NewSolutionTry;
   private Color              BgColorSection;
   private Color              BgColordivisor;
   private Color              colorText;
   int[] size = {1000, 295};
   

    public MultiplicationUI(){        
        BgColorSection =  Colors.gray1; 
        BgColordivisor =  Colors.gray2;  
        colorText    =  Colors.black;    
        UI= new SectionScrollUI(size);
        UI.Set_BackgroundColor(BgColorSection);
    }
    public SectionScrollUI Get_UI(){
        System.out.println("Jalo hasta devolver MultiplicationUI ");
        return UI;
    }

    public void Print_Matrix(double[][] matrix, String message){
        MatrixUI matrixUI = new MatrixUI(matrix, message);
        UI.Add_Component(matrixUI);
    }
    public void Print_Step(int index1, int index2){               
        TextUI text = new TextUI(String.format("Multiplying row %d in Matrix 1 by column %d in Matrix 2.\n", (index1+1), (index2+1)));
        UI.Add_Component(text);
    }

}
