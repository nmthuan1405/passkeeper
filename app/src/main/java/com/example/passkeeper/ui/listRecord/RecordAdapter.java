package com.example.passkeeper.ui.listRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    final private LayoutInflater layoutInflater;
    private List<Record> mListRecord = null;

    public RecordAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_record_card, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record currentRecord = mListRecord.get(position);
        //holder.titleRecord.setText("");
        //holder.titleRecord.setText("");
    }

    @Override
    public int getItemCount() {
        if (mListRecord != null)
            return mListRecord.size();
        return 0;
    }


    public void setListRecord(List<Record> listRecord) {
        if (listRecord != null) {
            mListRecord = listRecord;
            notifyDataSetChanged();
        }
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {

        private TextView titleRecord;
        private TextView descriptionRecord;
        private ImageView isLikeRecord;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            titleRecord = itemView.findViewById(R.id.name_record_text_view);
            descriptionRecord = itemView.findViewById(R.id.description_text_view);
            isLikeRecord = itemView.findViewById(R.id.icon);
        }
    }
}
