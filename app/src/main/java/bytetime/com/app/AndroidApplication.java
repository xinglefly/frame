package bytetime.com.app;

import android.app.Application;

import com.bytetime.jrim.JRIM;
import com.bytetime.jrim.utils.LogUtil;

public class AndroidApplication extends Application {

    private static AndroidApplication instance;

    public static AndroidApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            JRIM.init(this);
        } catch (Exception e) {
            LogUtil.e(e, "Initial JRIM failed");
        }
    }
}
