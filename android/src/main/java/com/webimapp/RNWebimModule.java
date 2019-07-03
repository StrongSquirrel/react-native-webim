package com.webimapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.webimapp.android.sdk.Webim;
import com.webimapp.android.sdk.WebimLog;
import com.webimapp.android.sdk.WebimSession;
import com.webimapp.android.sdk.MessageStream;
import com.webimapp.android.sdk.MessageTracker;
import com.webimapp.android.sdk.MessageListener;
import com.webimapp.android.sdk.Message;

public class RNWebimModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private WebimSession session;
    private MessageStream stream;
    private MessageTracker tracker;

    public RNWebimModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNWebim";
    }

    @ReactMethod
    public void init(ReadableMap config, Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject("Activity doesn't exist");
            return;
        }

        String accountName = config.hasKey("accountName") ? config.getString("accountName") : "demo";

        session = Webim.newSessionBuilder().setContext(currentActivity).setAccountName(accountName)
                .setLocation("mobile").setLogger(new WebimLog() {
                    @Override
                    public void log(String log) {
                        Log.i("WEBIM LOG", log);
                    }
                }, Webim.SessionBuilder.WebimLogVerbosityLevel.VERBOSE).build();

        stream = session.getStream();
        initListeners();

        promise.resolve("Well done");
        return;
    }

    private void sendEvent(String eventName, @Nullable String payload) {
        this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, payload);
    }

    private void initListeners() {
        stream.setUnreadByVisitorMessageCountChangeListener(
                new MessageStream.UnreadByVisitorMessageCountChangeListener() {
                    @Override
                    public void onUnreadByVisitorMessageCountChanged(int newMessageCount) {
                        sendEvent("unreadMessageCount", Integer.toString(newMessageCount));
                    }
                });

        this.tracker = stream.newMessageTracker(new MessageListener() {
            @Override
            public void messageAdded(@Nullable Message before, @NonNull Message message) {
                sendEvent("messageAdded", new Gson().toJson(message));
                return;
            }

            @Override
            public void messageRemoved(@NonNull Message message) {
                sendEvent("messageRemoved", new Gson().toJson(message));
                return;
            }

            @Override
            public void messageChanged(@NonNull Message from, @NonNull Message to) {
                return;
            }

            @Override
            public void allMessagesRemoved() {
                return;
            }
        });
    }

    @ReactMethod
    public void getAllMessages(final Promise promise) {
        tracker.getAllMessages(new MessageTracker.GetMessagesCallback() {
            @Override
            public void receive(@NonNull final List<? extends Message> received) {
                promise.resolve(new Gson().toJson(received));
            }
        });
    }

    @ReactMethod
    public void getNextMessages(int limitOfMessages, final Promise promise) {
        tracker.getNextMessages(limitOfMessages, new MessageTracker.GetMessagesCallback() {
            @Override
            public void receive(@NonNull final List<? extends Message> received) {
                promise.resolve(new Gson().toJson(received));
            }
        });
    }

    @ReactMethod
    public void destroySession() {
        session.destroy();
    }

    @ReactMethod
    public void pauseSession() {
        session.pause();
    }

    @ReactMethod
    public void resumeSession() {
        session.resume();
    }

    @ReactMethod
    public void sendMessage(String message) {
        stream.sendMessage(message);
    }

}
