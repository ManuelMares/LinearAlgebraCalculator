package Methods.SolveMatrix;
import java.awt.Dimension;

import Classes.*;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Pivots.Classes.Pivots_Augmented;
import Classes.Matrix.Matrix_Reduce;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import Classes.Utilities.Inputs;
import Classes.Utilities.Colors;
import GUI.Components.Containers.*;
import GUI.Components.Tables.*;
import GUI.Components.Text.*;
import GUI.Controllers.InverseUI;
import GUI.Controllers.ReduceUI;
import Methods.Controller.GetMatrix;


public class SolveMatrix_Reduce {
    Recursion   recursion = new Recursion();
    //matrix produces Section center; 
    Matrix_Reduce matrix;
    private SectionCardinalUI UI;
    SectionScrollUI top, center, bottom;
    int[] size = {1000, 200};

    public void Main(double[][] mat){
        matrix = new Matrix_Reduce("1", mat);
        Set_UIProperties();
        
        Reduce_Matrix();
        Set_MatrixStatus();
        Conclusion();
        
        Set_UI();
    }
    public  SectionCardinalUI Get_UI(){
        return UI;
    }
    private void Set_UIProperties(){
        UI= new SectionCardinalUI(); 
        UI.Set_BackgroundColor(Colors.gray1);  
        Set_TopProperties();
        Set_BottomProperties();
    }
    private void Set_TopProperties(){
        top = new SectionScrollUI(size);
        top.Set_BackgroundColor(Colors.gray3);
        Subtitle1UI Title = new Subtitle1UI("Reduce Matrix", Colors.black, Colors.gray3);
        top.Add_Component(Title);        
        MatrixUI matrixUI =  new MatrixUI(matrix, "matrix to solve");
        matrixUI.Set_BackgroundColor(Colors.gray3);
        top.Add_Component(matrixUI);
    }
    private void Set_BottomProperties(){ 
        bottom = new SectionScrollUI(size);
        bottom.Set_BackgroundColor(Colors.gray3);
        //bottom.Set_BackgroundColor(Colors.gray3); 
        Subtitle1UI conclusion = new Subtitle1UI("Conclusion", Colors.black, Colors.gray3);
        bottom.Add_Component(conclusion);    
    }
    public  void Set_UI(){  
        UI.Add_Component(top, "NORTH");  
        
        center = matrix.Get_UI();
        //center.setPreferredSize(new Dimension(1000,300));
        UI.Add_Component(center, "CENTER");  
        
        UI.Add_Component(bottom, "SOUTH");   
    }


    
    private void    Reduce_Matrix(){
        matrix.ReduceMatrix_AllPivots();
    }
    private void    Set_MatrixStatus(){
        matrix.CheckConsistentsy();
    }
    private void    Conclusion(){       
        int inconsistentRow = matrix.CheckConsistentsy();
        String header = "Matrix Status";
        String[] categories = {"It is Consistent", "It is Linearly Independent", "It is Linearly Dependent"};
        String[][] content = {{"NO, because of row " + (inconsistentRow+1), "NA", "NA"}};       
        if(matrix.Get_IsConsistent()){
            if(matrix.pivots.check_LinearlyIndependence(matrix.Get_CopyMatrix()))  
                content = new String[][] {{"YES", "YES", "NO"}};
            else
                content = new String[][] {{"YES", "NO", "YES"}};
            MatrixUI matrixUI = new MatrixUI(matrix, "Final matrix");
            matrixUI.Set_TableColor(Colors.gray3);
            bottom.Add_Component(matrixUI);
        }
        Pivots_Augmented pivots = matrix.pivots;
        PivotsUI pivotsUI = new PivotsUI(pivots, "Pivots' status");
        pivotsUI.Set_BackgroundColor(Colors.gray3);
        bottom.Add_Component(pivotsUI);
        
        ConclusionUI  conclusion = ReduceUI.Print_Conclusion(header, categories, content);
        conclusion.Set_TableColor(Colors.gray3);
        bottom.Add_Component(conclusion);
    }
    

    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_Reduce("Matrix A", givenMatrix);
        Reduce_Matrix();
        Set_MatrixStatus();        
        int inconsistentRow = matrix.CheckConsistentsy();
        if(matrix.Get_IsConsistent())
            return matrix;
        else
            return null;
    }

}
