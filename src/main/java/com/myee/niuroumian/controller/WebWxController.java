package com.myee.niuroumian.controller;

import com.myee.niuroumian.domain.OrderInfo;
import com.myee.niuroumian.domain.TTradingOrders;
import com.myee.niuroumian.service.WeixinService;
import com.myee.niuroumian.util.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import weixin.popular.api.PayMchAPI;
import weixin.popular.api.SnsAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.token.Token;
import weixin.popular.bean.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
@Controller
@RequestMapping(value = "wxitf")
public class WebWxController {
    private Logger logger = LoggerFactory.getLogger(WebWxController.class);
    private static final String wpSite = "http://www.myee7.com/biplus";

    @Autowired
    private WeixinService weixinService;//

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    protected WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    private static String openId;


    @RequestMapping(value = "service")
    @ResponseBody
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("微信公众号请求信息");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = resp.getWriter();
        String signature = req.getParameter("signature");
        System.out.println("===================signature:"+signature);
        String nonce = req.getParameter("nonce");
        System.out.println("================nonce:"+nonce);
        String timestamp = req.getParameter("timestamp");
        System.out.println("=====================timestamp:"+timestamp);
        String echostr = req.getParameter("echostr");
        System.out.println("=====d============echostr:"+echostr);

        // 默认返回的文本消息内容
        String respContent = "请求处理异常，请稍候尝试！";
        try {
            if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
                out.print("非法请求");
            } else if (StringUtils.isNotBlank(echostr)) {
                out.write(echostr);
            }
            Map map = new HashMap();
            String encryptType = StringUtils.isBlank(req.getParameter("encrypt_type")) ? "raw" : req.getParameter("encrypt_type");
            if ("raw".equals(encryptType)) {
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(req.getInputStream());
                openId = inMessage.getFromUserName();
                if(inMessage.getMsgType() != null && inMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT) && inMessage.getEvent() != null && inMessage.getEvent().equals(WxConsts.EVT_SCAN)) {
                    String url = "";
                    //获取快速订餐的菜单
                    map = quickOrderMenu(1L);
                    map.put("openId", openId);
                    map.put("url", url);
                }

                //微信自带的扫二维码，已关注
                if(inMessage.getMsgType() != null && inMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT) && inMessage.getEvent() != null && inMessage.getEvent().equals(WxConsts.EVT_SUBSCRIBE) && inMessage.getTicket() != null) {
                    String url = "";
                    //获取快速订餐的菜单
                    map = quickOrderMenu(1L);
                    map.put("openId", openId);
                    map.put("url", url);
                }
                inMessage.setMap(map);
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                String str = outMessage.toXml();
                out.print(str);
            }else if ("aes".equals(encryptType)) {
                String msgSignature = req.getParameter("msg_signature");
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(req.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                out.write(outMessage.toEncryptedXml(wxMpConfigStorage));
            } else {
                logger.error("不可识别的加密类型aaaa");
                out.write("不可识别的加密类型");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            out.close();
        }
    }

    /**
     * 快速点餐菜单推送
     * @return
     */
    public Map quickOrderMenu(Long storeId) {
        Map map = weixinService.findQuickOrderMenu(storeId);
        return map;
    }

    /**
     * 快速点餐菜单推送1
     * @return
     */
    @RequestMapping(value = "quickPushMenu")
    @ResponseBody
    public Map quickOrderMenu2(Long storeId) {
        System.out.println("++++++++++++++++++++++++++++++storeId：" + storeId);
        Map map = weixinService.findQuickOrderMenu(storeId);
        return map;
    }

    /**
     * 发送模板消息
     * @param map
     */
    public void sendModelMsg(Map map) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(map.get("openId").toString());
        templateMessage.setTemplateId(Constant.TEMPLEID_WELCOME);
        templateMessage.setUrl(map.get("url").toString());
        templateMessage.setTopColor("#36b2cc");
        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", map.get("content1").toString(), "#36b2cc"));
        templateMessage.getDatas().add(new WxMpTemplateData("keyword2", map.get("content2").toString(), "#36b2cc"));

        try {
            wxMpService.templateSend(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送用户信息
     */
    @RequestMapping(value = "send_user_info")
    @ResponseBody
    public WxMpUser sendUserInfo() {
        String lang = "zh_CN"; //语言
        WxMpUser user = null;
        try {
            user = wxMpService.userInfo(openId, lang);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return  user;
    }


    @RequestMapping("/home")
    public SnsToken home(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        System.out.println("===========code:" + code);
        SnsToken snsToken = new SnsToken();
        if (!"authdeny".equals(code) & code != null) {
            snsToken = getSnsToken("wxe67244505b4041b6","ae3b4cd8a550fab663c90ab16d548579",code);
        } else {

        }

        return snsToken;
    }

    /**
     * <暂时无用>
     * 获取用户基本信息
     * @param access_token access_token
     * @param openid openid
     * @return User
     */
    public User getUserInfo(String access_token,String openid){
        User user = UserAPI.userInfo(access_token, openid);
        return user;
    }

    /**
     * *<暂时无用>
     * 获取access_token
     * @param appid appid
     * @param secret secret
     * @return Token
     */
    public Token getToken(String appid,String secret){
        Token token = TokenAPI.token(appid, secret);
        return token;
    }

    /**
     * 通过code换取网页授权access_token
     * @param appid appid
     * @param secret secret
     * @param code code
     * @return SnsToken
     */
    public SnsToken getSnsToken(String appid,String secret,String code){
        SnsToken snsToken = SnsAPI.oauth2AccessToken(appid, secret, code);
        return snsToken;
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * @param access_token access_token
     * @param openid openid
     * @param lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return User
     */
    public User getUser(String access_token,String openid,String lang){
        User user = SnsAPI.userinfo(access_token, openid, lang);
        return user;
    }

    /**
     * 支付成功回调函数
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/busNoticeWeiXin", method = RequestMethod.POST)
    public void busNoticeWeiXin(HttpServletRequest request) {
        try {
            String inputLine;
            String notityXml = "";
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
            WeiXinPayResData weiXinPayResData = (WeiXinPayResData) ControllerUtil.getObjectFromXML(notityXml, WeiXinPayResData.class);
            String result_code = weiXinPayResData.getResult_code();
            logger.info("===========微信调用保存支付订单信息start~~~~weiXinPayResData.getOut_trade_no():"+weiXinPayResData.getOut_trade_no());
            TTradingOrders tradingOrdersData = new TTradingOrders();
            tradingOrdersData.setMerchantNo(weiXinPayResData.getMch_id());
            tradingOrdersData.setOrderStatus(result_code.equals("SUCCESS") ? "SUCCESS" : "FAILURE");
            tradingOrdersData.setPayAmount(String.valueOf(Double.valueOf(weiXinPayResData.getTotal_fee()) / 100));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
            Date parse = sdf2.parse(weiXinPayResData.getTime_end());
            String payTime2 = sdf.format(parse);
            tradingOrdersData.setPayTime(payTime2);
            tradingOrdersData.setPrepayId(weiXinPayResData.getPrepay_id());
            tradingOrdersData.setRespMsg(weiXinPayResData.getReturn_msg());
            tradingOrdersData.setSignData(weiXinPayResData.getSign());
            tradingOrdersData.setTradingStatus(Byte.valueOf("1"));// '交易状态：1=支付成功;2=支付失败',
            tradingOrdersData.setTransactionOrder(weiXinPayResData.getTransaction_id());
            tradingOrdersData.setOpenId(weiXinPayResData.getOpenid());
            tradingOrdersData.setOrdersNo(weiXinPayResData.getOut_trade_no());
            System.out.println("============tradingOrdersData:"+tradingOrdersData);
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("transactionOrder", weiXinPayResData.getTransaction_id());
            logger.info("===========微信调用保存支付订单信息end~~~~");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("BackRcvResponse接收后台通知结束");
    }

    /**
     * 页面触发下单
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/payOrder")
    public String payOrder(@RequestParam("data") String data,  HttpServletRequest request) {
        logger.info("===============支付下单==============data："+data);
        OrderInfo orderInfo = (OrderInfo) JsonParseUtil.jsonToObject(data, OrderInfo.class);
        Unifiedorder unifiedorder = new Unifiedorder();
        String openId = "oLueSs4LYOyeB2kL4I6VM4UmQnrM";
        UnifiedorderResult unifiedorderResult = payUnifiedorder(unifiedorder, "QsxnytrdFGJSKoefsdfeHSBXCNyeufjE", orderInfo, openId);

        //支付
        if ("FAIL".equals(unifiedorderResult.getReturn_code())) {//微信支付失败
            logger.info("===============支付失败==============");
            String res = JsonParseUtil.objectToJson(unifiedorderResult);
            return res;
        }
        // 更新订单start
        String res = JsonParseUtil.objectToJson(unifiedorderResult);
        // 下单成功
        return res;
    }

    /**
     * 微信支付统一下单
     * unifiedorder 微信支付获取预订单信息prepayId接口
     * @param unifiedorder unifiedorder
     * @param key key
     * @return UnifiedorderResult
     */
    @RequestMapping("pay")
    public UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder,String key,OrderInfo orderInfo,String openId){
        logger.info("===============开始支付操作==============");
        unifiedorder.setNonce_str(ControllerUtil.getRandomStringByLength(32));
        unifiedorder.setBody("商品名称");//
        unifiedorder.setNotify_url("http://pay.myee7.com/niuroumian/wxitf/busNoticeWeiXin");//回调
        unifiedorder.setOut_trade_no(orderInfo.getOrderId().toString());
        unifiedorder.setSpbill_create_ip("210.14.72.168");// 这里需要服务器地址，就用这个图片地址了，因为图片也是这个地址
        unifiedorder.setTotal_fee("0.1");
//        unifiedorder.setTotal_fee(orderInfo.getOrderPrice().toString());
        unifiedorder.setAttach("4;");
        logger.info("===============开始支付orderInfo.getOrderPrice():=============="+orderInfo.getOrderPrice());
        unifiedorder.setAppid("wxe67244505b4041b6");
        unifiedorder.setMch_id("1295359601");
        unifiedorder.setDevice_info("WEB");
        unifiedorder.setTrade_type("JSAPI");
        unifiedorder.setOpenid(openId);

        UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder, key);
        Map<String, Object> payMap = new HashMap<String, Object>();
        payMap.put("appId", "wxe67244505b4041b6");
        Long timeStamp = System.currentTimeMillis() / 1000;
        payMap.put("timeStamp", timeStamp);
        String nonce_str = ControllerUtil.getRandomStringByLength(32);
        payMap.put("nonceStr", nonce_str);
        payMap.put("signType", "MD5");
        String prepay_id = unifiedorderResult.getPrepay_id();
        logger.info("===============开始支付prepay_id:=============="+prepay_id);
        payMap.put("package", "prepay_id=" + prepay_id);
        String paySign = ControllerUtil.getSign(payMap,key);
        unifiedorderResult.setSign(paySign);
        unifiedorderResult.setNonce_str(nonce_str);
        return unifiedorderResult;
    }

    /**
     * 根据用户openid获取微信用户信息
     * @param openid
     * @return
     */
    @RequestMapping(value = "/queryUserObj")
    @ResponseBody
    public AjaxResultObj queryUserObj(@RequestParam("openid") String openid) {
        logger.info("获取RAM使用情况查询信息");
        try {
            if(org.apache.commons.lang3.StringUtils.isEmpty(openid)){
                return AjaxResultObj.failed("请输入设备ID");
            }
            Token token = getToken("wxe67244505b4041b6","ae3b4cd8a550fab663c90ab16d548579");
            User user = getUserInfo(token.getAccess_token(),openid);
            return AjaxResultObj.success(user,"获取微信用户信息成功！","xxxx","niuroumian");

        }  catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return AjaxResultObj.failed("糟了...系统出错了...");
        }
    }
}
