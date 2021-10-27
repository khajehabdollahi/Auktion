<template>
<div>
<div>Hej {{ this.$store.getters.currentUser.name }}!</div>
<p>Phone: {{ this.$store.getters.currentUser.phone }}</p>
<p>Email: {{ this.$store.getters.currentUser.email }}</p>

</div>
</template>
 
<script>
import M from 'materialize-css'
export default{
  data() {
    return {
      user: {}
    }
  },
  methods: {
    async fetchUser() {
      console.log('hej')
      let user = this.$store.getters.currentUser;
      //let user = JSON.parse(localStorage.getItem('currentUser'));
      console.log("Current User: ", user);
      this.user = user
    }
  },
  created(){
    M.AutoInit();
    this.fetchUser();
  }
}

 </script>