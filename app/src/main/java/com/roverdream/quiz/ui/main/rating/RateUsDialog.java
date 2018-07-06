package com.roverdream.quiz.ui.main.rating;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roverdream.quiz.R;
import com.roverdream.quiz.databinding.DialogRareUsBinding;
import com.roverdream.quiz.ui.base.BaseDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RateUsDialog extends BaseDialog implements RateUsCallback {

    private static final String TAG = RateUsCallback.class.getSimpleName();

    @Inject
    RateUsViewModel mRateUsViewModel;

    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogRareUsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_rare_us, container, false);
        View view = binding.getRoot();
        AndroidSupportInjection.inject(this);
        binding.setViewModel(mRateUsViewModel);
        mRateUsViewModel.setNavigator(this);
        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }
}
