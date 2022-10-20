<script setup lang="ts">

import Document from "../components/Document.vue";
import {searchStore} from "../stores/search"
import { ref,onMounted } from 'vue'
import type {PropType} from 'vue'
import {getDocument} from "../api/apiService";
import Pagination from "../components/Pagination.vue";
/*
const props = defineProps({
    ind: Number,    
    lines:Array as PropType<any>
});
*/
function getNewDocument(){
  getDocument(store.keyword,store.manda,store.start,store.rows).then(documentsJson => {
  lines.value = documentsJson;
})
}
const store = searchStore()

// lines is a json that contains a list of Element
const lines : any = ref("");

getNewDocument();

store.$subscribe( (mutation,state) => {
  getNewDocument();
}) 



</script>

<template>
  
<link rel="stylesheet" href="css/paginate.css">

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
  

