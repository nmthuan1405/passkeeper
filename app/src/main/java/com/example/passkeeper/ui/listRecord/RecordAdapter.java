package com.example.passkeeper.ui.listRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordField;
import com.example.passkeeper.databinding.ItemRecordBinding;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    final private LayoutInflater layoutInflater;
    private List<Record> mListRecord = null;
    private ItemRecordBinding binding;

    public RecordAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record currentRecord = mListRecord.get(position);
        String recordName = currentRecord.getRecordFieldValue("name");
        holder.binding.nameRecordTextView.setText(recordName==null ? "None":recordName);
        String recordType = currentRecord.getRecordType();
        String recordDescription = null;
        switch (recordType){
            case "password":
                recordDescription = currentRecord.getRecordFieldValue("username");
            break;
            case "card":
                recordDescription = currentRecord.getRecordFieldValue("cardholdername");
            break;
        }
        holder.binding.descriptionRecordTextView.setText(recordDescription==null ? "None":recordDescription);
        if (currentRecord.getIsFavorite()){
            holder.binding.favoriteToggle.setChecked(true);
        } else {
            holder.binding.favoriteToggle.setChecked(false);
        }
        switch (currentRecord.getRecordType()){
            case "password":
                holder.binding.iconTypeRecord.setImageResource(R.drawable.ic_password);
                break;
            case "card":
                holder.binding.iconTypeRecord.setImageResource(R.drawable.ic_card);
                break;
            case "note":
                holder.binding.iconTypeRecord.setImageResource(R.drawable.ic_note);
                break;
        }
        holder.binding.favoriteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            //TODO: Handle when changes favourite status of this item
            public void onClick(View view) {
                currentRecord.setIsFavorite(!currentRecord.getIsFavorite());
            }
        });
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

        private final ItemRecordBinding binding;

        public RecordViewHolder(@NonNull ItemRecordBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
