package com.bytetime.jrim.ui.module;

import com.bytetime.jrim.ui.ViewScope;
import com.bytetime.jrim.ui.presenter.LoginPresenter;
import com.bytetime.jrim.ui.view.LoginView;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    @ViewScope
    LoginView provideLoginView() {return this.loginView;}

    @Provides
    @ViewScope
    LoginPresenter provideLoginPresenter()
    {
        return new LoginPresenter(loginView);
    }
}
