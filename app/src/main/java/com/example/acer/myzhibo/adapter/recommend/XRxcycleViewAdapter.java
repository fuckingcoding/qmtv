package com.example.acer.myzhibo.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/11/24.
 * Eemil:635727195@qq.com
 */

public class XRxcycleViewAdapter extends RecyclerViewAdapterHelper<RecomBean.RoomBean> {
    private Context mContext;


    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public XRxcycleViewAdapter(Context context, List<RecomBean.RoomBean> list) {
        super(context, list);
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_xrecycleview_out, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RecomBean.RoomBean roomBean = mList.get(position);

        ViewHolder vh = (ViewHolder) holder;
        vh.text_name.setText(roomBean.getName());
        vh.img_chouchou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastHelper.showToast(mContext, "进入" + roomBean.getName());
            }
        });
        vh.adapter.reloadRecyclerView(roomBean.getList(), true);


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_name;
        private ImageView img_chouchou;
        private RecyclerView recycleview_in;


        InRecycleAdapter adapter;
        List<RecomBean.RoomBean.ListBean> mlist = new ArrayList<>();


        public ViewHolder(View itemView) {
            super(itemView);


            text_name = (TextView) itemView.findViewById(R.id.text_pindaomingzi);
            img_chouchou = (ImageView) itemView.findViewById(R.id.chouchou);
            recycleview_in = (RecyclerView) itemView.findViewById(R.id.recycleview_in);

            GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), 2);
            recycleview_in.setLayoutManager(layoutManager);

            adapter = new InRecycleAdapter(itemView.getContext(), mlist);
            recycleview_in.setAdapter(adapter);
        }


    }
}