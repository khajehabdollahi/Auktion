<template>
  <div>
    <h4>Current Auctions:</h4>
    <h4>{{ searchedAuction }}</h4>
    <div class="article-list">
      <AuctionListItem
        v-for="auction in (searchedAuctions == undefined || searchedAuctions.length == 0 ? auctionsData : searchedAuctions)" 
        :key = "auction.id"
        :auction="auction"
      />
      <!-- <AuctionListItem
      v-for="auction in auctionsData" 
      :key = "auction.id"
      :auction="auction"
     /> -->
    </div>
  </div>
</template>


<script>
// TODO: auction in (searchedAuction != [](empty) ? searchedAuction : auctionsData) 

import AuctionListItem from './AuctionListItem';

export default {

  components: {
    AuctionListItem
  },
   data() {
    return {
      searchedAuctions: [],
      //auctions: this.$store.getters.auctionList,
    };
  },

  computed: {
    auctionsData() {
      return this.$store.getters.auctionList;
    },
    auctionSearched() {
      return this.$store.getters.auctionSearched;
    }
  },
  watch: {
    auctionSearched() {
      
      let result = this.auctionsData.filter(auction => auction.productName.toLowerCase().includes(this.auctionSearched.toLowerCase()));

      // Sort result
      // if (result !== undefined || result.length !== 0) {
      //   result.sort(function(a) { if (a === this.auctionSearched) return 1; else return -1; }); 
      // }

      this.searchedAuctions = result;


      //console.log("RESULT", result);


      //   // TODO: FIlter array
      // for (let auction of this.auctionsData) {
      //   if (auction.productName.includes(this.auctionSearched)) {
      //     console.log("VICTORY!");
      //   }
      // }

    }
  }


 

}
</script>

<style scoped>
  .article-list {
    background-color: rgb(231, 231, 231);
    display: flex;
    flex-flow: row wrap;
    justify-content: space-around;
  }

  h4 {
    font-size: 150%;
    text-align: start;
    padding-left: 25px;
  }

</style>