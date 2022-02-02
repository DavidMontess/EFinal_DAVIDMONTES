package com.idat.efinal.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.idat.efinal.application.FinalAplication;

public class ImagenRequest {
    private static ImagenRequest instance=null;
    private final Context context;
    private final RequestQueue requestQueue;
    private final ImageLoader imageLoader;
    private int maxByteSize;

    private ImagenRequest(){
        context= FinalAplication.getAppContext();
        this.requestQueue= Volley.newRequestQueue(context);
        this.requestQueue.start();
        this.maxByteSize= calculaMaxByteSize();
        this.imageLoader= new ImageLoader(
                requestQueue,
                new ImageLoader.ImageCache(){

                    private final LruCache<String, Bitmap> lruCache=
                            new LruCache<String, Bitmap>(maxByteSize) {
                                @Override
                                protected int sizeOf(String url, Bitmap bitmap) {
                                    return bitmap.getByteCount();
                                }

                            };
                    @Override
                    public Bitmap getBitmap(String url) {
                        return lruCache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        lruCache.put(url,bitmap);
                    }
                });
    }

    public static ImagenRequest getInstance(){
        if(instance==null){
            instance=new ImagenRequest();
        }
        return instance;
    }
    public void setImageFromUrl(NetworkImageView networkImageView, String url){
        networkImageView.setImageUrl(url,imageLoader);
    }

    private int calculaMaxByteSize(){
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        final int screenBytes=displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        return screenBytes * 3;
    }
}
