import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GreedyAlgorithmsTest {

    @Test
    public void test_sort() {
        int[] array = new int[]{3,7,5,4,2};
        GreedyAlgorithms.sort(array);
        assertThat(array, is(new int[]{2,3,4,5,7}));
    }

    @Test
    public void test_sortOnEndTime() {
        int[][] array = new int[][]{ {0,40}, {30,60}, {20,30}, {60,80}, {10,50}, {30,40}, {40,60}, {50,100}, {50, 70}, {50, 60}};
        GreedyAlgorithms.sortOnEndTime(array);
        int[][] sorted = new int[][]{ {20,30}, {0,40}, {30,40}, {10,50}, {40,60}, {30,60}, {50,60}, {50,70}, {60,80}, {50,100}};
        assertThat(array, is(array));
    }

    @Test
    public void test_scheduler() {

        String input = "10\n0 40\n30 60\n20 30\n60 80\n10 50\n30 40\n40 60\n50 100\n50 70\n50 60\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        GreedyAlgorithms.scheduler();
        String[] print = out.toString().split("\n");
        assertThat(print.length, is(4));
    }

    @Test
    public void test_kruskalMST() {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        List<Edge> edgeList = new ArrayList();

        edgeList.add(new Edge(0, 1, 1));
        edgeList.add(new Edge(0, 2, 3));

        edgeList.add(new Edge(1, 2, 4));
        edgeList.add(new Edge(1, 4, 5));

        edgeList.add(new Edge(2, 3, 2));

        edgeList.add(new Edge(3, 1, 3));
        edgeList.add(new Edge(3, 4, 3));

        edgeList.add(new Edge(4, 5, 7));

        edgeList.add(new Edge(5, 6, 9));
        GreedyAlgorithms.kruskalMST(edgeList, 9);

        String[] print = out.toString().split("\n");
        assertThat(print.length, is(6));

    }
}
