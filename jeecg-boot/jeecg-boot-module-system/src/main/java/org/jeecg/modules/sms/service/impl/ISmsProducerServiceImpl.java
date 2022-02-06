package org.jeecg.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.sms.entity.SmsProducer;
import org.jeecg.modules.sms.mapper.SmsProducerMapper;
import org.jeecg.modules.sms.service.ISmsProducerService;
import org.jeecg.modules.system.entity.SysUserRole;
import org.jeecg.modules.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ISmsProducerServiceImpl extends ServiceImpl<SmsProducerMapper,SmsProducer> implements ISmsProducerService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SmsProducerMapper producerMapper;

    @Override
    public int saveProducer(SmsProducer smsProducer) {
        return producerMapper.insert(smsProducer);
    }

    @Override
    public List<SmsProducer> selectProducerList(SmsProducer smsProducer) {
        return producerMapper.selectBatchIds(new ArrayList<>(smsProducer.getProducerId()));
    }

    @Override
    public int updateProducer(SmsProducer smsProducer) {

        return producerMapper.updateById(smsProducer);
    }

    @Override
    public int deleteProducer(Long producerId) {
        return producerMapper.deleteById(producerId);
    }

}
