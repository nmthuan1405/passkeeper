package com.example.passkeeper.ui.listRecord;

import com.example.passkeeper.data.model.Record;

public class ListFavRecordFragment extends ListRecordFragment {
    @Override
    protected void onFavoriteStatusChangeSuccess(Record originRecord, Record record) {
        if (!record.isFavorite()) {
            mAdapter.deleteRecord(originRecord);
        } else {
            super.onFavoriteStatusChangeSuccess(originRecord, record);
        }
    }

    @Override
    protected String getType() {
        return mViewModel.FAV;
    }
}
