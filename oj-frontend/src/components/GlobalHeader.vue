<template>
  <a-row
    id="globalHeader"
    class="grid-demo"
    style="margin-bottom: 0"
    align="center"
  >
    <a-col flex="100px">
      <div>
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          @click="handleIconClick"
        >
          <div class="titleBar">
            <img class="icon" src="../assets/logo2.png" />
            <div class="title">OJ 平台</div>
          </div>
        </a-menu-item>
      </div>
    </a-col>
    <a-col flex="auto">
      <div>
        <a-menu
          mode="horizontal"
          :selected-keys="selectedKeys"
          @menu-item-click="doMenuClick"
        >
          <a-menu-item v-for="item in routes" :key="item.path">{{
            item.name
          }}</a-menu-item>
          <!-- 从routes中获取path的值和name值并且显示 -->
        </a-menu>
      </div>
    </a-col>
    <a-col flex="100px">
      <div>江涛</div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "../router/routes";
import { useRouter } from "vue-router";
import { ref } from "vue";

const router = useRouter();

//默认主页
const selectedKeys = ref(["/"]);

//路由跳转后，更新选中的菜单项
router.afterEach((to) => {
  selectedKeys.value = [to.path];
});
// router.afterEach 是一个 Vue Router 钩子函数，它在每次路由导航完成后执行，
// 并调用回调函数来更新 selectedKeys，以确保页面中显示的菜单项正确反映当前路由的选中状态。
const doMenuClick = (key: string) => {
  console.log("Clicking menu item with key:", key);
  router.push({
    path: key,
  });
};
// 通过menu-item-click获取key值就是item对应的name值之后调用并传递给doMenuClick
// 之后通过router.push实现页面的跳转

//处理点击icon的函数，点击icon后跳转到/路径
const handleIconClick = () => {
  router.push("/");
};
</script>

<style scoped>
.titleBar {
  display: flex;
  align-items: center;
}

.icon {
  height: 50px;
}

.title {
  color: #444;
  margin-left: 16px;
}
</style>
