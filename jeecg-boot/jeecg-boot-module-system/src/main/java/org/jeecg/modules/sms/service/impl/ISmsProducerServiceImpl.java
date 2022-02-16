package org.jeecg.modules.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.modules.sms.entity.SmsProducer;
import org.jeecg.modules.sms.mapper.SmsProducerMapper;
import org.jeecg.modules.sms.service.ISmsProducerService;
import org.jeecg.modules.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean deleteProducer(String producerId) {
        return this.removeById(producerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchProducers(String producerIds){
        return this.removeByIds(Arrays.asList(producerIds.split(",")));
    }
}
