


<script setup lang="ts">
import { onBeforeMount, ref } from 'vue'
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
  getDocuments(search.keyword, "home2", "0", "50",search.fieldSelector).then((o => {
    console.log(o);
    cards.value = o;
  }))
}

function updatePreview(page: CardElement) {
  console.log("updatePREV")
  preview.card = page
}

onBeforeMount(() => {
  search.filterType = "elastic";
})


// TODO
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
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}

.preview-container {
  background-color: rgba(232, 232, 232, 0.5);

  display: inline;
  float: right;
  overflow-y: scroll;
  overflow-x: hidden;
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
  border-left: 1px solid rgba(0,0,0,0.16);

}

.cards-container-full {
  width: 100%;
}

.cards-container-half {
  width: 67.2%;
}

.preview-container-half {
  width: 32.8%;
  height: 100%;
}

.preview-container-none {
  width: 0%;
  height: 0%;
}
</style>
