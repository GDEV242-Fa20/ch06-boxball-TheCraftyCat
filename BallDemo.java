import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Catherine Oldfield
 * for RVCC GDEV242 - Fall 2020
 * from a program by Michael Kölling and David J. Barnes
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
     * The balls will bounce for two minutes before freezing in place
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
        drawBox(ground, ceiling, leftWall, rightWall);

        //create an ArrayList of BoxBalls:
        boxBallList = new ArrayList<BoxBall>();
        int index = 0;
        while(index < howMany)
        {
            //create a randomizer to aid ball creation
            Random ballCreator = new Random();
            
            // define parameters for the ball
            
            // ball size randomly between 10 and 60 pixels:
            int size = ballCreator.nextInt(51) + 10;
            
            // x and y start positions randomly within the box
            int xRange = rightWall - leftWall - size/2;
            int xStart = ballCreator.nextInt(xRange) + leftWall;
            int yRange = ground - ceiling - size/2;
            int yStart = ballCreator.nextInt(yRange) + ceiling;
            
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
            
            boxBallList.add(currentBall);
            index++;
        }
        
        // make the balls bounce
        long startTime = System.currentTimeMillis();
        long endTime;
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            // move each ball
            for(BoxBall ball : boxBallList)
            {
                //ball.draw(); // for testing ball positioning
                ball.move();
                
                // re-draw the box
                drawBox(ground, ceiling, leftWall, rightWall);
            }
            endTime = System.currentTimeMillis();
            long timeDifference = Math.subtractExact(endTime, startTime);
            // stop the simulation after 2 minutes
            if (timeDifference >= 120000)
            {
                finished = true;
            }
        }
    }
    
    /**
     * This is a helper method for drawing a box on the Canvas
     * @param bottom The y- coordinate of the bottom of the box
     * @param top The y-coordinate of the top of the box
     * @param left The x-coordinate of the left side of the box
     * @param right The x-coordinate of the right side of the box
     */
    private void drawBox(int bottom, int top, int left, int right)
    {
        // draw the box
        myCanvas.setForegroundColor(Color.BLACK);
        // ground:
        myCanvas.drawLine(left, bottom, right, bottom);
        // left wall:
        myCanvas.drawLine(left, bottom, left, top);
        // right wall:
        myCanvas.drawLine(right, bottom, right, top);
        // ceiling:
        myCanvas.drawLine(left, top, right, top);
    }
    
}
