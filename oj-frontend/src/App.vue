<template>
  <div class="app">
    <BasicLayout />
  </div>
</template>

<style></style>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout";
import { onMounted } from "vue";

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
</script>
