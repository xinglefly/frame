package com.bytetime.jrim.ui.component;


import com.bytetime.jrim.ui.ViewScope;
import com.bytetime.jrim.ui.module.LoginModule;
import com.bytetime.jrim.ui.view.LoginView;

import dagger.Subcomponent;

@ViewScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    LoginView inject(LoginView loginView);
}
