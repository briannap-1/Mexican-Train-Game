import org.junit.Test; 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.*;

/** Brianna Penkala 
  * This is tester for the Mexican Train Game.
  */
      
public class MexicanTrainGameTester {
  
  /**This tests the methods of the Domino class */
  @Test 
  public void testDominoMethods() {
    Domino d = new Domino (1,2);
    d.setUpGame(); //uses the methods to create the dominoSet, dominoList, and playerHands
    assertEquals(1, d.getFirstValue());
    assertEquals(2, d.getSecondValue());
    assertNotNull(d.getDominoSet());
    assertNotNull(d.getPlayer1Hand());
    assertNotNull(d.getPlayer2Hand());
    assertNotNull(d.getDrawPile());
    assertNotNull(d.getDominoList());
    d.rotate();
    assertEquals("[2|1]", d.toString()); //tests both rotate and toString          
  }
  
  /**This tests the methods of the DominoTrain class */
  @Test 
  public void testDominoTrainMethods() {
    Domino d = new Domino (1,2);
    DominoTrain train = new DominoTrain(9);
    assertEquals(9, train.getEndValue());
    assertNotNull(train.getP1Train());
    assertNotNull(train.getP2Train());
    assertNotNull(train.getMTTrain());
    //not sure how to test addDomino and getDominoToAdd
    Domino d2 = new Domino (3,3);
    assertEquals(false, train.canAdd(d2));
  }
    
  @Test 
  public void testLinkedList() {
    LinkedList<Domino> list = new LinkedList<Domino>();
    Domino d = new Domino (1,2);
    LLNode<Domino> node = new LLNode<Domino>(d, null);
    assertEquals(null, list.getFirstNode());
    list.setFirstNode(node);
    assertEquals(node, list.getFirstNode());
    assertEquals("[1|2]", list.getFirstElement().toString());
    Domino d2 = new Domino (8,9);
    list.addToFront(d2);
    assertEquals(d2, list.getFirstElement());
    list.removeFromFront();
    assertEquals(d, list.getFirstElement());
    assertEquals(1, list.length());
    LinkedList<Domino> emptyList = new LinkedList<Domino>();
    assertEquals(true, emptyList.isEmpty());
    emptyList.addToEnd(d2);
    assertEquals(d2, emptyList.getFirstElement());
  }

}