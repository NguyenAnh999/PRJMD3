//package com.ra.demo9.zalopay.Reponse;
//
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64;
//import java.util.Dictionary;
//
//public class ZaloPayReponse
//{
//    private int AppId ;
//    private String AppUser;
//    private Long AppTime;
//    private Long Amount ;
//    private String AppTransactionId ;
//    private String ReturnUrl ;
//    private String EmbedData ;
//    private String Mac ;
//    private String BankCode ;
//    private String Description;
//
//    public ZaloPayReponse(int appId, String appUser, Long appTime, Long amount, String appTransactionId, String returnUrl, String embedData, String mac, String bankCode, String description)
//    {
//        AppId = appId;
//        AppUser = appUser;
//        AppTime = appTime;
//        Amount = amount;
//        AppTransactionId = appTransactionId;
//        ReturnUrl = returnUrl;
//        EmbedData = embedData;
//        Mac = mac;
//        BankCode = bankCode;
//        Description = description;
//    }
//    public void makeSignature(String key) {
//        try {
//            String data = AppId + "|" + AppUser + "|" + AppTime + "|" + AppTransactionId + "|" + AppTime + "|" + Amount + "|";
//            this.Mac = hmacSHA256(data, key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String hmacSHA256(String data, String key) throws Exception {
//        Mac mac = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
//        mac.init(secretKeySpec);
//        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
//        return Base64.getEncoder().encodeToString(rawHmac);
//    }
////    public void MakeSignature (String key){
////       String data =  AppId +"|"+ AppUser +"|"+ AppTime +"|"+ AppTransactionId +"|"+ AppTransactionId +"|"+ Amount +"|";
////       this.Mac = HashHelper.HmacSHA256(data,key);
////    }
//    public Dictionary<String, String> GetContent()
//    {
//        Dictionary<string, string> keyValuePairs = new Dictionary<string, string>();
//
////        keyValuePairs.Add("appid", AppId.ToString());
//        keyValuePairs.Add("appuser", AppUser);
//        keyValuePairs.Add("apptime", AppTime.ToString());
//        keyValuePairs.Add("amount", Amount.ToString());
//        keyValuePairs.Add("apptransid", AppTransId);
//        keyValuePairs.Add("description", Description);
//        keyValuePairs.Add("bankcode", "zalopayapp");
//        keyValuePairs.Add("mac", Mac);
//
//        return keyValuePairs;
//    }
//
//}
