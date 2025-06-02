import Vue from 'vue'
import App from './App.vue'
import router from './router'
// 引入elementui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 导入Axios
import Axios from "axios";

// 将Axios赋值给vue的自定义属性;方便后续使用
Vue.prototype.axios = Axios;

Vue.config.productionTip = false
// 显示使用elementui
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
