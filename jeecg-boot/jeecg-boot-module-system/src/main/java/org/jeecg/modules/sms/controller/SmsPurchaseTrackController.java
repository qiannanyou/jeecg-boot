package org.jeecg.modules.sms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.modules.demo.test.entity.JoaDemo;
import org.jeecg.modules.sms.entity.SmsProducer;
import org.jeecg.modules.sms.entity.SmsPurchaseTrackDo;
import org.jeecg.modules.sms.service.ISmsPurchaseTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 采购单追踪号
 *
 * @author lintt
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/sms/purchaseTrack")
public class SmsPurchaseTrackController {
@Autowired
    private ISmsPurchaseTrackService smsPurchaseTrackService;

/*
 * 新增采购订单
 * @param [purchaseTrackDo]
 * @return 
 * @Author: ltt
 */
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody SmsPurchaseTrackDo purchaseTrackDo){
        Result<String> result = new Result<>();
        try {
            QueryWrapper<SmsPurchaseTrackDo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SmsPurchaseTrackDo::getPurchaseNo, purchaseTrackDo.getPurchaseNo());
            List<SmsPurchaseTrackDo> list = smsPurchaseTrackService.list(queryWrapper);
            if (list.size() > 0) {
                result.setMessage("采购订单已存在");
                result.setSuccess(false);
                return result;
            }
            String track_num = UUIDGenerator.generate();
            String purchase_id = UUIDGenerator.generate();
            purchaseTrackDo.setTrackNum(track_num);
            purchaseTrackDo.setPurchaseId((int)((Math.random()*9+1)*100000));
            boolean success = smsPurchaseTrackService.saveOrUpdate(purchaseTrackDo);
            result.setMessage(success?"新增采购订单成功":"新增采购订单失败");
            result.setSuccess(success);
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }

    @GetMapping("/list")
    public Result<IPage<SmsPurchaseTrackDo>> purchaseTrackList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, @RequestParam(name="purchaseNo",required = false) String purchaseNo,@RequestParam(name="isRecv",defaultValue = "0",required = false) Integer isRecv){
        Result<IPage<SmsPurchaseTrackDo>> result = new Result<IPage<SmsPurchaseTrackDo>>();
        QueryWrapper<SmsPurchaseTrackDo> queryWrapper = new QueryWrapper<>();
        Page<SmsPurchaseTrackDo> page = new Page<SmsPurchaseTrackDo>(pageNo, pageSize);
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        String userId = sysUser.getId();
//        queryWrapper.lambda().eq(SmsPurchaseTrackDo::getCreateId, userId);
        if (!purchaseNo.isEmpty()) {
            queryWrapper.lambda().like(SmsPurchaseTrackDo::getPurchaseNo ,purchaseNo);
        }
        queryWrapper.lambda().eq(SmsPurchaseTrackDo::getIsRecv, isRecv);
        queryWrapper.orderByDesc("create_time");

        IPage<SmsPurchaseTrackDo> list = smsPurchaseTrackService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    @PostMapping("/edit")
    public Result<String> producerUpdate(@RequestBody SmsPurchaseTrackDo smsPurchaseTrackDo){
        Result<String> result = new Result<>();
        try {
            boolean success = smsPurchaseTrackService.updateById(smsPurchaseTrackDo);
            result.setMessage(success?"修改采购订单成功":"修改采购订单失败");
            result.setSuccess(success);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
        return result;
    }

    @PostMapping("/updateStatusBatch")
    public Result<String> updateStatusBatch(@RequestParam(name="ids",required=true) String ids){
        Result<String> result = new Result<>();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        List<String> asList = Arrays.asList(ids.split(","));
        List<SmsPurchaseTrackDo> array = new ArrayList<>();
        for (String id:asList) {
            SmsPurchaseTrackDo item = smsPurchaseTrackService.getById(id);
            item.setIsRecv(1);
            array.add(item);
        }
        boolean batch = smsPurchaseTrackService.updateBatchById(array);
        result.setMessage(batch?"操作成功":"操作失败");
        result.setSuccess(batch);
        return result;
    }

    @DeleteMapping("/delete")
    public Result<?> producerDelete(@RequestParam(name="id",required=true) String id){
        boolean b = smsPurchaseTrackService.removeById(id);
        if (b){
            return Result.ok("删除采购订单成功");
        }
        return Result.error("删除采购订单失败");
    }

    @DeleteMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids){
        boolean b = smsPurchaseTrackService.removeByIds(Arrays.asList(ids.split(",")));
        if (b) {
            return Result.ok("批量删除供应商成功");
        }
        return Result.error("批量删除供应商失败");
    }

}

