package com.qzsang.weichatshake.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qzsang.weichatshake.R;

/**
 * Created by qzsang on 2017/9/19.
 * 仿微信摇一摇
 */

public class ShakeView extends LinearLayout{
    protected View rootView;
    protected ImageView ivTop;
    protected ImageView ivBottom;

    protected int maxAnimHeight = 254;
    protected int animTime = 1300;

    private ObjectAnimator mAnimatorTop;
    private ObjectAnimator mAnimatorBottom;

    public ShakeView(Context context) {
        super(context);
        init();
    }

    public ShakeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShakeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init () {
        rootView = View.inflate(getContext(), R.layout.view_shake,this);
        ivTop = rootView.findViewById(R.id.iv_top);
        ivBottom = rootView.findViewById(R.id.iv_bottom);

        mAnimatorTop = ObjectAnimator.ofFloat(ivTop, "translationY", 0f, -maxAnimHeight, 0f);
        mAnimatorTop.setDuration(animTime);
        mAnimatorBottom = ObjectAnimator.ofFloat(ivBottom, "translationY", 0f, maxAnimHeight, 0f);
        mAnimatorBottom.setDuration(animTime);
    }


    public void startAnimation () {
        if (mAnimatorTop.isRunning() || mAnimatorBottom.isRunning()) {
            return;
        }
        mAnimatorTop.start();
        mAnimatorBottom.start();
    }

}
