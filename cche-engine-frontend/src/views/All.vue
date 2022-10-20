<script setup lang="ts">

import Document from "../components/Document.vue";
import {searchStore} from "../stores/search"
import { ref } from 'vue'
import {getDocument} from "../api/apiService";
import Pagination from "../components/Pagination.vue";

function getNewDocument(){
  getDocument(store.keyword,store.manda,store.start,store.rows).then(documentsJson => {
  lines.value = documentsJson;
})
}
const store = searchStore()

// lines is a json that contains a list of Element
const lines : any = ref("");

getNewDocument();

store.$subscribe( () => {
  getNewDocument();
}) 


</script>
<template>

  <div class="cards">
    <div >
      <Document v-for="(line,i) in lines" :ind="i" v-bind="line"></Document>
    </div>
    <Pagination> HERE</Pagination>
</div>
</template>
   
  
<style scoped>

.cards{
  padding-left: 200px;
  padding-bottom: 100px;

}




</style>
  

