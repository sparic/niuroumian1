//package com.myee.niuroumian.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.myee.niuroumian.domain.OrderInfo;
//import com.myee.niuroumian.domain.OrderState;
//import com.myee.niuroumian.response.Response;
//import com.myee.niuroumian.response.ResponseData;
//import com.myee.niuroumian.websocket.OrderServer;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/niuroumian/")
//public class WebSocketController {
//
//    @Autowired
//    private OrderServer orderServer;
//
//    @ExceptionHandler({Exception.class})
//    public void sockjsError(Exception ex, HttpServletResponse response) {
//        Response resp = Response.errorResult(ex.getMessage());
//        try {
//            response.getWriter().append(Response.toRawString(resp));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping("sendorder")
//    @ResponseBody
//    public String sendorder(@RequestParam("message") String message) {
//        JSONObject object = JSON.parseObject(message);
//        if (StringUtils.isNotBlank(message)) {
//            OrderInfo orderInfo = new OrderInfo();
//            Long dishId = object.getLong("dishId");
//            orderInfo.setDishId(dishId);
//            Long shopId = object.getLong("shopId");
//            orderInfo.setShopId(shopId);
//            Long userId = object.getLong("userId");
//            orderInfo.setUserId(userId);
//            orderInfo.setOrderType(6);
//            orderInfo.setOrderState(OrderState.WAITING.getValue());
//            OrderInfo orderResult = orderServer.createOrder(orderInfo);
//            return JSON.toJSONString(ResponseData.successData(orderResult));
//        }
//        return JSON.toJSONString(ResponseData.errorData("error"));
//    }
////    @RequestMapping("cancelorder")
////    @ResponseBody
////    public String archive(long orgID, @RequestParam("file") CommonsMultipartFile file, String path) throws IllegalStateException, IOException {
////        File dest = WebAppController.getResFile(orgID, path);
////        if (!file.isEmpty()) {
////            dest.mkdirs();
////            file.transferTo(dest);
////        }
////        return dest.getAbsolutePath();
////    }
////
////    @RequestMapping("repastOrder")
////    @ResponseBody
////    public String uploadResource(long orgID, @RequestParam("resFile") MultipartFile[] files, String path) throws IllegalStateException, IOException {
////        String returnStr = "0";
////        if(files != null && files.length > 0){
////            for(int i = 0 ; i < files.length ; i++){
////                CommonsMultipartFile file = (CommonsMultipartFile)files[i];
////                if (!file.isEmpty()) {
////                    File dest = WebAppController.getResFile(orgID, path+"/"+TypeConverter.toLong(DateTime.getShortDate())+"/"+file.getOriginalFilename());
////                    dest.mkdirs();
////                    file.transferTo(dest);
////                    returnStr = "1";
////                }
////            }
////        }
////        return returnStr;
////    }
////
////    @RequestMapping("sendSMS")
////    @ResponseBody
////    @Transactional
////    public InfoSendSMSVo sendSMS(@ModelAttribute("in") InfoSendSMSVo in) throws IllegalStateException, IOException {
////        final long st = System.currentTimeMillis();
////        if (!paraJude(in)) {
////            return new InfoSendSMSVo(false, "参数错误", 0, 0);
////        }
////        InfoPhone ip = restaurant.selectByTableId(in.getTableID());
////        if(ip == null || (ip != null && ip.getTableID() <= 0 || (MyStringUtils.isBlank(ip.getPhone())
////                && MyStringUtils.isBlank(ip.getPhone1()) && MyStringUtils.isBlank(ip.getPhone2())))){
////            return new InfoSendSMSVo(false, "参数错误", 0, 0);
////        }
////        Table tb = restaurant.getOne(ip.getTableID());
////        if(tb == null){//如果桌号查询不到,则返回错误
////            return new InfoSendSMSVo(false, "参数错误", 0, 0);
////        }
////        if(MyStringUtils.isBlank(tb.getName())){
////            return new InfoSendSMSVo(false, "参数错误", 0, 0);
////        }
////        AlidayuSmsClient.sendSMS(ip, tb, in, cleverm, st);//默认发送成功,保存发送结果
////        return new InfoSendSMSVo(true, "发送成功", in.getTableID(), in.getTemplateID());
////    }
////
////    /**
////     * 判断传入参数,如果合法返回true
////     * @param in
////     * @return
////     */
////    public boolean paraJude(InfoSendSMSVo in){
////        if(in == null){
////            return false;
////        }else if(in != null
////                && (in.getTemplateID() < TemplateSMSType.ADDFOOD.getIndex()
////                || in.getTemplateID() > TemplateSMSType.CLEANDESK.getIndex()
////                || (in.getTemplateID() > TemplateSMSType.ADDSTEAMEDRICE.getIndex()
////                && in.getTemplateID() < TemplateSMSType.PENPULLOUT.getIndex() )
////                || in.getTableID() <= 0)){
////            return false;
////        }else{
////            return true;
////        }
////    }
//}
