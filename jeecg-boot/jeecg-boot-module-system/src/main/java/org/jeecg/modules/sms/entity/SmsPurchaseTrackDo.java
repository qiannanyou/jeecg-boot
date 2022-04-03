package org.jeecg.modules.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecg.modules.sms.entity.vo.SmsBaseVo;

import java.time.LocalDateTime;

/**
 *
 * 采购单追踪号
 *
 * @author lintt
 * @since 2022-03-27
 */
@Data
  @EqualsAndHashCode(callSuper = true)
    @TableName("sms_purchase_track")
public class SmsPurchaseTrackDo extends SmsBaseVo {

    private static final long serialVersionUID = 1L;

      /**
     * 自增id
     */
      @TableId(type = IdType.AUTO)
      private Integer trackId;

      /**
     * 采购id
     */
      private Integer purchaseId;

      /**
     * 商品 id 
     */
      private Integer goodId;

      /**
     * 快递单号
     */
      private String expressNo;

      /**
     * 物流商名称
     */
      private String logisticsName;

      /**
     * 物流商id 
     */
      private Integer logisticsId;

      /**
     * 物流状态
     */
      private Integer trackStatus;

      /**
     * 追踪号
     */
      private String trackNum;

      /**
     * 物流信息
     */
      private String trackInfo;

      /**
     *  0包邮，1自付，2垫付 
     */
      private Integer payType;

      /**
     * 是否收货， 1已收货，0未收货 
     */
      private Integer isRecv;

      /**
     * 收货时间
     */
      private LocalDateTime recvDate;

      /**
     * 收货人
     */
      private Integer recvUserId;

      /**
     * 包裹重量 g 
     */
      private Integer weight;

      /**
     * 订单编号
     */
      private String purchaseNo;

      /**
     * 采购链接
     */
      private String purchaseUrl;

      /**
     * 采购平台
     */
      private String purchasePlatform;

      /**
     * 采购平台id 
     */
      private Integer purchasePlatformId;

      /**
     * 电商平台订单编号
     */
      private String crossBorderNo;

      /**
     * 产品图片
     */
      private String cover;

      /**
     * 备注
     */
      private String remark;

}
