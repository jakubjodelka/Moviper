package com.mateuszkoslacz.moviper.base.service;

import android.app.Service;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateCallback;
import com.hannesdorfmann.mosby3.mvp.delegate.ActivityMvpDelegateImpl;

public abstract class MvpService
        <ViewType extends MvpView,
                Presenter extends MvpPresenter<ViewType>>
        extends Service
        implements ActivityMvpDelegateCallback<ViewType, Presenter>, MvpView {

    protected ActivityMvpDelegate mvpDelegate;
    protected Presenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        getMvpDelegate().onCreate(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    /**
     * Instantiate a presenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    @NonNull
    public abstract Presenter createPresenter();

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and
     * detaching
     * view from presenter.
     *
     * <p><b>Please note that only one instance of mvp delegate should be used per Activity
     * instance</b>.
     * </p>
     *
     * <p>
     * Only override this method if you really know what you are doing.
     * </p>
     *
     * @return {@link ActivityMvpDelegateImpl}
     */
    @NonNull
    protected ActivityMvpDelegate<ViewType, Presenter> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new ActivityMvpDelegateImpl(this);
        }

        return mvpDelegate;
    }

    @NonNull
    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewType getMvpView() {
        return (ViewType) this;
    }

    @Override
    public boolean isRetainInstance() {
        return false;
    }

    @Override
    public void setRetainInstance(boolean retainingInstance) {

    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return false;
    }

    @Override
    public Object onRetainNonMosbyCustomNonConfigurationInstance() {
        return null;
    }

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return null;
    }

    @Override
    public Object getNonMosbyLastCustomNonConfigurationInstance() {
        return null;
    }
}
