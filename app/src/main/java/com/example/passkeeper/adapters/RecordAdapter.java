package com.example.passkeeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.models.Record;
import com.example.passkeeper.utils.RecordDatabaseHelper;

import java.util.Collections;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    final static public String RECORD_OBJECT_DATA = "RECORD_OBJECT_DATA";

    final private LayoutInflater mLayoutInflater;
    private RecordDatabaseHelper mDatabaseHelper;
    private List<Record> mListRecord;

    public RecordAdapter(Context context) {
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public RecordAdapter(Context context, RecordDatabaseHelper databaseHelper) {
        this(context);
        this.mDatabaseHelper = databaseHelper;
    }

    public RecordAdapter(Context context, RecordDatabaseHelper databaseHelper, List<Record> listRecord) {
        this(context, databaseHelper);
        this.mListRecord = listRecord;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_record_note, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = mListRecord.get(position);
        if (record == null){
            return;
        }

        holder.mRecordName.setText(record.getmNameRecord());
    }

    @Override
    public int getItemCount() {
        return mListRecord == null ? 0: mListRecord.size();
    }

    private void setData(List<Record> listRecord){
        mListRecord = listRecord;
        notifyDataSetChanged();
    }

    public Record getRecordAt(int position) {
        return mListRecord.get(position);
    }

    public void moveAlarm(int fromPosition, int toPosition) {
        Collections.swap(mListRecord, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
//        databaseHelper.onUpdate(alarms.get(fromPosition));
//        databaseHelper.onUpdate(alarms.get(toPosition));
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {

        final private TextView mRecordName;
        final private ImageView mIsLikeLabel;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecordName = itemView.findViewById(R.id.name_record_text_view);
            mIsLikeLabel = itemView.findViewById(R.id.isLike_label);
        }
    }
}
