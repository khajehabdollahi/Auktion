<template>
  <div class="home-page">
    <div class="search-field">
      <input v-model="search" type="text" placeholder="Search Auction..." />
    </div>
      <AuctionList />
  </div>
</template>


<script>
import AuctionList from "../components/AuctionList.vue";

export default { 
  data() {
    return {
      title: 'Hello',
      search: "",
    };
  },
  components: {
    AuctionList
  },
  watch: {
    search() {
      this.$store.commit('setAuctionSearched', this.search);      
    }
  },
}
</script>

<style>
.home-page h1 {
    text-align: center;
}

.search-field {
  width: 80vw;
  margin: auto;
  padding-top: 20px;
}

div {
  background-color: #f3f3f3;
}
</style>

