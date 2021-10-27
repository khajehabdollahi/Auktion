<template>
  <div @click="goToAuctionDetails(auction)" class="auction-item">
    <!-- <img class="item-img" src="https://i.ebayimg.com/images/g/N0oAAOSwmo1fdoDf/s-l640.jpg" /> -->
    <img class="item-img" :src="auction.imageURL" />
    <div class="item-desc">
      <h3 class="product-name">{{ auction.productName }}</h3>
      <div class="currentbid">
        <!-- <h4>Starting bid: {{ auction.startBid }}</h4> -->
        <!-- <h5>Current Bid: {{ (auction.currentBid == undefined ? "Not Available" : auction.currentBid) }}</h5> -->
        <h5>Current Bid: {{ (currentBid == undefined || currentBid === 0 ? "No bid" : currentBid) }}</h5>
      </div>
      
      <div class="expires">
        <h4>Ends on: {{ auction.endDate }}</h4>
      </div>
    </div>
   </div>
</template>

<script>

export default {
  props: ['auction'],
  data() {
    return {
      currentBid: 0,
    };
  },
  methods: {
    goToAuctionDetails(auction) {
      this.$router.push({ name: 'AuctionDetail', params: {auction: auction} });
      //this.$router.push(`detail/${auction}`);
    },
    async fetchCurrentBid() {
      //let testBid = "asuia33";
      //let res = await fetch(`http://localhost:5000/api/v1/bid/auctionId:${testBid}`);
      let res = await fetch(`http://localhost:5000/api/v1/bid/auctionId:${this.auction.id}`);
      let json = await res.json();

      if (json.length != 0) {
        this.showHighestBid(json);
      }
    },
    showHighestBid(bids) {
      let highestBid = 0;

      for (let bid of bids) {
        if (highestBid < bid.price) {
          highestBid = bid.price;
        }
      }
      this.currentBid = highestBid;
      this.auction.currentBid = highestBid;
    },
  },
  created() {
    this.fetchCurrentBid();
    this.interval = setInterval(() => this.fetchCurrentBid(), 1000);
  },
  beforeDestroy() {
    clearInterval(this.interval);
  }
}
</script>

<style>
.auction-item {
  width: 25vw;
  margin: 5px 20px 5px 20px;
  /* padding-left: 15px; */
  cursor: pointer;
  background-color: white;
  border: 2px solid darkgray;
}

.item-desc {
  text-align: start;
  padding: 0px 15px;
  background-color: white;
  /* padding-left: 25px; */
}

.product-name {
  width: 100%;
  margin-top: 2px;
  margin-bottom: 2px;
  font-size: 200%;
  text-align: center;
}

h5 {
  margin-top: 0px;
  background-color: white;

  font-size: 130%;
}

.auction-item img {
  padding-top: 10px;
  flex-shrink: 0;
  max-width: 60%;
  max-height: 60%;
}

.expires {
  background-color: white;
  text-align: end;
  padding-right: 10px;
  padding-bottom: 0px;
  margin-bottom: 0px;
}

.expires h4 {
  font-size: 130%;
  margin: 0px;
  padding-bottom: 2px;
  padding-top: 20px;
}

.item-img {
  height: 30vh;
  object-fit: cover;
  object-position: center;
}
</style>