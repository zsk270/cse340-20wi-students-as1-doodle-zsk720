package cse340.doodle;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class describes the requirements of a Doodler (addLine, addText, addImage) and
 * provides some convenience methods such as scaleX, scaleY, and PHONE_DIMS. I'd recommend
 * that you take a look through this class.
 *
 * @author rfrowe
 * @version 1.0
 */
abstract public class Doodler extends AppCompatActivity {
    /**
     * Current phone dimensions, for your convenience.
     * Display width is stored in PHONE_DIMS.x and height in PHONE_DIMS.y.
     */
    protected static final Point PHONE_DIMS = new Point();

    /** Dimensions of the Pixel 2 XL. Private because you should not need these. */
    private static final Point PIXEL_DIMS = new Point(1440, 2712);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindowManager().getDefaultDisplay().getSize(PHONE_DIMS);

        // Sets the UI layout to this activity.
        setContentView(R.layout.activity_main);

        // Finds canvas from UI layout to add content onto it.
        FrameLayout mainCanvas = findViewById(R.id.canvas);

        // Calls the doodle function to draw on mainCanvas.
        doodle(mainCanvas);
    }

    /**
     * This is the function that does the actual doodling! It gets a main canvas and can then
     * call addLine, addImage, and addText to doodle on the mainCanvas.
     *
     * @param mainCanvas    Canvas on which to doodle.
     */
    public abstract void doodle(FrameLayout mainCanvas);

    /**
     * Adds an image to the main canvas with the given position and size.
     *
     * @param mainCanvas    Canvas to put new image in.
     * @param imageName Filename for image in 'res/drawable'.
     * @param x Horizontal location from top left in px.
     * @param y Vertical location from top left px.
     * @param size Width and height of image on-screen in ox.
     * @return ImageView which has been added to mainCanvas.
     */
    public abstract ImageView addImage(FrameLayout mainCanvas, String imageName, float x, float y, int size);

    /**
     * Adds text to the main canvas with the given position, size, and color.
     *
     * @param mainCanvas    Canvas to put new image in.
     * @param text  Text to display on-screen.
     * @param x Horizontal location from top left in px.
     * @param y Vertical location from top left px.
     * @param fontSize  Scaled pixel size for text.
     * @param color Color for text in RGB.
     * @return TextView which has been added to mainCanvas.
     */
    public abstract TextView addText(FrameLayout mainCanvas, String text, float x, float y, int fontSize,
                            int color);

    /**
     * Adds text to the main canvas with the given position, size, and color.
     *
     * @param mainCanvas    Canvas to put new image in.
     * @param startX    Starting x coordinate for line.
     * @param startY    Ending x coordinate for line.
     * @param endX  Ending x coordinate for line.
     * @param endY  Ending y coordinate for line.
     * @param width Stroke width for line.
     * @param color Color for text in RGB.
     * @return ImageView which has been added to mainCanvas.
     */
    public abstract ImageView addLine(FrameLayout mainCanvas, float startX, float startY, float endX,
                               float endY, int width, int color);

    /**
     * Scales a y coordinate from Pixel 2 XL to any phone.
     *
     * @param y Original coordinate.
     * @return Coordinate scaled to the current device screen size.
     */
    public float scaleY(float y) {
        return y * PHONE_DIMS.y / PIXEL_DIMS.y;
    }

    /**
     * Scales an x coordinate from Pixel 2 XL to any phone.
     *
     * @param x Original coordinate.
     * @return Coordinate scaled to the current device screen size.
     */
    public float scaleX(float x) {
        return x * PHONE_DIMS.x / PIXEL_DIMS.x;
    }

    public void addAllImagesFromData(FrameLayout mainCanvas) {
        try {
            Scanner scan = new Scanner(new InputStreamReader(getAssets().open("data.csv")));
            scan.useDelimiter("[,\n]");
            while (scan.hasNextLine()) {
                addImage(mainCanvas, scan.next(), scaleX(scan.nextFloat()), scaleY(scan.nextFloat()),
                        (int) scaleX(scan.nextInt()));
            }
        } catch (IOException e) {
            throw new IllegalStateException("data.csv not found in assets");
        } catch (InputMismatchException e) {
            throw new IllegalStateException("data.csv is malformed");
        }
    }
}
