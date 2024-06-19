<template>
  <div class="user">
    <el-tabs type="border-card">
      <el-tab-pane label="用户信息">
        <span slot="label"><i class="el-icon-date"></i>用户信息 </span>
        <el-row class="demo-avatar demo-basic">
          <el-col :span="12" style="padding: 10px 10px">
            <div class="demo-basic--circle">
              <div class="block">
                <el-avatar shape="square" :src="squareUrl"></el-avatar>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-descriptions class="margin-top" :column="3" :size="size" border>
          <el-descriptions-item label="用户名">user</el-descriptions-item>
          <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
          <el-descriptions-item label="居住地">呼和浩特市</el-descriptions-item>
          <el-descriptions-item label="备注">
            <el-tag size="small">学校</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="联系地址">内蒙古自治区呼和浩特市赛罕区内蒙古农业大学</el-descriptions-item>
        </el-descriptions>
<!--        <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>-->

        <!-- 对话框将在第二部分显示 -->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import { ref, onMounted, onUnmounted } from 'vue';
import { ElTabs, ElTabPane, ElRow, ElCol, ElAvatar, ElRadioGroup, ElRadio, ElDescriptions, ElDescriptionsItem, ElButton } from 'element-plus';

export default {
  components: {
    ElTabs, ElTabPane, ElRow, ElCol, ElAvatar, ElRadioGroup, ElRadio, ElDescriptions, ElDescriptionsItem, ElButton
  },
  setup() {
    const squareUrl = "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";
    const size = '';
    const form = ref({
      name: '',
      region: '',
      date1: '',
      date2: '',
      delivery: false,
      type: [],
      resource: '',
      desc: ''
    });
    const formLabelWidth = '120px';

    const dialogFormVisible = ref(false);

    const openDialog = () => {
      dialogFormVisible.value = true;
    };
    onMounted(() => {
      // 组件挂载完成后执行的代码
      console.log('组件已挂载');
    });

    onUnmounted(() => {
      // 组件卸载前执行的代码
      console.log('组件卸载');
    });

    return {
      squareUrl,
      size,
      dialogFormVisible,
      form,
      formLabelWidth,
      openDialog
    };
  }
};
</script>
