package com.mateuszkoslacz.moviper.sample.viper.interactor;

import com.mateuszkoslacz.moviper.base.interactor.BaseInteractor;
import com.mateuszkoslacz.moviper.sample.data.retrofit.GitHubApiInterface;
import com.mateuszkoslacz.moviper.sample.viper.contract.UserDetailsContract;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDetailsInteractor
        extends BaseInteractor<UserDetailsContract.PresenterForInteractor>
        implements UserDetailsContract.Interactor {

    private GitHubApiInterface mGitHubApiInterface;

    public UserDetailsInteractor() {
        final Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(GitHubApiInterface.GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mGitHubApiInterface = mRetrofit.create(GitHubApiInterface.class);
    }

    @Override
    public void getUserForUsername(String user) {
        Thread thread = new Thread(() -> {
            try {
                if (isPresenterAttached()) {
                    getPresenter().onUserFetched(mGitHubApiInterface
                            .getUserForUsername(user).execute().body());
                }
            } catch (IOException e) {
                if (isPresenterAttached()) getPresenter().onUserFetchedError(e);
            }
        });

        thread.start();
    }
}
