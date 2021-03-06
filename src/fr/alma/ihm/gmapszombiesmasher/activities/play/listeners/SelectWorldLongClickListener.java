package fr.alma.ihm.gmapszombiesmasher.activities.play.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import fr.alma.ihm.gmapszombiesmasher.R;

public class SelectWorldLongClickListener implements OnItemLongClickListener {

	private static Activity parent;

	public SelectWorldLongClickListener(Activity activity) {
		parent = activity;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterParent, View view,
			int position, long id) {

		if (!adapterParent.getItemAtPosition(position).equals(parent.getString(R.string.play_here))) {
			final CharSequence[] items = { 
					parent.getString(R.string.edit), 
					parent.getString(R.string.rename), 
					parent.getString(R.string.delete)};

			AlertDialog.Builder builder = new AlertDialog.Builder(parent);
			builder.setTitle(parent.getString(R.string.pick_option));
			builder.setItems(items, new OnClickWorldOptionListener(parent,
					(String) adapterParent.getItemAtPosition(position)));

			AlertDialog alert = builder.create();
			alert.show();
		}
		return false;
	}

}
