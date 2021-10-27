
<template>

 <div class="row">
    <div class="card cover">

      <div class=" card center-align blue darken-1 ">
        <div class="card-content white-text">
          <span class="card-title text">About us</span>
          <h2 class="text">Here you can buy what you need and sell what you don't need. You can suggest higher price also.</h2>
        </div>
        <div class="card-action red-text">
          <h2 class="yellow-text">Contact Us</h2>
          <ul>
         <li> <a class="btn-large" href="#">Send Mail</a></li>
         <br/>
          <li><a class="btn-large" href="#">Call Us</a></li>
          </ul>
        </div>
      </div>
      </div>
     
  </div>
  

      



</template>

 <script>
import M from 'materialize-css'

export default {
    mounted () {
    M.AutoInit();
    let modal = document.querySelectorAll(".modal");
    this.$M.Modal.init(modal);
    }
}
</script>

<style>
.text {
  color: rgb(100, 98, 98);
}

</style>
