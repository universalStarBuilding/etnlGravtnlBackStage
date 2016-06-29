package com.etnlgravtnl.common.exception.MapperSupport;

import com.etnlgravtnl.common.exception.MapperSupport.Constant.WebExceptionType;

/**
 * Created by admin on 2016/6/24.
 */
public abstract class AbstractException extends RuntimeException{


    public AbstractException(Throwable ex){
        super(ex);
    }

    public abstract String getExceptionType();
    public abstract WebExceptionType getUserDefindExType();

}
