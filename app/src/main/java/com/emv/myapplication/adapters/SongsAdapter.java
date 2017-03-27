package com.emv.myapplication.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emv.myapplication.R;
import com.emv.myapplication.dto.Result;
import com.emv.myapplication.threads.LoadImage;

import java.util.List;

/**
 * Created by g on 26/03/17.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {

    private View.OnClickListener onClickListener;
    private List<Result> list;

    public SongsAdapter(List<Result> list, View.OnClickListener onClickListener) {
        this.list = list;
        this.onClickListener = onClickListener;
    }

    @Override
    public SongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SongsViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SongsViewHolder extends RecyclerView.ViewHolder {

        public void setData(Result data) {
            ((TextView) itemView.findViewById(R.id.tvTrack)).setText(data.getTrackName());
            ((TextView) itemView.findViewById(R.id.tvArtist)).setText(data.getArtistName());
            new LoadImage(data, new LoadImage.LoadImageCallBack() {
                @Override
                public void getImage(final Bitmap bitmap) {
                    ((Activity) itemView.getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((ImageView) itemView.findViewById(R.id.ivThumbnail)).setImageBitmap(bitmap);
                        }
                    });
                }
            }).execute();
        }

        public SongsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
