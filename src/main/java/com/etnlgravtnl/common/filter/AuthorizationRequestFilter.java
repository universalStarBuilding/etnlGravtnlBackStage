package com.etnlgravtnl.common.filter;


import com.etnlgravtnl.common.security.JwtUtil;
import com.etnlgravtnl.common.session.VirtualSession;
import com.etnlgravtnl.common.session.VirtualSessionManager;
import com.etnlgravtnl.modules.model.User;
import com.etnlgravtnl.modules.service.user.UserService;
import org.glassfish.jersey.server.ContainerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

/**
 * @Name:
 * @Author: junz（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2016-06-19（创建日期）
 * @Description:
 * 权限认证过滤器
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationRequestFilter implements ContainerRequestFilter
{
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationRequestFilter.class);
	private List<String>tokenCkList;
	@Inject
	javax.inject.Provider<UriInfo> uriInfo;

	@Autowired
	UserService userService;

	public AuthorizationRequestFilter(List<String> tokenCkList)
	{
		this.tokenCkList = tokenCkList;
	}
	public AuthorizationRequestFilter(){};

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException
	{
		//获取需检验的token url集合
		this.tokenCkList= FeatureAnnotatedFactory.gettokenCkListInstance();

		//得到访问的方法 例如GET,POST
		String method = requestContext.getMethod().toLowerCase();
		//得到访问路径
		String path = "/"+((ContainerRequest) requestContext).getPath(true).toLowerCase();

		//判断是否需要跳过验证token，默认为false
		boolean skipCheck=true;
		for(String url:tokenCkList)
		{
			if(path.equals(url.toLowerCase()))
			{
				skipCheck=false;
			}
		}
		if(skipCheck)
			return;

		//获取头信息中的token
		String tokenText=requestContext.getHeaderString("Authorization");

		//如果token为空抛出
		if (tokenText == null)
		{
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);//抛出未认证的错误
		}
		//把Bear Token换成Token
		String strToken= JwtUtil.extractJwtTokenFromAuthorizationHeader(tokenText);
		VirtualSession session= VirtualSessionManager.getInstance().getSession(tokenText, false);
		if(session==null)
		{
			throw new WebApplicationException("Authentication failed");
		}
		if (JwtUtil.isValid(strToken))
		{
			User user=(User)session.getAttribute("user");

			//注入SecurityContext
			requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,user, new String[]{"user"}));

			/*String userId=JwtUtil.getUserId(strToken);//反解出Name
			String[] roles=JwtUtil.getRoles(strToken);//反解出角色
			int version=JwtUtil.getVersion(strToken);//得到版本
			if(userId !=null&&roles.length!=0&&version!=-1)
			{
				VirtualSession session= VirtualSessionManager.getInstance().getSession(tokenText, false);
				if (session!=null && session.getAttribute("user")!=null)
				{
					final UserVo user=(UserVo)session.getAttribute("user");
					if(String.valueOf(user.getId()).equals(userId))
					{
						requestContext.setSecurityContext(new SecurityContextAuthorizer(uriInfo,user, new String[]{"user"}));
						return;
					}
					else{
						logger.info("UserVo not found " + userId);
					}
				}
				else {
					throw new WebApplicationException("Authentication failed");
				}


			}
			else {
				logger.info("name, roles or version missing from token");
			}*/
			return;
		}
		else
		{
			logger.info("token is invalid");
			System.out.println("token is invalid");
		}

	}

}