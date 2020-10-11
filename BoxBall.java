import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

/**
 * Write a description of class BoxBall here.
 *
 * @author Catherine Oldfield
 * for RVCC GDEV242 - Fall 2020
 * from an program by Michael Kölling and David J. Barnes
 * @version 10-10-2020
 */
public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;       // y position of ground
    private final int ceilingPosition;      // y position of ceiling
    private final int leftWallPosition;     // x position of left wall
    private final int rightWallPosition;    // x position of right wall
    private Canvas canvas;
    private int xSpeed;                   // initial horizontal speed
    private int ySpeed;                   // initial vertical speed

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int ceilingPos, int leftPos, 
                        int rightPos, Canvas drawingCanvas, int xSpd,
                        int ySpd)
    {
        // initialise instance variables
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        groundPosition = groundPos;
        ceilingPosition = ceilingPos;
        leftWallPosition = leftPos;
        rightWallPosition = rightPos;
        canvas = drawingCanvas;
        xSpeed = xSpd;
        ySpeed = ySpd;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
           
        // compute new position
        // effect of gravity
        ySpeed += GRAVITY;
        
        // motion is based on random distance moved in x and y for each
        // tick of the simulation. change in distance is +/- 7 on x and 
        // y axis, and cannot be either directly vertical or horizontal 
        // motion (can't have a zero value)
        Random speedModifier = new Random();
        yPosition += (speedModifier.nextInt(7) + 1);
        xPosition += (speedModifier.nextInt(7) + 1);

        // check if it has hit the ground, ceiling or wall & rebound
        if(yPosition >= (groundPosition))
        {
            yPosition = (int)(groundPosition);
            ySpeed = - ySpeed;
        }
        else if(yPosition <=(ceilingPosition))
        {
            yPosition = (int)(ceilingPosition);
            ySpeed = - ySpeed;
        }
        else if(xPosition >= (rightWallPosition - diameter))
        {
            xPosition = (int)(rightWallPosition - diameter);
            xSpeed = -xSpeed;
        }
        else if(xPosition <= (leftWallPosition))
        {
            xPosition = (int)(leftWallPosition);
            xSpeed = -xSpeed;
        }

        // draw again at new position
        draw();
    }  
    
    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    } 
}
