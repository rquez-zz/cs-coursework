/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: Pipe
 */
public class Pipe {

    private boolean lock;
    private int number;

    public Pipe(int number) {
        lock = false;
        this.number = number;
    }

    public boolean isLocked() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
