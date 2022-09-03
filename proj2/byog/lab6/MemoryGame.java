package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"🐂🐂🐂", "😊😊😊",
                                                   "👍👍👍(●'◡'●)", "🤦‍♂️🤦‍♂️", "🍾🍾☕☕",
                                                   "😘😘😒😒", "👴👴👴"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(1000, 500);
        game.rand.setSeed(seed);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random();
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder s = new StringBuilder();
        for(int i=0;i<n;i++){
            s.append(CHARACTERS[rand.nextInt(26)]);
        }
        return s.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.setCanvasSize(width,height);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
       // StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear();
        StdDraw.text(width/2,height/2,s);

        StdDraw.text(70,height-30,"Round: "+round);
        StdDraw.text(470,height-30,"注意看");
        StdDraw.text(870,height-30,ENCOURAGEMENT[(int)(Math.random()*7)]);
        StdDraw.text(500,height-50,"——————————————————————————————————");

        StdDraw.enableDoubleBuffering();
        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(int i=0;i<letters.length();i++){
            drawFrame(letters.charAt(i)+"");
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
        //playerTurn = true;
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder s = new StringBuilder();
        while (n>0){
            //if(!playerTurn&&StdDraw.hasNextKeyTyped()){
             //   StdDraw.nextKeyTyped();
           // }
            if(StdDraw.hasNextKeyTyped()){
                char cur = StdDraw.nextKeyTyped();
                s.append(cur);
                drawFrame(s.toString());
                n--;
            }
        }
        return s.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Game loop
        gameOver = false;
        playerTurn = false;
        round = 1;
        while (!gameOver){
            drawFrame("round "+round);
            StdDraw.pause(1000);
            String random_Str = generateRandomString(round);
            flashSequence(random_Str);
            String input = solicitNCharsInput(round);
            if (!input.equals(random_Str)) {
                    gameOver = true;
            } else {
                    drawFrame("牛 下一轮");
                    StdDraw.pause(2000);
            }
            round++;

            playerTurn = false;
        }
        int m = 0;
        if(round>=12) m = 0;
        else if(round>=9) m = 1;
        else if(round>=7) m = 2;
        else if(round>=5) m = 3;
        else if(round<5) m = 4;
        drawFrame("得分："+(round-1)+". \n "+"一眼丁真，鉴定为纯纯的低能");

    }

}
