package Utils;

import static java.lang.Thread.sleep;

public class Wait {
    /**
     * Wait while condition will be true
     *
     * @param func    Condition for waiting
     * @param timeOut Waiting time, in milliSeconds
     * @return Result of condition
     * @throws InterruptedException
     */
    public static boolean waitFor(WaitForInterface func, int timeOut) {
        int count = timeOut / 1000;
        for (int i = 0; i < count; i++) {
            if (!func.doSomeWork())
                try {
                    sleep(1000);
                } catch (Exception e) {
                }
            else
                return true;
        }
        return false;
    }

    public static boolean waitFor(WaitForInterface func, int count, int timeOutMs) {
        for (int i = 0; i < count; i++) {
            if (!func.doSomeWork())
                try {
                    sleep(timeOutMs);
                } catch (Exception e) {
                }
            else
                return true;
        }
        return false;
    }
}



