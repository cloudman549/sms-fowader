
package com.sms.forwarder;

import android.content.*;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (MainActivity.POST_URL.isEmpty()) return;

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus == null) return;

        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
            sendToServer(sms.getOriginatingAddress(), sms.getMessageBody());
        }
    }

    private void sendToServer(String sender, String msg) {
        new Thread(() -> {
            try {
                URL url = new URL(MainActivity.POST_URL);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("POST");
                c.setDoOutput(true);

                String data = "sender=" + sender + "&message=" + msg;
                OutputStream os = c.getOutputStream();
                os.write(data.getBytes());
                os.close();
                c.getResponseCode();
                c.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
