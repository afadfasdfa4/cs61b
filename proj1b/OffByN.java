public class OffByN implements CharacterComparator{
    private static int n;

    public OffByN(int N){
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x-y) == n;
    }

}
