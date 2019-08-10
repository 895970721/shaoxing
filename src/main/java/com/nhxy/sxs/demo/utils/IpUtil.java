package com.nhxy.sxs.demo.utils;

import com.maxmind.geoip2.DatabaseReader;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * <p>Class: IpUtil</p>
 * 代码来源:https://jannchie.github.io/2018/12/21/defense-against/
 *
 * @author jannchie
 */
public class IpUtil {
    private static final String UNKNOWN = "unknown";
    private static final String COMMA = ",";
    private static final Integer IP_LENGTH = 15;

    /**
     * 从请求头中取到ip地址的值
     * e.g. 120.120.120.120
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
                if (ipAddress.indexOf(COMMA) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     * @description: 获得国家
     */
    public static String getCountry(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCountry().getNames().get("zh-CN");
    }

    /**
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     * @description: 获得省份
     */
    public static String getProvince(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getMostSpecificSubdivision().getNames().get("zh-CN");
    }

    /**
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     * @description: 获得城市
     */
    public static String getCity(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getCity().getNames().get("zh-CN");
    }

    /**
     * 获得详细地址
     *
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     */
    public static String getAddress(DatabaseReader reader, String ip) throws Exception {
        String country = reader.city(InetAddress.getByName(ip)).getCountry().getNames().get("zh-CN");
        String province = reader.city(InetAddress.getByName(ip)).getMostSpecificSubdivision().getNames().get("zh-CN");
        String city = reader.city(InetAddress.getByName(ip)).getCity().getNames().get("zh-CN");
        String f = "-";
        return country + f + province + f + city;
    }

    /**
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     * @description: 获得经度
     */
    public static Double getLongitude(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLongitude();
    }

    /**
     * @param reader
     * @param ip
     * @return
     * @throws Exception
     * @description: 获得纬度
     */
    public static Double getLatitude(DatabaseReader reader, String ip) throws Exception {
        return reader.city(InetAddress.getByName(ip)).getLocation().getLatitude();
    }

}
