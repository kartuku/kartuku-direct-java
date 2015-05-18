
package com.kartuku.directclient.util;

import com.kartuku.directclient.exception.KartukuDirectException;
import com.kartuku.directclient.model.Request;
import com.kartuku.directclient.model.request.AuthorizeRequest;
import com.kartuku.directclient.model.request.CaptureRequest;
import com.kartuku.directclient.model.request.OttRequest;
import com.kartuku.directclient.model.request.PurchaseRequest;
import com.kartuku.directclient.model.request.QueryRequest;
import com.kartuku.directclient.model.request.RefundRequest;
import com.kartuku.directclient.model.request.TokenListRequest;
import com.kartuku.directclient.model.request.TokenRemoveRequest;
import com.kartuku.directclient.model.request.TokenStoreRequest;
import com.kartuku.directclient.model.request.VoidAuthorizeRequest;
import com.kartuku.directclient.model.request.VoidCaptureRequest;
import com.kartuku.directclient.model.request.VoidPurchaseRequest;
import com.kartuku.directclient.model.request.VoidRefundRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author mfachri
 */

public class CommonUtil {
    public static final String URL_PURCHASE       = "/direct/purchase";
    public static final String URL_AUTHORIZE      = "/direct/authorize";
    public static final String URL_CAPTURE        = "/direct/capture";
    public static final String URL_QUERY          = "/direct/query";
    public static final String URL_REFUND         = "/direct/refund";
    public static final String URL_VOID_CAPTURE   = "/direct/voidCapture";
    public static final String URL_VOID_PURCHASE  = "/direct/voidPurchase";
    public static final String URL_VOID_REFUND    = "/direct/voidRefund";
    public static final String URL_VOID_AUTHORIZE = "/direct/voidAuthorize";
    public static final String URL_TOKEN_STORE    = "/card/token/store";
    public static final String URL_TOKEN_LIST     = "/card/token/list";
    public static final String URL_TOKEN_REMOVE   = "/card/token/remove";
    public static final String URL_ONE_TIME_TOKEN = "/card/ott/v2/";
    
    private static final SimpleDateFormat ipgDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String calculateDigest(String message, String digestKey) throws KartukuDirectException{
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((digestKey).getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);

            byte[] bytes = mac.doFinal(message.getBytes("ASCII"));

            StringBuilder hash = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new KartukuDirectException("Cannot calculate hash.", e);
        }
        return digest.toUpperCase();
    }    
    
    public static String getCommandUrl(Request request) throws KartukuDirectException {
        if(request instanceof PurchaseRequest){
            return URL_PURCHASE;
        }else if(request instanceof AuthorizeRequest){
            return URL_AUTHORIZE;
        }else if(request instanceof CaptureRequest){
            return URL_CAPTURE;
        }else if(request instanceof QueryRequest){
            return URL_QUERY;
        }else if(request instanceof RefundRequest){
            return URL_REFUND;
        }else if(request instanceof VoidCaptureRequest){
            return URL_VOID_CAPTURE;
        }else if(request instanceof VoidPurchaseRequest){
            return URL_VOID_PURCHASE;
        }else if(request instanceof VoidAuthorizeRequest){
            return URL_VOID_AUTHORIZE;
        }else if(request instanceof VoidRefundRequest){
            return URL_VOID_REFUND;
        }else if(request instanceof TokenStoreRequest){
            return URL_TOKEN_STORE;
        }else if(request instanceof TokenListRequest){
            return URL_TOKEN_LIST;
        }else if(request instanceof TokenRemoveRequest){
            return URL_TOKEN_REMOVE;
        }else if(request instanceof OttRequest){
            return URL_ONE_TIME_TOKEN;
        }
        throw new KartukuDirectException(String.format("Request URL for %s is not found.", request.getClass().getName()));        
    }

    /**
     * Convert String to Base64 format
     *
     * @param message the String to be converted
     * @return Base64 result
     */
    public static String base64Encode(String message) {
        byte[] encodedMessage = Base64.encodeBase64(message.getBytes());
        return new String(encodedMessage);
    }

    /**
     * Convert Base64 format to readable String
     *
     * @param message String in Base64 format
     * @return readable String result
     */
    public static String base64Decode(String message) {
        byte[] decodedBytes = Base64.decodeBase64(message);
        return new String(decodedBytes);
    }

    public static String sendPostRequest(URL url, String payload, int connectionTimeout) throws IOException {
        return url.getProtocol().startsWith("https")? sendHttpsPost(url, payload, connectionTimeout) : sendHttpPost(url, payload, connectionTimeout);
    }
    
    private static String sendHttpsPost(URL url, String payload, int connectionTimeout) throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setConnectTimeout(connectionTimeout);
        con.setReadTimeout(connectionTimeout);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        writeToConnection(con, payload);
        return getMessage(con);
    }
    
    private static String sendHttpPost(URL url, String payload, int connectionTimeout) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(connectionTimeout);
        con.setReadTimeout(connectionTimeout);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("POST");
        writeToConnection(con, payload);
        return getMessage(con);
    }

    private static void writeToConnection(URLConnection connection, String payload) throws IOException {
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(payload);
        outputStream.flush();
        outputStream.close();
    }

    private static String getMessage(URLConnection connection) throws SocketTimeoutException, IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    
    /**
     * Convert date to acceptable format
     *
     * @param date the date in time stamp
     * @return acceptable date String yyyy-MM-dd HH:mm:ss
     */
    public static String convertDate(long date) {
        return convertDate(new Date(date));
    }

    /**
     * Convert date to acceptable format
     *
     * @param date the date object
     * @return acceptable date String yyyy-MM-dd HH:mm:ss
     */
    public static String convertDate(Date date) {
        return ipgDateFormat.format(date);
    }
}
