package com.emv.myapplication.threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.emv.myapplication.dto.Result;

import java.io.IOException;
import java.net.URL;

/**
 * Created by g on 26/03/17.
 */

public class LoadImage extends AsyncTask<Void, Integer, Void> {
    private LoadImageCallBack loadImageCallBack;
    private Result result;

    public LoadImage(Result result, LoadImageCallBack loadImageCallBack) {
        this.result = result;
        this.loadImageCallBack = loadImageCallBack;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            loadImageCallBack.getImage(BitmapFactory.decodeStream(
                    new URL(result.getArtworkUrl100()).openConnection().getInputStream()));
        } catch (IOException e) {
            Log.e(LoadImage.class.getSimpleName(), "error", e);
        }
        return null;
    }

    public interface LoadImageCallBack {
        void getImage(Bitmap bitmap);
    }
}
