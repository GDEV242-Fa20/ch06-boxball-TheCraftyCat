import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Catherine Oldfield
 * for RVCC GDEV242 - Fall 2020
 * from an program by Michael Kölling and David J. Barnes
 * @version 10-10-2020
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private ArrayList<BoxBall> boxBallList;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * This method simulates a random number of bouncing balls
     * @param howMany How many balls are in the box
     */
    public void boxBounce(int howMany)
    {
        myCanvas.setVisible(true);
        
        // define the box offsets:
        int ground = 400;
        int leftWall = 50;
        int rightWall = 550;
        int ceiling = 100;

        // draw the box
        myCanvas.setForegroundColor(Color.BLACK);
        // ground:
        myCanvas.drawLine(leftWall, ground, rightWall, ground);
        // left wall:
        myCanvas.drawLine(leftWall, ground, leftWall, ceiling);
        // right wall:
        myCanvas.drawLine(rightWall, ground, rightWall, ceiling);
        // ceiling:
        myCanvas.drawLine(leftWall, ceiling, rightWall, ceiling);
        
        //create an ArrayList of BoxBalls:
        boxBallList = new ArrayList<BoxBall>();
        int index = 0;
        while(index < howMany)
        {
            //create a randomizer to aid ball creation
            Random ballCreator = new Random();
            
            // define parameters for the ball
            
            // x and y start positions randomly within the box
            int xStart = ballCreator.nextInt(rightWall - leftWall + 1);
            int yStart = ballCreator.nextInt(ground - ceiling + 1);
            
            // ball size randomly between 20 and 80 pixels:
            int size = ballCreator.nextInt(61) + 20;
            
            // ball color randomly chosen (RGB values only allowed up to
            // 230, to account for visibility on white canvas)
            int rVal = ballCreator.nextInt(231);
            int gVal = ballCreator.nextInt(231);
            int bVal = ballCreator.nextInt(231);
            
            Color ballColor = new Color(rVal, gVal, bVal);
            
            // ball speeds randomly between 1 and 7:
            int horizontalSpeed = ballCreator.nextInt(7) + 1;
            int verticalSpeed = ballCreator.nextInt(7) + 1;
            
            BoxBall currentBall = new BoxBall(xStart, yStart, size, 
                ballColor, ground, ceiling, leftWall, rightWall, 
                myCanvas, horizontalSpeed, verticalSpeed);
            
            //boxBallList.add(currentBall);
            index++;
        }
        
        
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            // move each ball
            for(BoxBall ball : boxBallList)
            {
                ball.move();
            }
            // stop once ball has travelled a certain distance on x axis
            // if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                // finished = true;
            // }
        }
    }
    
}
