package com.bsahu.chotaurl.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author bsahu
 *
 */
public class UrlConversionUtil {
    
	
    private UrlConversionUtil() {}
	
	/**
     * base 62 string set
     */
    public static final String BASE62STRSET = "Mheo9PI2qNs5Zpf80TBn7lmRbtQ4YKXHvwAEWxuzdra316OJigGLSVUCyFjkDc";
    
    public static final int BASE62COUNT = BASE62STRSET.length();


    public static String getAlias(Long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, BASE62STRSET.charAt((int) (num % BASE62COUNT)));
            num = num / BASE62COUNT;
        }
        return sb.toString();
    }


    public static Long getId(String str) {
        Long num = 0L;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE62COUNT + BASE62STRSET.indexOf(str.charAt(i));
        }
        return num;
    }
    
    public static String getBaseUrl(String url) throws MalformedURLException {
        URL reqUrl = new URL(url);
        String protocol = reqUrl.getProtocol();
        String host = reqUrl.getHost();
        int port = reqUrl.getPort();

        if (port == -1) {
            return String.format("%s://%s/", protocol, host);
        } else {
            return String.format("%s://%s:%d/", protocol, host, port);
        }

    }



}
