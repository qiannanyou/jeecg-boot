package org.jeecg.modules.system.test;

import org.jeecg.JeecgSystemApplication;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.RestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;

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

/**
 * 系统用户单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgSystemApplication.class)
@SuppressWarnings({"FieldCanBeLocal", "SpringJavaAutowiredMembersInspection"})
public class SysUserTest {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 测试地址：实际使用时替换成你自己的地址
     */
    private final String BASE_URL = "http://localhost:8080/jeecg-boot/sys/user/";

    // 请实际使用时替换成你自己的用户名和密码
    private final String USERNAME = "admin";
    private final String PASSWORD = "123456";

    /**
     * 测试用例：新增
     */
    @Test
    public void testAdd() throws IOException {
//        // 用户Token
//        String token = this.getToken();
//        // 请求地址
//        String url = BASE_URL + "add" ;
//        // 请求 Header （用于传递Token）
//        HttpHeaders headers = this.getHeaders(token);
//        // 请求方式是 POST 代表提交新增数据
//        HttpMethod method = HttpMethod.POST;
//
//        System.out.println("请求地址：" + url);
//        System.out.println("请求方式：" + method);
//        System.out.println("请求Token：" + token);
//
//        JSONObject params = new JSONObject();
//        params.put("username", "wangwuTest");
//        params.put("password", "123456");
//        params.put("confirmpassword","123456");
//        params.put("realname", "单元测试");
//        params.put("activitiSync", "1");
//        params.put("userIdentity","1");
//        params.put("workNo","0025");
//
//        System.out.println("请求参数：" + params.toJSONString());
//
//        // 利用 RestUtil 请求该url
//        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, null, params, JSONObject.class);
//        if (result != null && result.getBody() != null) {
//            System.out.println("返回结果：" + result.getBody().toJSONString());
//        } else {
//            System.out.println("查询失败");
//        }
//        String no = "";
//        //获取缓存
//        Object recordJson = redisUtil.get(no);
//        if (recordJson != null) {
//            System.out.println("读取到缓存-------：" + recordJson);
//            return;
//        }
//        String host = "https://kdwlcxf.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
//        String path = "/kdwlcx";// 【2】后缀
//        String appcode = "a55e877e46fd4b88b075b8228dcd5ff4"; // 【3】开通服务后 买家中心-查看AppCode
//        //【4】no 请求参数---快递单号，详见文档描述
//        //【5】请求参数---快递公司代码，不知道可不填 95%能自动识别
//        String urlSend = host + path + "?no=" + no + "&type=" + ""; // 【6】拼接请求链接
//
//        try {
//            URL url = new URL(urlSend);
//            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
//            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
//            // (中间是英文空格)
//            int httpCode = httpURLCon.getResponseCode();
//            if (httpCode == 200) {
//                String json = read(httpURLCon.getInputStream());
//                System.out.println("正常请求计费(其他均不计费)");
//                System.out.println("获取返回的json:" + json);
//                //设置有效时间
//                redisUtil.set(no, json,10800);
//                System.out.println("请求结果-------：" + recordJson);
//            } else {
//                Map<String, List<String>> map = httpURLCon.getHeaderFields();
//                String error = map.get("X-Ca-Error-Message").get(0);
//                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
//                    System.out.println("AppCode错误 ");
//                } else if (httpCode == 400 && error.equals("Invalid Url")) {
//                    System.out.println("请求的 Method、Path 或者环境错误");
//                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
//                    System.out.println("参数错误");
//                } else if (httpCode == 403 && error.equals("Unauthorized")) {
//                    System.out.println("服务未被授权（或URL和Path不正确）");
//                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
//                    System.out.println("套餐包次数用完 ");
//                } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
//                    System.out.println("套餐包次数用完，请续购套餐");
//                } else {
//                    System.out.println("参数名错误 或 其他错误");
//                    System.out.println(error);
//                }
//            }
//
//        } catch (MalformedURLException e) {
//            System.out.println("URL格式错误");
//        } catch (UnknownHostException e) {
//            System.out.println("URL地址错误");
//        } catch (Exception e) {
//            // 打开注释查看详细报错异常信息
//            // e.printStackTrace();
//        }
        URL url = new URL("http://www.kuaidi.com/index-ajaxselectinfo-777080346702789.html");
        HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
        System.out.println(httpURLCon);
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
    /**
     * 测试用例：修改
     */
    @Test
    public void testEdit() {
        // 数据Id
        String dataId = "1331795062924374018";
        // 用户Token
        String token = this.getToken();
        // 请求地址
        String url = BASE_URL + "edit";
        // 请求 Header （用于传递Token）
        HttpHeaders headers = this.getHeaders(token);
        // 请求方式是 PUT 代表提交修改数据
        HttpMethod method = HttpMethod.PUT;

        System.out.println("请求地址：" + url);
        System.out.println("请求方式：" + method);
        System.out.println("请求Token：" + token);

        JSONObject params = new JSONObject();
        params.put("username", "wangwuTest");
        params.put("realname", "单元测试1111");
        params.put("activitiSync", "1");
        params.put("userIdentity","1");
        params.put("workNo","0025");
        params.put("id",dataId);

        System.out.println("请求参数：" + params.toJSONString());

        // 利用 RestUtil 请求该url
        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, null, params, JSONObject.class);
        if (result != null && result.getBody() != null) {
            System.out.println("返回结果：" + result.getBody().toJSONString());
        } else {
            System.out.println("查询失败");
        }
    }


    /**
     * 测试用例：删除
     */
    @Test
    public void testDelete() {
        // 数据Id
        String dataId = "1331795062924374018";
        // 用户Token
        String token = this.getToken();
        // 请求地址
        String url = BASE_URL + "delete" + "?id=" + dataId;
        // 请求 Header （用于传递Token）
        HttpHeaders headers = this.getHeaders(token);
        // 请求方式是 DELETE 代表删除数据
        HttpMethod method = HttpMethod.DELETE;

        System.out.println("请求地址：" + url);
        System.out.println("请求方式：" + method);
        System.out.println("请求Token：" + token);

        // 利用 RestUtil 请求该url
        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, null, null, JSONObject.class);
        if (result != null && result.getBody() != null) {
            System.out.println("返回结果：" + result.getBody().toJSONString());
        } else {
            System.out.println("查询失败");
        }
    }

    /**
     * 测试用例：查询记录
     */
    @Test
    public void testQuery() {
        // 用户Token
        String token = this.getToken();
        // 请求地址
        String url = BASE_URL + "list";
        // 请求 Header （用于传递Token）
        HttpHeaders headers = this.getHeaders(token);
        // 请求方式是 GET 代表获取数据
        HttpMethod method = HttpMethod.GET;

        System.out.println("请求地址：" + url);
        System.out.println("请求方式：" + method);
        System.out.println("请求Token：" + token);

        // 利用 RestUtil 请求该url
        ResponseEntity<JSONObject> result = RestUtil.request(url, method, headers, null, null, JSONObject.class);
        if (result != null && result.getBody() != null) {
            System.out.println("返回结果：" + result.getBody().toJSONString());
        } else {
            System.out.println("查询失败");
        }
    }

    private String getToken() {
        String token = JwtUtil.sign(USERNAME, PASSWORD);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, 60);
        return token;
    }

    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        String mediaType = MediaType.APPLICATION_JSON_UTF8_VALUE;
        headers.setContentType(MediaType.parseMediaType(mediaType));
        headers.set("Accept", mediaType);
        headers.set("X-Access-Token", token);
        return headers;
    }
}
