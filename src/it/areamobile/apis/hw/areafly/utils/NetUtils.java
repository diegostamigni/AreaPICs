package it.areamobile.apis.hw.areafly.utils;

import android.net.DhcpInfo;
import android.util.Log;
import it.areamobile.apis.hw.areafly.ijones.Discoverer;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class NetUtils {
    public static InetAddress getBroadcastAddress(android.net.wifi.WifiManager mWifi) throws IOException {
        DhcpInfo dhcp = mWifi.getDhcpInfo();
        if (dhcp == null) {
            Log.d(Discoverer.TAG, "Could not get dhcp info.");
            return null;
        }

        int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
        byte[] quads = new byte[4];

        for (int k = 0; k < 4; k++)
            quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);

        return InetAddress.getByAddress(quads);
    }
}
