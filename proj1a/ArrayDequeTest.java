public class ArrayDequeTest {
    public void TestRemoveLast(){
        ArrayDeque arr = new ArrayDeque<>();
       /* arr.addFirst(1);
        arr.addFirst(2);
        arr.addFirst(3);
        arr.addFirst(4);
        arr.removeFirst();
        arr.removeLast();
        arr.addLast(5);*/
        for(int i=1;i<50;i++){
            arr.addFirst(i);
            arr.show();
            System.out.println();

        }
        for(int i=0;i<45;i++){
            arr.removeLast();
            arr.show();
            System.out.println();
        }

        arr.printDeque();
        System.out.println();


    }

    public static void main(String[] args) {
        new ArrayDequeTest().TestRemoveLast();
    }












}
