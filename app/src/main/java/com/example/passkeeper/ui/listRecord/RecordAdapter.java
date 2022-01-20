package com.example.passkeeper.ui.listRecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.ItemRecordBinding;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Record> mListRecord = null;
    private OnCheckedListener onFavoriteCheckedListener;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemLongClickListener;

    public interface OnCheckedListener {
        void onChecked(RecordViewHolder holder, Record record, boolean isChecked);
    }
    public interface OnItemClickListener {
        void onItemClick(RecordViewHolder holder, Record record);
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecordBinding binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = mListRecord.get(position);

        String name = record.getFieldValue("name");
        if (name != null) {
            holder.binding.nameRecordTextView.setText(name);
        }

        String description = record.getDescription();
        if (description != null) {
            holder.binding.descriptionRecordTextView.setText(description);
        } else {
            holder.binding.descriptionRecordTextView.setVisibility(View.GONE);
        }

        Integer icon = getIconId(record.getType());
        if (icon != null) {
            holder.binding.iconTypeRecord.setImageResource(icon);
        }

        holder.binding.favoriteToggle.setChecked(record.isFavorite());

        holder.binding.favoriteToggle.setOnCheckedChangeListener((compoundButton, b) -> onFavoriteCheckedListener.onChecked(holder, record, b));
        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(holder, record));
        holder.itemView.setOnLongClickListener(view -> {
            onItemLongClickListener.onItemClick(holder, record);
            return false;
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

    public void setOnFavoriteCheckedListener(OnCheckedListener onFavoriteClickListener) {
        this.onFavoriteCheckedListener = onFavoriteClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    private Integer getIconId(String type) {
        switch (type) {
            case "password":
                return R.drawable.ic_password;
            case "card":
                return R.drawable.ic_card;
            case "note":
                return R.drawable.ic_note;
            default:
                return null;
        }
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public final ItemRecordBinding binding;

        public RecordViewHolder(@NonNull ItemRecordBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
