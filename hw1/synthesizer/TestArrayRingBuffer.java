package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        assertEquals(5,arb.dequeue());
        assertEquals(10,arb.capacity());
        assertEquals(2,arb.fillCount());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        //jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
        ArrayRingBuffer<Integer> a = new ArrayRingBuffer<>(10);
        a.enqueue(5);
        a.enqueue(4);
        a.enqueue(3);
        a.enqueue(2);
        for(int e:a){
            System.out.println(e);
        }
    }
} 
