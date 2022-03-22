package GUI.Components.Text;
import java.awt.Color;
import java.awt.Font;

public class Subtitle1UI extends Text {
    public Subtitle1UI(String message){
        Set_Properties(20, Font.BOLD, Color.BLACK);
        Set_Message(message);
    }   
    public Subtitle1UI(String message, Color fontColor, Color backgroundColor){
        Set_Properties(20, Font.BOLD, fontColor);
        Set_Background(backgroundColor);
        Set_Message(message);
    }   
}
