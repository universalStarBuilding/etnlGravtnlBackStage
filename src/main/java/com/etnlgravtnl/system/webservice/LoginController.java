package com.etnlgravtnl.system.webservice;


import com.etnlgravtnl.common.persistence.AuthenticationResult;
import com.etnlgravtnl.common.security.JwtUtil;
import com.etnlgravtnl.common.session.VirtualSession;
import com.etnlgravtnl.common.session.VirtualSessionManager;
import com.etnlgravtnl.common.utils.StringUtils;
import com.etnlgravtnl.modules.model.User;
import com.etnlgravtnl.modules.service.user.UserService;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *登录 Controller
 *Controller
 * @author Junz
 * @version 2015-05-19
 */
@Path("/")
public class LoginController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public  Viewable  welcome2System()
    {
        return new Viewable("/WEB-INF/views/modules/system/login", null);
    }


    @POST
    @Path("doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AuthenticationResult doLogin(User user) {
        AuthenticationResult result = new AuthenticationResult();
        user = userService.get(user);
        if (user == null) {
            //异常处理机制，记录异常
            return null;
        }
        //判断用户在已经登录且session未失效时，重复登录时的处理
        if (StringUtils.isNotBlank(user.getToken())) {
            VirtualSession virtualSession =
                    VirtualSessionManager.getInstance().getSession(user.getToken(), false);
            if (virtualSession == null) {

            }
        } else {
            //验证登录等相关操作，暂时省略，等认证以及权限机制搞定再去处理
            //.........
            //登录成功，生成token，生成用户虚拟session
            String token = JwtUtil.generateToken(String.valueOf(user.getId()));
            VirtualSessionManager.getInstance().getSession(token, true).addAttribute("user", user);
            System.out.println(VirtualSessionManager.getInstance().getSession(token, false));
            logger.debug("login token=" + token);
            result.setToken(token);
        }
        return result;
    }

}
