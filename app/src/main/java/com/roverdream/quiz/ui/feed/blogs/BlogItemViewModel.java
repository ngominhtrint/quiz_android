package com.roverdream.quiz.ui.feed.blogs;

import android.databinding.ObservableField;

import com.roverdream.quiz.data.model.api.BlogResponse;

public class BlogItemViewModel {

    public final ObservableField<String> author;

    public final ObservableField<String> content;

    public final ObservableField<String> date;

    public final ObservableField<String> imageUrl;

    public final BlogItemViewModelListener mListener;

    public final ObservableField<String> title;

    private final BlogResponse.Blog mBlog;

    public BlogItemViewModel(BlogResponse.Blog mBlog, BlogItemViewModelListener mListener) {
        this.mBlog = mBlog;
        this.mListener = mListener;
        imageUrl = new ObservableField<>(mBlog.getCoverImgUrl());
        title = new ObservableField<>(mBlog.getTitle());
        author = new ObservableField<>(mBlog.getAuthor());
        date = new ObservableField<>(mBlog.getDate());
        content = new ObservableField<>(mBlog.getDescription());
    }

    public void onItemClick() {
        mListener.onItemClick(mBlog.getBlogUrl());
    }

    public interface BlogItemViewModelListener {
        void onItemClick(String blogUrl);
    }
}
