package it.areamobile.apis.hw.areafly.entity;

/**
 * Created by AreaMobile
 * Date: 28/12/11
 */
public abstract class FlyPort extends Warrior implements Comparable {
    private String mac_address;
    private String ip_address;
    private int port;
    private String netbios_name;
    private final String TOSTRING_SEPARETOR = "*";

    public FlyPort() {};

    @Override
    public String toString() {
        return this.netbios_name + this.TOSTRING_SEPARETOR + this.mac_address + this.TOSTRING_SEPARETOR;
    }

    @Override
    public String getMacAddress() {
        return mac_address;
    }

    @Override
    public void setMacAddress(String mac_address) {
        this.mac_address = mac_address;
    }

    @Override
    public String getIPAddress() {
        return ip_address;
    }

    @Override
    public void setIPAddress(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getNetBiosName() {
        return netbios_name;
    }

    @Override
    public void setNetBiosName(String netbios_name) {
        this.netbios_name = netbios_name;
    }
};
