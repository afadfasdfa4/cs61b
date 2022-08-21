public class ArrayDeque<T>{
    private T[] arr;
    private int size;
    public ArrayDeque(){
        arr = (T[])new Object[8];
        size = 0;
    }
    public void addFirst(T item){
        if(size+1>=arr.length){
            arr = resize_more(arr,size);
        }
        for(int i=size;i>=1;i--){
            arr[i] = arr[i-1];
        }
        arr[0] = item;
        size++;
    }
    private T[] resize_more(T[] arr,int size){
        T[] a = (T[]) new Object[(int)(arr.length*1.5)];
        for(int i=0;i<size;i++){
            a[i] = arr[i];
        }
        return a;
    }
    private T[] resize_less(T[] arr,int size){
        T[] a = (T[]) new Object[(int)(arr.length*0.5)];
        for(int i=0;i<size;i++){
            a[i] = arr[i];
        }
        return a;
    }
    public void addLast(T item){
        if(size+1>=arr.length){
            arr = resize_more(arr,size);
        }
        arr[size] = item;
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
            System.out.print(arr[i]+" ");
        }
    }
    public T removeLast(){
        if(size==0) return null;
        if(arr.length>=16&&(size-1)<=(arr.length/4)){
            resize_less(arr,size);
        }
        T a = arr[size-1];
        size--;
        return a;
    }
    public T removeFirst(){
        if(size==0) return null;
        if(arr.length>=16&&(size-1)<=(arr.length/4)){
            resize_less(arr,size);
        }
        T a = arr[0];
        for(int i=1;i<size;i++){
            arr[i-1] = arr[i];
        }
        size--;
        return a;

    }
    public T get(int index){
        if(index>=size) return null;
        return arr[index];
    }









}