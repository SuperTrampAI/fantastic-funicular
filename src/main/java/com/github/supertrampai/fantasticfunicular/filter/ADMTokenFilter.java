/*
package com.github.supertrampai.fantasticfunicular.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.supertrampai.fantasticfunicular.Resp;
import com.github.supertrampai.fantasticfunicular.utils.RequestWrapper;
import com.github.supertrampai.fantasticfunicular.utils.TokenUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "admTokenFilter")
public class ADMTokenFilter implements Filter{
    private static List<String> excludeUrl = new ArrayList();
   /@Autowired
    private AdminerService adminerService;
    @Autowired
    private JedisService jedisService;
    @Value(value = "${fantasticfunicular.login.single}")
    private Boolean singleLogin;

    @Override
    public void init(FilterConfig filterConfig){
        excludeUrl.add("/login");
        excludeUrl.add("/sms");


        excludeUrl.add("/swagger-ui.html");
        excludeUrl.add("/swagger-resources/configuration/ui");
        excludeUrl.add("/swagger-resources/configuration/security");
        excludeUrl.add("/swagger-resources");
        excludeUrl.add("/v2/api-docs");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	 HttpServletRequest req = (HttpServletRequest)request;
         String token = req.getHeader("access-token");
     	String fUrl=req.getRequestURI().replaceFirst(req.getContextPath(), "");
     	String url = fUrl;
     	log.info("访问路径："+url+","+req.getPathInfo()+","+req.getContextPath());
         if(StringUtils.hasText(token)&&!"null".equals(token)&&!"/login".equals(fUrl)) {
             Map<String, String[]> m = new HashMap<>(req.getParameterMap());
             String uid="";
             try {
                 uid = TokenUtil.getUidFromToken(token);
             }catch(Exception e ) {
             	e.printStackTrace();
             }
             if (StringUtils.hasText(uid)) {
              	if(singleLogin) {
                  	String redisToken = jedisService.getTokenById(uid);
                  	if(!(StringUtils.hasText(redisToken) && redisToken.equals(token)) ) {
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(new Resp(401,"未登录或登录已过期。").toString());
                        return;
                  	}
              	}
            	 if(url.contains("adminer")) {
                 	AdmUser u = adminerService.getById(null,Integer.parseInt(uid));
                 	if(!u.getUsername().equals("admin")) {
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write(new Resp(403,"仅超级管理员可管理后台账号").toString());
                        return;
                 	}
            	 }

          		AdmAdminerfuncs f = adminerService.findFunctionByUrl(fUrl,Integer.parseInt(uid));
          		if(f==null) {
          			log.info("权限不足："+fUrl);
                     response.setCharacterEncoding("UTF-8");
                     response.setContentType("application/json;charset=UTF-8");
                     response.getWriter().write(new Resp(403,"权限不足："+fUrl).toString());
                     return;
          		}

                m.put("admid", new String[]{uid});
                req = new RequestWrapper(req, m);
                filterChain.doFilter(req, response);
                return;
             }
         }

         if(excludeUrl.contains(url) || url.contains("/webjars/")) {
             filterChain.doFilter(request, response);
             return;
         }else {
             response.setCharacterEncoding("UTF-8");
             response.setContentType("application/json;charset=UTF-8");
             response.getWriter().write(new Resp(401,"未登录或登录已过期。").toString());
         }
    }

    @Override
    public void destroy() {
    }



}
*/
