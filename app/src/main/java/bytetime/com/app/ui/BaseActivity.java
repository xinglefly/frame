package bytetime.com.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bytetime.jrim.JRIM;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JRIM.registerEventBus(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JRIM.unregisterEventBus(this);
    }
}
