package GUI.Components;

import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import Classes.Utilities.Colors;

import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ButtonUI extends JPanel {
    JButton button;
    int[] size;
    int option;
    Consumer<Integer> consumer;
    
    public ButtonUI(String text, Consumer action, int indexOption){
        option = indexOption;
        consumer = action;
        Set_Properties(text);
    }
    public JPanel Get_UI(){
        this.add(button);
        return this;
    }

    public void Set_Properties(String text){
        Dimension containerSize =  new Dimension(300, 50);
        this.setMinimumSize(containerSize);
        this.setMaximumSize(containerSize);
        this.setPreferredSize(containerSize);

        Dimension buttonSize =  new Dimension(300, 40);
        button = new JButton(text);
        button.setBackground(Colors.black);
        button.setForeground(Colors.white);
        button.setSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setPreferredSize(buttonSize);

        event GetOption = new event();
        button.addActionListener((ActionListener) GetOption);
    }

    public void Set_BackgroundColor(Color color)    {this.setBackground(color);                              }
    
    public class event implements ActionListener{
        public void actionPerformed(ActionEvent GetOption){
            try{
                consumer.accept(option);
            }catch(Exception ex){
                System.out.print("Button does not work " + ex );
            }
        }
    }



}
