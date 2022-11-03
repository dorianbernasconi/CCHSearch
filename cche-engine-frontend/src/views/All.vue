<script setup lang="ts">
import Document from "../components/Document.vue";
import { searchStore } from "../stores/search"
import { ref } from 'vue'
import { getSolrDocument } from "../api/apiService";
import Pagination from "../components/Pagination.vue";
import { onMounted } from "vue";

const rows = 10;


// Variable use in Pagination

// max button to display
let maxVisibleButtons = 3;
// Max page to display
let totalPages = ref(0)
// number of element per page
let per = 10;
// current page
let currentPage = ref<Number>(1)

let numberOfDocumentFound = ref(0)


function getNewDocument() {
  getSolrDocument(store.keyword, store.manda,  ((currentPage.value.valueOf() - 1)* rows).toString(), rows.toString()).then( (documentsJson : any) => {
    lines.value = documentsJson["documents"];
    totalPages.value = Math.round(documentsJson["nbFound"] / 10)
  })
}
const store = searchStore()


onMounted(() => {
  console.log("ALL MOUNTED")
  store.filterType = "solr";
  console.log(store.filterType)
})

// lines is a json that contains a list of Element
const lines: any = ref("");

getNewDocument();

// Maybe subscribe to a specific variable and not to the store itself
// Because we want an update only in the situation where the keyword is modified ??
store.$subscribe(() => {
  getNewDocument();
})

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
.cards {
  padding-left: 200px;
  padding-bottom: 100px;
}
</style>
  

