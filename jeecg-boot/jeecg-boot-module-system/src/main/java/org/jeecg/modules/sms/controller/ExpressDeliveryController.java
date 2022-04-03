package org.jeecg.modules.sms.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.sms.entity.ExpressDeliveryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

@RestController
@RequestMapping("/sms/express")
public class ExpressDeliveryController {
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("查询快递")
    @RequestMapping(value = "/getExpressDelivery", method = RequestMethod.GET)
    @ResponseBody
    public Result<?> expressDelivery(@RequestParam("no") String no, @RequestParam(value = "type",required = false) String type) throws IOException {
        Result<String> result = new Result<>();
        //获取缓存
        Object recordJson = redisUtil.get(no);
        if (recordJson != null) {
            return result.OK(recordJson);
        }
//        String expressDelivery = getExpressDeliveryHandle(no);
//        if (expressDelivery.isEmpty()){
//            return result.error("操作失败");
//        }
//        redisUtil.set(no, expressDelivery,3600);
//        return result.OK(expressDelivery);
        String host = "https://kdwlcxf.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
        String path = "/kdwlcx";// 【2】后缀
        String appcode = "a55e877e46fd4b88b075b8228dcd5ff4"; // 【3】开通服务后 买家中心-查看AppCode
        //【4】no 请求参数---快递单号，详见文档描述
        //【5】请求参数---快递公司代码，不知道可不填 95%能自动识别
        String urlSend = host + path + "?no=" + no + "&type=" + type; // 【6】拼接请求链接

        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
            // (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json:" + json);
                //设置有效时间
                redisUtil.set(no, json,10800);
                return result.OK(json);
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    System.out.println("AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    System.out.println("请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    System.out.println("参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    System.out.println("套餐包次数用完 ");
                } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
                    System.out.println("套餐包次数用完，请续购套餐");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
        } catch (UnknownHostException e) {
            System.out.println("URL地址错误");
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            // e.printStackTrace();
        }
        return result.error("操作失败");
    }
    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private static String getExpressDeliveryHandle(String no) throws IOException {
        URL url = new URL("http://www.kuaidi.com/index-ajaxselectinfo-"+no+".html");
        HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
        String json = read(httpURLCon.getInputStream());
        JSONObject parseObject = parseObject(json);
        Object expressObj = parseObject.get('0');
        ExpressDeliveryDao expressDeliveryDao = (ExpressDeliveryDao)JSONObject.parseObject(String.valueOf(expressObj),ExpressDeliveryDao.class);

        String timer = String.valueOf(System.currentTimeMillis());// 当前时间戳
        URL url_kd = new URL("http://www.kuaidi.com/index-ajaxselectcourierinfo-"+ no+"-"+expressDeliveryDao.getExname()+"-KUAIDIC0M"+timer+".html");
        HttpURLConnection httpURLCon_kd = (HttpURLConnection) url_kd.openConnection();
        String json_kd = read(httpURLCon_kd.getInputStream());
        return json_kd;
    }
}
