import request from '@/utils/request'

const api_name = '/vod/course'

export default {
    getPageList(current, limit, searchObj) {
        return request({
            url: `${api_name}/page/${current}/${limit}`,
            method: `post`,
            data: searchObj
        })
    },
    removeById(id) {
        return request({
            url: `${api_name}/remove/${id}`,
            method: `delete`
        })
    },
    saveCourseInfo(courseInfo) {
        return request({
            url: `${api_name}/save`,
            method: `post`,
            data: courseInfo
        })
    },
    getCourseInfoById(id) {
        return request({
            url: `${api_name}/get/${id}`,
            method: `get`
        })
    },
    updateCourseInfoById(courseInfo) {
        return request({
            url: `${api_name}/update`,
            method: `post`,
            data: courseInfo
        })
    },
    //获取发布课程信息  
    getCoursePublishById(id) {
        return request({
            url: `${api_name}/getCoursePublishVo/${id}`,
            method: 'get'
        })
    },
    //发布课程  
    publishCourseById(id) {
        return request({
            url: `${api_name}/publishCourseById/${id}`,
            method: 'put'
        })
    },
}