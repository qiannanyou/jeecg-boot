<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="8" :sm="24">
            <a-form-item label="订单编号" prop="purchaseNo">
              <a-input v-model="queryParam.purchaseNo" placeholder="请输入采购订单编号" allow-clear />
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24">
            <a-form-item label="物流状态" prop="isRecv">
              <a-radio-group :value="queryParam.isRecv" @change="handleIsRecvChange">
                <a-radio-button :value="0">
                  待收货
                </a-radio-button>
                <a-radio-button :value="1">
                  已收货
                </a-radio-button>
              </a-radio-group>
            </a-form-item>
          </a-col>
          <a-col :md="4" :sm="24">
            <span class="table-page-search-submitButtons">
              <a-button type="primary" style="margin-right: .3rem;" icon="search" @click="handleSearch">查询</a-button>
              <a-button style="margin-left: .3rem" icon="reload" @click="resetSearchForm">重置</a-button>
            </span>
          </a-col>
          <a-col :sm="24">
            <a-button class="mr-50" type="primary" icon="plus-circle" @click="handleAdd">新增</a-button>
            <a-button class="mr-50" type="danger" icon="minus-circle" v-if="selectedRowKeys.length > 0"
              @click="batchDel">删除
            </a-button>
            <a-button type="primary" icon="safety-certificate"
              style="font-size: 13px;background:#67C23A;border-color:#67C23A;" v-if="selectedRowKeys.length > 0"
              @click="batchUpdate">确认收货
            </a-button>
          </a-col>
        </a-row>
      </a-form>
      <a-table class="mt-50 table" ref="table" bordered :columns="columns" :data-source="dataSource"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}" :loading="confirmLoading"
        :pagination="pagination" :customHeaderRow="customHeaderRow" :customRow="customRow"
        :highlight-current-row="true">
        <span slot="action" slot-scope="text, record">
          <a-space>
            <a-button type="primary" style="font-size: 13px;background:#67C23A;border-color:#67C23A;"
              @click="getExpressDeliveryHandle(record.expressNo)">查看物流
            </a-button>
            <a-button type="primary" style="font-size: 13px;" v-if="!record.isRecv"
              @click="handleIsStatus(record.trackId)">确认收货
            </a-button>
            <a-button type="danger" style="font-size: 13px;" v-if="!record.isRecv" @click="handleEdit(record)">编辑
            </a-button>
          </a-space>
        </span>
        <span slot="site" slot-scope="text, model">
          <a @click="handleOpenBlank(model.site)" :title="model.site">
            <a-tooltip>
              <template slot="title">
                {{model.site}}
              </template>
              {{model.site}}
            </a-tooltip>
          </a>
        </span>
        <template slot="remark" slot-scope="text, model">

          <div style="display: grid;">
            <span class="ellipsisText" v-if="!model.remark">-</span>
            <span class="ellipsisText" v-else>{{model.remark}}</span>
          </div>
        </template>
        <template slot="code" slot-scope="text, model">
          <a data-clipboard-action="copy" class="copyByCode"
            style=";cursor:hand;text-decoration:none;color: rgba(0, 0, 0, 0.65);" :data-clipboard-text="model.code"
            @click="copyLink">
            {{model.code}}</a>
        </template>
        <template slot="cover" slot-scope="text, model">
          <a-popover placement="right" trigger="hover" v-if="model.cover">
            <template slot="content">
              <img :src="getImage(model.cover)" style="max-height: 300px;max-width: 300px" />
            </template>
            <img :src="getImage(model.cover)" style="max-height: 60px;max-width: 300px">
          </a-popover>
          <img v-else src="@/assets/img-empty.svg" style="max-height: 60px;max-width: 300px" />
        </template>
        <template slot="purchaseUrl" slot-scope="text, model">
          <div style="display: grid;">
            <a class="ellipsisText" :href="model.purchaseUrl" target="_blank">{{model.purchaseUrl}}</a>
          </div>
        </template>
        <template slot="logisticsId" slot-scope="text, model">
          <span>{{logisticsIdOptions[model.logisticsId]}}</span>
        </template>
        <template slot="purchasePlatformId" slot-scope="text, model">
          <span>{{platformOptions[model.purchasePlatformId]}}</span>
        </template>
        <template slot="isRecv" slot-scope="text, model">
          <a-badge :status="model.isRecv?'success':'warning'" />
          <span>{{model.isRecv?'已收货':'待收货'}}</span>
        </template>
      </a-table>
    </div>
    <purchase-track-model ref="modalForm" @ok="modalFormOk"></purchase-track-model>
    <a-modal :title="expressData.expName" :width="700" :visible="visible" :maskClosable="false" @ok="handleCancel"
      @cancel="handleCancel" centered>
      <a-timeline>
        <a-timeline-item v-for="(item,index) in expressData.list" :key="index">
          <a-icon slot="dot" type="clock-circle-o" style="font-size: 16px;" />
          <div class="express">
            <span class="express-time"
              :class="{'is-Active': expressData.deliverystatus === '3' && index==0}">{{item.time}}</span>
            <span class="express-status"
              :class="{'is-Active': expressData.deliverystatus === '3' && index==0}">{{item.status}}</span>
          </div>
        </a-timeline-item>
      </a-timeline>
    </a-modal>
  </a-card>
</template>

<script>
  import PurchaseTrackModel from "../modules/PurchaseTrackModel.vue";
  import {
    getFileAccessHttpUrl
  } from '@/api/manage';
  import {
    purchaseTrackList,
    updateStatusBatch,
    getExpressDelivery
  } from "@/api/purchaseTrack.js";
  import {
    deleteAction
  } from '../../../api/manage';
  export default {
    name: 'PurchaseTrack',
    components: {
      PurchaseTrackModel
    },
    data() {
      return {
        description: '采购订单界面',
        confirmLoading: false,
        queryParam: {
          isRecv: 0
        },
        recycleBinVisible: false,
        columns: [{
            title: '图片凭证',
            align: "center",
            width: 200,
            dataIndex: 'cover',
            scopedSlots: {
              customRender: 'cover'
            },
          },
          {
            title: '订单编号',
            align: "center",
            width: 190,
            fontSize: 12,
            dataIndex: 'purchaseNo',
          },
          {
            title: '采购平台',
            align: "center",
            width: 90,
            dataIndex: 'purchasePlatformId',
            scopedSlots: {
              customRender: 'purchasePlatformId'
            },
          },
          {
            title: '采购链接',
            align: "center",
            width: 180,
            dataIndex: 'purchaseUrl',
            scopedSlots: {
              customRender: 'purchaseUrl'
            },
          },
          // {
          //   title: '编号',
          //   align: "center",
          //   width: 100,
          //   dataIndex: 'code',
          //   scopedSlots: {
          //     customRender: 'code'
          //   },
          // },
          {
            title: '快递单号',
            align: "center",
            width: 190,
            dataIndex: 'expressNo',
          },
          {
            title: '物流商',
            align: "center",
            width: 100,
            dataIndex: 'logisticsId',
            scopedSlots: {
              customRender: 'logisticsId'
            },
          },
          {
            title: ' 物流状态',
            align: "center",
            width: 100,
            dataIndex: 'isRecv',
            scopedSlots: {
              customRender: 'isRecv'
            },
          },
          {
            title: '创建日期',
            align: "center",
            width: 150,
            dataIndex: 'createTime',
            // ellipsis: true,
          },
          {
            title: '备注信息',
            align: "center",
            width: 150,
            dataIndex: 'remark',
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            scopedSlots: {
              customRender: 'action'
            },
          }
        ],
        logisticsIdOptions: {
          1: '申通',
          2: '中通',
          3: '圆通',
          4: '极兔',
          5: '邮政',
          6: '顺丰',
          7: '韵达',
          99: '其他',
        },
        platformOptions: {
          '1': '1688',
          '2': '拼多多',
          '3': '淘宝',
          '4': '其他平台',
        },
        dataSource: [],
        selectedRowKeys: [],
        // 分页参数
        pagination: {
          current: 1,
          pageSize: 10,
          defaultPageSize: 10,
          total: 0,
          showTotal: total => `共 ${this.pagination.total} 条数据`,
          showSizeChanger: true,
          pageSizeOptions: ['10', '20', '30'],
          onShowSizeChange: (current, pageSize) => {
            this.pagination.pageSize = pageSize;
            this.pagination.current = current;
            this.loadData();
          },
          onChange: (page, pageSize) => {
            console.log(page, pageSize);
            this.pagination.pageSize = pageSize;
            this.pagination.current = page;
            this.loadData();
          },
        },
        url: {
          delete: '/sms/purchaseTrack/delete',
          deleteBatch: '/sms/purchaseTrack/deleteBatch'
        },
        visible: false,
        expressData: Object,
      }
    },
    computed: {

    },
    created() {
      this.loadData();
    },
    mounted() {},
    methods: {
      loadData() {
        let params = {
          pageNo: this.pagination.current,
          pageSize: this.pagination.pageSize,
          purchaseNo: this.queryParam.purchaseNo || ""
        }
        this.getPurchaseTrackList(params);
      },

      getPurchaseTrackList(params) {
        this.confirmLoading = true;
        let _that = this;
        purchaseTrackList(params).then((res) => {
          _that.confirmLoading = false;
          let data = res.result
          if (data) {
            _that.pagination.total = Number(data.total)
            _that.dataSource = data.records
          } else {
            _that.pagination.total = 0
            _that.dataSource = []
          }
        }).catch((err) => {
          _that.$emit('fail', err)
        })
      },

      getExpressDeliveryHandle(id) {
        this.confirmLoading = true;
        let _that = this;
        getExpressDelivery({
          no: id,
          type: ''
        }).then((res) => {
          _that.confirmLoading = false;
          _that.visible = true;
          let data = JSON.parse(res.result);
          console.log(data);
          if (data) {
            _that.expressData = data.result;
          } else {
            _that.expressData = Object
          }
        }).catch((err) => {
          _that.$emit('fail', err)
        })
      },

      resetSearchForm() {
        this.queryParam = {}
        this.pagination.pageSize = 10;
        this.pagination.current = 1;
        this.selectedRowKeys = [];
        this.loadData();
      },

      handleSearch() {
        this.pagination.pageSize = 10;
        this.pagination.current = 1;
        let params = {
          pageNo: this.pagination.current,
          pageSize: this.pagination.pageSize,
          purchaseNo: this.queryParam.purchaseNo || "",
          isRecv: this.queryParam.isRecv
        }
        this.getPurchaseTrackList(params);
      },

      handleAdd: function () {
        this.$refs.modalForm.visible = true;
      },

      modalFormOk() {
        this.resetSearchForm();
      },

      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
      },

      handleOpenBlank(href) {
        window.open(href, '_blank');
      },

      handleEdit(obj) {
        this.$refs.modalForm.model = JSON.parse(JSON.stringify(obj));
        this.$refs.modalForm.title = "修改采购订单";
        this.$refs.modalForm.visible = true;
      },
      handleIsRecvChange(val) {
        this.queryParam.isRecv = val.target.value
        let params = {
          pageNo: this.pagination.current,
          pageSize: this.pagination.pageSize,
          purchaseNo: this.queryParam.purchaseNo || "",
          isRecv: this.queryParam.isRecv || 0
        }
        this.getPurchaseTrackList(params);
      },
      batchUpdate() {
        let ids = "";
        let that = this;
        that.selectedRowKeys.forEach(function (row) {
          let model = that.dataSource[row];
          ids += model.trackId + ",";
        });
        var reg = /,$/gi;
        ids = ids.replace(reg, "");
        this.handleIsStatus(ids);
      },
      handleIsStatus(ids) {
        let _that = this;
        this.$confirm({
          title: "确认操作",
          content: "是否确认收货",
          onOk: function () {
            updateStatusBatch(ids).then((res) => {
              if (res.success) {
                _that.$message.success(res.message);
                _that.resetSearchForm();
              } else {
                _that.$message.warning(res.message);
              }
            });
          }
        });
      },

      handleDelete(id) {
        let _that = this;
        deleteAction(_that.url.delete, {
          id: id
        }).then((res) => {
          if (res.success) {
            _that.$message.success(res.message);
            _that.resetSearchForm();
          } else {
            _that.$message.warning(res.message);
          }
        });
      },

      batchDel() {
        let ids = "";
        let that = this;
        that.selectedRowKeys.forEach(function (row) {
          let model = that.dataSource[row];
          ids += model.trackId + ",";
        });
        var reg = /,$/gi;
        ids = ids.replace(reg, "");
        that.$confirm({
          title: "确认操作",
          content: "是否删除选中的采购订单",
          onOk: function () {
            deleteAction(that.url.deleteBatch, {
              ids: ids
            }).then((res) => {
              if (res.success) {
                that.$message.success(res.message);
                that.resetSearchForm();
              } else {
                that.$message.warning(res.message);
              }
            });
          }
        });
      },

      copyLink() {
        let that = this;
        let clipboard = new this.clipboard(".copyByCode");
        clipboard.on('success', e => {
          that.$message.success('复制编号成功');
          // 释放内存
          clipboard.destroy()
        })
        clipboard.on('error', e => {
          // 不支持复制
          that.$message.error('该浏览器不支持自动复制');
          // 释放内存
          clipboard.destroy()
        })
      },
      customHeaderRow() {
        return {
          style: {
            'font-size': '12px'
          },
        }
      },
      customRow() {
        return {
          style: {
            'font-size': '12px'
          },
        }
      },

      close() {
        this.visible = false
        this.timelineData = []
      },
      handleOk() {

      },
      handleCancel() {
        this.close()
      },
      getImage(img) {
        return getFileAccessHttpUrl(img);
      },
    }
  }
</script>


<style>
  .ellipsisText {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .express {
    width: 60%;
    display: flex;
    flex-direction: column;
  }

  .express-time {
    color: black;
    font-size: 18px;
    font-weight: 600;
  }

  .express-status {
    font-size: 14px;
  }

  .is-Active {
    color: #67C23A;
  }

  .table {
    overflow-x: auto;
  }
</style>

<style scoped>
  @import '~@assets/less/common-style.css'
</style>