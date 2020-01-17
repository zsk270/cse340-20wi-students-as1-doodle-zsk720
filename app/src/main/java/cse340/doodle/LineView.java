package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatImageView;

public class LineView extends AppCompatImageView {

    private PointF endPoint;

    private Paint brush;

    public LineView(Context context, float startX, float startY, float endX, float endY, int width, @ColorInt int color) {
        super(context);
        endPoint = new PointF(endX - startX, endY - startY);
        brush = new Paint();
        brush.setColor(color);
        brush.setStrokeWidth(width);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, endPoint.x, endPoint.y, brush);
    }
}
