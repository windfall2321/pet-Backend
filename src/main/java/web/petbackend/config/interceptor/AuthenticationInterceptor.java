package web.petbackend.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import web.petbackend.config.exception.BusinessException;
import web.petbackend.config.exception.ErrorCode;
import web.petbackend.utils.JwtUtil;
import web.petbackend.utils.UserContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String[] WHITE_LIST = {
            "/pet/api/user/login",
            "/pet/api/user/register",
            "/pet/swagger-ui",
            "/pet/v3/api-docs",
            "/pet/swagger-resources"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("拦截到请求: {}", requestURI);
        
        // 检查是否是白名单路径
        for (String whitePath : WHITE_LIST) {
            log.info("检查白名单路径: {}", whitePath);
            if (requestURI.equals(whitePath)) {
                log.info("请求路径在白名单中，直接放行");
                return true;
            }
        }

        // 获取token
        String token = request.getHeader("Authorization");
        log.info("获取到的token: {}", token);
        
        if (token == null || token.isEmpty()) {
            log.error("未获取到token");
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR.getCode(), "未登录");
        }

        try {
            // 解析token获取用户ID
            Integer userId = JwtUtil.parseToken(token);
            log.info("解析token成功，用户ID: {}", userId);
            // 设置到ThreadLocal
            UserContextHolder.setUserId(userId);
            return true;
        } catch (Exception e) {
            log.error("token解析失败: {}", e.getMessage());
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR.getCode(), "登录已过期");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("请求完成，清理ThreadLocal");
        UserContextHolder.clear();
    }
} 