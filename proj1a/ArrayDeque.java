public class ArrayDeque<T>{
    private T[] arr;
    private int start;
    private int size;
    public ArrayDeque(){
        arr = (T[])new Object[8];
        start = 0;
        size = 0;
    }

    private T[] resize_more(T[] arr,int start,int size){
        T[] a = (T[]) new Object[(int)(arr.length*1.5)];
        for(int i=0;i<size;i++){
            int index = (start+i)%arr.length;
            a[i] = arr[index];
        }
        start = 0;
        return a;
    }
    private T[] resize_less(T[] arr,int start,int size){
        T[] a = (T[]) new Object[(int)(arr.length/2)];
        for(int i=0;i<size;i++){
            int index = (start+i)%arr.length;
            a[i] = arr[index];
        }
        start = 0;
        return a;
    }

    public void addFirst(T item){
        if(size+1>=arr.length){
            arr = resize_more(arr,start,size);
        }
        int index = start - 1;
        if(index<0) index += arr.length;
        arr[index] = item;
        size++;
    }

    public void addLast(T item){
        if(size+1>=arr.length){
            arr = resize_more(arr,start,size);
        }

        int index = (start + size)%arr.length;
        arr[index] = item;
        size++;
    }

    public boolean isEmpty(){
        if(size==0) return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(arr[(i+start)%arr.length]+" ");
        }
    }
    public T removeLast(){
        if(size==0) return null;
        if(arr.length>=16&&(size-1)<=(arr.length/4)){
            arr = resize_less(arr,start,size);
        }
        T a = arr[(start+size-1)%arr.length];
        size--;
        return a;
    }
    public T removeFirst(){
        if(size==0) return null;
        if(arr.length>=16&&(size-1)<=(arr.length/4)){
            arr = resize_less(arr,start,size);
        }
        T a = arr[start];
        start++;
        size--;
        return a;

    }
    public T get(int index){
        if(index>=size) return null;
        return arr[(start+index)%arr.length];
    }

}
