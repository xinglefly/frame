package com.bytetime.jrim.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytetime.jrim.B;
import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.R;
import com.bytetime.jrim.ui.BaseLinearLayoutView;
import com.bytetime.jrim.ui.module.LoginModule;
import com.bytetime.jrim.ui.presenter.LoginPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterfork.Bind;
import butterfork.ButterFork;
import butterfork.OnClick;


public class LoginView extends BaseLinearLayoutView{

    @Bind(B.id.txtUsername) TextView txtUsername;
    @Bind(B.id.txtPassword) TextView txtPassword;
    @Bind(B.id.btnLogin) Button btnLogin;
    @Bind(B.id.imageView) ImageView imageView;

    @Inject
    LoginPresenter loginPresenter;

    private Context context;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.login, this);
        ButterFork.bind(this);

        this.context = context;

        txtUsername.setText("liu2");
        txtPassword.setText("123456");
    }

    @OnClick(B.id.btnLogin)
    void onBtnLoginClick()
    {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        loginPresenter.login(username, password);
    }

    public void setupInject() {
        JRIM.getJRIMComponent()
                .plus(new LoginModule(this))
                .inject(this);
    }

    public void showUserAvatar(String avatarUrl) {
        Picasso.with(context).load(avatarUrl).into(imageView);
    }
}
