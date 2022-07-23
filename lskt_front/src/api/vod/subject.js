import request from '@/utils/request'

export default {
    getChildren(id) {
        return request({
            url: `/vod/subject/list/${id}`,
            method: `get`
        })
    },
}