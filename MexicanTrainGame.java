import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.ArrayList;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color; 
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.control.TextArea;

/**
 * Brianna Penkala
 * This class represents the Mexican Train Game
 */

public class MexicanTrainGame extends Application {
  
  /**This field represents the number of players in the game */
  private int numberOfPlayers = 2; 
  /**This field represents the instance of Domino */
  private Domino domino = new Domino(9, 9);
  /**This field represents player 1's train */
  private DominoTrain dominoTrainP1 = new DominoTrain(9); 
  /**This field represents player 2's train */
  private DominoTrain dominoTrainP2 = new DominoTrain(9);
  /**This field represents the mexican train */
  private DominoTrain dominoTrainMT = new DominoTrain(9); 
  /**This field represents the DominoTrain instance */
  private DominoTrain dominoTrain = new DominoTrain(9);
  /**This field represents player 1's hand HBox */
  private HBox hBoxP1 = new HBox();
  /**This field represents player 2's hand HBox */
  private HBox hBoxP2 = new HBox();
  /**This field represents player 1's mexican train HBox */
  private HBox hBoxMT1 = new HBox(2);
  /**This field represents player 2's mexican train HBox */
  private HBox hBoxMT2 = new HBox(2);
  /**This field represents the main mexican train HBox */
  private HBox hBoxMT3 = new HBox(2);
  /**This field represents the children of player 1's HBox */
  private ObservableList<Node> p1Children = hBoxP1.getChildren();
  /**This field represents the children of player 2's HBox */
  private ObservableList<Node> p2Children = hBoxP2.getChildren();
  /**This field represents the last button selected */
  private Button lastButtonSelected = new Button();
  /**This field represents the string version of the last button selected */
  private String lastStringDominoSelected = domino.toString();
  /**This field represents the last domino selected */
  private Domino lastDominoSelected;
  /**This field represents the current player*/
  private String thisPlayer = "player1"; //initializes to start with player 1
  /**This field represents player 1's scene */
  private Scene player1Scene;
  /**This field represents player 2's scene */
  private Scene player2Scene;
  /**This field represents the mexican train scene */
  private Scene mexicanTrainScene;
  /**This field represents if player 1's train is open */
  private boolean p1TrainOpen; 
  /**This field represents if player 2's train is open */
  private boolean p2TrainOpen; 
  /**This field represents the button to add a domino to the mexican train */
  private Button mTAdd = new Button("Add Domino");
  /**This field represents the button to add a domino to player 1's train */
  private Button p1Add = new Button("Add Domino");
  /**This field represents the button to add a domino to player 2's train */
  private Button p2Add = new Button("Add Domino");
  
  /**This is the setter number for the number of players 
    * @param number the number of players in the game (originally planned on allowing more than 3 players)
    */
  public void setNumberOfPlayers (int number) {
    this.numberOfPlayers = number;
  }
  
  /**This is the start method for the GUI 
    * @param primaryStage the primary stage of the GUI
    */
  public void start (Stage primaryStage) { //represents the screen with the 3 trains
    
    /**This method call sets up the domino set and player hands */
    setDominoInstance();
    
    /**This is the stage setup for the individual players */
    Stage player1Stage = new Stage(); //represents player 1's dominos screen
    Stage player2Stage = new Stage(); //represents player 2's dominos screen
    
    /**This sets up the buttons, layout, and labels of the player trains */
    Text p1Label = new Text("Player 1 Train: ");
    Text p2Label = new Text("Player 2 Train: ");
    Text mTLabel = new Text("Mexican Train: ");
    p1Add.setOnAction(addDominoEventHandler);
    p2Add.setOnAction(addDominoEventHandler);
    mTAdd.setOnAction(addDominoEventHandler);
    
    /**This sets up the HBoxes for the primary stage */
    hBoxMT1.setSpacing(20);
    hBoxMT1.getChildren().addAll(p1Label, p1Add);
    hBoxMT2 = new HBox(p2Label, p2Add);
    hBoxMT2.setSpacing(20);
    hBoxMT3 = new HBox(mTLabel, mTAdd);
    hBoxMT3.setSpacing(20);
    VBox vBoxMT = new VBox(hBoxMT1, hBoxMT2, hBoxMT3); //creates a layout in a vertical column (player1 train, player2 train, mexican train)
    
    /**This runs the createChildrenExecution method */
    createChildrenExecution();
    
    /**This sets up player 1's draw button and layout */
    Button b4 = new Button("Draw");
    b4.setOnAction(drawButtonEventHandler);
    HBox drawHBoxP1 = new HBox(b4);
    hBoxP1.getChildren().add(b4);
    hBoxP1.setSpacing(10);
    
    /**This sets up player 2's draw button and layout */
    Button b5 = new Button("Draw");
    b5.setOnAction(drawButtonEventHandler);
    hBoxP2.getChildren().add(b5);
    hBoxP2.setSpacing(10);
    
    /**This displays the main scene */
    Scene mainScene = new Scene(vBoxMT);
    primaryStage.setTitle("Mexican Train Game");
    primaryStage.setScene(mainScene); 
    primaryStage.setMinWidth(1000);
    primaryStage.show();                    
    
    /**This displays player 1's scene */
    Scene p1Scene = new Scene(hBoxP1);
    player1Scene = p1Scene;
    player1Stage.setTitle("Player 1");
    player1Stage.setScene(p1Scene);
    player1Stage.show();
    
    /**This displays player 2's scene */
    Scene p2Scene = new Scene(hBoxP2);
    player2Scene = p2Scene;
    player2Stage.setTitle("Player 2");
    player2Stage.setScene(p2Scene);
    player2Stage.show();
  }
  
  /**This method loops through the player hands to create the dominos of player 1 and player 2's HBoxes 
    * @param dominoList the specified player hand
    * @param hBox the HBox to add the dominos to
    */
  public void createChildren (LinkedList<Domino> dominoList, HBox hBox) {
    for (Domino d : dominoList) { //goes through the player hands, creates a button for each with the label of the domino.toString, the event handler, adds to HBox
      Button button = new Button(d.toString());
      setResetButton(button);
      button.setOnAction(dominoEventHandler);
      hBox.getChildren().add(button);
    }
  }
  
  /**This method executes the createChildren method with parameters for player 1 and player 2 */
  public void createChildrenExecution() {
    createChildren(domino.getPlayer1Hand(), this.hBoxP1);
    createChildren(domino.getPlayer2Hand(), this.hBoxP2);
  }
  
  /**This method calls the domino setUpGame() method */
  public void setDominoInstance() {
    domino.setUpGame();
  }
  
  /**This is the private class event handler for the "Add Domino" buttons */
  private class AddDominoButtonEvent implements EventHandler<ActionEvent> {  
    /**This is the handle method for the event handler
      * @param e the event
      */
    public void handle(ActionEvent e) { 
      Button b = (Button) e.getSource();
      //adds the selected domino to player 1's train, sets it closed
      if (thisPlayer.equals("player1") && b.equals(p1Add)) {
        addButtonHelper(player1Scene, "player1", dominoTrainP1.getP1Train(), dominoTrainP1, hBoxMT1);
        p1TrainOpen = false;
        trainColor(p1TrainOpen, hBoxMT1);
      }
      //adds the selected domino to player 2's train, sets it closed
      if (thisPlayer.equals("player2") && b.equals(p2Add)) {
        addButtonHelper(player2Scene, "player2", dominoTrainP2.getP2Train(), dominoTrainP2, hBoxMT2);
        p2TrainOpen = false;
        trainColor(p2TrainOpen, hBoxMT2);
      }
      //adds the selected domino to the mexican train
      if (b.equals(mTAdd)) { 
        if (dominoTrainMT.canAdd(lastDominoSelected)) {  
          dominoTrainMT.addToFront(dominoTrainMT.getMTTrain(), lastDominoSelected);
          Button button = new Button(dominoTrainMT.getDominoToAdd().toString());
          setResetButton(button);
          lastButtonSelected.setText("");
          setResetButton(lastButtonSelected);
          hBoxMT3.getChildren().addAll(button);
        }
      }
      
      //checks if a domino from player 1 can be added to player 2's train when it is open
      if (thisPlayer.equals("player1") && p2TrainOpen == true && b.equals(p2Add))
        addButtonHelper(player1Scene, "player1", dominoTrainP2.getP2Train(), dominoTrainP2, hBoxMT2);
      //checks if a domino from player 2 can be added to player 1's train when it is open
      if (thisPlayer.equals("player2") && p1TrainOpen == true && b.equals(p1Add))
        addButtonHelper(player2Scene, "player2", dominoTrainP1.getP1Train(), dominoTrainP1, hBoxMT1);
      
      //switches the current player
      if (thisPlayer.equals("player1"))
        thisPlayer = "player2";
      else
        thisPlayer = "player1";
    }
  }
  
  /**This is the helper method for the AddDominoButtonEvent 
    * @param scene the scene of this button
    * @param player the desired player
    * @param train the train to add to
    * @param whichTrain the specific instance of DominoTrain to add to
    * @param hBox the hBox to add to
    */
  public void addButtonHelper(Scene scene, String player, LinkedList<Domino> train, DominoTrain whichTrain, HBox hBox) {
    //checks if this button is a valid action for the player
    if (lastButtonSelected.getScene().equals(scene) && thisPlayer.equals(player)) {
      //checks if the domino can be added to the selected train
      if (whichTrain.canAdd(lastDominoSelected)) { 
        whichTrain.addToFront(train, lastDominoSelected);
        Button button = new Button(whichTrain.getDominoToAdd().toString());
        setResetButton(button);
        lastButtonSelected.setText("");
        setResetButton(lastButtonSelected);
        hBox.getChildren().addAll(button);
      }
    }
  }
  
  /**This executes the AddDominoButtonEvent */
  AddDominoButtonEvent addDominoEventHandler = new AddDominoButtonEvent();
  
  /**This is the private class event handler for the "Draw" buttons */
  private class DrawButtonEvent implements EventHandler<ActionEvent> {
    /**This is the handle method for the event handler
      * @param e the event
      */
    public void handle(ActionEvent e) { 
      Button b = (Button) e.getSource();
      Domino drawnDomino = domino.drawDomino(dominoTrainP1.getP1Train());
      //player1 draw button
      //checks if this domino can be drawn for player 1
      if (b.getScene().equals(player1Scene) && thisPlayer.equals("player1")) {  
        //checks if the drawn domino can be added to the player 1 train, if so then it is added and the train is closed
        if (dominoTrainP1.canAdd(drawnDomino)) {  
          dominoTrainP1.addToFront(dominoTrainP1.getP1Train(), drawnDomino);
          Button button = new Button(dominoTrainP1.getDominoToAdd().toString());
          setResetButton(button);
          hBoxMT1.getChildren().addAll(button);
          lastButtonSelected.setText("");
          setResetButton(lastButtonSelected);
          p1TrainOpen = false;
          trainColor(p1TrainOpen, hBoxMT1);
          thisPlayer = "player2";
        }
        //if the domino cannot be added the train is opened and this player is switched
        else {
          p1TrainOpen = true; 
          trainColor(p1TrainOpen, hBoxMT1);
          thisPlayer = "player2";
        }
      }
      //player2 button
      //checks if this domino can be drawn for player 1
      if (b.getScene().equals(player2Scene) && thisPlayer.equals("player2")) {  
         //checks if the drawn domino can be added to the player 2 train, if so then it is added and the train is closed
        if (dominoTrainP2.canAdd(drawnDomino)) {  
          dominoTrainP2.addToFront(dominoTrainP2.getP2Train(), drawnDomino);
          Button button = new Button(dominoTrainP2.getDominoToAdd().toString());
          setResetButton(button);
          hBoxMT2.getChildren().addAll(button);
          lastButtonSelected.setText("");
          setResetButton(lastButtonSelected);
          p2TrainOpen = false;
          trainColor(p2TrainOpen, hBoxMT2);
          thisPlayer = "player1";
        }
        //if the domino cannot be added the train is opened and this player is switched
        else {
          p2TrainOpen = true; 
          trainColor(p2TrainOpen, hBoxMT2);
          thisPlayer = "player1";
        }
      }
    }
  }
  
  /**This executes the DrawButtonEvent */
  DrawButtonEvent drawButtonEventHandler = new DrawButtonEvent();
  
  /**This is the private class event handler for the basic domino buttons */
  private class DominoButtonEvent implements EventHandler<ActionEvent> { 
    /**This is the handle method for the event handler
      * @param e the event
      */
    public void handle(ActionEvent e) {
      Button b = (Button) e.getSource();
      lastStringDominoSelected = b.getText(); 
      checkDominoEquality(b);
    }
  }
  
  /**This executes the DominoButtonEvent */
  DominoButtonEvent dominoEventHandler = new DominoButtonEvent();
  
  /**This method checks which hand the domino came from 
    * @param button the button selected
    */
  public void checkDominoEquality (Button button) {
    //checks if the domino is from player 1's hand
    for (Domino d : domino.getPlayer1Hand()) {
      if (lastStringDominoSelected.equals(d.toString()) && thisPlayer.equals("player1")) { //second part is likely irrelevant
        lastDominoSelected = d;
        setButtonColor(button);
        lastButtonSelected = button;
      }
      else
        ; 
    }
    //checks if the domino is from player 2's hand
    for (Domino d : domino.getPlayer2Hand()) {
      if (lastStringDominoSelected.equals(d.toString()) && thisPlayer.equals("player2")) {
        lastDominoSelected = d;
        setButtonColor(button);
        lastButtonSelected = button;
      }
      else 
        ; 
    }
  }
  
  /**This method sets the selected button color 
    * @param button the selected button
    */
  public void setButtonColor(Button button) {
    BackgroundFill bf = new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY , Insets.EMPTY);
    Background background = new Background(bf);
    button.setBackground(background);
    lastButtonSelected.setText("");
    setResetButton(lastButtonSelected);
  }
  
  /**This method sets the resets button color 
    * @param button the button to reset
    */
  public void setResetButton(Button button) {
    BackgroundFill reset = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY , Insets.EMPTY);
    Background resetBackground = new Background(reset);
    button.setBackground(resetBackground);
  }
  
  /**This method sets train color red when the train is open and resets it when it is closed 
    * @param thisTrainOpen the train to check
    * @param hBox the HBox with the selected train 
    */
  public void trainColor(Boolean thisTrainOpen, HBox hBox) {
    //sets the train red when it is open
    if (thisTrainOpen == true) { 
      BackgroundFill bf = new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY , Insets.EMPTY);
      Background trainBackground = new Background(bf);
      hBox.setBackground(trainBackground);
    }
    //resets the train if it is closed
    else {
      BackgroundFill reset = new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY , Insets.EMPTY);
      Background trainBackground = new Background(reset);
      hBox.setBackground(trainBackground);
    }
  }
  
  /**This is the main method for the game */
  public static void main (String[] args) { 
    if (args.length > 0) {
      MexicanTrainGame players = new MexicanTrainGame();
      players.setNumberOfPlayers(Integer.parseInt(args[0])); //put outside if possible
      System.out.println("The game will have " + Integer.parseInt(args[0]) + " players."); //planned on allowing up to 4 players, only 2 are allowed
    }
    else 
      System.out.println("The game will have 2 players.");
    Application.launch(args);
  }
}
