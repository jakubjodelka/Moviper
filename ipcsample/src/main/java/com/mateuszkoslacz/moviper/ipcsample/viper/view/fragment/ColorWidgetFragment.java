package com.mateuszkoslacz.moviper.ipcsample.viper.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.mateuszkoslacz.moviper.ipcsample.R;
import com.mateuszkoslacz.moviper.ipcsample.constants.Constants;
import com.mateuszkoslacz.moviper.ipcsample.viper.contract.ColorWidgetContract;
import com.mateuszkoslacz.moviper.ipcsample.viper.presenter.ColorWidgetPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorWidgetFragment
        extends MvpFragment<ColorWidgetContract.View, ColorWidgetContract.Presenter>
        implements ColorWidgetContract.View, ColorWidgetContract.ViewHelper {

    @BindView(R.id.color_name)
    TextView textViewColorName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viper, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getPresenter().onViewCreated();
    }

    @OnClick(R.id.button_first)
    void onFirstButtonClick() {
        getPresenter().changeWidgetColor(Constants.FRAGMENT_NAME_FIRST);
    }

    @OnClick(R.id.button_second)
    void onSecondButtonClick() {
        getPresenter().changeWidgetColor(Constants.FRAGMENT_NAME_SECOND);
    }

    @OnClick(R.id.button_third)
    void onThirdButtonClick() {
        getPresenter().changeWidgetColor(Constants.FRAGMENT_NAME_THIRD);
    }

    @OnClick(R.id.button_all)
    void onAllButtonClick() {
        getPresenter().changeAllWidgetsColor();
    }

    @Override
    public void setColorName(String colorName) {
        textViewColorName.setText(colorName);
    }

    @Override
    public void setBackgroundColor(int color) {
        getActivity().runOnUiThread(() -> getView().setBackgroundColor(color));
    }

    @NonNull
    @Override
    public ColorWidgetContract.Presenter createPresenter() {
        return new ColorWidgetPresenter(this, getArguments());
    }
}
