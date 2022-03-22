package Methods.Controller;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Classes.Utilities.Colors;
import GUI.Components.Containers.SectionScrollUI;
import GUI.Components.Containers.SectionVertical;
import GUI.Components.Text.TextUI;
import GUI.Inputs.Input_BiggerThan;

public class GetSize extends SectionVertical {
    JTextField textField;
    SectionScrollUI newSection;
    Input_BiggerThan columns;     
    Input_BiggerThan rows;
    Integer[] size = new Integer[2];
    JButton button;
    Consumer<Integer[]> consumer;

    public GetSize(Consumer<Integer[]> action){
        consumer = action;
        Set_Properties();
    }
    
    public void Set_Properties(){
        rows = new Input_BiggerThan(0, "Num rows");
        this.add(rows.Get_UI());
        columns = new Input_BiggerThan(0, "Num columns");
        this.add(columns.Get_UI());

        button = new JButton("Next");
        this.add(button);
        event GetSize = new event();
        button.addActionListener((ActionListener) GetSize);
    }


    public class event implements ActionListener{
        public void actionPerformed(ActionEvent GetSize){
            try{
                size[0] = rows.Get_Value().intValue();
                size[1] = columns.Get_Value().intValue();
                consumer.accept(size);
            }catch(Exception ex){
                System.out.println("size not valid: " + rows.Get_Value() + ",    : " + columns.Get_Value());
                System.out.println(ex);
            }
        }
    }

    
    public  SectionScrollUI Get_Component(){
        newSection = new SectionScrollUI(new int[] {1000, 200} );
        newSection.Set_BackgroundColor(Colors.blue4);
        newSection.Add_Component(this);

        return newSection;
    }

    public Integer[] Get_MatrixSize(){
        return size;
    }
}
