package org.jeecg.modules.sms.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 基类
 * <p>
 *
 * @Author ltt
 * @Date 2022/2/3 上午9:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsBaseVo implements Serializable {

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建人id
     */
    private String createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新人Id
     */
    private String updateId;

    /**
     * 更新时间
     */
    private Date updateTime;
}
