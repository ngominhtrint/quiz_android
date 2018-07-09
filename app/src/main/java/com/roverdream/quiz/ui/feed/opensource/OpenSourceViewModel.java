package com.roverdream.quiz.ui.feed.opensource;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.roverdream.quiz.data.DataManager;
import com.roverdream.quiz.data.model.api.OpenSourceResponse;
import com.roverdream.quiz.ui.base.BaseViewModel;
import com.roverdream.quiz.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class OpenSourceViewModel extends BaseViewModel<OpenSourceNavigator> {

    private final ObservableList<OpenSourceItemViewModel> openSourceItemViewModels = new ObservableArrayList<>();

    private final MutableLiveData<List<OpenSourceItemViewModel>> openSourceItemsLiveData;

    public OpenSourceViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        openSourceItemsLiveData = new MutableLiveData<>();
        fetchRepos();
    }

    public void addOpenSourceItemsToList(List<OpenSourceItemViewModel> openSourceItems) {
        openSourceItemViewModels.clear();
        openSourceItemViewModels.addAll(openSourceItems);
    }

    public void fetchRepos() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOpenSourceApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(openSourceResponse -> {
                    if (openSourceResponse != null && openSourceResponse.getData() != null) {
                        openSourceItemsLiveData.setValue(getViewModelList(openSourceResponse.getData()));
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public ObservableList<OpenSourceItemViewModel> getOpenSourceItemViewModels() {
        return openSourceItemViewModels;
    }

    public MutableLiveData<List<OpenSourceItemViewModel>> getOpenSourceRepos() {
        return openSourceItemsLiveData;
    }

    public List<OpenSourceItemViewModel> getViewModelList(List<OpenSourceResponse.Repo> repoList) {
        List<OpenSourceItemViewModel> openSourceItemViewModels = new ArrayList<>();
        for (OpenSourceResponse.Repo repo : repoList) {
            openSourceItemViewModels.add(new OpenSourceItemViewModel(
                    repo.getCoverImgUrl(), repo.getTitle(),
                    repo.getDescription(), repo.getProjectUrl()
            ));
        }
        return openSourceItemViewModels;
    }
}
