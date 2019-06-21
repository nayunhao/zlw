package com.zlead.interceptor;

import com.zlead.domain.IpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MoreRefreshInterceptor implements HandlerInterceptor {

    Logger logger= LoggerFactory.getLogger(MoreRefreshInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress=getIpAddress(request);
        HttpSession session=request.getSession();
        IpEntity ipEntity=(IpEntity) session.getAttribute(ipAddress);
        if(ipEntity==null){//通过
            IpEntity ipEntity1=new IpEntity();
            ipEntity1.setRecount(1);
            ipEntity1.setTime(System.currentTimeMillis());
            session.setAttribute(ipAddress,ipEntity1);
            return true;
        }else{
            if(ipEntity.getIpAddress()!=null){
                return false;
            }else{
                Long time = ipEntity.getTime();
                //如果时间为null表示单秒内刷新过快
                if(time==null){
                    IpEntity ipEn=new IpEntity();
                    ipEn.setIpAddress(ipAddress);
                    session.setAttribute(ipAddress, ipEn);
                    logger.info("刷新过快");
                    return false;
                }else{
                    //当前时间减去session中的时间大于两秒的
                    if(((System.currentTimeMillis() - time)/1000) > 2){
                        //当前时间离上一次请求时间大于2秒，可以直接通过,保存这次的请求
                        IpEntity ipEn1=new IpEntity();
                        ipEn1.setTime(System.currentTimeMillis());
                        ipEn1.setRecount(1);
                        session.setAttribute(ipAddress, ipEn1);
                        logger.info("大于两秒");
                        return true;
                    }else{
                        //小于两秒的
                        if(ipEntity.getRecount() > 12){
                            //小于2秒，并且2秒间隔之内请求了12次
                            logger.info("拦截请求");
                            //将ip放到session中用来禁止该ip访问
                            ipEntity.setIpAddress(ipAddress);
                            return false;
                        }else{
                            //小于2秒，但请求数小于12次，给对象添加
                            ipEntity.setTime(System.currentTimeMillis());
                            ipEntity.setRecount(ipEntity.getRecount()+1);
                            logger.info("访问次数"+ipEntity.getRecount());
                            request.getSession().setAttribute(ipAddress,ipEntity);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private String getIpAddress(HttpServletRequest request){
        String ip  =  request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        //获取真实ip
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
