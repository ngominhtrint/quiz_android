package com.roverdream.quiz.ui.feed.blogs;

import com.roverdream.quiz.data.model.api.BlogResponse;

import java.util.List;

public interface BlogNavigator {

    void handleError(Throwable throwable);

    void updateBlog(List<BlogResponse.Blog> blogList);
}
