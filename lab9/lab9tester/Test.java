package lab9tester;

import lab9.BSTMap;

public class Test {

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        System.out.println(bstmap.get("cat"));
    }
}
