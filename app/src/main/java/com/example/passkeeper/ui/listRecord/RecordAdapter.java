package com.example.passkeeper.ui.listRecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passkeeper.R;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.ItemRecordBinding;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private List<Record> listRecord = null;
    private List<Record> storedListRecord = null;
    private String currentKeyword = "";
    private OnCheckedListener onFavoriteCheckedListener;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemLongClickListener;

    public interface OnCheckedListener {
        void onChecked(Record record, boolean isChecked);
    }
    public interface OnItemClickListener {
        void onItemClick(Record record);
    }

    @NonNull
    @Override
    public RecordAdapter.RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecordBinding binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Record record = listRecord.get(position);

        holder.binding.favoriteToggle.setOnClickListener(view -> {
            boolean isChecked = holder.binding.favoriteToggle.isChecked();
            onFavoriteCheckedListener.onChecked(record, isChecked);
        });
        holder.itemView.setOnClickListener(view -> onItemClickListener.onItemClick(record));
        holder.itemView.setOnLongClickListener(view -> {
            onItemLongClickListener.onItemClick(record);
            return true;
        });

        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        if (listRecord != null)
            return listRecord.size();
        return 0;
    }

    public void setListRecord(List<Record> listRecord) {
        if (listRecord != null) {
            this.storedListRecord = listRecord;
            applyFilter();
        }
    }

    public void editRecord(Record originRecord, Record record) {
        if (originRecord != null && record != null) {
            int position = listRecord.indexOf(originRecord);
            listRecord.set(position, record);
            notifyItemChanged(position, record);
        }
    }

    public void deleteRecord(Record originRecord) {
        int position = listRecord.indexOf(originRecord);
        listRecord.remove(position);
        notifyItemRemoved(position);
    }

    public void setFilter(String keyword) {
        currentKeyword = keyword.toLowerCase();
        applyFilter();
    }

    public void applyFilter() {
        if (currentKeyword.equals("")) {
            listRecord = storedListRecord;
        } else {
            listRecord = new ArrayList<>();
            for (Record record : storedListRecord) {
                String name = record.getFieldValue("name");
                if (name != null && name.toLowerCase().contains(currentKeyword)) {
                    listRecord.add(record);
                }
            }
        }
        notifyDataSetChanged();
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

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public final ItemRecordBinding binding;

        public RecordViewHolder(@NonNull ItemRecordBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }

        public void bind(Record record) {
            String name = record.getFieldValue("name");
            if (name != null) {
                binding.nameRecordTextView.setText(name);
            } else {
                binding.nameRecordTextView.setText("");
            }

            String description = record.getDescription();
            if (description != null) {
                binding.descriptionRecordTextView.setText(description);
                binding.descriptionRecordTextView.setVisibility(View.VISIBLE);
            } else {
                binding.descriptionRecordTextView.setVisibility(View.GONE);
            }

            Integer icon = getIconId(record.getType());
            if (icon != null) {
                binding.iconTypeRecord.setImageResource(icon);
            }

            binding.favoriteToggle.setChecked(record.isFavorite());
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
    }
}