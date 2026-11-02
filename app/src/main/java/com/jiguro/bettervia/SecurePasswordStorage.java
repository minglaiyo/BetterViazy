package com.jiguro.bettervia;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Reconstruction, not the author's original code: upstream ships this file as a
 * 30-byte placeholder comment, which makes the whole module uncompilable.
 * Backed by SharedPreferences. Callers only ever store PBKDF2 hashes here
 * (via SecurePasswordHelper), never plaintext passwords.
 */
public class SecurePasswordStorage {

  public static final String KEY_PASSWORD_SET = "privacy_lock_password_set";
  public static final String KEY_PATTERN_PASSWORD = "privacy_lock_pattern_password";
  public static final String KEY_PIN_PASSWORD = "privacy_lock_pin_password";
  public static final String KEY_LAST_FAILURE_TIME = "privacy_lock_last_failure_time";
  public static final String KEY_CONTINUOUS_FAILURE_COUNT =
      "privacy_lock_continuous_failure_count";
  public static final String KEY_LOCKOUT_END_TIME = "privacy_lock_lockout_end_time";

  private static final String PREFS_NAME = "secure_password_storage";

  private final SharedPreferences prefs;

  public SecurePasswordStorage(Context context) {
    this.prefs =
        context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
  }

  public String getSecureValue(String key) {
    return prefs.getString(key, null);
  }

  public void putSecureValue(String key, String value) {
    prefs.edit().putString(key, value).apply();
  }

  public boolean getSecureBoolean(String key, boolean defaultValue) {
    return prefs.getBoolean(key, defaultValue);
  }

  public void putSecureBoolean(String key, boolean value) {
    prefs.edit().putBoolean(key, value).apply();
  }

  public int getSecureInt(String key, int defaultValue) {
    return prefs.getInt(key, defaultValue);
  }

  public void putSecureInt(String key, int value) {
    prefs.edit().putInt(key, value).apply();
  }

  public long getSecureLong(String key, long defaultValue) {
    return prefs.getLong(key, defaultValue);
  }

  public void putSecureLong(String key, long value) {
    prefs.edit().putLong(key, value).apply();
  }

  public void removeSecureValue(String key) {
    prefs.edit().remove(key).apply();
  }

  /**
   * With plain preferences there is no checksum to verify: a key is
   * consistent when it is absent or its stored value reads back cleanly.
   */
  public boolean verifyConsistency(String key) {
    try {
      return !prefs.contains(key) || prefs.getAll().get(key) != null;
    } catch (ClassCastException e) {
      return false;
    }
  }

  public void close() {}
}
