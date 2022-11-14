


<script setup lang="ts">
import { ref,onBeforeMount } from 'vue'
import { getDocuments } from "../api/elasticService"
import Preview from "../components/Preview.vue";
import Cards from "../components/Cards.vue";
import { previewStore } from "../stores/preview"
import { searchStore } from "../stores/search"
import type { CardElement } from "../api/type"

const store = searchStore();

let value = ref("");

function search(){
  store.keyword = value.value;
}

</script>

<template>
  <div class="nav-bar header-background-color" role="navigation">
    <div class="nav-bar-el header-bar-element">

      <img class="" src="src/components/icons/logo.png" />
    </div>
    <div id="navbar" class="nav-bar-el ">
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>
    </div>
    <!--<button @click="search()" > SEARCH </button>-->
  </div>

</template>
 
<style scoped>

img{
  width: 70%;
  display: inline-block;
  vertical-align: middle
}
.header-bar-element{


}
.nav-bar-brand {
  margin-left: 80px;
}

.nav-bar {
  width: 100px;
    height: 100px;
    background-color: red;
    
    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;
    
    margin: auto;

}

.nav-bar-input {
  padding-left: 0px;
  width: 30%;
}

.nav-bar-el{
  padding-left: 20px;
  width: fit-content;
}

#search_box {
  padding-left: 10px;
  height: 40px;
  width: 700px;
  border-radius: 30px;
  font-size: 1.5rem;
  color: #464646;
}

#navbar{
  padding-left: 170px;

}
</style>