package com.example.by.andriodtrain;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaRouter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * @author: by
 * @time: 2016/1/22.10:18
 */
class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    public final static String TAG="MySerfaceView";
    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder holder;
    private Thread thread;
    private boolean flag;
    private  float x,y;
    private  float speedx,speedy;
    private int radius;
    private int color;
    public MySurfaceView(Context context) {
        super(context);
        init();
    }
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init()
    {
        holder=getHolder();
        holder.addCallback(this);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        color=Color.YELLOW;
        radius=50;

    }
    private  void initGame()
    {
        x=0;
        y=0;
        speedx=10;
        speedy=20;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");
        initGame();
        flag=true;
        thread=new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG,"surfaceDestroyed");
        flag=false;
    }

    /**
     * 画图
     */
    private void myDraw(Canvas canvas)
    {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(x,y,100,paint);

    }

    /**
     * 逻辑函数
     */
    private void logic()
    {
        x+=speedx;
        y+=speedy;
        if(x>=getWidth()||x<0)
            speedx=-speedx;
        if(y>=getWidth()||y<0)
            speedy=-speedy;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        color = Color.argb(10, x % 155, y%255,100);
        radius=new Random().nextInt(10)+50;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while(flag)
        {   long start=System.currentTimeMillis();
            canvas=holder.lockCanvas();
            if(canvas!=null) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end=System.currentTimeMillis();
            if(end-start<50)
            {
                try {
                    thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
