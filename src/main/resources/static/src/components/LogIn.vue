<template>
    <div class="container-fluid">
      <div class="row login">
        <div class="col s12 m8 offset-m2">
          <div class="center">
            <h4>Logga in</h4>
            <hr class="hr-style">
            <form @submit="login">
              <div class="input-field">
                <input type="email" id="email" v-model="email" />
                <label for="email">E-post</label>
              </div>
              <div class="input-field">
                <input type="password" id="password" v-model="password" />
                <label for="password">Lösenord</label>
              </div>
              <div class="alert center red-text" v-if="alert !== ''">
                <h6>{{alert}}</h6>
              </div>
              <button
                type="submit"
                class="btn btn-small waves-effect"
              >Logga in</button>
            </form>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {

  data() {
    return {
      email: "",
      password: "",
    
    }
  },
  methods: {
    login(){
      console.log('u tried to log in');
    }
  }
}
  </script>