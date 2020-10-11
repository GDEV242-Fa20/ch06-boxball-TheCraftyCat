import java.awt.Color;
import java.util.ArrayList;

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

        // draw the box
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, 400, 550, 400);    // ground
        myCanvas.drawLine(50, 400, 50, 100);     // left wall
        myCanvas.drawLine(550, 400, 550, 100);   // right wall
        myCanvas.drawLine(50, 100, 550, 100);    // ceiling
        
        //create an ArrayList of BoxBalls:
        boxBallList = new ArrayList<BoxBall>();
        int index = 0;
        while(index < howMany)
        {
            //create the balls
            index++;
        }
        
        
        
    }
    
}
