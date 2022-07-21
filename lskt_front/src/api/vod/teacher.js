import request from '@/utils/request'

export default {
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
    }
}