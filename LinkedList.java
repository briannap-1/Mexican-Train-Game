import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.  The list is iterable (i.e. we can loop over its data).
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> firstNode;
//  private LLNode<T> previousNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
//    previousNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }
  
  /**
   * Returns the first element of the list without removing it          ///////my addition
   * @return the first element of the list  
   */
  public T getFirstElement () {
    return getFirstNode().getElement();
  }
  
//  public LLNode<T> getPreviousNode() {
//    return previousNode;
//  }
  

  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
  }
  
  /**
   * Removes and returns the element at the front of the linked list
   * @return the element removed from the front of the linked list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
  }

  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int lengthSoFar = 0;
    LLNode<T> nodeptr = getFirstNode();
    while (nodeptr != null) {
      lengthSoFar++;
      nodeptr = nodeptr.getNext();
    }
    return lengthSoFar;
  }

  /**
   * Add an element to the end of a list.
   * @param element the element to add
   */
  public void addToEnd(T element) {
    if (isEmpty())
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFirstNode();
      while (nodeptr.getNext() != null)
//        previousNode = nodeptr;
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  /**
   * The method required by the Iterable interface returns an iterator that loops over the data in the list.
   * @return an iterator that loops over the data in the list
   */
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<T>(getFirstNode());
  } 
}
