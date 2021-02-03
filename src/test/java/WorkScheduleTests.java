  /*
  * Elements can be compared for equality as in `x = y`
  * `ws of the queue in any method
  * (e.g., it is like the `this` pointer only rather than `this` it is `ws` for readability)
  * `fresh()` creates an empty workSchedule
  * `size(ws)` returns the number of elements in the WorkSchedule
  * `ws[i]` returns the element at location `i` in `ws` for `0 <= i < size(ws)`
  * `old(ws)` is the previous state of `ws` to use in `ensures` clauses
  * (e.g., provide a means to compare the pre-method state, `old(ws)`,  to the post method state, `ws`)
  *  WorkSchedule.workingEmployees gets scheduled employees for an hour
  *
  *
  * Creates a work schedule with a bounded size.
  *
  * @requires 0 < size < 24
  *
  * @ensures ws.size = size
  * @ensures ws[i] = 0 && WorkSchedule.workingEmployees = null;
  * @ensures ws != null
  * @ensures fresh(ws)
  *
  * public WorkSchedule(int size) {}
  *
  *
  * Returns an object of type hour which lists the required number of working employees
  * and the names of those scheduled to work.
  *
  * @requires ws != null
  * @requires 0 <= time < size(ws)
  *
  * @ensures hour != null
  * @ensures WorkSchedule.workingEmployees = ws[time]
  *
  * @returns hour
  * public Hour readSchedule(int time) {}
  *
  *
  * sets the number of required working employees to nemployee for all hours in
  * the interval starttime to endtime
  *
  * @requires nemployee >= 0
  * @requires ws != null
  * @requires 0 <= starttime <= endtime < size(ws)
  *
  * @ensures forall i, starttime <= i <= endtime, ws[i] = nemployee
  * @ensures old(ws.size) = ws.size
  *
  * public void setRequiredNumber(int nemployee, int starttime, int endtime) {}
  *
  *
  * schedules employee to work during the hours from starttime to endtime.
  *
  * @requires employee != null
  * @requires ws != null
  * @requires 0 <= starttime <= endtime < size(ws)
  *
  * @ensures old(ws.size) = ws.size
  * @ensures forall i, starttime <= i <= endtime, workSchedule.workingEmployees includes employee
  *
  * @returns true/false
  *
  * public boolean addWorkingPeriod(String employee, int starttime, int endtime) {}
  *
  *
  * returns a list of all employees working at some point during the interval
  * starttime to endtime.
  *
  * @requires 0 <= starttime <= endtime < size(ws)
  * @requires ws != null
  * @requires workSchedule.workingEmployees > 0
  *
  * @ensures forall i, i < workSchedule.workingEmployees, print
  *
  * public String[] workingEmployees(int starttime, int endtime) {}
  *
  *
  * returns the closest time starting from currenttime for which the required
  * amount of employees has not yet been scheduled.
  *
  * @requires ws != null
  * @requires currenttime != null
  * @requires workSchedule.setRequiredNumber()
  *
  * @ensures currenttime <= return int <= size
  *
  * public int nextIncomplete(int currenttime) {}
  */

  import org.junit.jupiter.api.*;

  import java.rmi.UnexpectedException;
  import java.security.InvalidParameterException;

  import static org.junit.jupiter.api.Assertions.*;

  @DisplayName("JUnit5 WorkSchedule tests")
  public class WorkScheduleTests {

    static WorkSchedule ws=null;

    /*
    partitions for setRequiredNumber() divided into 5 tests
      1) nemployee < 0
      2) all valid params
      3) starttime < 0
      4) endtime > size
      5) endtime < starttime
     */
    @Nested
    @DisplayName("setRequiredNumber valid start/endtime")
    class validStartEnd {
      int starttime=2;
      int endtime=3;

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(7);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      //nothing was thrown, but something should have been
      @Test
      @Tag("setRequiredNumber_test")
      @DisplayName("nemployee is negative")
      public void invalidNemployee() {
        int nemployee=-1;

        assertThrows(IllegalArgumentException.class, () -> ws.setRequiredNumber(nemployee, starttime, endtime));
      }

      @Test
      @Tag("setRequiredNumber_test")
      @DisplayName("all valid params")
      public void allValid() {
        int nemployee=3;
        WorkSchedule before=ws;

        ws.setRequiredNumber(nemployee, starttime, endtime);

        assertEquals(before, ws);
      }
    }

    @Nested
    @DisplayName("setRequiredNumber INvalid start/endtime")
    class invalidStartEnd {

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(4);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      @Test
      @Tag("setRequiredNumber_test")
      @DisplayName("starttime is negative")
      public void startNeg() {
        int endTime=3;
        int startTime=-1;
        int nemployee=2;

        assertThrows(IndexOutOfBoundsException.class, () -> ws.setRequiredNumber(nemployee, startTime, endTime));
      }

      @Test
      @Tag("setRequiredNumber_test")
      @DisplayName("endtime greater than size")
      public void bigEndtime() {
        int endTime=17;
        int startTime=1;
        int nemployee=2;

        assertThrows(IndexOutOfBoundsException.class, () -> ws.setRequiredNumber(nemployee, startTime, endTime));
      }

      //nothing was thrown, but something should have been
      @Test
      @Tag("setRequiredNumber_test")
      @DisplayName("endtime less than starttime")
      public void smallEndtime() {
        int endTime=1;
        int startTime=2;
        int nemployee=2;

        assertThrows(IllegalArgumentException.class, () -> ws.setRequiredNumber(nemployee, startTime, endTime));
      }

    }

    /*
      partitions for setRequiredNumber() divided into 5 tests
      1) employee = null
      2) all valid params
      3) starttime < 0
      4) endtime > size
      5) endtime < starttime
    */

    @Nested
    @DisplayName("addWorkingPeriod valid start/endtime")
    class validWorking {
      int starttime=2;
      int endtime=3;
      int nemployee=1;

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(4);
        ws.setRequiredNumber(nemployee, starttime, endtime);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      //nothing was thrown and something should have been
      @Test
      @Tag("addWorkingPeriod_Test")
      @DisplayName("employee is null")
      public void nullEmployee() {
        String employee=null;

        assertThrows(IllegalArgumentException.class, () -> ws.addWorkingPeriod(employee, starttime, endtime));
      }

      @Test
      @Tag("addWorkingPeriod_Test")
      @DisplayName("all valid params")
      public void allValid() {
        String employee="Cloud Strife";
        String[] expected={"Cloud Strife"};

        assertAll(
          () -> assertTrue(ws.addWorkingPeriod(employee, starttime, endtime)),
          () -> assertEquals(employee, ws.readSchedule(2).workingEmployees[0]),
          () -> assertEquals(employee, ws.readSchedule(3).workingEmployees[0]));
      }
    }

    @Nested
    @DisplayName("addWorkingPeriod INvalid start/end")
    class invalidWorking {
      int starttime=2;
      int endtime=3;
      int nemployee=1;

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(4);
        ws.setRequiredNumber(nemployee, starttime, endtime);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      @Test
      @Tag("addWorkingPeriod_Test")
      @DisplayName("negative starttime")
      public void negStart() {
        int workStart = -1;
        int workEnd = 2;
        String employee="Cloud Strife";

        assertFalse(ws.addWorkingPeriod(employee, workStart, workEnd));
      }

      @Test
      @Tag("addWorkingPeriod_Test")
      @DisplayName("endtime greater than size")
      public void bigEnd() {
        int workStart = 1;
        int workEnd = 20;
        String employee="Cloud Strife";

        assertFalse(ws.addWorkingPeriod(employee, workStart, workEnd));
      }

      //gives true but should be false
      @Test
      @Tag("addWorkingPeriod_Test")
      @DisplayName("endtime less than starttime")
      public void smallEnd() {
        int workStart = 2;
        int workEnd = 1;
        String employee="Cloud Strife";

        assertFalse(ws.addWorkingPeriod(employee, workStart, workEnd));
      }
    }

    /*
      partitions for workingEmployees() divided into 5 tests
      1) workSchedule.workingEmployees = 0;
      2) all valid params
      3) starttime < 0
      4) endtime > size
      5) endtime < starttime
    */

    @Nested
    @DisplayName("workingEmployees valid start/endtime")
    class validEmployees {
      int starttime=2;
      int endtime=3;
      int nemployee=2;
      String employee="Cloud Strife";
      String employee2="Red 13";

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(4);
        ws.setRequiredNumber(nemployee, starttime, endtime);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      //returns a string, but should return nothing
      @Test
      @Tag("workingEmployees_test")
      @DisplayName("no working employees")
      public void noEmployees() {

        assertNull(ws.workingEmployees(starttime, endtime));
      }

      @Test
      @Tag("workingEmployees_test")
      @DisplayName("all valid params")
      public void allValid () {
        ws.addWorkingPeriod(employee, starttime, endtime);
        ws.addWorkingPeriod(employee2, starttime, endtime);
        String expectedCloud = "Cloud Strife";
        String expectedRed = "Red 13";

        assertAll (
          () -> assertEquals(expectedCloud, ws.workingEmployees(starttime, endtime)[0]),
          () -> assertEquals(expectedRed, ws.workingEmployees(starttime, endtime)[1])
        );
      }
    }

    @Nested
    @DisplayName("workingEmployees INvalid start/endtime")
    class invalidEmployees {
      int starttime=2;
      int endtime=3;
      int nemployee=2;
      String employee="Cloud Strife";
      String employee2="Red 13";

      @BeforeEach
      public void setUp() {
        ws=new WorkSchedule(4);
        ws.setRequiredNumber(nemployee, starttime, endtime);
        ws.addWorkingPeriod(employee, starttime, endtime);
        ws.addWorkingPeriod(employee2, starttime, endtime);
      }

      @AfterEach
      public void tearDown() {
        ws=null;
      }

      @Test
      @Tag("workingEmployees_test")
      @DisplayName("negative start")
      public void negStart() {
        int workStart = -1;
        int workEnd = 2;

        assertThrows(IndexOutOfBoundsException.class, () -> ws.workingEmployees(workStart, workEnd));
      }

      @Test
      @Tag("workingEmployees_test")
      @DisplayName("end greater than size")
      public void bigEnd() {
        int workStart = 1;
        int workEnd = 20;

        assertThrows(IndexOutOfBoundsException.class, () -> ws.workingEmployees(workStart, workEnd));
      }

      //nothing was thrown but something should have been
      @Test
      @Tag("workingEmployees_test")
      @DisplayName("end less than start")
      public void smallEnd() {
        int workStart = 2;
        int workEnd = 1;

        assertThrows(IllegalArgumentException.class, () -> ws.workingEmployees(workStart, workEnd));
      }
    }
}
