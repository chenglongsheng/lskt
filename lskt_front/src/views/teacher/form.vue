<template>
  <div class="app-container">
    <!-- 输入表单 -->
    <el-form :model="teacher" label-width="120px" :rules="rules" ref="teacher">
      <el-form-item label="讲师名称" prop="name">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="入驻时间" prop="joinDate">
        <el-date-picker v-model="teacher.joinDate" value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" :min="0" />
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师简介" prop="intro">
        <el-input v-model="teacher.intro" />
      </el-form-item>
      <el-form-item label="讲师资历" prop="career">
        <el-input v-model="teacher.career" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="handleAvatarError"
          :action="BASE_API+'/vod/file/upload?module=avatar'"
          class="avatar-uploader"
        >
          <img v-if="teacher.avatar" :src="teacher.avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate('teacher')">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/vod/teacher'
export default {
  data() {
    return {
      // 初始化讲师默认数据
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        joinDate: '',
        intro: '',
        career: ''
      },
      BASE_API: 'http://localhost:8301',
      saveBtnDisabled: false, // 保存按钮是否禁用，防止表单重复提交
      rules: {
        name: [
          { required: true, message: '请输入讲师名称', trigger: 'change' }
        ],
        joinDate: [
          {
            required: true,
            message: '请输入入驻时间',
            trigger: 'change'
          }
        ],
        intro: [
          { required: true, message: '请输入讲师简介', trigger: 'change' }
        ],
        career: [
          { required: true, message: '请输入讲师资历', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    if (this.$route.params.id) {
      this.fetchDataById(this.$route.params.id)
    }
  },
  methods: {
    saveOrUpdate(formName) {
      //   this.saveBtnDisabled = true
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.teacher.id) {
            this.updateData()
          } else {
            this.saveData()
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    saveData() {
      teacherApi.save(this.teacher).then(response => {
        this.$message({
          type: 'success',
          message: '添加成功！'
        })
        // 导航到列表页面
        this.$router.push({ path: '/vod/teacher/list' })
      })
    },
    updateData() {
      teacherApi.update(this.teacher).then(response => {
        this.$message({
          type: 'success',
          message: '更新成功！'
        })
        // 导航到列表页面
        this.$router.push({ path: '/vod/teacher/list' })
      })
    },
    fetchDataById(id) {
      teacherApi.get(id).then(response => {
        this.teacher = response.data
      })
    },
    // 上传成功回调
    handleAvatarSuccess(res, file) {
      console.log('res', res, 'file', file)
      if (res.code === 20000) {
        this.teacher.avatar = res.data
        // 强制重新渲染
        this.$forceUpdate()
      } else {
        this.$message.error('上传失败')
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleAvatarError(err, file, fileList) {
      this.$message.error('上传失败（http失败）')
    }
  }
}
</script>

<style scoped>
.avatar-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar-uploader .avatar-uploader-icon:hover {
  border-color: #409eff;
}
.avatar-uploader img {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
