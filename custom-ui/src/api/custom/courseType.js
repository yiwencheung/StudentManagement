import request from '@/utils/request'

// 查询课程性质管理列表
export function listCourseType(query) {
  return request({
    url: '/custom/courseType/list',
    method: 'get',
    params: query
  })
}

// 查询课程性质管理详细
export function getCourseType(id) {
  return request({
    url: '/custom/courseType/' + id,
    method: 'get'
  })
}

// 新增课程性质管理
export function addCourseType(data) {
  return request({
    url: '/custom/courseType',
    method: 'post',
    data: data
  })
}

// 修改课程性质管理
export function updateCourseType(data) {
  return request({
    url: '/custom/courseType',
    method: 'put',
    data: data
  })
}

// 删除课程性质管理
export function delCourseType(id) {
  return request({
    url: '/custom/courseType/' + id,
    method: 'delete'
  })
}
