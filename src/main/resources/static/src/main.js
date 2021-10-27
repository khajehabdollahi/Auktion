import Vue from 'vue'
import App from './App.vue'
import 'materialize-css'

import 'materialize-css/dist/css/materialize.min.css'
//import 'material-design-icons/iconfont/material-icons.css'
import store from './store'
import router from './router'
import 'materialize-css/dist/css/materialize.min.css'


Vue.config.productionTip = false

new Vue({
  store,
  router,
  //beforeCreate() {
    //let user = localStorage.getItem('currentUser');
    //console.log("CURRENT USER LOLOLOL: ", user);
    //this.$store.commit('setCurrentUser', user);
  //},
  render: h => h(App),

}).$mount('#app')
