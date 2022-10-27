


<script setup lang="ts">
import Card from "../components/Card.vue";
import { ref } from 'vue'
import { getElastic } from "../api/apiService"
import Preview from "../components/Preview.vue";
import Cards from "../components/Cards.vue";

let previewOpen = ref(true)


</script>
<template>

  <div class="cards-container" :class="{'cards-container-half':previewOpen, 'cards-container-full':!previewOpen, }">
    <Cards />
  </div>
  <div class="preview-container"  :class="{'preview-container-half':previewOpen, 'preview-container-none':!previewOpen, }">
    <Preview @close="previewOpen = false" />
  </div>

</template>
 
<style scoped>


.cards-container{
  display: inline;
  float: left;
  overflow-y: scroll;
  height: 100%;
}

.preview-container{
  display: inline;
  float: right;
  overflow-y: scroll;
}
.cards-container-full{
  width: 100%;
}
.cards-container-half{
  width: 50%;
}

.preview-container-half{
  width: 50%;
  height: 100%;
}

.preview-container-none{
  width: 0%;
  height: 0%;
}



</style>
