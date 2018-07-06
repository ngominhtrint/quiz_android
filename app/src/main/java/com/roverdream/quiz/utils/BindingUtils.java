package com.roverdream.quiz.utils;

import android.databinding.BindingAdapter;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.roverdream.quiz.data.model.others.QuestionCardData;
import com.roverdream.quiz.ui.main.MainViewModel;
import com.roverdream.quiz.ui.main.QuestionCard;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        //This class is not publicly instantiable
    }

    @BindingAdapter({"adapter", "action"})
    public static void addQuestionItems(SwipePlaceHolderView mCardsContainerView, List<QuestionCardData> mQuestionList, int mAction) {
        if (mAction == MainViewModel.ACTION_ADD_ALL) {
            if (mQuestionList != null) {
                mCardsContainerView.removeAllViews();
                for (QuestionCardData question : mQuestionList) {
                    if (question != null && question.options != null && question.options.size() == 3) {
                        mCardsContainerView.addView(new QuestionCard(question));
                    }
                }
                ViewAnimationUtils.scaleAnimateView(mCardsContainerView);
            }
        }
    }


}
