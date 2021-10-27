<template>
  <div id="app">
    <Header/>
    <router-view />
    <Footer/>
  </div>
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'app',
  methods: {

    async fetchAuctions() {
      this.$store.dispatch('fetchAuctions');
    },

  
    async signInUser() {

      let email = JSON.parse(localStorage.getItem('currentUserEmail'));

      let user = await fetch(`http://localhost:5000/api/v1/user/autologin:${email}`);
      let json = await user.json();

      if (json) {
        this.$store.commit('setCurrentUser', json);
        localStorage.setItem('currentUser', JSON.stringify(json)); // For those who are fetching it directly from localStorage
      }

      // let user = await fetch("http://localhost:5000/api/v1/user/autologin:", {
      //   method: 'POST',
      //   //mode: "no-cors",
      //   headers: { "Content-Type": "application/json" },
      //   body: JSON.stringify(email)
      //   //body: JSON.stringify({email: this.$store.getters.currentUserEmail})
      // });

      //user = await user.json();
    } 
    
  },
  created() {
    this.fetchAuctions();
    this.signInUser();
  },
  components: {
    Header,
    Footer
  }
}

</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
