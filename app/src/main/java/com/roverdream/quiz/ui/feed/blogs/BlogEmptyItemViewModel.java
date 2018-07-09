package com.roverdream.quiz.ui.feed.blogs;

public class BlogEmptyItemViewModel {

    private BlogEmptyItemViewModelListener mListener;

    public BlogEmptyItemViewModel(BlogEmptyItemViewModelListener mListener) {
        this.mListener = mListener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface BlogEmptyItemViewModelListener {
        void onRetryClick();
    }
}
