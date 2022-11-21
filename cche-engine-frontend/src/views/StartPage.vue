


<script setup lang="ts">
import { ref,onBeforeMount } from 'vue'
import { getDocuments } from "../api/elasticService"
import Preview from "../components/Preview.vue";
import Cards from "../components/Cards.vue";
import { previewStore } from "../stores/preview"
import { searchStore } from "../stores/search"
import type { CardElement } from "../api/type"

const store = searchStore();
const emit = defineEmits(["startpage"]);

let value = ref("");

function search(){
  store.keyword = value.value;
  emit("startpage");
}

</script>

<template>



<!--
  <div class="page" role="navigation">

   <div class="img-on-top-of-inpu">

      <img class="" src="src/components/icons/logovec.png" />
    </div> 

    <div >
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>
    </div>
    </div>
  -->

    <!--<button @click="search()" > SEARCH </button>-->
  <div class="page" role="navigation">
    <ul>
      <li>
        <img class="" src="src/components/icons/logovec.png" />

      </li>
      <li>
        <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>

      </li>
    </ul>
  </div>  
</template>
 
<style scoped>



ul{
  text-align: center;
}

img{
  top: 3px;
  padding-bottom: 50px;
  width: 10%;
  display: inline-block;
  vertical-align: middle,
}


.page{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-color: #343a40 !important;
  font-family: "Open Sans", sans-serif;
}

#search_box {
  padding-left: 0px;
  height: 40px;
  width: 700px;
  padding: 6px;
  border-radius: 26px;
     font-size: 20px;
     border-width: 0px;
          color: #000000;
     box-shadow: 0px 0px 2px rgba(66,66,66,.79);
     text-shadow: 0px 0px 0px rgba(66,66,66,.75);
}
.css-input {
     padding: 6px;
     font-size: 18px;
     border-width: 0px;
     border-color: #CCCCCC;
     background-color: #FFFFFF;
     color: #000000;
     border-style: solid;
     border-radius: 26px;
     box-shadow: 0px 0px 2px rgba(66,66,66,.79);
     text-shadow: 0px 0px 0px rgba(66,66,66,.75);
}
 .css-input:focus {
     outline:none;
}




</style>