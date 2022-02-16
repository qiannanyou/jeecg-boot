<!--
 * @Author: your name
 * @Date: 2022-02-02 23:37:12
 * @LastEditTime: 2022-02-09 16:24:51
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: /ant-design-vue-jeecg/src/views/sms/purchase/supplier.vue
-->
<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="8" :sm="24">
            <a-form-item label="供应商">
              <a-input v-model="queryParam.search" placeholder="请输入供应商名称/编号" />
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24">
            <span class="table-page-search-submitButtons">
              <a-button type="primary" style="margin-right: .3rem;" icon="search" @click="handleSearch">查询</a-button>
              <a-button style="margin-left: .3rem" icon="reload" @click="resetSearchForm">重置</a-button>
            </span>
          </a-col>
          <a-col :sm="24">
            <a-button class="mr-50" type="primary" icon="plus-circle" @click="handleAdd">新增</a-button>
            <a-button type="danger" icon="minus-circle" v-if="selectedRowKeys.length > 0" @click="batchDel">删除
            </a-button>
          </a-col>
        </a-row>
      </a-form>
      <a-table class="mt-50" ref="table" bordered :columns="columns" :data-source="dataSource"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}" :loading="confirmLoading"
        :pagination="pagination">
        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.producerId)">
            <a>删除</a>
          </a-popconfirm>
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
          <span v-if="!model.remark">-</span>
          <span v-else>{{model.remark}}</span>
        </template>
        <template slot="code" slot-scope="text, model">
          <a data-clipboard-action="copy" class="copyByCode"
            style=";cursor:hand;text-decoration:none;color: rgba(0, 0, 0, 0.65);" :data-clipboard-text="model.code"
            @click="copyLink">
            {{model.code}}</a>
        </template>
      </a-table>
    </div>
    <supplier-modal ref="modalForm" @ok="modalFormOk"></supplier-modal>
  </a-card>
</template>

<script>
  import SupplierModal from "../modules/SupplierModal.vue";
  import {
    purchaseList
  } from "@/api/supplier.js";
  import {
    deleteAction
  } from '../../../api/manage';
  export default {
    name: 'Supplier',
    components: {
      SupplierModal
    },
    data() {
      return {
        description: '供应商界面',
        confirmLoading: false,
        queryParam: {},
        recycleBinVisible: false,
        columns: [{
            title: '供应商名称',
            align: "center",
            dataIndex: 'name',
            ellipsis: true,
          },
          {
            title: '编号',
            align: "center",
            width: 100,
            dataIndex: 'code',
            scopedSlots: {
              customRender: 'code'
            },
          },
          {
            title: '主营商品',
            align: "center",
            width: 100,
            dataIndex: 'mainProduct',
          },
          {
            title: '质量',
            align: "center",
            width: 100,
            dataIndex: 'quality',
          },
          {
            title: '采购金额/元',
            align: "center",
            width: 150,
            dataIndex: 'totalPrice',
          },
          {
            title: '交付周期/天',
            align: "center",
            width: 150,
            dataIndex: 'deliveryDay',
          },
          {
            title: '联系人',
            align: "center",
            width: 100,
            dataIndex: 'contacts',
          },
          {
            title: '地址',
            align: "center",
            width: 100,
            dataIndex: 'address',
            ellipsis: true,
          },
          {
            title: '平台',
            align: "center",
            width: 100,
            dataIndex: 'platform',
            ellipsis: true,
          },
          {
            title: '网站',
            align: "center",
            width: 100,
            dataIndex: 'site',
            ellipsis: true,
            scopedSlots: {
              customRender: 'site'
            },
          },
          {
            title: '备注',
            align: "center",
            width: 100,
            dataIndex: 'remark',
            ellipsis: true,
            scopedSlots: {
              customRender: 'remark'
            },
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
        dataSource: [],
        selectedRowKeys: [],
        // 分页参数
        pagination: {
          defaultPageSize: 10,
          showTotal: total => `共 ${total} 条数据`,
          showSizeChanger: true,
          pageSizeOptions: ['10', '20', '30'],
          onShowSizeChange: (current, pageSize) => {
            this.pageSize = pageSize;
            this.loadData();
          }
        },
        url: {
          delete: '/sms/purchase/delete',
          deleteBatch: '/sms/purchase/deleteBatch'
        }
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
          search: this.queryParam.search || ""
        }
        this.confirmLoading = true;
        let _that = this;
        purchaseList(params).then((res) => {
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
        this.loadData();
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
        this.$refs.modalForm.title = "修改供应商";
        this.$refs.modalForm.visible = true;
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
          ids += model.producerId + ",";
        });
        var reg = /,$/gi;
        ids = ids.replace(reg, "");
        console.log(ids);
        that.$confirm({
          title: "确认操作",
          content: "是否删除选中的供应商",
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
      }
    }
  }
</script>

<style scoped>
  @import '~@assets/less/common-style.css'
</style>