
package com.kartuku.directclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kartuku.directclient.model.request.VoidPurchaseRequest;
import com.kartuku.directclient.model.request.TokenListRequest;
import com.kartuku.directclient.model.request.TokenRemoveRequest;
import com.kartuku.directclient.model.request.RefundRequest;
import com.kartuku.directclient.model.request.TokenStoreRequest;
import com.kartuku.directclient.model.request.VoidRefundRequest;
import com.kartuku.directclient.model.request.AuthorizeRequest;
import com.kartuku.directclient.model.request.VoidCaptureRequest;
import com.kartuku.directclient.model.request.PurchaseRequest;
import com.kartuku.directclient.model.request.QueryRequest;
import com.kartuku.directclient.model.request.CaptureRequest;
import com.kartuku.directclient.model.request.VoidAuthorizeRequest;
import com.kartuku.directclient.model.response.RefundResponse;
import com.kartuku.directclient.model.response.VoidRefundResponse;
import com.kartuku.directclient.model.response.TokenStoreResponse;
import com.kartuku.directclient.model.response.PurchaseResponse;
import com.kartuku.directclient.model.response.QueryResponse;
import com.kartuku.directclient.model.response.CaptureResponse;
import com.kartuku.directclient.model.response.TokenListResponse;
import com.kartuku.directclient.model.response.VoidCaptureResponse;
import com.kartuku.directclient.model.response.VoidPurchaseResponse;
import com.kartuku.directclient.model.response.VoidAuthorizeResponse;
import com.kartuku.directclient.model.response.TokenRemoveResponse;
import com.kartuku.directclient.model.response.AuthorizeResponse;
import com.kartuku.directclient.model.Message;
import com.kartuku.directclient.model.Request;
import com.kartuku.directclient.model.MessageWrapper;
import com.kartuku.directclient.config.Config;
import com.kartuku.directclient.exception.KartukuDirectException;
import com.kartuku.directclient.exception.NoSignatureException;
import com.kartuku.directclient.model.request.OttRequest;
import com.kartuku.directclient.model.response.OttResponse;
import com.kartuku.directclient.util.CommonUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Direct API Client. Use this to interact with Kartuku Internet Payment Gateway
 * Direct API.
 *
 * @author mfachri
 */
public class KartukuDirect {

    public static final String IPG_SANDBOX    = "https://ipg-dev.kartuku.com";
    public static final String IPG_PRODUCTION = "https://ipg.kartuku.com";

    /**
     * define whether this client point to production (true) or staging (false).
     */
    private boolean production = false;

    private Config config;
    private int connectionTimeout;

    private final ObjectMapper objectMapper;

    public KartukuDirect() {
        objectMapper = new ObjectMapper();        
    }

    private String getUrl(Request request) throws KartukuDirectException {
        return isProduction() ? IPG_PRODUCTION : IPG_SANDBOX + CommonUtil.getCommandUrl(request);
    }

    private <T extends Request> String sendTransaction(T request) throws NoSignatureException, KartukuDirectException {
        if (request instanceof OttRequest) {
            return sendPlainTransactionRequest(request);
        } else {
            return sendTransactionRequest(request);
        }
    }

    private <T extends Request> String sendTransactionRequest(T request) throws NoSignatureException, KartukuDirectException {
        // wrap result        
        MessageWrapper message = wrapAndSign(request);
        String result = sendMessage(getUrl(request), jsonEncode(message));
        MessageWrapper wrapper = jsonDecode(result, MessageWrapper.class);
        // check digest        
        return getMessageAndCheckHash(wrapper);
    }

    private <T extends Request> String sendPlainTransactionRequest(T request) throws KartukuDirectException {
        String result = sendMessage(getUrl(request), jsonEncode(request));
        return result;
    }

    // <editor-fold defaultstate="folded" desc="Setter and Getter declaration">
    // setter and getter declaration
    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public boolean isProduction() {
        return production;
    }
    /**
     * define whether this client point to production (true) or staging (false).
     * @param production true for production and false for staging
     */
    public void setProduction(boolean production) {
        this.production = production;
    }

    /**
     * set connection timeout to server
     * @param connectionTimeout timeout in millisecond(s)
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    // end setter and getter declaration
    // </editor-fold>
    private String sendMessage(String urlString, String payload) throws KartukuDirectException {
        URL url;
        try {
            url = new URL(urlString);
            String result = CommonUtil.sendPostRequest(url, payload, connectionTimeout);
            return result;
        } catch (MalformedURLException ex) {
            throw new KartukuDirectException(String.format("URL %s is invalid", urlString), ex);
        } catch(SocketTimeoutException ex){
            throw new KartukuDirectException("Time out while connecting to server.", ex);
        } catch (IOException ex) {
            throw new KartukuDirectException("Failed when connecting to server", ex);
        }
    }

    private <T extends Request> MessageWrapper wrapAndSign(T request) throws KartukuDirectException {
        String message = jsonEncode(request);
        message = CommonUtil.base64Encode(message);
        String messageDigest = CommonUtil.calculateDigest(message, config.getMerchantSecretKey());
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setMessage(message);
        messageWrapper.setMessageDigest(messageDigest);
        return messageWrapper;
    }

    private <T extends Message> String jsonEncode(T message) throws KartukuDirectException {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            throw new KartukuDirectException("Failed to write message to json format.", ex);
        }
    }

    private <T extends Message> T jsonDecode(String message, Class<T> type) throws KartukuDirectException {
        try {
            return objectMapper.readValue(message, type);
        } catch (IOException ex) {
            throw new KartukuDirectException(String.format("Failed to parse message from json format. Message : [%s]", message), ex);
        }
    }

    /**
     * Perform purchase request. Require OTT Token (performed on client side) or
     * Tokenized Card Token in place of credit card. Note that some gateway only
     * support 1 kind of payment (purchase / auth-capture) only.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public PurchaseResponse purchase(PurchaseRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, PurchaseResponse.class);
    }

    /**
     * Perform authorize request for the given amount, to be followed be
     * Capture. Require OTT Token (performed on client side) or Tokenized Card
     * Token in place of credit card. Note that some gateway only support 1 kind
     * of payment (purchase / auth-capture) only.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public AuthorizeResponse authorize(AuthorizeRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, AuthorizeResponse.class);
    }

    /**
     * Perform capture request. Note that the sum amount of multiple capture
     * should not exceed the initial amount requested during authorize. Also
     * some gateway might not support multiple capture.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public CaptureResponse capture(CaptureRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, CaptureResponse.class);
    }

    /**
     * Query transaction status.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public QueryResponse query(QueryRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, QueryResponse.class);
    }

    /**
     * Perform refund request. Note that the amount refunded cannot exceed
     * purchase / sum captured amount.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public RefundResponse refund(RefundRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, RefundResponse.class);
    }

    /**
     * Perform void purchase request, returning all amount.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidPurchaseResponse voidPurchase(VoidPurchaseRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, VoidPurchaseResponse.class);
    }

    /**
     * Perform void authorize, releasing locked amount during authorize.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidAuthorizeResponse voidAuthorize(VoidAuthorizeRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, VoidAuthorizeResponse.class);
    }

    /**
     * Perform void capture request, returning last amount captured. Note on
     * multiple capture, some gateway might not support multiple void capture.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidCaptureResponse voidCapture(VoidCaptureRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, VoidCaptureResponse.class);
    }

    /**
     * Perform void refund.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidRefundResponse voidRefund(VoidRefundRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, VoidRefundResponse.class);
    }

    /**
     * Retrieve list of token-ized card for specific userCode, to be used on
     * Authorize/Purchase.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenListResponse tokenList(TokenListRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, TokenListResponse.class);
    }

    /**
     * Remove tokenized card.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenRemoveResponse tokenRemove(TokenRemoveRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, TokenRemoveResponse.class);
    }

    /**
     * Tokenize card. Require OTT to be performed on client side to get OTT
     * Token. Note that CVV is not required.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenStoreResponse tokenStore(TokenStoreRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, TokenStoreResponse.class);
    }

    /**
     * Should be performed on client side to prevent card details from reaching
     * Merchant's server. Note that OTT only require Merchant Token, DO NOT
     * STORE MERCHANT SECRET KEY ON YOUR CLIENT SIDE.
     *
     * @param request
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public OttResponse ott(OttRequest request) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request);
        return jsonDecode(result, OttResponse.class);
    }

    private String getMessageAndCheckHash(MessageWrapper wrapper) throws NoSignatureException, KartukuDirectException {
        String clientHash = CommonUtil.calculateDigest(wrapper.getMessage(), config.getMerchantSecretKey());
        if (wrapper.getMessageDigest() == null || wrapper.getMessageDigest().trim().length() <= 0) {
            throw new NoSignatureException(CommonUtil.base64Decode(wrapper.getMessage()));
        }
        if (!clientHash.toUpperCase().equals(wrapper.getMessageDigest().toUpperCase())) {
            throw new KartukuDirectException("Failed to check message integrity");
        }
        return CommonUtil.base64Decode(wrapper.getMessage());
    }
}
