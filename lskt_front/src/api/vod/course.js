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
    }
}