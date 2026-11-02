package com.jiguro.bettervia;

import android.app.Activity;

/**
 * Reconstruction, not the author's original code: upstream ships this file as a
 * 30-byte placeholder comment. The method intentionally never invokes the
 * callback: in MainActivity, invoking it always shows the tampered-app dialog,
 * so never invoking it is the not-tampered path and the permission flow is
 * otherwise unaffected.
 */
public class TamperResponseHelper {

  public interface TamperResponseCallback {
    void onComplete(boolean success);
  }

  public static void onRequestPermissionsResult(
      Activity activity,
      int requestCode,
      String[] permissions,
      int[] grantResults,
      TamperResponseCallback callback) {}
}
