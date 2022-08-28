import jdk.jshell.spi.SPIResolutionException;

public class LinkedListDeque<T> implements Deque<T>{
    private static class LinkedNode<T> {
        private T item;
        private LinkedNode next;
        private LinkedNode pre;
        public LinkedNode(T item,LinkedNode pre,LinkedNode next){
            this.item = item;
            this.next = next;
            this.pre = pre;
        }
        public LinkedNode(){
            item = null;
            next = null;
            pre = null;
        }
    }

    private LinkedNode sentinel;
    private int size;
    private LinkedNode last;
    public LinkedListDeque(){
        size = 0;
        sentinel = new LinkedNode<>();
        last = sentinel;
    }

    @Override
    public void addFirst(T item){
        LinkedNode a = new LinkedNode(item,sentinel,sentinel.next);
        sentinel.next = a;
        if(a.next!=null)a.next.pre = a;
        if(size==0) last = a;
        size++;
    }
    @Override
    public void addLast(T item){
        LinkedNode a = new LinkedNode(item,last,null);
        last = a;
        a.pre.next = a;
        size++;
    }

    @Override
    public boolean isEmpty(){
        if(size==0) return true;
        else return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        LinkedNode a = sentinel;
        while (a.next!=null){
            System.out.print(a.next.item+" ");
            a = a.next;
        }

    }
    @Override
    public T removeFirst(){
        if(size==0) return null;
        LinkedNode a = sentinel.next;
        sentinel.next = a.next;
        if(sentinel.next!=null) sentinel.next.pre = sentinel;
        size--;
        if(size==0) last = sentinel;
        return (T)a.item;
    }
    @Override
    public T removeLast(){
        if(size==0) return null;
        LinkedNode a = last;
        last = last.pre;
        last.next = null;
        size--;
        return (T)a.item;
    }

    @Override
    public T get(int index){
        LinkedNode a = sentinel;
        for(int i=0;i<=index;i++){
            if(a!=null) a = a.next;
            else return null;
        }
        return (T)a.item;

    }
    public T getRecursive(int index){
        LinkedNode a = getRecursive_help(index);
        if(a==null) return null;
        else return (T)a.item;

    }
    private LinkedNode getRecursive_help(int index){
        if(index==0){
            if(size==0) return null;
            return sentinel.next;
        }
        LinkedNode a = getRecursive_help(index-1);
        if(a==null) return null;
        return a.next;
    }

}