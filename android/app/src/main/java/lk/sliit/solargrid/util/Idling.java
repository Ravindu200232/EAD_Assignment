package lk.sliit.solargrid.util;

import java.util.concurrent.atomic.AtomicInteger;

public final class Idling {

    private static final AtomicInteger running = new AtomicInteger(0);

    /** Nobody builds this class; it only holds the counter. */
    private Idling() {
    }

    /** Called when a request starts. */
    public static void begin() {
        running.incrementAndGet();
    }

    /** Called when a request finishes, whether it worked or not. */
    public static void end() {
        running.decrementAndGet();
    }

    /** True when nothing is waiting for the server. */
    public static boolean isIdle() {
        return running.get() <= 0;
    }
}
