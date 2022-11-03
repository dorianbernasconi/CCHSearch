


<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getElasticDetail } from "../api/apiService"
import Preview from "../components/Preview.vue";
import Cards from "../components/Cards.vue";
import { previewStore } from "../stores/preview"
import { searchStore } from "../stores/search"
import type { CardElement } from "../api/type"


let previewOpen = ref<Boolean>(false)
let cards = ref<Array<CardElement>>([])

const preview = previewStore();
const search = searchStore();

function getNewDetails() {
  getElasticDetail(search.keyword, "home2", "0", "50",search.fieldSelector).then((o => {
    console.log(o);
    cards.value = o;
  }))
}


onMounted(() => {
  search.filterType = "elastic";
})


function updatePreview(page: CardElement) {
  preview.card = page
}

preview.$subscribe(() => {
  previewOpen.value = true;
})

search.$subscribe(() => {
  getNewDetails();
})

getNewDetails();

</script>

<template>

  <div class="cards-container" :class="{ 'cards-container-half': previewOpen, 'cards-container-full': !previewOpen, }">
    <Cards :callback="updatePreview" :cards="cards" />
  </div>
  <div class="preview-container"
    :class="{ 'preview-container-half': previewOpen, 'preview-container-none': !previewOpen, }">
    <Preview @close="previewOpen = false" />
  </div>


</template>
 
<style scoped>
.cards-container {
  display: inline;
  float: left;
  overflow-y: scroll;
  height: 100%;
  min-width: 800px;
}

.preview-container {
  background-color: rgba(232, 232, 232, 0.5);

  display: inline;
  float: right;
  overflow-y: scroll;
  overflow-x: hidden;
}

.cards-container-full {
  width: 100%;
}

.cards-container-half {
  width: 50%;
}

.preview-container-half {
  width: 50%;
  height: 100%;
}

.preview-container-none {
  width: 0%;
  height: 0%;
}
</style>
