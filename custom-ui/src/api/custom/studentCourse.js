import request from '@/utils/request'

// 查询学生选课管理列表
export function listStudentCourse(query) {
  return request({
    url: '/custom/studentCourse/list',
    method: 'get',
    params: query
  })
}

// 查询学生选课管理详细
export function getStudentCourse(id) {
  return request({
    url: '/custom/studentCourse/' + id,
    method: 'get'
  })
}

// 新增学生选课管理
export function addStudentCourse(data) {
  return request({
    url: '/custom/studentCourse',
    method: 'post',
    data: data
  })
}

// 修改学生选课管理
export function updateStudentCourse(data) {
  return request({
    url: '/custom/studentCourse',
    method: 'put',
    data: data
  })
}

// 删除学生选课管理
export function delStudentCourse(id) {
  return request({
    url: '/custom/studentCourse/' + id,
    method: 'delete'
  })
}
