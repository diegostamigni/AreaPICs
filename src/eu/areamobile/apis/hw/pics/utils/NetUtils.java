package eu.areamobile.apis.hw.pics.utils;

import java.io.IOException;
import java.net.InetAddress;

import android.net.DhcpInfo;
import android.util.Log;
import eu.areamobile.apis.hw.pics.ijones.Discoverer;

/**
 * Created by AreaMobile
 * Date: 29/12/11
 *
 * Some useful utility
 * @see NetUtils#getBroadcastAddress(android.net.wifi.WifiManager)
 * @author Diego Stamigni (diegostamigni@areamobile.eu)
 */

public class NetUtils {
    /**
     * Useful method to get the Broadcast Address from the current connection.
     * @param mWifi is the WiFiManager
     * @return broadcast address in InetAddress obj
     * @throws IOException something goes wrong
     * @see InetAddress
     * @see android.net.wifi.WifiManager
     * @see android.net.wifi.WifiManager#getDhcpInfo()
     * @see InetAddress#getByAddress(byte[])
     */
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
