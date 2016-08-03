package com.etnlgravtnl.modules.storeManagement.webservice;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.etnlgravtnl.common.utils.JqueryDataTableUtils;
import com.etnlgravtnl.common.utils.StringUtils;
import com.etnlgravtnl.common.web.BaseController;
import com.etnlgravtnl.modules.storeManagement.entity.SellShops;
import com.etnlgravtnl.modules.storeManagement.service.StoreRegulatoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Path("/storeRegulatory")
public class StoreRegulatoryController extends BaseController {

    @Autowired
    private StoreRegulatoryService storeRegulatoryService;

    @Context
    SecurityContext securityContext;


    @POST
    @Path("getStoreInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getStoreInfo(String tableDataParamJsonStr)
    {
        Map<String,String> dataTableParams= JqueryDataTableUtils.returnDataTableParams(tableDataParamJsonStr);
        SellShops sellShops=new SellShops();
        sellShops.setStartPage(Integer.parseInt(dataTableParams.get("start")));
        sellShops.setEndPage(sellShops.getStartPage()+Integer.parseInt(dataTableParams.get("length")));
        sellShops.setSearchSql(dataTableParams.get("searchSqlText"));
        sellShops.setOrderSqlText(dataTableParams.get("orderSqlText"));

        String recordsTotal=String.valueOf(storeRegulatoryService.getTotalShopsCount(sellShops));
        String recordsFiltered=recordsTotal;
        List<SellShops> sellShopsList = storeRegulatoryService.getShopsInfoList(sellShops);

        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", sellShopsList);
        info.put("recordsTotal", recordsTotal);
        info.put("recordsFiltered", recordsFiltered);
        info.put("draw", dataTableParams.get("draw"));
        System.out.println(JSON.toJSONString(info));
        return JSON.toJSONString(info);
    }

    @POST
    @Path("logicallyDeleted")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void logicallyDeleted(String id)
    {
        JSONObject idJson=JSON.parseObject(id);
        storeRegulatoryService.logicallyDeleted(idJson.get("id").toString());
    }

    @POST
    @Path("updateStoreInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStoreInfo(SellShops sellShops)
    {
        storeRegulatoryService.update(sellShops);
    }

    @POST
    @Path("closeStore")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void closeStore(String params)
    {
        JSONObject paramsJson=JSON.parseObject(params);
        storeRegulatoryService.updateStoreStatus(paramsJson.get("id").toString(),Boolean.parseBoolean(paramsJson.get("closeFlag").toString()));
    }

    @POST
    @Path("getCertifiedSellerInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getCertifiedSellerInfo(String tableDataParamJsonStr)
    {
        Map<String,String> dataTableParams= JqueryDataTableUtils.returnDataTableParams(tableDataParamJsonStr);
        SellShops sellShops=new SellShops();
        sellShops.setStartPage(Integer.parseInt(dataTableParams.get("start")));
        sellShops.setEndPage(sellShops.getStartPage()+Integer.parseInt(dataTableParams.get("length")));
        sellShops.setSearchSql(dataTableParams.get("searchSqlText"));
        sellShops.setOrderSqlText(dataTableParams.get("orderSqlText"));

        String recordsTotal=String.valueOf(storeRegulatoryService.getTotalCertifiedSellerCount(sellShops));
        String recordsFiltered=recordsTotal;
        List<SellShops> sellShopsList = storeRegulatoryService.getCertifiedSellerInfo(sellShops);

        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", sellShopsList);
        info.put("recordsTotal", recordsTotal);
        info.put("recordsFiltered", recordsFiltered);
        info.put("draw", dataTableParams.get("draw"));
        return JSON.toJSONString(info);
    }

    @POST
    @Path("certifiedSeller")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void certifiedSeller(String params)
    {
        JSONObject paramsJson=JSON.parseObject(params);
        storeRegulatoryService.certifiedSeller(paramsJson.get("id").toString(),paramsJson.get("certifiedFlag").toString());
    }


    public static void main(String[] args) {
        String key="columns[0][data]";
        String rex="columns\\[([0-9])\\]\\[data\\]";
        Pattern p=Pattern.compile(rex);
        Matcher matcher=p.matcher(key);
        if(matcher.find())
        {
            System.out.println(matcher.group(1));
            //  colsList.add();
        }
    }

}
