package com.dong.demo.interceptor;

import com.auth0.jwt.JWT;
import com.dong.demo.annotation.PassToken;
import com.dong.demo.annotation.UserLoginToken;
import com.dong.demo.domain.User;
import com.dong.demo.service.UserService;
import com.dong.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-20
 **/
public class TraceInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(TraceInterceptor.class);

    /**
     * 预处理回调函数
     * 进入Controller之前执行
     * 如果返回 true，则进入下一个拦截器，所有拦截器全部通过，则进入 Controller 相应的方法
     * 如果返回 false，则请求被拦截。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("entry preHandler");
        String uri = request.getRequestURI();
        log.info("<-------------登录验证开始----------------------");
        log.info("request uri: {}", uri);

//        // 如果不是映射到方法直接通过
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod=(HandlerMethod)handler;
//        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()){
//                return true;
//            }
//        }
        /**
         * session 验证
         * */
        HttpSession session = request.getSession(false);
        //判断是否登录，登录，放行
        if (session != null && session.getAttribute("user")!=null){
            log.info("登录验证成功！！");
            return true;
        }else{
            log.info("登录验证失败！！");
            request.getSession().setAttribute("errorMsg","没有权限，请重新登录！");
            response.sendRedirect("localhost:8089/login");
//            request.setAttribute("errorMsg","没有权限，请重新登录！");
//            request.getRequestDispatcher("login").forward(request,response);
            return false;
        }
//        /**
//         * token 验证
//         * */
//        String token = request.getHeader("token");//从http请求头获取token
//        //执行认证
//        if (token != null) {
//            //获取token中userId
//            Integer userId;
//            userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
//
//            User userParam = new User();
//            userParam.setId(userId);
//            User user = userService.selectSingleUser(userParam);
//            if (user == null){
//                request.getSession().setAttribute("errorMsg","用户不存在，请重新登录！");
//                response.sendRedirect("localhost:8089/login");
//                return false;
//            }
//
//            //验证token
//            JwtUtil.verify(token,user);
//
//            log.info("登录验证成功！！");
//            return true;
//        }else{
//            log.info("登录验证失败！！");
//            request.getSession().setAttribute("errorMsg","无token，请重新登录！");
//            response.sendRedirect("localhost:8089/login");
//            return false;
//        }

    }

    /**
     * 后处理回调方法
     * 经过Controller处理之后执行
     * 在此处可以对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("entry postHandler");
        log.info("---------------登录验证结束-------------------->");
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调
     * 在此处可以进行一些资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
