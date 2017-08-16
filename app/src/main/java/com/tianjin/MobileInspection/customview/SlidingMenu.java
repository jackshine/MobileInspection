package com.tianjin.MobileInspection.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.tianjin.MobileInspection.R;
import com.tianjin.MobileInspection.until.ScreenUtils;

public class SlidingMenu extends HorizontalScrollView
{
	/**
	 * 屏幕宽度
	 */
	private int mScreenWidth;
	/**
	 * RightPadding dp
	 */
	private int mMenuRightPadding;
	/**
	 * 菜单的宽度
	 */
	private int mLeftMenuWidth;
	private int mHalfMenuWidth;

	private boolean isOpen;

	private boolean once;

	private ViewGroup mLeftMenu;
	private ViewGroup mContent;
	//手势滑动的起始位置
	private float start=0;
	
	public SlidingMenu(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		mScreenWidth = ScreenUtils.getScreenWidth(context);

		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case 0:
				// 默认50
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50f,
								getResources().getDisplayMetrics()));// 默认为10DP
				break;
			}
		}
		a.recycle();
	}

	public SlidingMenu(Context context)
	{
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		/**
		 * 显示的设置一个宽度
		 */
		if (!once)
		{
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			mLeftMenu = (ViewGroup) wrapper.getChildAt(0);
			mContent = (ViewGroup) wrapper.getChildAt(1);

			mLeftMenuWidth = mScreenWidth - mMenuRightPadding;
			mHalfMenuWidth = mLeftMenuWidth / 2;
			mLeftMenu.getLayoutParams().width = mLeftMenuWidth;
			mContent.getLayoutParams().width = mScreenWidth;

		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			// 将菜单隐藏
			this.scrollTo(mLeftMenuWidth, 0);
			once = true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		int action = ev.getAction();
		switch (action)
		{
		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if (scrollX > mHalfMenuWidth)
			{
				this.smoothScrollTo(mLeftMenuWidth, 0);
				isOpen = false;
			} else
			{
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}
			return true;
		case MotionEvent.ACTION_DOWN:
			start=getTranslationX();
			break;
		}

		return super.onTouchEvent(ev);
	}

	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu()
	{
		if (isOpen)
		{
			this.smoothScrollTo(mLeftMenuWidth, 0);
			isOpen = false;
		}
	}

	/**
	 * 切换菜单状态
	 */
	public void toggle()
	{
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mLeftMenuWidth;
		float leftScale = 1 - 0.3f * scale;
		float rightScale = 0.8f + scale * 0.2f;
		
//		ViewHelper.setScaleX(mLeftMenu, leftScale);//x轴压缩动画
//		ViewHelper.setScaleY(mLeftMenu, leftScale);//Y轴压缩动画
		ViewHelper.setAlpha(mLeftMenu, 0.6f + 0.4f * (1 - scale));
		ViewHelper.setTranslationX(mLeftMenu, mLeftMenuWidth * scale * 0.7f);

		ViewHelper.setPivotX(mContent, 0);
		//滑动时主页面的压缩动画
//		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
//		ViewHelper.setScaleX(mContent, rightScale);
//		ViewHelper.setScaleY(mContent, rightScale);

	}

}
