package GUI.Components.Tables;

public class ConclusionUI extends Table {
    public ConclusionUI(String title, String[] categories, String[][] content){           
        Set_Layout(title);   
        this.categories = categories;
        this.content = content;
        Set_Table();

        int[] size = {500, 75};
        Set_Size(size);
    }
}
