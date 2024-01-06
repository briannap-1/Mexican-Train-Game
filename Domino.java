  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.Iterator;
  import java.util.NoSuchElementException;

/**
 * Brianna Penkala
 * This class represents a domino.
 */

public class Domino {
  
  /**This field represents the first value of the domino */
  private int firstValue;
  /**This field represents the first value of the domino */
  private int secondValue;
  /**This field represents the domino set*/
  private LinkedList<Domino> dominoSet;
  /**This field represents the domino set in list form*/
  private ArrayList<Domino> dominoList;
  /**This field represents player 1's hand of dominos */
  private LinkedList<Domino> player1Hand;
  /**This field represents player 2's hand of dominos */
  private LinkedList<Domino> player2Hand;
  /**This field represents the draw pile of dominos */
  private ArrayList<Domino> drawPileList;
  /**This field represents the drawn domino*/
  private Domino drawnDomino;

  /**This constructor sets the values of the domino 
    * @param firstValue the first value of the domino
    * @param secondValue the second value of the domino
    */
  public Domino (int firstValue, int secondValue) {
    this.firstValue = firstValue;
    this.secondValue = secondValue;
  }
  
  /**This is the getter method for the first value 
    * @return the first value
    */
  public int getFirstValue() {
    return this.firstValue;
  }
  
  /**This is the getter method for the second value 
    * @return the second value
    */
  public int getSecondValue() {
    return this.secondValue;
  }
  
  /**This is the getter method for domino set 
    * @return the domino set
    */
  public LinkedList<Domino> getDominoSet() {
    return this.dominoSet;
  }
  
  /**This is the getter method for player 1's hand 
    * @return player 1's hand of dominos
    */
  public LinkedList<Domino> getPlayer1Hand() {
    return this.player1Hand;
  }
  
  /**This is the getter method for player 2's hand 
    * @return player 2's hand of dominos
    */
  public LinkedList<Domino> getPlayer2Hand() {
    return this.player2Hand;
  }
  
  /**This is the getter method for the draw pile
    * @return the draw pile
    */
  public ArrayList<Domino> getDrawPile() {
    return this.drawPileList;
  }
  
  /**This is the getter method for the domino list
    * @return the dominoList
    */
  public ArrayList<Domino> getDominoList() {
    return this.dominoList;
  }
    
  /**This method rotates the domino */
  public void rotate () {
    int save = firstValue;
    this.firstValue = secondValue;
    this.secondValue = save;
  }
  
  /**This method returns the string representation of the domino 
    * @return the string representation of the domino
    */
  @Override
  public String toString() {
    return "[" + getFirstValue() + "|" + getSecondValue() + "]";
  }

  /**This method creates the domino set 
    * @return the domino set
    */
  public void setDominoSet() {  //to make every possible combination of numbers (represents set of dominos)
    ArrayList<Domino> dominoList = new ArrayList<>(); //this makes the array list version of the domino set
    for (int i = 1; i <= 9; i++) {
      for (int j = 0; j <= 9; j++) {
        if (!(i == 9 && j == 9))
          dominoList.add(new Domino(i, j)); 
      }
    }                                                  //this shuffles the made list 
    Collections.shuffle(dominoList);                                
    ArrayList<Domino> subList = new ArrayList<Domino> (dominoList.subList(0, 55));
    this.dominoList = subList;
    
    LinkedList<Domino> dominoSet = new LinkedList<>(); //this turns the arrayList into a LinkedList
    for (int i = 0; i < subList.size(); i++) {
      dominoSet.addToEnd(subList.get(i));
    }
    this.dominoSet = dominoSet;    
  }
  
  /**This method creates the player hands */     
  public void playerHands() {
    
    //this makes player 1's hand
    LinkedList<Domino> player1Hand = new LinkedList<Domino> ();
    int i = 0;
    Iterator<Domino> iterator = dominoList.iterator();   
    
    //adds the first 12 dominos to player 1's hand
    while (iterator.hasNext() && i < 12) {
      Domino nextDomino = iterator.next();
      player1Hand.addToEnd(nextDomino);
      iterator.remove();
      i++;
    }
    this.player1Hand = player1Hand;
    
    //this makes player 2's hand
    LinkedList<Domino> player2Hand = new LinkedList<Domino> ();
    
    //adds the next 12 dominos to player 2's hand
    while (iterator.hasNext() && i < 24) {
      Domino nextDomino = iterator.next();
      player2Hand.addToEnd(nextDomino);
      iterator.remove();
      i++;
    }
    this.player2Hand = player2Hand;
    this.drawPileList = dominoList;
    
    //this converts the dominoList (an arrayList) into dominoSet (a linkedList)
    LinkedList<Domino> dominoSet = new LinkedList<>(); 
    for (int j = 0; j < dominoList.size(); j++) {
      dominoSet.addToEnd(dominoList.get(i));
    }
    this.dominoSet = dominoSet;    
  }
  
  /**This method runs the setDominoSet and playerHands method to set up the game */
  public void setUpGame() {
    setDominoSet();
    playerHands();
  }
  
  /**This method draws a domino from the draw pile 
    * @ param playerList the player's hand that the domino is being applied to
    * @return the saved domino
    */
  public Domino drawDomino(LinkedList<Domino> playerList) {
    Domino fake = new Domino(0,0);
    Iterator<Domino> iterator = drawPileList.iterator();
    if (iterator.hasNext()) {
      Domino save = iterator.next();
      playerList.addToEnd(save);
      iterator.remove();
      return save;
    }
    throw new NoSuchElementException();
  }
}
  
//   public void playerHands() {
//    
//    //this makes player 1's hand
//    LinkedList<Domino> player1Hand = new LinkedList<Domino>();
//    int i = 0;
//    LinkedListIterator<Domino> iterator = dominoSet.iterator();   
//      
//    while (iterator.hasNext() && i < 12) {
//      Domino nextDomino = iterator.next();
//      player1Hand.addToEnd(nextDomino);
//     // iterator.remove();
//      i++;
//    }
//    this.player1Hand = player1Hand;
//    
//    //this makes player 2's hand
//    LinkedList<Domino> player2Hand = new LinkedList<Domino> ();
//    while (iterator.hasNext() && i < 24) {
//      Domino nextDomino = iterator.next();
//      player2Hand.addToEnd(nextDomino);
//     // iterator.remove();
//      i++;
//    }
//    this.player2Hand = player2Hand;
//  
//    //this converts the dominoList (arrayList) into dominoSet (linkedList)
//    LinkedList<Domino> dominoSet = new LinkedList<>(); //this turns the arrayList into a LinkedList
//    for (int j = 0; j < dominoList.size(); j++) {
//      dominoSet.addToEnd(dominoList.get(i));
//    }
//   }
//}

//  public void playerHands() { 
//    LinkedList<Domino> player1Hand = new LinkedList<Domino> ();
//    int i = 0;
//    LinkedListIterator<Domino> iterator = this.dominoSet.iterator();   
//    while (iterator.hasNext() && i < 12) {
//      //player1Hand.addToEnd(iterator.next());
//      Domino nextDomino = iterator.next();
//      player1Hand.addToEnd(nextDomino);
//      iterator.remove();
//      i++;
//    }
//    this.player1Hand = player1Hand;
//    
//    LinkedList<Domino> player2Hand = new LinkedList<Domino> ();
//    while (iterator.hasNext() && i < 24) {
//      Domino nextDomino = iterator.next();
//      player2Hand.addToEnd(nextDomino);
//      iterator.remove();
//      i++;
//    }
//    this.player2Hand = player2Hand;
//    
//  }


 


