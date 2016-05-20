package com.bytetime.jrim.ui.presenter;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.actions.ErrorAction;
import com.bytetime.jrim.actions.SuccessAction;
import com.bytetime.jrim.data.model.User;
import com.bytetime.jrim.ui.view.LoginView;
import com.bytetime.jrim.utils.LogUtil;
import com.bytetime.jrim.utils.ToastUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username, String password) {
        loginView.showLoading();
        JRIM.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SuccessAction<String>() {
                    @Override
                    public void onSuccess(String s) {
                        loginView.dismissLoading();
                        ToastUtil.showToast("Login success!");
                        LogUtil.d("Login Result: %s", s);

                        JRIM.getUserInfo("liu2")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SuccessAction<User>() {
                                    @Override
                                    public void onSuccess(User user) {
                                        LogUtil.d("Got user info");
                                        LogUtil.Object(user);
                                        loginView.showUserAvatar(user.avatar);
                                    }
                                }, new ErrorAction() {
                                    @Override
                                    public void onError(Throwable throwable) {
                                        LogUtil.e(throwable, "Got user failed");
                                    }
                                });
                    }
                }, new ErrorAction() {
                    @Override
                    public void onError(Throwable throwable) {
                        loginView.dismissLoading();
                        ToastUtil.showToast("Login failed!");
                        LogUtil.e(throwable, "Login failed");
                    }
                });
    }
}
