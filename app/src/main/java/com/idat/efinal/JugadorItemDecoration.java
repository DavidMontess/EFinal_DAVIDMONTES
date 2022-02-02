package com.idat.efinal;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class JugadorItemDecoration extends RecyclerView.ItemDecoration{
    private int largePadding;
    private int smallPadding;

    public JugadorItemDecoration(int LargePadding, int smallPadding){
        this.largePadding= LargePadding;
        this.smallPadding= smallPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        outRect.left=smallPadding;
        outRect.right=smallPadding;
        outRect.top=largePadding;
        outRect.bottom=largePadding;
    }
}
