/**
 * Brianna Penkala
 * This class represents a domino train.
 */
public class DominoTrain extends LinkedList {
  
  /**This field represents the last value of the train (the one to be matched) */
  private int endValue = 9;  //game starts with 9|9 domino
  /**This field represents the domino train for player 1 */
  private LinkedList<Domino> p1TrainList = new LinkedList<Domino>();
  /**This field represents the domino train for player 2 */
  private LinkedList<Domino> p2TrainList = new LinkedList<Domino>();
  /**This field represents the domino train for the mexican train */
  private LinkedList<Domino> MTTrainList = new LinkedList<Domino>();
  /**This field represents the domino to add */
  private Domino dominoToAdd;

  /**This constructor creates the train with a domino input */
  public DominoTrain (int startingValue) {
    this.endValue = startingValue;
    Domino d = new Domino (1, startingValue); //arbitrary first value
  }
  
  /**This is the getter method for the end value
    * @return the end value
    */
  public int getEndValue() {
    return this.endValue;
  }
  
  /**This is the getter method for player 1's train
    * @return player1train player 1's train
    */
  public LinkedList<Domino> getP1Train() {
    return this.p1TrainList;
  }
  
  /**This is the getter method for player 2's train
    * @return player2train player 2's train
    */
  public LinkedList<Domino> getP2Train() {
    return this.p2TrainList;
  }
  
  /**This is the getter method for the mexican train
    * @return mexicanTrain the mexican train
    */
  public LinkedList<Domino> getMTTrain() {
    return this.MTTrainList;
  }
  
  /**This is the getter method for the domino to add
    * @return dominoToAdd the domino to add
    */
  public Domino getDominoToAdd() {
    return this.dominoToAdd;
  }
  
  /**This method adds a domino to the front of the train if it matches
    * @param trainList the train that is being added to
    * @param domino the domino to add to the train
    */
  public void addToFront (LinkedList<Domino> trainList, Domino domino) {
    if (domino.getFirstValue() == endValue) {
      trainList.addToEnd(domino);
      dominoToAdd = domino;
      endValue = domino.getSecondValue();
    }
    else if (domino.getSecondValue() == endValue) {
      domino.rotate();
      Domino save = domino;
      dominoToAdd = domino;
      trainList.addToEnd(save);
      endValue = save.getSecondValue();
    }
    else 
      ;
  }
  
  /**This method checks if a domino can be added to the train 
    * @param domino the domino that might be added
    * @return true if the value can be added, false otherwise
    */
  public boolean canAdd (Domino domino) {
    return (domino.getFirstValue() == getEndValue()) || (domino.getSecondValue() == getEndValue());
  }
}
      
      
  
  
    
  
 
  