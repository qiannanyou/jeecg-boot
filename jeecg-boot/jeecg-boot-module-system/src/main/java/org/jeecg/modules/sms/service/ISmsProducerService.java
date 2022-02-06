package org.jeecg.modules.sms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sms.entity.SmsProducer;

import java.util.List;

/**
 * <p>
 * 供应商表
 * <p>
 *
 * @Author ltt
 * @Date 2022/2/2 下午10:28
 */
public interface ISmsProducerService extends IService<SmsProducer> {
    /**
     * 新增供应商
     *
     * @param smsProducer 供应商信息
     * @return 结果
     */
    public int saveProducer(SmsProducer smsProducer);

    /**
     * 获取供应商列表
     *
     * @return 供应商信息
     */
    public List<SmsProducer> selectProducerList(SmsProducer smsProducer);

    /**
     * 更新供应商信息
     *
     * @param smsProducer 供应商信息
     * @return 结果
     */
    public int updateProducer(SmsProducer smsProducer);

    /**
     * 更新供应商信息
     *
     * @param producerId 供应商ID
     * @return 结果
     */
    public int deleteProducer(Long producerId);
}
