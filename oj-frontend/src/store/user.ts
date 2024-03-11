// 引入 Vuex 中的 StoreOptions 类型
import { ACCESS_ENUM } from "@/access/accessEnum";
import { StoreOptions } from "vuex";
import { UserControllerService } from "../../generated";
// 导出 Vuex store 的配置对象
export default {
  // 命名空间，使得模块化 Vuex store 更容易管理
  namespaced: true,

  // 定义 Vuex store 的初始状态
  state: () => ({
    loginUser: {
      userName: "未登录",
      // userRole: ACCESS_ENUM.NOT_LOGIN,
    },
  }),

  // 定义异步操作，可通过提交 mutations 来修改状态
  actions: {
    // 异步操作，获取登录用户信息
    // 参数包括 commit 方法用于提交 mutations，state 用于访问当前状态，payload 用于传递额外的数据
    async getLoginUser({ commit, state }, payload) {
      // 从远程获取登录信息
      // 提交更新用户信息的 mutations
      const res = await UserControllerService.getLoginUserUsingGet();
      if (res.code === 0) {
        commit("updateUser", res.data);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          userRole: ACCESS_ENUM.NOT_LOGIN,
        });
      }
    },
  },
  // 定义同步操作，通过直接修改状态来实现
  mutations: {
    // 更新用户信息的 mutations
    // 参数包括当前状态对象 state 和负载数据 payload
    updateUser(state, payload) {
      // 更新用户信息
      state.loginUser = payload;
    },
  },
} as StoreOptions<any>;
