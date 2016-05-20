package bytetime.com.app.ui;

import android.os.Bundle;

import com.bytetime.jrim.events.NewMessageEvent;
import com.bytetime.jrim.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bytetime.com.app.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewMessageEvent(NewMessageEvent event) {
        ToastUtil.showToast("Receive new message from: %s, content: %s",
                event.message.getFrom(), event.message.getBody());
    }
}
