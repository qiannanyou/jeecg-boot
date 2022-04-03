import { axios } from '@/utils/request'

export function purchaseTrackAdd(parameter) {
  return axios({
    url: '/sms/purchaseTrack/add',
    method: 'post',
    data: parameter
  })
}

export function purchaseTrackList(parameter) {
  return axios({
    url: '/sms/purchaseTrack/list',
    method: 'get',
    params: parameter
  })
}

export function purchaseTrackEdit(parameter) {
  return axios({
    url: '/sms/purchaseTrack/edit',
    method: 'post',
    data: parameter
  })
}

export function purchaseTrackDelete(parameter) {
  return axios({
    url: '/sms/purchaseTrack/delete',
    method: 'delete',
    data: parameter
  })
}

export function purchaseTrackDeleteBatch(parameter) {
  return axios({
    url: '/sms/purchaseTrack/deleteBatch',
    method: 'delete',
    data: parameter
  })
}

export function updateStatusBatch(ids) {
  return axios({
    url: '/sms/purchaseTrack/updateStatusBatch',
    method: 'post',
    params: { ids }
  })
}

export function getExpressDelivery(parameter) {
  return axios({
    url: '/sms/express/getExpressDelivery',
    method: 'get',
    params: {
      no: parameter.no,
      type: parameter.type
    }
  })
}