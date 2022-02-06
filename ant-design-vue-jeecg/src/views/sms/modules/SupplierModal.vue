<!--
 * @Author: your name
 * @Date: 2022-02-03 20:29:51
 * @LastEditTime: 2022-02-05 23:39:02
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: /ant-design-vue-jeecg/src/views/sms/modules/SupplierModal.vue
-->
<template>
  <a-drawer :title="title" :maskClosable="true" :width="drawerWidth" placement="right" :closable="true"
    @close="handleCancel" :visible="visible" style="height: 100%;overflow: auto;padding-bottom: 53px;">

    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-col :span="col">
          <a-form-model-item label="供应商名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
            <a-input placeholder="请输入供应商名称" allowClear v-model="model.name" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="供应商编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="code">
            <a-input placeholder="请输入供应商编号" allowClear v-model="model.code" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="主营商品" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mainProduct">
            <a-input placeholder="请输入主营商品" allowClear v-model="model.mainProduct" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="交货天数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deliveryDay">
            <a-input placeholder="请输入交货天数" allowClear v-model="model.deliveryDay" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="供应商地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
            <a-input placeholder="请输入供应商地址" allowClear v-model="model.address" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="线上平台" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="platform">
            <a-input placeholder="请输入线上平台" allowClear v-model="model.platform" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="网址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="site">
            <a-input placeholder="请输入网址" allowClear v-model="model.site" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
            <a-select placeholder="请选择类型" v-model="model.type">
              <a-select-option :value="0">
                线上
              </a-select-option>
              <a-select-option :value="1">
                线下
              </a-select-option>
              <a-select-option :value="2">
                代工厂
              </a-select-option>
              <a-select-option :value="3">
                中间商
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="付款方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="payType">
            <a-select placeholder="请选择付款方式" v-model="model.payType">
              <a-select-option :value="10">
                支付宝
              </a-select-option>
              <a-select-option :value="20">
                微信
              </a-select-option>
              <a-select-option :value="30">
                银行转账
              </a-select-option>
              <a-select-option :value="40">
                货到付款
              </a-select-option>
              <a-select-option :value="50">
                现金
              </a-select-option>
              <a-select-option :value="60">
                账期
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="银行" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bank">
            <a-input placeholder="请输入银行" allowClear v-model="model.bank" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="银行账号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="account">
            <a-input placeholder="请输入银行账号" allowClear v-model="model.account" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="开户名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bankUser">
            <a-input placeholder="请输入开户名" allowClear v-model="model.bankUser" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="银行地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bankAddress">
            <a-input placeholder="请输入银行地址" allowClear v-model="model.bankAddress" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="预付比例" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="beforePay">
            <a-input placeholder="请输入预付款百分比" allowClear v-model="model.beforePay" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="账期天数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="creditDay">
            <a-input placeholder="请输入账期天数" allowClear v-model="model.creditDay" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="供应商质量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="quality">
            <a-input placeholder="请输入供应商质量分数(10)" allowClear v-model="model.quality" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="联系人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contacts">
            <a-input placeholder="请输入联系人姓名" allowClear v-model="model.contacts" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="手机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mobile">
            <a-input placeholder="请输入手机号" allowClear v-model="model.mobile" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="电话" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="phone">
            <a-input placeholder="请输入电话号码" allowClear v-model="model.phone" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="阿里旺旺" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="wangwang">
            <a-input placeholder="请输入阿里旺旺" allowClear v-model="model.wangwang" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="QQ" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="qq">
            <a-input placeholder="请输入QQ" allowClear v-model="model.qq" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备用联系人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="spareContacts">
            <a-input placeholder="请输入备用联系人" allowClear v-model="model.spareContacts" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备用手机" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="spareMobile">
            <a-input placeholder="请输入备用手机号" allowClear v-model="model.spareMobile" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备用电话" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sparePhone">
            <a-input placeholder="请输入备用电话号码" allowClear v-model="model.sparePhone" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备用QQ" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="spareQq">
            <a-input placeholder="请输入备用QQ" allowClear v-model="model.spareQq" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sort">
            <a-input placeholder="请输入排序" allowClear v-model="model.sort" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remark">
            <a-input placeholder="请输入备注" allowClear v-model="model.remark" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
            <a-switch default-checked v-model="model.status" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="是否开发票" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isInvoice">
            <a-switch v-model="model.isInvoice" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="是否含税" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="isFreeTax">
            <a-switch v-model="model.isFreeTax" />
          </a-form-model-item>
        </a-col>
      </a-form-model>
    </a-spin>

    <div class="drawer-bootom-buttonx" v-show="!disableSubmit">
      <a-popconfirm title="确定放弃编辑？" @confirm="handleCancel" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="confirmLoading">提交</a-button>
    </div>
  </a-drawer>
</template>

<script>
  import {
    purchaseAdd,
    purchaseEdit
  } from "@/api/supplier.js";
  export default {
    name: "SupplierModal",
    components: {},
    data() {
      return {
        title: "新增供应商",
        visible: false,
        disableSubmit: false,
        confirmLoading: false,
        drawerWidth: 700,
        model: {
          status: 1
        },
        col: 12,
        labelCol: {
          span: 7
        },
        wrapperCol: {
          span: 16
        },
        validatorRules: {
          name: [{
            required: true,
            message: '请输入供应商名称!'
          }],
          type: [{
            required: true,
            message: '请选择类型!'
          }],
          payType: [{
            required: true,
            message: '请输入付款方式!'
          }],
          mobile: [{
            pattern: /^1[3|4|5|7|8|9][0-9]\d{8}$/,
            message: '请输入正确格式的手机号码!'
          }],
          spareMobile: [{
            pattern: /^1[3|4|5|7|8|9][0-9]\d{8}$/,
            message: '请输入正确格式的手机号码!'
          }],
        }
      }
    },
    mounted() {
      window.onresize = () => {
        return (() => {
          if (document.body.clientWidth < 900) {
            this.drawerWidth = 400;
            this.col = 24;
            return;
          }
          this.col = 12;
          this.drawerWidth = 700;
        })();
      };
    },
    methods: {
      //取消
      handleCancel() {
        this.close()
      },
      //关闭
      close() {
        this.$emit('close');
        this.visible = false;
        this.model = {
          status: 1
        };
        this.disableSubmit = false;
        this.$refs.form.resetFields();
      },
      //提交
      handleSubmit() {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            if (that.model.status === true) {
              that.model.status = 1
            } else if (that.model.status === false) {
              that.model.status = 0
            }
            if (that.model.producerId) {
              purchaseEdit(that.model).then((res) => {
                if (res.success) {
                  that.$message.success(res.message);
                  that.$emit('ok');
                  that.close();
                  return;
                }
                that.$message.error(res.message);
              }).catch((err) => {
                that.$emit('fail', err)
              }).finally(() => {
                that.confirmLoading = false;
              })
              return
            }
            purchaseAdd(that.model).then((res) => {
              if (res.success) {
                that.$message.success(res.message);
                that.$emit('ok');
                that.close();
                return;
              }
              that.$message.error(res.message);
            }).catch((err) => {
              that.$emit('fail', err)
            }).finally(() => {
              that.confirmLoading = false;
            })
          } else {
            return false;
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>