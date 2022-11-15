<script setup lang="ts">
import Document from "../components/Document.vue";
import { searchStore } from "../stores/search"
import { ref } from 'vue'
import { getDocuments } from "../api/solrService";
import Pagination from "../components/Pagination.vue";
import { onMounted, onBeforeMount,onBeforeUnmount} from "vue";


const store = searchStore()

const storeIsStillActive = ref(true)

const lines: any = ref("");
const rows = 10;
let maxVisibleButtons = 3;
let currentPage = ref<Number>(1)
// Max page to display
let totalPages = ref(0)
// number of element per page
let per = 10;

function getNewDocument() {

  getDocuments(currentPage.value.toString()).then( (documentsJson : any) => {
    lines.value = documentsJson["documents"];
    totalPages.value = Math.round(documentsJson["nbFound"] / 10)
  })
}

function onClickNextPage(currentPageEmit: number) {
  currentPage.value = currentPageEmit.valueOf()
  getNewDocument();
}

function selectPage(newCurrentPage: number) {
  if(newCurrentPage.valueOf() > 0 && newCurrentPage.valueOf() <= totalPages.value){
    currentPage.value = newCurrentPage.valueOf()
    getNewDocument();
  }
}
onBeforeMount(() => {
  store.filterType = "solr";
  store.ftype = "Image";
})
// If the router is gonna switch to another page, we don't want to update the store

onBeforeUnmount(() => {
  storeIsStillActive.value = false

})

store.$subscribe((e:any) => {
    if (storeIsStillActive.value) {
      getNewDocument();
    }
})






</script>

<template>

  <div class="cards">
    <div>
      <Document v-for="(line,i) in lines" :ind="((currentPage.valueOf()  - 1)* rows) + i" :obj="line"></Document>
    </div>
  </div>
  <Pagination v-if="totalPages > 0" @selectPage="selectPage" @onClickNextPage="onClickNextPage" :max-visible-buttons=maxVisibleButtons :total-pages=totalPages :per-page=per :current-page=currentPage.valueOf()></Pagination>
</template>
   
  
<style scoped>
#pagination{
  padding-bottom: 50px;
}
.cards {
  padding-left: 70px;
  padding-bottom: 100px;
  padding-top: 20px;

  
}

.defilement {
  height: 100%;
}
</style>
  

