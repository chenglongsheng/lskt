import request from '@/utils/request'

const api_name = '/order/orderInfo'

export default {

    getPageList(page, limit, searchObj) {
        return request({
            url: `${api_name}/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    }
}