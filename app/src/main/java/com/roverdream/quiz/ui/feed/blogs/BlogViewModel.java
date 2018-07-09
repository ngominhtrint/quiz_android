package com.roverdream.quiz.ui.feed.blogs;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.data.model.api.BlogResponse;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import java.util.List;

public class BlogViewModel extends BaseViewModel<BlogNavigator> {

    public final ObservableList<BlogResponse.Blog> blogObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<BlogResponse.Blog>> blogListLiveData;

    public BlogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        blogListLiveData = new MutableLiveData<>();
        fetchBlogs();
    }

    public void addBlogItemsToList(List<BlogResponse.Blog> blogs) {
        blogObservableArrayList.clear();
        blogObservableArrayList.addAll(blogs);
    }

    public void fetchBlogs() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null && blogResponse.getData() != null) {
                        blogListLiveData.setValue(blogResponse.getData());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public ObservableList<BlogResponse.Blog> getBlogObservableArrayList() {
        return blogObservableArrayList;
    }

    public MutableLiveData<List<BlogResponse.Blog>> getBlogListLiveData() {
        return blogListLiveData;
    }
}
