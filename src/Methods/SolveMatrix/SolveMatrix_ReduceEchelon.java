package Methods.SolveMatrix;
import Classes.*;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Matrix_ReduceEchelon;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import GUI.Components.Containers.SectionCardinalUI;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Tables.ConclusionUI;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Text.Subtitle1UI;
import GUI.Controllers.ReduceEchelonUI;
import Methods.Controller.GetMatrix;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;


public class SolveMatrix_ReduceEchelon {
    Recursion   recursion = new Recursion();
    Matrix_ReduceEchelon matrix;
    private SectionCardinalUI UI;
    SectionScrollUI top, center, bottom;
    int[] size = {900, 200};

    public void Main(double[][] mat){
        matrix = new Matrix_ReduceEchelon("1", mat);
        //Create_Matrix();
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
        Subtitle1UI Title = new Subtitle1UI("Reduce Echelon Matrix", Colors.black, Colors.gray3);
        top.Add_Component(Title);        
        MatrixUI matrixUI =  new MatrixUI(matrix, "matrix to solve");
        matrixUI.Set_TableColor(Colors.gray3);
        top.Add_Component(matrixUI);
    }
    private void Set_BottomProperties(){ 
        bottom = new SectionScrollUI(size);
        bottom.Set_BackgroundColor(Colors.gray3);
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




    private void    Create_Matrix(){
        Printer.Title2("Solve matrix  by reduction method");
        Printer.Subtitle2("Let's start by indicate the size of the matrix");
        //double[][] entries = CreateMatrix.Free();
        //matrix = new Matrix_ReduceEchelon("A", entries);
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
        
        ConclusionUI  conclusion = ReduceEchelonUI.Print_Conclusion(header, categories, content);
        conclusion.Set_TableColor(Colors.gray3);
        bottom.Add_Component(conclusion);
    }
    

    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_ReduceEchelon("Matrix A", givenMatrix);
        Reduce_Matrix();
        Set_MatrixStatus();        
        int inconsistentRow = matrix.CheckConsistentsy();
        if(matrix.Get_IsConsistent())
            return matrix;
        else
            return null;
    }


}
