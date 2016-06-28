package com.inngeniun.a2dgame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Harol Higuera on 6/28/16.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;


    public GamePanel(Context ctn){
        super(ctn);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while (retry){
            try {
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
                retry = false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void update() {

    }
}
