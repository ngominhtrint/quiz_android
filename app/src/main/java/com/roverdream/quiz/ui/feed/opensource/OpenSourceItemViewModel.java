package com.roverdream.quiz.ui.feed.opensource;

import android.databinding.ObservableField;

public class OpenSourceItemViewModel {

    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableField<String> imageUrl = new ObservableField<>();

    public final ObservableField<String> projectUrl = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public OpenSourceItemViewModel(String imageUrl, String title, String content, String projectUrl) {
        this.imageUrl.set(imageUrl);
        this.title.set(title);
        this.content.set(content);
        this.projectUrl.set(projectUrl);
    }
}
