/*
 * @Author: your name
 * @Date: 2022-02-04 23:47:47
 * @LastEditTime: 2022-02-05 21:55:14
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: /ant-design-vue-jeecg/src/api/supplier.js
 */
import api from './index'
import { axios } from '@/utils/request'

export function purchaseAdd(parameter) {
  return axios({
    url: '/sms/purchase/add',
    method: 'post',
    data: parameter
  })
}

export function purchaseList(parameter) {
  return axios({
    url: '/sms/purchase/list',
    method: 'get',
    data: parameter
  })
}

export function purchaseEdit(parameter) {
  return axios({
    url: '/sms/purchase/edit',
    method: 'post',
    data: parameter
  })
}