package com.etnlgravtnl.common.exception.MapperSupport;

import com.etnlgravtnl.common.exception.MapperSupport.Constant.WebExceptionType;

/**
 * Created by admin on 2016/6/24.
 */
public class WebActionException extends AbstractException {
    protected WebExceptionType type;
    protected Object data;
    public WebActionException(Throwable ex, WebExceptionType type, Object data){
        super(ex);
        this.type = type;
        this.data = data;
    }

    @Override
    public String getExceptionType() {
        return "WebActionExceptionType";
    }

    public WebActionException(WebExceptionType type, Object data){
        this(null,type,data);
    }
    @Override
    public WebExceptionType getUserDefindExType(){
        return this.type;
    }
    public  Object getData(){
        return  this.data;
    }

}
