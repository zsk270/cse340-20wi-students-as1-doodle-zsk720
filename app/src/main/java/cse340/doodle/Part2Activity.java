package cse340.doodle;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Part2Activity extends Part1 {
    @Override
    public void doodle(FrameLayout mainCanvas) {
        // Create images
        float startY = 0f;
        float endY = 3000f;
        String[] foodNames = {"food_0", "food_1", "food_2", "food_3", "food_4", "food_5", "food_6",
                              "food_7", "food_8", "food_9", "food_10", "food_11", "food_12"};
        float[][] startPos = {{0f, startY}, {113f, startY}, {234f, startY}, {301f, startY},
                              {438f, startY}, {589f, startY}, {644f, startY}, {745f, startY},
                              {877f, startY}, {923f, startY}, {1023f, startY}, {1100, startY},
                              {1187, startY}};
        float[][] endPos = {{23f, endY}, {89f, endY}, {200f, endY}, {345f, endY},
                            {487f, endY}, {555f, endY}, {534f, endY}, {700f, endY}, {900f, endY},
                            {968f, endY}, {1050f, endY}, {1165f, endY}, {1224f, endY}};
        int[] timing = {1234, 4578, 7274, 5213, 2384, 9218, 6213, 3293, 7239, 3471, 6123, 1843, 7777};
        List<ImageView> imageList = new ArrayList<>();

        // Add images
        for (int i = 0; i < foodNames.length; i++) {
            imageList.add(addImage(mainCanvas, foodNames[i], scaleX(startPos[i][0]), scaleY(startPos[i][1]), 100));
        }
        // Generate paths
        List<Path> pathList = new ArrayList<>();
        for (int i = 0; i < foodNames.length; i++) {
            Path path = new Path();
            path.moveTo(scaleX(startPos[i][0]), scaleY(startPos[i][1]));
            path.lineTo(scaleX(endPos[i][0]), scaleY(endPos[i][1]));
            pathList.add(path);
        }
        // Run animations
        for (int i = 0; i < imageList.size(); i++) {
            moveImage(imageList.get(i), pathList.get(i), timing[i]);
        }

        // Add text
        addText(mainCanvas, "Food Rain", scaleX(250), scaleY(1200), 50, Color.rgb(45, 134, 55));

        // Add line
        addLine(mainCanvas, scaleX(250), scaleY(1450), scaleX(1275), scaleY(1450), 30, Color.rgb(23, 45, 176));
    }

    // Moves a target via a path
    private void moveImage(View target, Path path, int duration) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, View.X, View.Y, path);
        animator.setDuration(duration);
        animator.start();
    }
}
