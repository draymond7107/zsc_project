//package com.zsc.base.spring.resolver;
//import com.zsc.base.auth.UserSession;
//import com.zsc.base.exception.TokenException;
//import com.zsc.base.spring.SpringContext;
//import com.zsc.base.spring.annotation.LoginAuth;
//import com.zsc.base.spring.annotation.LoginedAuth;
//import com.zsc.base.utils.StringUtils;
//
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.core.MethodParameter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import java.lang.annotation.Annotation;
//
///**
// * 登陆解析器
// * 功能说明：<br>
// * 模块名称：<br>
// * 功能描述：LoginedArgumentResolver<br>
// * 文件名称: LoginedArgumentResolver.java<br>
// * 系统名称：ICELOVE<br>
// * 软件著作权：ICELOVE 版权所有<br>
// * 开发人员：lujun <br>
// * 开发时间：2016-8-29 下午9:56:54<br>
// * 系统版本：1.0.0<br>
// */
//public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
//    private Log logger = LogFactory.getLog(LoginArgumentResolver.class);
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        if (!parameter.hasParameterAnnotations()) return false;
//        boolean find = false;
//        Annotation[] anns = parameter.getParameterAnnotations();
//        for (Annotation ann : anns) {
//            if (LoginAuth.class.isInstance(ann) || LoginedAuth.class.isInstance(ann)) {
//                find = true;
//                break;
//            }
//        }
//        if (!find) return false;
//        Class<?> type = parameter.getParameterType();
//       // if (type == UserSession.class || type == AdminSession.class) return true;
//        return false;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Class<?> type = parameter.getParameterType();
//        String openIdStr = SecurityContextHolder.getContext().getAuthentication().getName();
//        if(parameter.hasParameterAnnotation(LoginedAuth.class)){
//            if (StringUtils.isEmpty(openIdStr)) {
//                throw new TokenException("请登录");
//            } else if (!openIdStr.contains("-")) {
//                throw new TokenException("请登录");
//            }
//        }else if(parameter.hasParameterAnnotation(LoginAuth.class)){
//            if (StringUtils.isEmpty(openIdStr) || !openIdStr.contains("-")) {
//                return null;
//            }
//        }
//        String[] strs = StringUtils.split(openIdStr, "-");
//        String openId = (strs != null && strs.length > 1) ? strs[0] : "";
//        logger.error("openId="+openId);
//        if (type == UserSession.class) {
//            UserSession userSession = null;
//            if (StringUtils.isNotEmpty(openId)) {
////                UserServiceExt userServiceExt = SpringContext.getBean("userServiceExt", UserServiceExt.class);
////                userSession = userServiceExt.getUserSession(openId);
//                logger.error("用户登录信息："+openId+">>>"+(userSession==null));
//            } else {
//                logger.error("openId is empty");
//            }
//            if (userSession == null) {
//                logger.error("openId is not find="+openId);
//                if (parameter.hasParameterAnnotation(LoginedAuth.class)) {
//                    throw new TokenException("请登录");
//                }
//            }
//            return userSession;
////        } else if (type == AdminSession.class) {
////            AdminSession adminSession = null;
////            if (StringUtils.isNotEmpty(openId)) {
//////                AdminServiceExt adminServiceExt = SpringContext.getBean("adminServiceExt", AdminServiceExt.class);
//////                adminSession = adminServiceExt.getAdminSession(openId);
////                logger.error("管理员登录信息："+openId+">>>"+(adminSession==null));
////            } else {
////                logger.error("openId is empty");
////            }
////            if (adminSession == null) {
////                logger.error("openId is not find="+openId);
////                //必须登录
////                throw new TokenException("请登录");
////            }
////            return adminSession;
//        }
//        return null;
//    }
//}
