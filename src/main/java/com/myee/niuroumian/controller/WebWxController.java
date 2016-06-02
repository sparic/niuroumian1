package com.myee.niuroumian.controller;

import com.myee.niuroumian.service.WeixinService;
import com.myee.niuroumian.util.Constant;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray.Fu on 2016/6/1.
 */
@Controller
@RequestMapping("wxitf")
public class WebWxController {
    private Logger logger = LoggerFactory.getLogger(WebWxController.class);
    private static final String wpSite = "http://www.myee7.com/biplus";

    @Autowired
    private WeixinService weixinService;

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
        String nonce = req.getParameter("nonce");
        String timestamp = req.getParameter("timestamp");
        String echostr = req.getParameter("echostr");

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

}
