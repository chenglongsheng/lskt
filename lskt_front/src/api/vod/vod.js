import request from '@/utils/request'

export default {
  //删除视频
  removeByVodId(id) {
    return request({
      url: `/vod/remove/${id}`,
      method: 'delete'
    })
  }
}