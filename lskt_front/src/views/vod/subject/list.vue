<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%"
      row-key="id"
      border
      lazy
      :load="load"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="title" label="名称" width="150"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
    </el-table>
  </div>
</template>
<script>
import subjectAPI from '@/api/vod/subject'
export default {
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getSubList(0)
  },
  methods: {
    getSubList(id) {
      subjectAPI.getChildren(id).then(res => {
        this.list = res.data
      })
    },
    load(tree, treeNode, resolve) {
      subjectAPI.getChildren(tree.id).then(response => {
        resolve(response.data)
      })
    }
  }
}
</script>