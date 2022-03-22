import java.util.Scanner;

import javax.swing.JButton;

import GUI.Components.Containers.*;
import GUI.Components.Text.*;
import Classes.Utilities.Colors;
import Classes.Utilities.Inputs;
import Classes.Utilities.Printer;
import Methods.Main;
import Methods.Controller.GetMatrix;
import Methods.Controller.GetSize;
import Methods.Inverse.InverseMatrix;
import Methods.MatrixAlgebra.MatrixMultiplication;
import Methods.SolveMatrix.SolveMatrix_Reduce;
import Methods.SolveMatrix.SolveMatrix_ReduceEchelon;
import Methods.Transpose.Transpose;
import Methods.LuFactorization.LuFactorization;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class LinearAlgebraCalculator{
   
   static MainFrame frame =new MainFrame();
   static int[] size;

   //IMPORTS
   static Inputs input = new Inputs();
   static Scanner scan = new Scanner (System.in);
   static Printer printer = new Printer();

   //VARIABLES
   static boolean method_SystemEquations;
   static boolean method_Matrix;
   static boolean method_Vectors;
   static int methodSelected;
   static String[] menuOptions = {"Reduce Echelon", "Reduce", "Multiply Matrices", "Invert Matrix", "Factorization", "Transpose"};
   
   
   
   public static void main (String args[]){ 
      Main menu = new Main();
   }  

   
}