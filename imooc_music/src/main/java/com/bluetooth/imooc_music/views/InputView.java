package com.bluetooth.imooc_music.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bluetooth.imooc_music.R;

/**
 * input_icon 输入框前面的小图标
 * inpu_hint 输入框的提示内容
 * is_password输入的内容是否需要以密文的形式
 */
public class InputView extends FrameLayout {
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;

    public InputView(Context context) {
        super(context);
        init(context, null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.drawable.logo);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password, false);
        typedArray.recycle();
        //绑定 layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mEtInput = mView.findViewById(R.id.et_phoneNumber);
        //布局关联 属性
        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);

        // InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD 两个属性设置EditText的密文显示
        mEtInput.setInputType(isPassword? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD :InputType.TYPE_CLASS_TEXT);



        addView(mView);

    }



    public String getInputStr(){
        return mEtInput.getText().toString().trim();
    }



}
