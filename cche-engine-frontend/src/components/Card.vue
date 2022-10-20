<template>
  <div class="card card_medium">
    <p style="text-align: right;">&#9829</p>
    <img :src="props.src" alt="" />
  <div>
    <p class="left" >{{filename}}</p>
    <p style="text-align:right" class="right" >{{echelle}}</p>
  </div>


    <p style="width: 100%; ">{{affaire}}</p>
    

  </div>
</template>

<script setup lang="ts">
const props = defineProps({
  src: String,
  filename: String,
  affaire: String,
  echelle: Number,


});
</script>

<style scoped>
.card_medium {
  box-shadow: 5px 3px 3px #8a8a8a;
  border: 1px solid rgb(0, 0, 0);
}

.card {
  border: 2px solid #e7e7e7;
  border-radius: 4px;
  padding: 0.5rem;
}

img{
  max-width:100%;
max-height:100%;
}

.right{
  float: right;
  width: 50%;
}

.left{
  float: left;
  width: 50%;
}

</style>
