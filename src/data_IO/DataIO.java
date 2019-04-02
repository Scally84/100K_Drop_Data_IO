package data_IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Stephen
 */
public class DataIO extends Application
{
    //variables
    GridPane grid;
    Label questionLabel, categoryLabel, noOfAnswersLabel, answer1Label, correctLabel, newLabel, oldLabel,
            answer2, answer3, answer4;
    TextField questionTextField, answer1TextField, answer2TextField, newTextField, answer3TextField, answer4TextField;
    ToggleGroup newOldRadio, twoThreeFour;
    RadioButton newButton , oldButton, two, three, four;
    HBox hbox1, hbox2;
    ListView<String> oldList;
    Button updateOldButton, submitButton;
    TextArea messageArea;
    
    @Override
    public void start(Stage primaryStage)
    { 
        //font
        Font font = new Font("Tahoma", 16);
        
        //color
        Color bk = Color.rgb(190, 190, 190);
        Color bkBut = Color.rgb(44, 44, 44);
        Color fgBut = Color.rgb(255, 255, 255);
        Color fgText = Color.RED;
        
        //label
        questionLabel = new Label("Question");
        questionLabel.setFont(font);
        categoryLabel = new Label("Category");
        categoryLabel.setFont(font);
        noOfAnswersLabel = new Label("No of Answers");
        noOfAnswersLabel.setFont(font);
        answer1Label = new Label("Answers 1");
        answer1Label.setFont(font);
        correctLabel = new Label("Correct");
        correctLabel.setFont(font);
        newLabel = new Label("New");
        newLabel.setFont(font);
        oldLabel = new Label("Old");
        oldLabel.setFont(font);
        answer2 = new Label("Answer 2");
        answer2.setFont(font);
        answer3 = new Label("Answer 3");
        answer3.setFont(font);
        answer4 = new Label("Answer 4");
        answer4.setFont(font);
        
        //textfield
        questionTextField = new TextField();
        questionTextField.setMinSize(400, 20);
        questionTextField.setFont(font);
        questionTextField.setStyle("-fx-text-inner-color: blue;");
        newTextField = new TextField();
        newTextField.setMinSize(200, 20);
        newTextField.setFont(font);
        answer1TextField = new TextField();
        answer1TextField.setMinSize(200, 20);
        answer1TextField.setFont(font);
        answer1TextField.setStyle("-fx-text-inner-color: blue;");
        answer2TextField = new TextField();
        answer2TextField.setMinSize(200, 20);
        answer2TextField.setFont(font);
        answer2TextField.setStyle("-fx-text-inner-color: blue;");
        answer3TextField = new TextField();
        answer3TextField.setMinSize(200, 20);
        answer3TextField.setFont(font);
        answer3TextField.setStyle("-fx-text-inner-color: blue;");
        answer4TextField = new TextField();
        answer4TextField.setMinSize(200, 20);
        answer4TextField.setFont(font);
        answer4TextField.setStyle("-fx-text-inner-color: blue;");
        
        //toggleGroup
        newOldRadio = new ToggleGroup();
        twoThreeFour = new ToggleGroup();

        //radioButton
        newButton = new RadioButton("New");
        newButton.setPadding(new Insets(10,10,10,10));
        newButton.setFont(font);
        oldButton = new RadioButton("Old");
        oldButton.setPadding(new Insets(10,10,10,10));
        oldButton.setFont(font);
        oldButton.setSelected(true);
        two = new RadioButton("2");
        two.setPadding(new Insets(10,10,10,10));
        two.setFont(font);
        three = new RadioButton("3");
        three.setPadding(new Insets(10,10,10,10));
        three.setFont(font);
        four = new RadioButton("4");
        four.setPadding(new Insets(10,10,10,10));
        four.setFont(font);
        four.setSelected(true);
        
        //add radio button to toggleGroup
        newButton.setToggleGroup(newOldRadio);
        oldButton.setToggleGroup(newOldRadio);
        two.setToggleGroup(twoThreeFour);
        three.setToggleGroup(twoThreeFour);
        four.setToggleGroup(twoThreeFour);
        
        //listView
        oldList = new ListView<>();
        oldList.setMinSize(200, 100);
        oldList.setStyle("-fx-font-size: 14;");
        
        //button
        updateOldButton = new Button("Update");
        updateOldButton.setMinSize(100, 30);
        updateOldButton.setFont(font);
        updateOldButton.setBackground(new Background(new BackgroundFill(bkBut, new CornerRadii(4), Insets.EMPTY)));
        updateOldButton.setTextFill(fgBut);
        updateOldButton.setOnAction(e -> this.updateCategories());
        submitButton = new Button("Submit");
        submitButton.setMinSize(100, 30);
        submitButton.setFont(font);
        submitButton.setBackground(new Background(new BackgroundFill(bkBut, new CornerRadii(4), Insets.EMPTY)));
        submitButton.setTextFill(fgBut);
        submitButton.setOnAction(e -> this.submit());
        
        //textarea
        messageArea = new TextArea();
        messageArea.setFont(font);
        messageArea.setMaxHeight(100);
        
        //grid
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(40,40,40,40));
        grid.setVgap(20);
        grid.setHgap(40);
        grid.setBackground(new Background(new BackgroundFill(bk, CornerRadii.EMPTY, Insets.EMPTY)));
        hbox1 = new HBox(newButton, oldButton);
        hbox2 = new HBox(two, three, four);
        //select element's position in grid
        GridPane.setConstraints(questionLabel, 0, 0);
        GridPane.setConstraints(questionTextField, 1, 0, 4, 1);
        GridPane.setConstraints(noOfAnswersLabel, 2, 1);
        GridPane.setConstraints(hbox2, 3, 1);
        GridPane.setConstraints(categoryLabel, 0, 1);
        GridPane.setConstraints(hbox1, 1, 1); 
        GridPane.setConstraints(answer1Label, 2, 3);
        GridPane.setConstraints(answer1TextField, 3, 3);
        GridPane.setConstraints(correctLabel, 4, 3);
        GridPane.setConstraints(newLabel, 0, 2);
        GridPane.setConstraints(oldLabel, 1, 2);
        GridPane.setConstraints(answer2, 2, 4);
        GridPane.setConstraints(answer2TextField, 3, 4);
        GridPane.setConstraints(newTextField, 0, 3);
        GridPane.setConstraints(oldList, 1, 3, 1, 5);
        GridPane.setConstraints(answer3, 2, 5);
        GridPane.setConstraints(answer3TextField, 3, 5);
        GridPane.setConstraints(answer4, 2, 6);
        GridPane.setConstraints(answer4TextField, 3, 6);
        GridPane.setConstraints(updateOldButton, 1, 8);
        GridPane.setConstraints(submitButton, 3, 7);
        GridPane.setConstraints(messageArea, 1, 9, 3, 1);
        
        //add elements to grid
        grid.getChildren().addAll(questionLabel, questionTextField, noOfAnswersLabel, hbox2, categoryLabel, hbox1,
                answer1Label, answer1TextField, correctLabel, newLabel, oldLabel, answer2, answer2TextField,
                newTextField, oldList, answer3, answer3TextField, answer4, answer4TextField, updateOldButton,
                submitButton, messageArea);
        
        //add grid to scene
        Scene scene = new Scene(grid, 1300, 700);
        
        //add scene to stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private static Properties getConnectionData() 
    {

        Properties props = new Properties();

        String fileName = "db.properties";

        try (FileInputStream in = new FileInputStream(fileName)) 
        {
            props.load(in);
        } 
        
        catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return props;
    }
    
    public Connection connectToDatabase() throws SQLException
    { 
        Connection con;
        Properties props = getConnectionData();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        
        con = DriverManager.getConnection(url, user, password);

        return con;
    }
    
    private void submit()
    {
        messageArea.setText("");
        
        Boolean isMatch = false;
        Boolean validRes = validSubmission();
        
        if (validRes)
        {
            if (newButton.isSelected())
            {
                if (isMatch("category", "Name"))
                {
                    messageArea.setText("Match");
                    
                    isMatch = true;
                }
                else
                { 
                    insert("category", "Name", newTextField.getText());
                }
            }      
        
            if (!(isMatch))
            {
                String input = oldOrNew();
                System.out.println("False");

                int catID = getID("category", "C_ID", "Name", input);
                System.out.println(catID);
                
                int twoThreeFour = twoThreeFour();
                insertWithID("question", "C_ID", "Question", "No_of_A", catID, questionTextField.getText(), twoThreeFour);
                
                //get Q__ID using getID()
                //using insertWithID() insert into answer table with No_of_A
                multiInserts();

                messageArea.setText("Data has been added to Database");
                questionTextField.setText("");
                newTextField.setText("");
                answer1TextField.setText("");
                answer2TextField.setText("");
                answer3TextField.setText("");
                answer4TextField.setText("");
            }
        }
        else
        {
            messageArea.setText("Invalid submission");
        }
    }
    
    private boolean isMatch(String aTable, String column)
    {
        Boolean match = null;
        Set<String> name = new HashSet<>();
        
        String query = "SELECT " + column + " FROM 100k_drop." + aTable;
        
        try(Connection con = connectToDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query))
        {
            while (rs.next())
            {
                name.add(rs.getString(1));
            }
        }
        
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        for (String eachName : name)
        {
            if (eachName.equals(newTextField.getText()))
            {
                match = true;
                break;
            }
            else
            {
                match = false;
            }
        }
        
        if (name.isEmpty())
        {
            match = false;
        }
        
        return match;
    }
    
    private void insert(String aTable, String column, String input)
    {
        String statement = "INSERT INTO 100k_drop." + aTable + "("+ column +") VALUES (?)";
        
        try(Connection con = connectToDatabase();
                PreparedStatement pst = con.prepareStatement(statement))
        {
            pst.setString(1, input);
            pst.executeUpdate();
        }

        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void insertWithID(String aTable, String col1, String col2, String col3, int input1, String input2, int input3)
    {
        String statement = "INSERT INTO 100k_drop." + aTable + "("+ col1 + ", " + col2 +", " + col3 +") VALUES (?,?,?)";
        
        try(Connection con = connectToDatabase();
                PreparedStatement pst = con.prepareStatement(statement))
        {
            pst.setInt(1, input1);
            pst.setString(2, input2);
            pst.setInt(3, input3);
            pst.executeUpdate();
        }

        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void multiInserts()
    {
        if (two.isSelected())
        {    
            int questID = getID("question", "Q_ID", "Question", questionTextField.getText());
            
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer1TextField.getText(), 1);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer2TextField.getText(), 0);
        }
        if (three.isSelected())
        {     
            int questID = getID("question", "Q_ID", "Question", questionTextField.getText());
            
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer1TextField.getText(), 1);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer2TextField.getText(), 0);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer3TextField.getText(), 0);
        }
        if (four.isSelected())
        {
            int questID = getID("question", "Q_ID", "Question", questionTextField.getText());
            
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer1TextField.getText(), 1);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer2TextField.getText(), 0);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer3TextField.getText(), 0);
            insertWithID("answer", "Q_ID", "Answer", "Correct", questID, answer4TextField.getText(), 0);
        }
    }
    
    private int getID(String aTable, String returnCol, String lookupCol, String input)
    {
        int catID = 0;
        String query = "SELECT " + returnCol+ " FROM 100k_drop." + aTable + " WHERE " + lookupCol + "='" + input + "'";
        
        try(Connection con = connectToDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query))
        {
            if (rs.next())
            {
                catID = rs.getInt(1);
            }
        }
        
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return catID;
    }
    
    private boolean validSubmission()
    {
        Boolean valid;
        Boolean question = this.isValidTextField(questionTextField);
        Boolean category;
        Boolean answer1 = this.isValidTextField(answer1TextField);
        Boolean answer234 = null;
        
        if (newButton.isSelected())
        {
            category = this.isValidTextField(newTextField);
        }
        else
        {
            category = this.isValidList(oldList);
        }

        if (two.isSelected())
        {
            answer234 = this.isValidTextField(answer2TextField);
        }
        if (three.isSelected())
        {
            if (this.isValidTextField(answer2TextField) && this.isValidTextField(answer3TextField))
            {
                answer234 = true;
            }
            else
            {
                answer234 = false;
            }
        }
        if (four.isSelected())
        {
            if (this.isValidTextField(answer2TextField) && this.isValidTextField(answer3TextField) &&
                    this.isValidTextField(answer4TextField))
            {
                answer234 = true;
            }
            else
            {
                answer234 = false;
            }
        }
        
        if (question && category && answer1 && answer234)
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
        
        return valid;
    }
    
    private boolean isValidTextField(TextField input)
    {
        boolean res = true;
        
        if (input.getText().equals(""))
        {
            res = false;
        }
        
        return res;
    }
    
    private boolean isValidList(ListView list)
    {
        boolean res = true;
        
        if (list.getSelectionModel().getSelectedItem() == null)
        {
            res = false;
        }
        
        return res;
    }
    
    private String oldOrNew()
    {
        String res = null;
        if (newButton.isSelected())
        {
            res = newTextField.getText();
        }
        else if(oldButton.isSelected())
        {
            res = oldList.getSelectionModel().getSelectedItem();
        }
        
        return res;
    }
    
    private int twoThreeFour()
    {
        int twoThreeFour = 0;
        
        if (two.isSelected())
        {
            twoThreeFour = 2;
        }
        if (three.isSelected())
        {
            twoThreeFour = 3;
        }
        if (four.isSelected())
        {
            twoThreeFour = 4;
        }
        return twoThreeFour;
    }

    private void updateCategories()
    {
        messageArea.setText("");
        
        oldList.getItems().clear();
        Set<String> cat = new HashSet<>();
        
        String query = "SELECT Name FROM 100k_drop.category";
        
        try(Connection con = connectToDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query))
        {
            while (rs.next())
            {
                cat.add(rs.getString(1));
            }
        }
        
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        oldList.getItems().addAll(cat);
        
        messageArea.setText("Categories have been updated");
    }
}
