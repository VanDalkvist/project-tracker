package com.orden.phoenix.tracker.presentation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.Button;

import com.orden.phoenix.tracker.R;

/**
 * Created on 5/17/2014.
 */
public class TriangleView extends Button {
    private static final int DEFAULT_ROTATION = 0;
    private static final int DEFAULT_COLOR = Color.DKGRAY;
    private static final int DEFAULT_SIZE = 70;
    private Paint trianglePaint;
    private Path trianglePath;
    private int rotation = DEFAULT_ROTATION;
    private int size = DEFAULT_SIZE;

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int color = DEFAULT_COLOR;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TriangleView, 0, 0);
        if(a!= null) {
            rotation = a.getInt(R.styleable.TriangleView_direction, DEFAULT_ROTATION);
            color = a.getColor(R.styleable.TriangleView_color, DEFAULT_COLOR);
            size = a.getDimensionPixelSize(R.styleable.TriangleView_size, DEFAULT_SIZE);
            a.recycle();
        }

        trianglePaint = new Paint();
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setColor(color);
        trianglePath = getEquilateralTriangle(size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //avoid creating objects in onDraw
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(-rotation, size / 2, size / 2);
        canvas.drawPath(trianglePath, trianglePaint);
        canvas.restore();
    }

    private static Path getEquilateralTriangle(int width) {
        // width passed instead of triangle height to make the figure rectangle
        Point p1 = new Point(0, width);
        Point p2 = new Point(p1.x + width, p1.y);
        Point p3 = new Point(p1.x + (width / 2), p1.y + (int)Math.round(Math.cos(60)*width));
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);

        return path;
    }

    public void setDirection(Direction direction) {
        rotation = direction.rotation;
    }

    public enum Direction {
        NORTH(0),
        SOUTH(180),
        EAST(-90),
        WEST(90);

        private int rotation;

        private Direction(int rotation) {
            this.rotation = rotation;
        }
    }
}
