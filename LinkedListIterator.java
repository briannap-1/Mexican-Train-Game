import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
 * Brianna Penkala
 * An iterator for our linked list.  The iterator loops over the data in the list from
  * the first node to the last.
  */
public class LinkedListIterator<T> implements Iterator<T> {
  
  // keeps track of which node will store the next value of the iteration
  private LLNode<T> nodeptr;
  private LLNode<T> previousNode;
  
  /**
   * Create an iterator that loops over the data in the list starting at the given first node
   * @param firstNode the node to start this loop over the data in the list
   */
  public LinkedListIterator(LLNode<T> firstNode) {
    nodeptr = firstNode;
    previousNode = null;
  }
  
  public LLNode<T> getPreviousNode() {
    return previousNode;
  }
  
  /**
   * Returns true if there is more data we can loop over and false if the loop reached the end of the list.
   * @return true if there is more data to loop over
   */
  public boolean hasNext() {
    return nodeptr != null;
  }
  
  /**
   * Returns the next value from the linked list in this iterator that loops over the list data.
   * @return the next value in this loop over the linked list data
   * @throws NoSuchElementException if next() is called after the loop reaches the end of the list
   */
  public T next() {
    if (nodeptr == null)
      throw new NoSuchElementException();
    
    T save = nodeptr.getElement();
    previousNode = nodeptr;
    nodeptr = nodeptr.getNext();
    return save;
  }
  
  public void remove(LinkedList<Domino> list) {
    if (nodeptr == null)
      throw new NoSuchElementException();
    else {
      if (nodeptr.getNext() != null)
      previousNode.setNext(nodeptr.getNext());
      nodeptr = nodeptr.getNext();
      }
    
  }
  
}