package com.example.by.andriodtrain;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: by
 * @time: 2016/1/22.8:21
 */
public class MyView extends View {
    private Paint paint;
    private  int x,y;
    public MyView(Context context) {
        super(context);
        init();
    }
    /*
    初始化
     */
    private void init() {
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        x=100;
        y=100;

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        drawtest(canvas);
//        drawPath(canvas);
//        drawBitmap(canvas);
        paint.setStrokeWidth(15);
        canvas.drawLine(0,0,x,y,paint);

    }
    private  void drawtest(Canvas canvas)
    {
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(15);
//        canvas.drawLine(0, 0, x, y, paint);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.GREEN);
//        canvas.drawCircle(200, 100, 100, paint);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(100, 500, 300, 600, paint);
//        canvas.drawRoundRect(100,700,200,800,10,10,paint);

    }
    /*
    路径绘制
     */
    private void drawPath(Canvas canvas)
    {
        Path path=new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 300);
        path.lineTo(200, 250);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        Path path1=new Path();
        path1.addCircle(500, 500, 200, Path.Direction.CW);
        path1.addCircle(500, 500, 180, Path.Direction.CCW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);
        //paint.setColor(Color.GREEN);
        //paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path1,paint);

    }

    /**
     * 
     * @param canvas
     */
    private void drawBitmap(Canvas canvas)
    {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        //canvas.drawBitmap(bitmap,500,500,paint);

        canvas.drawLine(0, 0, x, y, paint);
        canvas.translate(x / 2, y / 2);
        canvas.drawLine(0,-y/2,0,y/2,paint);
        canvas.drawLine(-x / 2, 0, x / 2, 0, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.save();
        canvas.rotate(90);
        canvas.drawBitmap(bitmap,0,0, paint);
        canvas.restore();
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=(int)event.getX();
        y=(int)event.getY();
        invalidate();
        return super.onTouchEvent(event);
    }
}
