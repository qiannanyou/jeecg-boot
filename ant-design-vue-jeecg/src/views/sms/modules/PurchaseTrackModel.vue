<template>
  <a-drawer :title="title" :maskClosable="true" :width="drawerWidth" placement="right" :closable="true"
    @close="handleCancel" :visible="visible" style="height: 100%;overflow: auto;padding-bottom: 53px;">

    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-col :span="col">
          <a-form-model-item label="订单编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="purchaseNo">
            <a-input placeholder="请输入订单编号" allowClear v-model="model.purchaseNo" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="采购平台" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="purchasePlatformId">
            <a-select placeholder="请选择采购平台" v-model="model.purchasePlatformId">
              <a-select-option :value="1">
                1688
              </a-select-option>
              <a-select-option :value="2">
                拼多多
              </a-select-option>
              <a-select-option :value="3">
                淘宝
              </a-select-option>
              <a-select-option :value="4">
                其他平台
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="采购链接" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="purchaseUrl">
            <a-input placeholder="请输入采购链接" allowClear v-model="model.purchaseUrl" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="快递单号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="expressNo">
            <a-input placeholder="请输入快递单号" allowClear v-model="model.expressNo" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="物流商" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="logisticsId">
            <a-select placeholder="请选择物流商" v-model="model.logisticsId">
              <a-select-option :value="1">
                申通
              </a-select-option>
              <a-select-option :value="2">
                中通
              </a-select-option>
              <a-select-option :value="3">
                圆通
              </a-select-option>
              <a-select-option :value="4">
                极兔
              </a-select-option>
              <a-select-option :value="5">
                邮政
              </a-select-option>
              <a-select-option :value="6">
                顺丰
              </a-select-option>
              <a-select-option :value="7">
                韵达
              </a-select-option>
              <a-select-option :value="99">
                其他
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="电商编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="crossBorderNo">
            <a-input placeholder="请输入电商平台订单编号" allowClear v-model="model.crossBorderNo" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="备注信息" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remark">
            <a-textarea v-model="model.remark" placeholder="请输入备注信息" :auto-size="{ minRows: 3}" />
          </a-form-model-item>
        </a-col>
        <a-col :span="col">
          <a-form-model-item label="图片凭证" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="crossBorderNo">
            <j-upload v-model="model.cover" :number="1" :file-type="'image'"></j-upload>
          </a-form-model-item>
        </a-col>
      </a-form-model>
    </a-spin>
    <a-col :span="6" :offset="18">
      <div class="drawer-bootom-buttonx" v-show="!disableSubmit">
        <a-popconfirm title="确定放弃编辑？" @confirm="handleCancel" okText="确定" cancelText="取消">
          <a-button style="margin-right: .8rem">取消</a-button>
        </a-popconfirm>
        <a-button @click="handleSubmit" type="primary" :loading="confirmLoading">提交</a-button>
      </div>
    </a-col>
  </a-drawer>
</template>

<script>
  import {
    purchaseTrackAdd,
    purchaseTrackEdit
  } from "@/api/purchaseTrack.js";
  import JUpload from '@/components/jeecg/JUpload'
  export default {
    name: "PurchaseTrackModel",
    components: {
      JUpload,
    },
    data() {
      return {
        title: "新增采购单",
        visible: false,
        disableSubmit: false,
        confirmLoading: false,
        drawerWidth: 700,
        model: {},
        col: 12,
        labelCol: {
          span: 7
        },
        wrapperCol: {
          span: 16
        },
        validatorRules: {
          purchaseNo: [{
            required: true,
            message: '请输入订单编号!'
          }],
          purchasePlatformId: [{
            required: true,
            message: '请选择采购平台!'
          }],
          purchaseUrl: [{
            required: true,
            message: '请输入采购链接!'
          }],
          expressNo: [{
            required: true,
            message: '请输入快递单号!'
          }],
        },
        logisticsOptions: []
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
            if (that.model.trackId) {
              purchaseTrackEdit(that.model).then((res) => {
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
            purchaseTrackAdd(that.model).then((res) => {
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