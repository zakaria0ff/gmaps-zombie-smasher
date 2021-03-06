package fr.alma.ihm.gmapszombiesmasher.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import fr.alma.ihm.gmapszombiesmasher.activities.game.GameActivity;
import fr.alma.ihm.gmapszombiesmasher.activities.settings.SettingPreferenceActivity;

public class ManagePreferences {

	// CHOPPER
	public static final int CHOPPER_LIFE_TIME = 10000;
	public static final long CHOPPER_BUTTON_LIFE_TIME = 15000;
	// BOMB
	public static final int BOMB_LIFE_TIME = 2000;
	public static final long BOMB_BUTTON_LIFE_TIME = 10000;
	// SPAWN
	private static final int ZOMBIES_EASY = 5;
	private static final int CITIZEN_EASY = 5;
	private static final int ZOMBIES_NORMAL = 10;
	private static final int CITIZEN_NORMAL = 10;
	private static final int ZOMBIES_HARD = 20;
	private static final int CITIZEN_HARD = 10;
	// SPEED
	private static final int ZOMBIES_SPEED_EASY = 6;
	private static final int CITIZEN_SPEED_EASY = 10;
	private static final int ZOMBIES_SPEED_NORMAL = 10;
	private static final int CITIZEN_SPEED_NORMAL = 10;
	private static final int ZOMBIES_SPEED_HARD = 10;
	private static final int CITIZEN_SPEED_HARD = 6;

	/**
	 * Get on the preference settings if the satellite mode view is on or not
	 * 
	 * @param activity
	 *            the current activity
	 * @return true if the satellite mode is on
	 */
	public static boolean isSateliteView(Activity activity) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(activity);
		return settings.getBoolean(
				SettingPreferenceActivity.SATELLITE_VIEW_IN_MAP, true);
	}

	/**
	 * Keep the screen on for the activity
	 * 
	 * @param activity
	 *            the current activity
	 */
	public static void setKeepScreenOn(Activity activity) {
		Window w = activity.getWindow();
		w.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/**
	 * Set the Screen Lock on or not
	 * 
	 * @param activity
	 *            the current activity
	 * @param bool
	 *            to lock or not the screen
	 */
	public static void setScreenLock(Activity activity, boolean bool) {
		// To desable screen lock, need to do "lock.reanable()" at the end of
		// the activity
		// Problem if the person kill the process (the screen lock will be
		// disabled)
		KeyguardManager keyguardManager = (KeyguardManager) activity
				.getSystemService(Activity.KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager
				.newKeyguardLock(Activity.KEYGUARD_SERVICE);

		if (bool) {
			lock.disableKeyguard();
		} else {
			lock.reenableKeyguard();
		}
	}

	/**
	 * Get the number of zombie to spawn according to the difficulty level
	 * 
	 * @param activity
	 *            the current activity
	 * @return the number of zombies to spawn
	 */
	public static int getZombieNumber(Activity activity) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(activity);
		String choice = settings.getString(
				SettingPreferenceActivity.DIFICULTY_LEVEL, "Normal");
		if (choice.equals("Easy")) {
			return ZOMBIES_EASY;
		} else if (choice.equals("Normal")) {
			return ZOMBIES_NORMAL;
		} else if (choice.equals("Hard")) {
			return ZOMBIES_HARD;
		}
		return ZOMBIES_NORMAL;
	}

	/**
	 * Get the number of citizen to spawn according to the difficulty level
	 * 
	 * @param activity
	 *            the current activity
	 * @return the number of citizen to spawn
	 */
	public static int getCitizenNumber(Activity activity) {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(activity);

		String choice = settings.getString(
				SettingPreferenceActivity.DIFICULTY_LEVEL, "normal");
		if (choice.equals("Easy"))
			return CITIZEN_EASY;
		else if (choice.equals("Normal"))
			return CITIZEN_NORMAL;
		else if (choice.equals("Hard"))
			return CITIZEN_HARD;

		return CITIZEN_NORMAL;
	}

	/**
	 * Return if the internet connection is on
	 * 
	 * @param activity
	 *            the current activity
	 * @return the status of the internet connection
	 */
	public static boolean isOnline(Activity activity) {
		ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null
	            && cm.getActiveNetworkInfo().isAvailable()
	            && cm.getActiveNetworkInfo().isConnected()) {
	        return true;
	    } else {
	        return false;
	    }
	}

	/**
	 * Return the min number of citizens the player have to save to win
	 * 
	 * @param activity the current activity
	 * @return the min number of citizen to save to win
	 */
	public static int getMinCitizenSavedToWin(Activity activity) {
		return getCitizenNumber(activity) / 2;
	}

	/**
	 * Return the citizen speed according to the difficulty level.
	 * 
	 * @return the citizen speed.
	 */
	public static int getCitizenSpeed() {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(GameActivity.getAppContext());

		String choice = settings.getString(
				SettingPreferenceActivity.DIFICULTY_LEVEL, "normal");
		if (choice.equals("Easy"))
			return CITIZEN_SPEED_EASY;
		else if (choice.equals("Normal"))
			return CITIZEN_SPEED_NORMAL;
		else if (choice.equals("Hard"))
			return CITIZEN_SPEED_HARD;

		return CITIZEN_SPEED_NORMAL;
	}
	
	/**
	 * Return the zombie speed according to the difficulty level.
	 * 
	 * @return the zombie speed.
	 */
	public static int getZombieSpeed() {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(GameActivity.getAppContext());

		String choice = settings.getString(
				SettingPreferenceActivity.DIFICULTY_LEVEL, "normal");
		if (choice.equals("Easy"))
			return ZOMBIES_SPEED_EASY;
		else if (choice.equals("Normal"))
			return ZOMBIES_SPEED_NORMAL;
		else if (choice.equals("Hard"))
			return ZOMBIES_SPEED_HARD;

		return ZOMBIES_SPEED_NORMAL;
	}

}
