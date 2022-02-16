package org.jeecg.modules.sms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.sms.entity.SmsProducer;
import org.jeecg.modules.sms.service.ISmsProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 采购管理控制器
 * <p>
 *
 * @Author ltt
 * @Date 2022/2/2 下午10:28
 */
@Slf4j
@RestController
@RequestMapping("/sms/purchase")
public class PurchasingManageController {
    @Autowired
    private ISmsProducerService smsProducerService;

    /*
     * 新增供应商
     * @param [producer]
     * @return
     **/
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody SmsProducer producer){
        Result<String> result = new Result<>();
        try {
            QueryWrapper<SmsProducer> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SmsProducer::getName, producer.getName());
            List<SmsProducer> list = smsProducerService.list(queryWrapper);
            if (list.size() > 0) {
                result.setMessage("供应商名称已存在");
                result.setSuccess(false);
                return result;
            }
            queryWrapper.lambda().eq(SmsProducer::getCode,producer.getCode());
            List<SmsProducer> codeList = smsProducerService.list(queryWrapper);
            if (codeList.size() > 0) {
                result.setMessage("供应商编号已存在");
                result.setSuccess(false);
                return result;
            }
            int success = smsProducerService.saveProducer(producer);
            result.setMessage(success==1?"新增供应商成功":"新增供应商失败");
            result.setSuccess(success==1?true:false);
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
    }
    
    /*
     * 获取供应商列表
     * @param [smsProducer]
     * @return 
     **/
    @GetMapping("/list")
    public Result<IPage<SmsProducer>> producerList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req){
        Result<IPage<SmsProducer>> result = new Result<IPage<SmsProducer>>();
        String search = req.getParameter("search");
        Page<SmsProducer> page = new Page<SmsProducer>(pageNo, pageSize);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String userId = sysUser.getId();
        QueryWrapper<SmsProducer> queryWrapper = new QueryWrapper<SmsProducer>();
        queryWrapper.lambda().eq(SmsProducer::getCreateId, userId);
        if (StringUtils.isNotEmpty(search)) {
            queryWrapper.like("name" ,search);
            queryWrapper.or().like("code",search);
        }
        IPage<SmsProducer> list = smsProducerService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    /*
     * 修改供应商
     * @param [producer]
     * @return 
     * @Author: ltt
     */
    @PostMapping("/edit")
    public Result<String> producerUpdate(@RequestBody SmsProducer producer){
        Result<String> result = new Result<>();
        try {
            boolean success = smsProducerService.updateById(producer);
            result.setMessage(success?"修改供应商成功":"修改供应商失败");
            result.setSuccess(success);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("出错了: " + e.getMessage());
            return result;
        }
        return result;
    }

    /*
     * 删除供应商
     * @param [id]
     * @return 
     * @Author: ltt
     */
    @DeleteMapping("/delete")
    public Result<?> producerDelete(@RequestParam(name="id",required=true) String id){
        boolean b = smsProducerService.deleteProducer(id);
        if (b){
            return Result.ok("删除供应商成功");
        }
        return Result.error("删除供应商失败");
    }
    
    /*
     * 批量删除供应商
     * @param [ids]
     * @return 
     * @Author: ltt
     */
    @DeleteMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids){
        boolean b = smsProducerService.deleteBatchProducers(ids);
        if (b) {
            return Result.ok("批量删除供应商成功");
        }
        return Result.error("批量删除供应商失败");
    }

}
