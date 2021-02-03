package misc;


/**
 * Defines the Misc class.
 * Elements can be compared for equality as in `x = y`
 * `Q` is the current state of the queue in any method
 * (e.g., it is like the `this` pointer only rather than `this` it is `Q` for readability)
 * `fresh()` creates an empty queue
 * `size(Q)` returns the number of elements in the queue
 * `Q[i]` returns the element at location `i` in `Q` for `0 <= i < size(Q)`
 * `old(Q)` is the previous state of `Q` to use in `ensures` clauses
 * (e.g., provide a means to compare the pre-method state, `old(Q)`,  to the post method state, `Q`)
 */
public class Misc {

  /**
   * Adds an element to an array if it is not already in the array.
   *
   * @requires x != null
   * @requires arr != null
   * @requires if !exists i in arr(i = x) ==> exists i in arr(i = null)
   * 
   * @ensures exists i in arr (i = x)
   * @ensures old(arr[i]) = null ==> arr[i] = x
   * 
   * @param x element to add
   * @param arr array to which x is added
   */
  public static void ff(Object x, Object[] arr) {
    int i;
    boolean exists = false;

    for (i = 0; i < arr.length; i++) { // A1
      if (x.equals(arr[i])) { // B1
        exists = true;
        break;
      } // B2
    } // A2
    if (!exists) { //C1
      for (i = 0;; i++) { // D1
        if (arr[i] == null) { // E1
          arr[i] = x;
          break;
        } // E2
      } // D2
    } // C2
  }
}
