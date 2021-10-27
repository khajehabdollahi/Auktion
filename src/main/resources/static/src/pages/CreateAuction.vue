<template>
<div class="containerAuction">
    <form action="#" @submit.prevent="createAuction()">
  <div class="auction">
      <h1>Create new auction</h1>
      <p class="p-tag">Product name</p>
      <input class="product-name width-input" v-model="name">
      <br>
      <p class="p-tag">Description</p>
      <input class="product-name description width-input" v-model="desc">
      <br>
      <p class="p-tag">Image URL</p>
      <input class="product-name img-url width-input" type="text" v-model="img">
      <br>
      <p class="p-tag">Start bid</p>
      <input class="product-name asking-bid width-input" type="number" min=0 oninput="validity.valid||(value='');" v-model="startBid">
      <br>
      <p class="p-tag">Number of days</p>
      <input class="product-name end-date width-input" type="number" min=1 max="30" oninput="validity.valid||(value='');" v-model="endDate">
      <br>
        <button class="create-auction btn waves-effect waves-light" type="submit" name="action" >Create auction</button>
  </div>
  </form>
  </div>
</template>

<script>

import M from 'materialize-css'
import moment from 'moment'

export default {
    data() {
        return {
            name: '',
            desc: '',
            img: '',
            startBid: '',
            endDate: '',
            user: {}
        }
    },

    mounted () {
        M.AutoInit(),

        fetch('http://localhost:5000/api/v1/user')
        .then(res => {
            return res.json()
        })
        this.fetchUser();
    },
    methods: {
        async createAuction(){
            console.log(this.user.id, 'user id');
            console.log(this.name, this.desc, this.img, this.startBid, this.endDate);
            this.endDate = this.endDate*86400;
            let currentTime = Math.round(+new Date()/1000);
            this.endDate = this.endDate + currentTime;
            console.log("second " + this.endDate);
            this.endDate = new Date(this.endDate * 1000);
            this.endDate = moment(this.endDate).format('MMM Do YYYY, h:mm:ss a')
    
            console.log("third " + this.endDate);
            await fetch('http://localhost:5000/api/v1/auction', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        userID: this.user.id,
        productName: this.name,
        description: this.desc,
        imageURL: this.img,
        startBid: this.startBid,
        endDate: this.endDate
      })
    })
        },
        async fetchUser() {
            let user = JSON.parse(localStorage.getItem('currentUser'));
            console.log("Current user: ", user, user.id);
            this.user = user;
    }
    }

}

</script>


<style>
.auction {
    margin:0 auto;
}

.width-input {
    width: 40% !important;
}

.asking-bid {
    width: 40% !important;
}

.p-tag {
    margin-bottom: 0;
}

.create-auction {
    margin-top: 40px;
}

</style>