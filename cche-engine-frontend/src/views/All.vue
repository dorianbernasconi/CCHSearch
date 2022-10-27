<script setup lang="ts">
import Document from "../components/Document.vue";
import { searchStore } from "../stores/search"
import { ref } from 'vue'
import { getSolrDocument } from "../api/apiService";

function getNewDocument() {
  getSolrDocument(store.keyword, store.manda, store.start, store.rows).then(documentsJson => {
    lines.value = documentsJson;
  })
}
const store = searchStore()

// lines is a json that contains a list of Element
const lines: any = ref("");

getNewDocument();

// Maybe subscribe to a specific variable and not to the store itself
// Because we want an update only in the situation where the keyword is modified
store.$subscribe(() => {
  getNewDocument();
})


</script>
<template>

  <div class="cards">
    <div>
      <Document v-for="(line,i) in lines" :ind="i" v-bind="line"></Document>
    </div>
  </div>
</template>
   
  
<style scoped>
.cards {
  padding-left: 200px;
  padding-bottom: 100px;
}
</style>
  

