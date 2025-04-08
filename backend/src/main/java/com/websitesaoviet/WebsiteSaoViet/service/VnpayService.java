package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VnpayService {
    @NonFinal
    @Value("${vnpay.tmnCode}")
    public String tmnCode;
    @NonFinal
    @Value("${vnpay.hashSecret}")
    public String hashSecret;

    public String createPayment(int amount, String orderId, String ipnUrl, String redirectUrl, String extraData) {
        try {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "bill";
            String vnp_IpAddr = "127.0.0.1";
            String bankCode = "NCB";

            String sandboxUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", tmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount * 100));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", orderId);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don dat tour;" + extraData);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", ipnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
            vnp_Params.put("vnp_CreateDate", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);

            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();

            for (String fieldName : fieldNames) {
                String value = vnp_Params.get(fieldName);
                if (value != null && !value.isEmpty()) {
                    hashData.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                    query.append(fieldName).append('=').append(URLEncoder.encode(value, StandardCharsets.US_ASCII)).append('&');
                }
            }

            hashData.deleteCharAt(hashData.length() - 1);
            query.deleteCharAt(query.length() - 1);

            String secureHash = hmacSHA512(hashSecret, hashData.toString());
            query.append("&vnp_SecureHash=").append(secureHash);

            return sandboxUrl + "?" + query;
        } catch (Exception e) {
            throw new AppException(ErrorCode.PAYMENT_VNPAY_FALIED);
        }
    }

    private String hmacSHA512(String key, String data) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hmacBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new AppException(ErrorCode.PAYMENT_VNPAY_FALIED);
        }
    }
}