package cse340.doodle;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is an implementation of Doodler. Here, you will implement addImage, addText, and addLine.
 *
 * Any class can extend Part1 and will be able to use these methods. Part1Activity, which is
 * implemented for you (except for the animation) is an example of this.
 *
 * For Part2Activity, you will have to extend Part1 but implement the doodle function yourself.
 *
 * @author rfrowe
 * @version 1.0
 */
public abstract class Part1 extends Doodler {
    @Override
    public ImageView addImage(FrameLayout mainCanvas, String imageName, float x, float y, int size) {
        // Creates ImageView and add it to canvas.
        ImageView imageView = new ImageView(this);
        mainCanvas.addView(imageView);
        /*
        TODO:
            Set the image size and location,
            and use the imageName parameter to set the image content.
         */
        imageView.getLayoutParams().height = size;
        imageView.getLayoutParams().width = size;
        imageView.setX(x);
        imageView.setY(y);
        // Note here that images have numerical IDs, which is the resID here.
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(resID);

        return imageView;
    }

    @Override
    public TextView addText(FrameLayout mainCanvas, String text, float x, float y, int fontSize,
                            int color) {
        /*
        TODO:
            Create the TextView and add it to the canvas.
            Set size, location, and color of the text.
            Lastly, modify the return statement to return the new TextView.
         */
        TextView textView = new TextView(this);
        mainCanvas.addView(textView);
        textView.setText(text);
        textView.setTextColor(color);
        textView.setTextSize(fontSize);
        textView.setX(x);
        textView.setY(y);

        return textView;
    }

    @Override
    public LineView addLine(FrameLayout mainCanvas, float startX, float startY, float endX, float endY,
                      int width, int color) {
        LineView line = new LineView(this, startX, startY, endX, endY, width, color);
        mainCanvas.addView(line);
        line.setX(startX);
        line.setY(startY);
        /*
        TODO:
            Create a line and add it to the canvas.
         */
        return line;
    }
}
