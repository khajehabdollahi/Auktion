<template>
       <nav class=footer>
    <div>
      <a href="#" class="brand-logo center">Connect with us!</a>
      <ul id="nav-mobile" class="center hide-on-med-and-down">
        <li><a href="/contact">Contact</a></li>
        <li><a href="badges.html">Facebook</a></li>
        <li><a href="collapsible.html">Twitter</a></li>
      </ul>
    </div>
  </nav>
            
</template>
<script>
import M from 'materialize-css'
export default {


  mounted(){
    M.AutoInit();

  },
}

</script>
<style scoped>

.footer{
  background-color: rgb(100, 98, 98) !important;
  position: fixed;
  bottom: 0;
}
</style>