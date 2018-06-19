package io.ferreyra.speedview.SpeedView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class SpeedView extends View{

    private static final float SECTION_MARGIN_SIZE = 2F;
    private Paint arcPaint;
    private RectF arcBounds;
    private SpeedViewModel mSpeedViewModel;

    private TriangleIndicator indicator;

    private int DEGREE_OFFSET = 180;

    private int startDegree = DEGREE_OFFSET, endDegree = 180 + DEGREE_OFFSET;
    private float degree = startDegree;


    public SpeedView(Context context) {
        this(context, null);
    }

    public SpeedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpeedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void updateSpeedView (final SpeedViewModel speedViewModel){
        this.mSpeedViewModel = speedViewModel;
        degree = mSpeedViewModel.getindicatorPositionDegree();
        invalidate();

    }

    private void init(AttributeSet attrs) {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(15f);
        arcBounds = new RectF();

        indicator = new TriangleIndicator(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        final float startAngle = 180f;
//        final float endAngle = 360f;

        int width = canvas.getWidth();
        int paddingDp = getPadding();

        int gaugeWidth = width - paddingDp;
        int gaugeHeight = width - 2 * paddingDp;

        arcBounds.left = paddingDp;
        arcBounds.top = paddingDp;
        arcBounds.right = gaugeWidth;
        arcBounds.bottom = gaugeHeight;

        arcPaint.setColor(mSpeedViewModel.getLowColor());
        canvas.drawArc(arcBounds, DEGREE_OFFSET, mSpeedViewModel.getLowRangeDegree()  - SECTION_MARGIN_SIZE, false, arcPaint);

        arcPaint.setColor(mSpeedViewModel.getMediumColor());
        canvas.drawArc(arcBounds, DEGREE_OFFSET + mSpeedViewModel.getLowRangeDegree() ,mSpeedViewModel.getHighRangeDegree() - mSpeedViewModel.getLowRangeDegree() - SECTION_MARGIN_SIZE, false, arcPaint);

        arcPaint.setColor(mSpeedViewModel.getHighColor());
        canvas.drawArc(arcBounds, DEGREE_OFFSET + mSpeedViewModel.getHighRangeDegree()  , DEGREE_OFFSET - mSpeedViewModel.getHighRangeDegree() , false, arcPaint);

        drawIndicator(canvas);

    }

    protected void drawIndicator(Canvas canvas){
        indicator.draw(canvas, degree);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //indicator.updateCanvasData(this);
    }

    private int dpTpPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi) / DisplayMetrics.DENSITY_DEFAULT);
    }

    public int getSize() {
            return getWidth();
    }

    private int getPadding() {
        return dpTpPx(8); //TODO set this attribute on the XML file
    }
}
