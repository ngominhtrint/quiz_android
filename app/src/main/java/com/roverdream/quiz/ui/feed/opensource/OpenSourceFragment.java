package com.roverdream.quiz.ui.feed.opensource;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.roverdream.quiz.R;
import com.roverdream.quiz.databinding.FragmentOpenSourceBinding;
import com.roverdream.quiz.ui.base.BaseFragment;

import javax.inject.Inject;

public class OpenSourceFragment extends BaseFragment<FragmentOpenSourceBinding, OpenSourceViewModel>
        implements OpenSourceNavigator, OpenSourceAdapter.OpenSourceAdapterListener {

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    OpenSourceAdapter mOpenSourceApdater;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    FragmentOpenSourceBinding mFragmentOpenSourceBinding;
    private OpenSourceViewModel mOpenSourceViewModel;

    public static OpenSourceFragment newInstance() {
        Bundle args = new Bundle();
        OpenSourceFragment fragment = new OpenSourceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_open_source;
    }

    public OpenSourceViewModel getViewModel() {
        mOpenSourceViewModel = ViewModelProviders.of(this, mViewModelFactory).get(OpenSourceViewModel.class);
        return mOpenSourceViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOpenSourceViewModel.setNavigator(this);
        mOpenSourceApdater.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mOpenSourceViewModel.fetchRepos();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentOpenSourceBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentOpenSourceBinding.openSourceRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentOpenSourceBinding.openSourceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentOpenSourceBinding.openSourceRecyclerView.setAdapter(mOpenSourceApdater);
    }

    private void subscribeToLiveData() {
        mOpenSourceViewModel.getOpenSourceRepos().observe(this,
                openSourceItemViewModels -> mOpenSourceViewModel.addOpenSourceItemsToList(openSourceItemViewModels));
    }
}
