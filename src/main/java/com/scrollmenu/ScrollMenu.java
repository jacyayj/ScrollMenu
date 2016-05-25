package com.scrollmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by jacyayj on 2016/5/24.
 */
public class ScrollMenu extends ScrollView{

    /*固定于顶部的菜单*/
    private View head = null;

    /*跟随页面拖动的菜单*/
    private View menu = null;

    /*页面主体*/
    private View body = null;

    private FrameLayout root = null;

    public ScrollMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.ScrollMenu);

        int head_layout = ta.getResourceId(R.styleable.ScrollMenu_scrollmenu_head,0);
        int body_layout = ta.getResourceId(R.styleable.ScrollMenu_scrollmenu_body,0);
        int menu_id = ta.getResourceId(R.styleable.ScrollMenu_scrollmenu_menu_id,0);

        if (head_layout != 0)
        {
            head = LayoutInflater.from(context).inflate(head_layout,null);
        }
        else
        {
            head = new View(context);
            head.setBackgroundColor(12403269);
            head.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        }

        if (body_layout != 0)
        {
            body = LayoutInflater.from(context).inflate(body_layout,null);
            menu = body.findViewById(menu_id);
        }
        else
        {
            body = new RelativeLayout(context);
            body.setBackgroundColor(3655793);
            body.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            body.setPadding(0,200,0,0);

            menu = new View(context);
            menu.setBackgroundColor(12403269);
            menu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        }

        root = new FrameLayout(context);
        root.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(root);

        /*添加页面主题到根页面*/
        root.addView(body);
        /*添加悬浮菜单*/
        root.addView(head);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScrolled(getScrollY());
            }
        });
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        onScrolled(t);
    }

    private void onScrolled(int scrollY) {
        head.setTranslationY(Math.max(menu.getTop(), scrollY));
    }

    public ScrollMenu(Context context) {
        super(context);
    }

    public View getHead() {
        return head;
    }

    public void setHead(View head) {
        this.head = head;
    }

    public View getMenu() {
        return menu;
    }

    public void setMenu(View menu) {
        this.menu = menu;
    }

    public View getBody() {
        return body;
    }

    public void setBody(View body) {
        this.body = body;
    }
}
