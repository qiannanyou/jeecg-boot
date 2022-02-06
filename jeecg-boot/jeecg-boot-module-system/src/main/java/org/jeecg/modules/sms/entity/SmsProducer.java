package org.jeecg.modules.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.sms.entity.vo.SmsBaseVo;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 供应商表
 *
 * @author lintt
 * @since 2022-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sms_producer")
public class SmsProducer extends SmsBaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer producerId;

    /**
     * 供应商名称
     */
    @Excel(name="供应商名称")
    @NotBlank(message = "供应商名称不能为空")
    @Size(min = 0, max = 30, message = "供应商名称长度不能超过30个字符")
    private String name;

    /**
     * 供应商编码
     */
    @Excel(name="供应商编码")
    private String code;

    /**
     * 供应商地址
     */
    @Excel(name="供应商地址")
    private String address;

    /**
     * 供应商类型：0.线下，1.线上，2.代工，3中间商
     */
    @Excel(name="供应商类型")
    private Integer type;

    /**
     * 线上平台
     */
    @Excel(name="线上平台")
    private String platform;

    /**
     * 是否可用，1是，0否
     */
    @Excel(name="是否可用")
    private Integer status;

    /**
     * 付款方式
     */
    @Excel(name="付款方式")
    private Integer payType;

    /**
     * 限制月最大采购金额，0不限制
     */
    @Excel(name="限制月最大采购金额")
    private Integer maxMoney;

    /**
     * 银行
     */
    @Excel(name="银行")
    private String bank;

    /**
     * 银行地址
     */
    @Excel(name="银行地址")
    private String bankAddress;

    /**
     * 户名
     */
    @Excel(name="户名")
    private String bankUser;

    /**
     * 银行账号
     */
    @Excel(name="银行账号")
    private String account;

    /**
     * 预付款，百分比
     */
    @Excel(name="预付款")
    private Integer beforePay;

    /**
     * 账期天数
     */
    @Excel(name="账期天数")
    private Integer creditDay;

    /**
     * 网站
     */
    @Excel(name="网站")
    private String site;

    /**
     * 是否可开发票
     */
    @Excel(name="是否可开发票")
    private Boolean isInvoice;

    /**
     * 是否包税
     */
    @Excel(name="是否包税")
    private Boolean isFreeTax;

    /**
     * 共采购金额
     */
    @Excel(name="共采购金额")
    private BigDecimal totalPrice;

    /**
     * 交货时间（天）
     */
    @Excel(name="交货时间（天）")
    private Integer deliveryDay;

    /**
     * 供应商质量分数
     */
    @Excel(name="供应商质量分数")
    private Integer quality;

    /**
     * 主营商品
     */
    @Excel(name="主营商品")
    private String mainProduct;

    /**
     * 联系人
     */
    @Excel(name="联系人")
    private String contacts;

    /**
     * 手机
     */
    @Excel(name="手机")
    private String mobile;

    /**
     * 电话
     */
    @Excel(name="电话")
    private String phone;

    /**
     * 阿里旺旺
     */
    @Excel(name="阿里旺旺")
    private String wangwang;

    /**
     * qq
     */
    @Excel(name="qq")
    private String qq;

    /**
     * 备用联系人
     */
    @Excel(name="备用联系人")
    private String spareContacts;

    /**
     * 备用联系人手机
     */
    @Excel(name="备用联系人手机")
    private String spareMobile;

    /**
     * 备用联系人电话
     */
    @Excel(name="备用联系人电话")
    private String sparePhone;

    /**
     * 备用联系人qq
     */
    @Excel(name="备用联系人qq")
    private String spareQq;

    /**
     * 排序
     */
    @Excel(name="排序")
    private Integer sort;

    /**
     * 供应商资质
     */
    @Excel(name="供应商资质")
    private String certificate;

    /**
     * 扩展字段
     */
    @Excel(name="扩展字段")
    private String extJson;

    /**备注*/
    @Excel(name="备注",width=15)
    private String remark;

}
