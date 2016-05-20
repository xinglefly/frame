package bytetime.com.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import bytetime.com.app.R;

public class RiderActiveView extends RelativeLayout{


    private Context context;

    public RiderActiveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.riders_active,this);
    }


}
