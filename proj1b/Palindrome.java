public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> a = new LinkedListDeque();
        char[] arr = word.toCharArray();
        for(int i=0;i<word.length();i++){
            a.addLast(arr[i]);
        }
        return a;
    }
    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        if(d.size()==1||d.size()==0) return true;
        if(d.size()%2==0){
            while (!d.isEmpty()){
                char a = d.removeFirst();
                char b = d.removeLast();
                if(a!=b) return false;
            }
        }else {
            while (d.size()>1){
                char a = d.removeFirst();
                char b = d.removeLast();
                if(a!=b) return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        if(d.size()==1||d.size()==0) return true;
        if(d.size()%2==0){
            while (!d.isEmpty()){
                char a = d.removeFirst();
                char b = d.removeLast();
                if(!cc.equalChars(a,b)) return false;
            }
        }else {
            while (d.size()>1){
                char a = d.removeFirst();
                char b = d.removeLast();
                if(!cc.equalChars(a,b)) return false;
            }
        }
        return true;

    }
}
