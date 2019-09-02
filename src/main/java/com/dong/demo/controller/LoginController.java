package com.dong.demo.controller;


import com.dong.demo.domain.User;
import com.dong.demo.service.UserService;
import com.dong.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/admin")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    //处理验证码
    @RequestMapping("/imageCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //这个方法实现验证码的生成
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);//创建图像缓冲区
        Graphics g = bi.getGraphics(); //通过缓冲区创建一个画布
        Color c = new Color(200, 150, 255); //创建颜色
        /*根据背景画了一个矩形框
         */
        g.setColor(c);//为画布创建背景颜色
        g.fillRect(0, 0, 68, 22); //fillRect:填充指定的矩形

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//转化为字符型的数组
        Random r = new Random();
        int len = ch.length;
        int index; //index用于存放随机数字
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);//产生随机数字
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  //设置颜色
            g.drawString(ch[index] + "", (i * 15) + 3, 18);//画数字以及数字的位置
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("imgCode", sb.toString()); //将数字保留在session中，便于后续的使用
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    @PostMapping (value = "/login")
    public Map<String,Object> login(@RequestBody Map param, HttpServletRequest request,
                                    HttpServletResponse response,HttpSession session){
        Map<String,Object> result = new HashMap<>();

        String username = param.get("username").toString();
        String password = param.get("password").toString();
        //session中取出验证码
        String imgCode = (String) session.getAttribute("imgCode");
        //用户输入验证码
        String imageCode = "";
        if (param.get("imgCode") != null) {
            imageCode = param.get("imgCode").toString();
        }

        User userParam = new User();
        userParam.setUsername(username);
        userParam.setPassword(password);

        User user = userService.selectSingleUser(userParam);

        //校验账号密码
        if (user == null) {
            session.setAttribute("errorMsg","账号密码错误！请检查！");
            result.put("msg","2");
        }else if(!imgCode.equalsIgnoreCase(imageCode)){
            session.setAttribute("errorMsg","验证码错误！请检查！");
            result.put("msg","3");
        }else{

            if (param.get("remember")!=null){
                String remember = param.get("remember").toString();
                if (remember != null) {
                    Cookie cookieName=new Cookie("username", username);
                    cookieName.setPath(request.getContextPath());
                    cookieName.setPath("/");
                    cookieName.setMaxAge(60*60);
                    Cookie cookiePassword=new Cookie("password", password);
                    cookiePassword.setPath(request.getContextPath());
                    cookiePassword.setPath(request.getContextPath());
                    cookiePassword.setPath("/");
                    cookiePassword.setMaxAge(60*60);
                    response.addCookie(cookieName);
                    response.addCookie(cookiePassword);
                }else{
                    Cookie cookies[] = request.getCookies();
                    if (cookies != null) {
                        for (int i = 0; i < cookies.length; i++) {
                            cookies[i].setValue("");
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookies[i]);}
                    }
                }
            }

            //生成token
            String token = JwtUtil.createToken(user);
            result.put("token",token);
            session.setAttribute("user",user);
            result.put("msg","1");
        }

        return result;
    }

}
