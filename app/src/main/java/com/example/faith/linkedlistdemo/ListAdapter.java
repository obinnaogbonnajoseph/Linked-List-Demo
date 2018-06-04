package com.example.faith.linkedlistdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    // Holds the list to display the list
    private LinkedList<String> mNames;
    private Context mContext;

    ListAdapter(Context context, LinkedList<String> names) {
        this.mContext = context;
        this.mNames = names;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        if(TextUtils.isEmpty(mNames.get(position))) {
            return;
        }
        String name = mNames.get(position);
        holder.nameTextView.setText(name);
        holder.namePosition.setText(String.valueOf(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public void updateList(LinkedList<String> mList) {
        mNames = mList;
        this.notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, namePosition;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            namePosition = itemView.findViewById(R.id.party_size_text_view);
        }
    }
}
