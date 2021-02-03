package misc;

/**
 * Defines the Queue class.
 */

public class Queue {
  Object[] arr;
  int size;
  int first;
  int last;

  /**
   * Creates a queue with a bounded size.
   *
   * @requires max != 0
   *
   * @ensures Q = fresh()
   * @ensures size = 0 && first = 0 && last = 0
   * @ensures Q[first] = null
   * @ensures Q[last] = null
   * @ensures Q.max() = max
   *
   * @param max   the size of the queue
   */
  public Queue(int max) {
    arr = new Object[max];
    size = 0;
    first = 0;
    last = 0;
  }

  /**
   * Gets the size of the queue.
   *
   * @requires Q != null
   *
   * @ensures 0 <= size < max(Q)
   *
   * @return size   the number of objects in the queue
   */
  public int size() {
    return size;
  }

  /**
   * Gets the max length of the queue.
   *
   * @requires Q != null
   * @requires arr.length != 0
   *
   * @ensures 0 < arr.length < max(Q)
   *
   * @return arr.length   the max length of the queue
   */
  public int max() {
    return arr.length;
  }

  /**
   * Adds a new object into the queue.
   *
   * @requires x != null
   * @reguires Q != null
   * @requires size(Q) < max(Q)
   * @requires 0 <= last < max(Q)
   *
   * @ensures Q[size(old(Q))] = x
   * @ensures size(Q) = size(old(Q)) + 1
   * @ensures old(Q) + x = Q
   *
   * @param x   the object to add to the queue
   */
  public void enqueue(Object x) {
    arr[last] = x;
    last++;
    if (last == arr.length) {
      last = 0;
    }
    size++;
  }

  /**
   * Removes an object from the queue.
   *
   * @requires Q != null
   * @requires 0 <= size(Q) < max(Q)
   * @requires first >= 0
   *
   * @ensures x = Q[first]
   * @ensures Q[first] = old(Q[first]) + 1
   * @ensures 0 <= first < max(Q)
   * @ensures size(Q) = size(old(Q)) - 1
   * 
   * @return the removed object
   */
  public Object dequeue() {
    if (size == 0) {
      return null;
    }
    final Object x = arr[first];
    first++;
    if (first == arr.length) {
      first = 0;
    }
    size--;
    return x;
  }
}
