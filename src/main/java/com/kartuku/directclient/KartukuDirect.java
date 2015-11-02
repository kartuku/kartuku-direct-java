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

    public String ipgSandboxUrl = "https://ipg-dev.kartuku.com";
    public String ipgProductionUrl = "https://ipg.kartuku.com";

    protected boolean production;
    protected int connectionTimeout;

    protected ObjectMapper objectMapper;

    public KartukuDirect() {
        objectMapper = new ObjectMapper();
    }

    public void setIpgSandboxUrl(String url) {
        ipgSandboxUrl = url;
    }

    public String getIpgProductionUrl() {
        return ipgProductionUrl;
    }
    
    public void setIpgProductionUrl(String url) {
        ipgProductionUrl = url;
    }

    public String getIpgSandboxUrl() {
        return ipgSandboxUrl;
    }

    protected String getUrl(Request request) throws KartukuDirectException {
        return production ? ipgProductionUrl : ipgSandboxUrl + CommonUtil.getCommandUrl(request);
    }

    protected <T extends Request> String sendTransaction(T request, String secretKey) throws NoSignatureException, KartukuDirectException {
        if (request instanceof OttRequest) {
            return sendPlainTransactionRequest(request);
        } else {
            return sendTransactionRequest(request, secretKey);
        }
    }

    protected <T extends Request> String sendTransactionRequest(T request, String secretKey) throws NoSignatureException, KartukuDirectException {
        // wrap result        
        MessageWrapper message = wrapAndSign(request, secretKey);
        String result = sendMessage(getUrl(request), jsonEncode(message));
        MessageWrapper wrapper = jsonDecode(result, MessageWrapper.class);
        // check digest        
        return getMessageAndCheckHash(wrapper, secretKey);
    }

    protected <T extends Request> String sendPlainTransactionRequest(T request) throws KartukuDirectException {
        String result = sendMessage(getUrl(request), jsonEncode(request));
        return result;
    }

    /**
     * set connection timeout to server
     *
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
    protected String sendMessage(String urlString, String payload) throws KartukuDirectException {
        URL url;
        try {
            url = new URL(urlString);
            String result = CommonUtil.sendPostRequest(url, payload, connectionTimeout);
            return result;
        } catch (MalformedURLException ex) {
            throw new KartukuDirectException(String.format("URL %s is invalid", urlString), ex);
        } catch (SocketTimeoutException ex) {
            throw new KartukuDirectException("Time out while connecting to server.", ex);
        } catch (IOException ex) {
            throw new KartukuDirectException("Failed when connecting to server", ex);
        }
    }

    protected <T extends Request> MessageWrapper wrapAndSign(T request, String secretKey) throws KartukuDirectException {
        String message = jsonEncode(request);
        message = CommonUtil.base64Encode(message);
        String messageDigest = CommonUtil.calculateDigest(message, secretKey);
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setMessage(message);
        messageWrapper.setMessageDigest(messageDigest);
        return messageWrapper;
    }

    protected <T extends Message> String jsonEncode(T message) throws KartukuDirectException {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException ex) {
            throw new KartukuDirectException("Failed to write message to json format.", ex);
        }
    }

    protected <T extends Message> T jsonDecode(String message, Class<T> type) throws KartukuDirectException {
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
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public PurchaseResponse purchase(PurchaseRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, PurchaseResponse.class);
    }

    /**
     * Perform authorize request for the given amount, to be followed be
     * Capture. Require OTT Token (performed on client side) or Tokenized Card
     * Token in place of credit card. Note that some gateway only support 1 kind
     * of payment (purchase / auth-capture) only.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public AuthorizeResponse authorize(AuthorizeRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, AuthorizeResponse.class);
    }

    /**
     * Perform capture request. Note that the sum amount of multiple capture
     * should not exceed the initial amount requested during authorize. Also
     * some gateway might not support multiple capture.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public CaptureResponse capture(CaptureRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, CaptureResponse.class);
    }

    /**
     * Query transaction status.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public QueryResponse query(QueryRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, QueryResponse.class);
    }

    /**
     * Perform refund request. Note that the amount refunded cannot exceed
     * purchase / sum captured amount.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public RefundResponse refund(RefundRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, RefundResponse.class);
    }

    /**
     * Perform void purchase request, returning all amount.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidPurchaseResponse voidPurchase(VoidPurchaseRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, VoidPurchaseResponse.class);
    }

    /**
     * Perform void authorize, releasing locked amount during authorize.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidAuthorizeResponse voidAuthorize(VoidAuthorizeRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, VoidAuthorizeResponse.class);
    }

    /**
     * Perform void capture request, returning last amount captured. Note on
     * multiple capture, some gateway might not support multiple void capture.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidCaptureResponse voidCapture(VoidCaptureRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, VoidCaptureResponse.class);
    }

    /**
     * Perform void refund.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public VoidRefundResponse voidRefund(VoidRefundRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, VoidRefundResponse.class);
    }

    /**
     * Retrieve list of token-ized card for specific userCode, to be used on
     * Authorize/Purchase.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenListResponse tokenList(TokenListRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, TokenListResponse.class);
    }

    /**
     * Remove tokenized card.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenRemoveResponse tokenRemove(TokenRemoveRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
        return jsonDecode(result, TokenRemoveResponse.class);
    }

    /**
     * Tokenize card. Require OTT to be performed on client side to get OTT
     * Token. Note that CVV is not required.
     *
     * @param request
     * @param secretKey merchant secret key
     * @return
     * @throws NoSignatureException
     * @throws KartukuDirectException
     */
    public TokenStoreResponse tokenStore(TokenStoreRequest request, String secretKey) throws NoSignatureException, KartukuDirectException {
        String result = sendTransaction(request, secretKey);
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
        String result = sendTransaction(request, null);
        return jsonDecode(result, OttResponse.class);
    }

    protected String getMessageAndCheckHash(MessageWrapper wrapper, String secretKey) throws NoSignatureException, KartukuDirectException {
        String clientHash = CommonUtil.calculateDigest(wrapper.getMessage(), secretKey);
        if (wrapper.getMessageDigest() == null || wrapper.getMessageDigest().trim().length() <= 0) {
            throw new NoSignatureException(CommonUtil.base64Decode(wrapper.getMessage()));
        }
        if (!clientHash.toUpperCase().equals(wrapper.getMessageDigest().toUpperCase())) {
            throw new KartukuDirectException("Failed to check message integrity");
        }
        return CommonUtil.base64Decode(wrapper.getMessage());
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public boolean isProduction() {
        return production;
    }
    
}
