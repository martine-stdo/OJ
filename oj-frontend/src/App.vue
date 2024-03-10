<template>
  <div class="app">
    <BasicLayout />
  </div>
</template>

<style></style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();
/**
 * 全局初始化函数，全局单次调用的代码都可以写在这里
 */
const doInit = () => {
  console.log();
};

onMounted(() => {
  doInit();
});

//权限管理

//在公共组件中获取下一个路由信息中是否需要meta.access并且通过
//状态管理获取用户权限来判断是否跳转
router.beforeEach((to, from, next) => {
  if (to.meta?.access === "canAdmin") {
    if (store.state.user.loginUser?.role !== "admin") {
      next("/404");
      return;
    }
  }
  next();
});
</script>
