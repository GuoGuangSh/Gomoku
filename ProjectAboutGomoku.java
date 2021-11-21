import java.awt.*;

public class ProjectAboutGomoku {
    public static void main(String[] args) {
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        int boardSize = 15;

        formBoard(15);
        StdDraw.show();
        int[][] cross = new int[16][16];
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                cross[i][j] = 0;
            }
        }
        int color = 2;
        int judgment;
        int switcher;
        while ( true ) {
            switcher = 0;
            do {
                while (StdDraw.isMousePressed()) {
                    //黑白交替落子
                    if ( CanInput(cross[(int) Math.round(StdDraw.mouseY())][(int) Math.round(StdDraw.mouseX())]) ) {
                        cross[(int) Math.round(StdDraw.mouseY())][(int) Math.round(StdDraw.mouseX())] = color;
                        if ( color==1 ) {color++;}
                        else if ( color==2 ) {color--;}
                    }

                    for (int i = 1; i < 16; i++) {
                        for (int j = 1; j < 16; j++) {

                            if (cross[i][j] == 1)
                            {
                                StdDraw.setPenColor(Color.WHITE);
                                StdDraw.filledCircle( j , i, 0.4 );
                                StdDraw.show();

                            }
                            if (cross[i][j] == 2)
                            {
                                StdDraw.setPenColor(Color.BLACK);
                                StdDraw.filledCircle( j , i, 0.4 );
                                StdDraw.show();

                            }
                            judgment = checkVictory( j, i, 1, cross, 15 );
                            if ( judgment == 1 ) {
                                StdDraw.clear();
                                System.out.println( "GAME OVER. WHITE WINS." );
                                System.exit(0);
                            }
                            judgment = checkVictory( j, i, 2, cross, 15 );
                            if ( judgment == 2 ) {
                                StdDraw.clear();
                                System.out.println( "GAME OVER. BLACK WINS." );
                                System.exit(0);
                            }
                        }
                    }
                    switcher = 1;
                }
            }while ( switcher == 1 );
        }
    }
    public static void formBoard ( int boardSize ) {
        StdDraw.setScale( 0, boardSize+1 );
        StdDraw.clear();
        StdDraw.setPenColor( 120, 120, 120 );
        StdDraw.filledSquare( (boardSize+1)/2.0, (boardSize+1)/2.0, (boardSize+1)/2.0 );
        StdDraw.setPenColor( StdDraw.BLACK );
        for (int i = 0 ; i < boardSize+1; i++) {
            StdDraw.line(i, boardSize+1, i, 0);
            StdDraw.line(boardSize+1, i, 0, i);
        }
    }
    public static boolean CanInput(int cross) {
        if(cross==0) return true;
        else return false;
    }
    public static int checkVictory(int x,int y,int chess, int[][] cross, int boardSize ) {
        //横向判断
        int trans = 0;
        for(int i = x-4; i < x+5 ; i++) {
            if(i < 0 || i >= boardSize+1) continue;
            if(cross[i][y] == chess) {
                trans++;
            }
            else {
                trans = 0;
            }
            if(trans == 5) return chess;
        }
        //纵向判断
        int longi = 0;
        for(int i = y-4 ; i < y+5 ; i++) {
            if(i < 0 || i >= boardSize+1) continue;
            if(cross[x][i] == chess) {
                longi++;
            }
            else {
                longi=0;
            }
            if(longi == 5) return chess;
        }
        //从左上到右下
        int leftUPToDown = 0;
        for(int i = x-4, j = y+4 ; i < x+5 && j > y-5 ; i++, j--) {
            if(i < 0 || i >= boardSize+1 || j < 0 || j >= boardSize+1) continue;
            if(cross[i][j] == chess) {
                leftUPToDown++;
            }else {
                leftUPToDown=0;
            }
            if(leftUPToDown == 5) return chess;
        }
        //从左下到右上
        int leftDownToUP = 0;
        for(int i = x+4, j = y+4;i > x-5 && j > y-5; i--, j--) {
            if(i < 0 || i >= boardSize+1 || j < 0 || j >= boardSize+1) continue;
            if(cross[i][j] == chess) {
                leftDownToUP++;
            }else {
                leftDownToUP=0;
            }
            if(leftDownToUP == 5) return chess;
        }
        return 0;
    }
}
