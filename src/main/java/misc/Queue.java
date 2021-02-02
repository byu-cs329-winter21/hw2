package misc;

/**
 * Elements can be compared for equality as in `x = y`
 * `Q` is the current state of the queue in any method (e.g., it is like the `this` pointer only rather than `this` it is `Q` for readability)
 * `fresh()` creates an empty queue
 * `size(Q)` returns the number of elements in the queue
 * `Q[i]` returns the element at location `i` in `Q` for `0 <= i < size(Q)`
 * `old(Q)` is the previous state of `Q` to use in `ensures` clauses (e.g., provide a means to compare the pre-method state, `old(Q)`,  to the post method state, `Q`)
 */

public class Queue {
  Object[] arr;
  int size;
  int first;
  int last;

  /**
   * Creates a queue with a bounded size.
   *
   * @requires max != 0;
   *
   * @param max   the size of the queue
   */
  public Queue(int max) {
    arr = new Object[max];
    size = 0;
    first = 0;
    last = 0;
    //this is another test
  }

  public int size() {
    return size;
  }

  public int max() {
    return arr.length;
  }

  /**
   * Adds a new object into the queue.
   * 
   * @param x the object to add to the queue
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
