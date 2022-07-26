import request from '@/utils/request'

export default {
    list() {
        return request({
            url: `/vod/teacher/list`,
            method: `get`
        })
    },
    page(current, limit, searchObj) {
        return request({
            url: `/vod/teacher/page/${current}/${limit}`,
            method: `post`,
            data: searchObj
        })
    },
    removeById(id) {
        return request({
            url: `/vod/teacher/remove/${id}`,
            method: `delete`
        })
    },
    save(teacher) {
        return request({
            url: `/vod/teacher/save`,
            method: `post`,
            data: teacher
        })
    },
    get(id) {
        return request({
            url: `/vod/teacher/get/${id}`,
            method: `get`
        })
    },
    update(teacher) {
        return request({
            url: `/vod/teacher/update`,
            method: `post`,
            data: teacher
        })
    },
    betchRemove(ids) {
        return request({
            url: `/vod/teacher/batchRemove`,
            method: `delete`,
            data: ids
        })
    }
}