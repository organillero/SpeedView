package io.ferreyra.speedview.SpeedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class TriangleIndicator {
    private Path indicatorPath = new Path();
    private float indicatorTop = 100f;

    protected Paint indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float density;
    private int indicatorColor = Color.BLACK;
    private float indicatorWidth;
    private float viewSize;
    private float speedometerWidth;
    private int padding;


    public TriangleIndicator(Context context) {
        init(context);
        updateCanvasData(null); //TODO fix me
    }

    private void init(Context context) {
        this.density = context.getResources().getDisplayMetrics().density;
        indicatorPaint.setColor(indicatorColor);
        indicatorWidth = getDefaultIndicatorWidth();
    }

    public float getTop(){
        return indicatorTop;
    }

    public float getBottom(){
        return indicatorTop + getIndicatorWidth();
    }

    public void draw(Canvas canvas, float degree){
        canvas.save();
        canvas.rotate(degree, getCenterX(), getCenterY());
        canvas.drawPath(indicatorPath, indicatorPaint);
        canvas.restore();
    }

    private float getCenterX() {
        return viewSize / 2f;
    }

    private float getCenterY() {
        return viewSize / 2f;
    }

    public float getSpeedometerWidth(){
        return speedometerWidth;
    }

    public float getPadding(){
        return padding;
    }

    public float getIndicatorWidth(){
        return indicatorWidth;
    }
    public int getIndicatorColor(){
        return indicatorColor;
    }

    public void updateCanvasData (View view){
        this.viewSize = dpToPx(300);
        this.speedometerWidth = 0 - 2 * getPadding(); //TODO get the width from the main view
        this.padding = 4 * 25; //TODO remove hardcoded value
        updateindicator();
    }

    private void updateindicator() {
        indicatorPath.reset();
        indicatorTop = getPadding() + getSpeedometerWidth() + dpToPx(5);
        indicatorPath.moveTo(getCenterX(), indicatorTop);

        indicatorPath.lineTo(getCenterX() - getIndicatorWidth(), indicatorTop + getIndicatorWidth());
        indicatorPath.lineTo(getCenterX() + getIndicatorWidth(), indicatorTop + getIndicatorWidth());
    }


    private float getDefaultIndicatorWidth(){
        return dpToPx(25f);
    }

    private float dpToPx(float dp){
        return dp * density;
    }

}
