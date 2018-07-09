package com.roverdream.quiz.ui.feed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.roverdream.quiz.ui.feed.blogs.BlogFragment;

public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public FeedPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }
}
