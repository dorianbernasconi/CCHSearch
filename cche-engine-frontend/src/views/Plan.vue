


<script setup lang="ts">
import { ref,onBeforeMount } from 'vue'
import { getDocuments } from "../api/elasticService"
import Preview from "../components/Preview.vue";
import Cards from "../components/Cards.vue";
import { previewStore } from "../stores/preview"
import { searchStore } from "../stores/search"
import type { CardElement } from "../api/type"

const preview = previewStore();
const search = searchStore();

let previewOpen = ref<Boolean>(false)
let cards = ref<Array<CardElement>>([])


function getNewDetails() {
  getDocuments(search.keyword, "home2", "50", "2000",search.fieldSelector).then((o => {
    cards.value = o;
  }))
}
function updatePreview(page: CardElement) {
  preview.card = page
}

preview.$subscribe(() => {
  previewOpen.value = true;
})

search.$subscribe(() => {
  getNewDetails();
})

onBeforeMount(() => {
  search.filterType = "elastic";
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
}

.preview-container {
  display: inline;
  float: right;
  overflow-y: scroll;
}

.cards-container-full {
  width: 100%;
}

.cards-container-half {
  width: 60%;
}

.preview-container-half {
  width: 40%;
  height: 100%;
}


.preview-container-none {
  width: 0%;
  height: 0%;
}
</style>
