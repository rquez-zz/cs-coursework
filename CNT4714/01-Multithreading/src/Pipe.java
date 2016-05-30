/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: Pipe
 * Description: Abstraction of a pipe in a the water treatment plant
 */
public class Pipe {

    // The station that currently owns the pipe
    private Station owner;

    private final int number;

    final static Station NONE = new Station(-1);

    public Pipe(int number) {
        this.owner = NONE;
        this.number = number;
    }

    public Station getOwner() {
        return owner;
    }

    public void setOwner(Station owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }
}
