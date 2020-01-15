package cse340.doodle;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Part1Activity extends Part1 {
    @Override
    public void doodle(FrameLayout mainCanvas) {
        // Adds all images as a heart collage.
        addAllImagesFromData(mainCanvas);

        // To allow this to run on multiple screen sizes, we scale all of our coordinate values
        // using scaleX and scaleY. For a more efficient way to do this, see EX2 Layout.

        // Adds "I" and "UW" text.
        addText(mainCanvas,"I", scaleX(50), scaleY(50), 50,
                Color.rgb(145,123,76));
        TextView uw = addText(mainCanvas,"UW", scaleX(50), scaleY(1650), 50,
                Color.rgb(51,0,111));

        // Adds a line under "UW".
        addLine(mainCanvas, 0, scaleY(1950), scaleX(1440), scaleY(1950), 5,
                Color.rgb(145,123,76));

        // TODO: Do your animation with the UW text view here! It's stored in the "uw" variable.
        Path path = new Path();
        path.moveTo(scaleX(50f), scaleY(1650f));
        path.lineTo(scaleX(1050f), scaleY(1650f));
        ObjectAnimator animator = ObjectAnimator.ofFloat(uw, TextView.X, TextView.Y, path);
        animator.setDuration(1000);
        animator.start();
    }
}
