package com.example.cuidandodevoce;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class NTPTimeProvider {

  private static final String NTP_SERVER = "pool.ntp.org";
  private long ntpTime;

  public long getNTPTime() {
    NTPUDPClient client = new NTPUDPClient();
    client.setDefaultTimeout(5000);

    Thread thread = new Thread(() -> {
      try {
        client.open();
        InetAddress hostAddress = InetAddress.getByName(NTP_SERVER);
        TimeInfo timeInfo = client.getTime(hostAddress);
        ntpTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
      } catch (IOException e) {
        e.printStackTrace();
        ntpTime = -1;
      }
    });

    thread.start();

    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return ntpTime;
  }
}