package dev.nick.extendedloggerdemo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import com.nick.scalpel.Scalpel;
import com.nick.scalpel.annotation.binding.AutoWired;
import com.nick.scalpel.annotation.binding.RegisterReceiver;
import com.nick.scalpel.annotation.binding.WorkerThreadHandler;
import com.nick.scalpel.annotation.request.RequirePermission;

import dev.nick.logger.LoggerManager;

@RequirePermission(permissions = Manifest.permission.READ_PHONE_STATE)
public class AudioModeListenerActivity extends AppCompatActivity {

    @WorkerThreadHandler
    Handler mHandler;

    @AutoWired
    AudioManager audioManager;

    @RegisterReceiver(actions = TelephonyManager.ACTION_PHONE_STATE_CHANGED)
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String extra = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            LoggerManager.getLogger(AudioModeListenerActivity.class).info("Changed...");
            if (extra.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                LoggerManager.getLogger(AudioModeListenerActivity.class).info("Ringing...");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        while (audioManager.getMode() != AudioManager.MODE_NORMAL) {
                            LoggerManager.getLogger(AudioModeListenerActivity.class).info("Waiting for mode：" + audioManager.getMode());
                            audioManager.setMode(AudioManager.MODE_NORMAL);

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                LoggerManager.getLogger(AudioModeListenerActivity.class).trace("Error when sleep:", e);
                            }
                        }

                        LoggerManager.getLogger(AudioModeListenerActivity.class).info("Mode set to：" + audioManager.getMode());
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Scalpel.getInstance().wire(this);
        LoggerManager.getLogger(getClass()).info("onCreate...");
    }
}
