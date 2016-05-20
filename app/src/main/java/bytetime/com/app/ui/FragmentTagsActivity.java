package bytetime.com.app.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import bytetime.com.app.R;
import bytetime.com.app.framement.ConversationFragment;
import bytetime.com.app.framement.MineFragment;
import bytetime.com.app.framement.MettingFragment;
import bytetime.com.app.framement.ContactFragment;


public class FragmentTagsActivity extends FragmentActivity  {

	@Bind(R.id.txtConversation) TextView txtConversation;
	@Bind(R.id.txtContact) TextView txtContact;
	@Bind(R.id.txtMetting) TextView txtMetting;
	@Bind(R.id.txtMine) TextView txtMine;

	private List<Fragment> fragments;
	private FragmentManager manager;
	private int selectedfragment=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framement_tags);
		ButterKnife.bind(this);
		fragments=new ArrayList<Fragment>();
		InitView();
	}


	private void InitView() {
		ConversationFragment conversationFragment = new ConversationFragment();

		fragments.add(conversationFragment);
		fragments.add(new ContactFragment());
		fragments.add(new MettingFragment());
		fragments.add(new MineFragment());
		
		manager=getSupportFragmentManager();

		FragmentTransaction beginTransaction=manager.beginTransaction();
		beginTransaction.add(R.id.fragment, conversationFragment);
		beginTransaction.commit();
	}


	private void SetTabsSelectedImg(int i) {
		FragmentTransaction beginTransaction=manager.beginTransaction();
		beginTransaction.remove(fragments.get(selectedfragment));
		beginTransaction.add(R.id.fragment,fragments.get(i));
		selectedfragment=i;
		switch (i) {
			case 0:
				txtConversation.setCompoundDrawables(null,returnDrawable(R.mipmap.ic_menu_deal_on),null,null);
				break;
			case 1:
				txtContact.setCompoundDrawables(null,returnDrawable(R.mipmap.ic_menu_more_on),null,null);

				break;
			case 2:
				txtMetting.setCompoundDrawables(null, returnDrawable(R.mipmap.ic_menu_user_on), null, null);

				break;
			case 3:
				txtMine.setCompoundDrawables(null, returnDrawable(R.mipmap.ic_menu_poi_on), null, null);
				break;
		}
		beginTransaction.commit();

	}

	@OnClick({R.id.txtConversation,R.id.txtContact,R.id.txtMetting,R.id.txtMine})
	void onTxtClick(View v){
		ResetTabsImg();
		switch (v.getId()) {
			case R.id.txtConversation:
				SetTabsSelectedImg(0);
				break;
			case R.id.txtContact:
				SetTabsSelectedImg(1);
				break;
			case R.id.txtMetting:
				SetTabsSelectedImg(2);
				break;
			case R.id.txtMine:
				SetTabsSelectedImg(3);
				break;
		}
	}



	private void ResetTabsImg() {
		txtConversation.setCompoundDrawables(null,returnDrawable(R.mipmap.ic_menu_deal_off),null,null);
				txtContact.setCompoundDrawables(null, returnDrawable(R.mipmap.ic_menu_more_off), null, null);
		txtMetting.setCompoundDrawables(null, returnDrawable(R.mipmap.ic_menu_user_off), null, null);
		txtMine.setCompoundDrawables(null, returnDrawable(R.mipmap.ic_menu_poi_off), null, null);
	}



	public Drawable returnDrawable(int icon) {
		Drawable drawable = getResources().getDrawable(icon);
		drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
		return drawable;
	}


}
