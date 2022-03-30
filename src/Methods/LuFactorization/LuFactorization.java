package Methods.LuFactorization;
import Classes.*;
import Classes.Matrix.AbstractClasses.Matrix;
import Classes.Matrix.Matrix_LuFactorization;
import Classes.Matrix.Matrix_Simple;
import Classes.Recursion.Recursion;
import Classes.Utilities.Printer;
import GUI.Components.Containers.SectionCardinalUI;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Tables.ConclusionUI;
import GUI.Components.Tables.MatrixUI;
import GUI.Components.Tables.PivotsUI;
import GUI.Components.Text.Subtitle1UI;
import GUI.Controllers.ReduceUI;
import Methods.Controller.GetMatrix;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;
import Classes.Matrix.Pivots.Classes.Pivots;


public class LuFactorization {
    Recursion   recursion = new Recursion();
    Matrix_Simple L;
    Matrix_Simple U;
    Matrix_LuFactorization matrix;
    
    private SectionCardinalUI UI;
    SectionScrollUI top, center, bottom;
    int[] size = {1000, 200};

    
    public void Main(double[][] entries){
        matrix = new Matrix_LuFactorization("A", entries);
        
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
        Subtitle1UI Title = new Subtitle1UI("Lu Factorization", Colors.black, Colors.gray3);
        top.Add_Component(Title);        
        MatrixUI matrixUI =  new MatrixUI(matrix, "matrix to factor");
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
            MatrixUI matrixUI1 = new MatrixUI(matrix.matrixL, "Final L");
            matrixUI1.Set_TableColor(Colors.gray3);
            bottom.Add_Component(matrixUI1);
            MatrixUI matrixUI2 = new MatrixUI(matrix.Get_CopyMatrix(), "Final U");
            matrixUI2.Set_TableColor(Colors.gray3);
            bottom.Add_Component(matrixUI2);
        }
        
        ConclusionUI  conclusion = ReduceUI.Print_Conclusion(header, categories, content);
        conclusion.Set_TableColor(Colors.gray3);
        bottom.Add_Component(conclusion);
    }
    

    public Matrix Reduce(double[][] givenMatrix){
        matrix = new Matrix_LuFactorization("Matrix A", givenMatrix);
        Reduce_Matrix();
        Set_MatrixStatus();        
        int inconsistentRow = matrix.CheckConsistentsy();
        if(matrix.Get_IsConsistent())
            return matrix;
        else
            return null;
    }


}
