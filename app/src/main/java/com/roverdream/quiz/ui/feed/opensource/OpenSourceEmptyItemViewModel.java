package com.roverdream.quiz.ui.feed.opensource;

public class OpenSourceEmptyItemViewModel {

    private final OpenSourceEmptyItemViewModelListener mListener;

    public OpenSourceEmptyItemViewModel(OpenSourceEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface OpenSourceEmptyItemViewModelListener {
        void onRetryClick();
    }
}
