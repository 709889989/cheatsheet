package com.example.demo;

import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderFacade;
import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderRequest;
import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderResponse;
import com.howbuy.tms.common.client.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/8/12 14:41
 * @Version 1.0
 */
@Slf4j
@RequestMapping("/hello")
@RestController
public class TestController {

    @Reference
    private QueryRefundOrderFacade queryRefundOrderFacade;

    @RequestMapping("/world")
    public String test(){
        queryRefundOrderFacade();
        return "hello world";
    }
    /**
     * 退款订单查询
     */
    private void queryRefundOrderFacade(){
        QueryRefundOrderRequest request = new QueryRefundOrderRequest();
        setCommonParams(request);
//            List<String> dtlNo = new ArrayList<>();
//            request.setDealDtlNos(dtlNo);
//            dtlNo.add("1019050710512800001187515");
        List<String> dealNo = new ArrayList<>();
//        request.setDealNos(dealNo);
        dealNo.add("1019050804453700000002304");


        QueryRefundOrderResponse response = queryRefundOrderFacade.execute(request);
        log.info("response={}", response);

    }

    private void setCommonParams(BaseRequest request){
        request.setDisCode("HB000A001");
        request.setOutletCode("H20131104");
        request.setOperIp("192.168.0.1");
        request.setTxChannel("4");
        request.setPageNo(0);
        request.setPageSize(20);
    }
}
