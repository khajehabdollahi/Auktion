<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col s12 m10 offset-m1">
        <div class="grey lighten-4 black-text center">
          <h4>Skapa konto</h4>
          <hr class="hr-style" />
          <form @submit.prevent="signup">
            <div class="input-field">
              <input required type="text" id="text" v-model="name" />
              <label for="email">Name</label>
            </div>
            <div class="input-field">
              <input required type="email" id="email" v-model="email" />
              <label for="email">E-mail</label>
            </div>
            <div class="input-field">
                <input required type="number" id="phone-number" v-model="phoneNumber" />
                <label for="phone-number">Phonenumber:</label>
            </div>
            <div class="input-field">
              <input required type="password" id="password" v-model="password" />
                <label for="password">Password:</label>
            </div>
            <div class="alert center red-text">
              
            </div>
            <button type="submit" class="btn btn-small waves-effect">Skapa konto</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import axios from 'axios' 

export default {
    name: 'register',
    data: function() {
        return {
            name: '',
            phoneNumber: '',
            email: '',
            password: ''
        }
    },
    methods: {
      saveUserToStorage(user) {
        localStorage.setItem('currentUser', JSON.stringify(user));
      },

        async signup(){
            await fetch('http://localhost:5000/api/v1/user/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: this.name,
                    phone: this.phoneNumber,
                    email: this.email,
                    password: this.password,
                    roles: []
                })
            }).then(res => {
                return res.json()
            }).then(data => { 
              console.log(data); 
              localStorage.setItem('currentUser', JSON.stringify(data));
            })
        }
      }
}
</script>
<style lang="css" scoped>
button {
  margin-top: 5%;
}
.hr-style {
  border: 0;
  height: 1px;
  margin: 0 0 10px 0;
  background: #fff;
  background-image: -webkit-linear-gradient(
    left,
    rgb(255, 255, 255),
    rgba(184, 10, 103, 0.993),
    rgb(255, 255, 255)
  );
}
.btn {
  background: #ee6e73;
}
</style>