package com.jzheadley.eat.views.layoutViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxGroupView extends GridLayout {

    List<CheckBox> checkboxes = new ArrayList<>();

    public CheckBoxGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void put(CheckBox checkBox) {
        checkboxes.add(checkBox);
        invalidate();
        requestLayout();
    }

    public void remove(Integer id) {
        // TODO: Remove items from ArrayList
    }

    public List<?> getCheckboxesChecked() {
        List<CheckBox> checkboxes = new ArrayList<>();
        for (CheckBox c : this.checkboxes) {
            if (c.isChecked())
                checkboxes.add(c);
        }
        return checkboxes;
    }

    public List<Object> getCheckedIds() {
        List<Object> checkboxes = new ArrayList<>();
        for (CheckBox c : this.checkboxes) {
            if (c.isChecked())
                checkboxes.add(c.getTag());
        }
        return checkboxes;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (CheckBox c : checkboxes) {
            addView(c);
        }
        invalidate();
        requestLayout();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


}
