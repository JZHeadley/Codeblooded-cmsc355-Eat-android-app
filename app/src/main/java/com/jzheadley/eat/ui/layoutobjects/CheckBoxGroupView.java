package com.jzheadley.eat.ui.layoutobjects;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxGroupView extends GridLayout {

    private List<CheckBox> mCheckboxes = new ArrayList<>();

    public CheckBoxGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void put(CheckBox checkBox) {
        mCheckboxes.add(checkBox);
        invalidate();
        requestLayout();
    }

    public void remove(Integer id) {
        // TODO: Remove items from ArrayList
    }

    public List<?> getCheckboxesChecked() {
        List<CheckBox> checkedboxes = new ArrayList<>();
        for (CheckBox c : this.mCheckboxes) {
            if (c.isChecked()) {
                checkedboxes.add(c);
            }
        }
        return checkedboxes;
    }

    public List<Object> getCheckedIds() {
        List<Object> checkedboxes = new ArrayList<>();
        for (CheckBox c : this.mCheckboxes) {
            if (c.isChecked()) {
                checkedboxes.add(c.getTag());
            }
        }
        return checkedboxes;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (CheckBox c : mCheckboxes) {
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
