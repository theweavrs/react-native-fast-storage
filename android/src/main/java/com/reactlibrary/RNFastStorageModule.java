
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.tencent.mmkv.MMKV;

public class RNFastStorageModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNFastStorageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNFastStorage";
  }

  @ReactMethod
  public void setupLibrary() {
    MMKV.initialize(getReactApplicationContext());
  }

  @ReactMethod
  public void setItem(String key, String value, Callback cb, Callback error) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.encode(key, value);
      cb.invoke(value);
    } catch (Error e) {
      error.invoke("Unable to setItem");
    } catch (Exception e) {
      error.invoke("Unable to setItem");
    }
  }

  @ReactMethod
  public void getItem(String key, Callback cb, Callback error) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      cb.invoke(kv.decodeString(key));
    } catch (Error e) {
      error.invoke("Unable to getItem");
    } catch (Exception e) {
      error.invoke("Unable to getItem");
    }
  }

  @ReactMethod
  public void containsKey(String key, Callback cb) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      cb.invoke(kv.containsKey(key));
    } catch (Error e) {
      error.invoke("Unable to check for key");
    } catch (Exception e) {
      error.invoke("Unable to check for key");
    }
  }

  @ReactMethod
  public void removeItem(String key, Callback cb, Callback error) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.removeValueForKey(key);
      cb.invoke(key);
    } catch (Error e) {
      error.invoke("Unable to removeItem");
    } catch (Exception e) {
      error.invoke("Unable to removeItem");
    }
  }

  @ReactMethod
  public void clearStore(Callback cb, Callback error) {
    try {
      MMKV kv = MMKV.defaultMMKV();
      kv.clearAll();
      cb.invoke("Done");
    } catch (Error e) {
      error.invoke("Unable to clear store");
    } catch (Exception e) {
      error.invoke("Unable to clear store");
    }
  }
}