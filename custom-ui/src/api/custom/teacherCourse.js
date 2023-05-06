import request from '@/utils/request'

// 查询教师课程管理列表
export function listTeacherCourse(query) {
  return request({
    url: '/custom/teacherCourse/list',
    method: 'get',
    params: query
  })
}

// 查询教师课程管理详细
export function getTeacherCourse(id) {
  return request({
    url: '/custom/teacherCourse/' + id,
    method: 'get'
  })
}

// 新增教师课程管理
export function addTeacherCourse(data) {
  return request({
    url: '/custom/teacherCourse',
    method: 'post',
    data: data
  })
}

// 修改教师课程管理
export function updateTeacherCourse(data) {
  return request({
    url: '/custom/teacherCourse',
    method: 'put',
    data: data
  })
}

// 删除教师课程管理
export function delTeacherCourse(id) {
  return request({
    url: '/custom/teacherCourse/' + id,
    method: 'delete'
  })
}
