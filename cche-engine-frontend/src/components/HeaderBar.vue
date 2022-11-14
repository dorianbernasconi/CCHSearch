<script setup lang="ts">
import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import {searchStore} from "../stores/search"
import { ref } from 'vue'

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
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" vmodel="store.keyword" placeholder='&#x1F50E' >
    </div>
    <!--<button @click="search()" > SEARCH </button>-->
  </div>

<!--
  <div class="nav-bar header-background-color" role="navigation">
    <div class="nav-bar-el">
      <CCHEWhiteIcon />
      <img src="src/components/icons/logo.png" />
    </div>
    <div id="navbar" class="nav-bar-el ">
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>
    </div>
    <button @click="search()" > SEARCH </button>
  </div>
-->
</template>

<style  scoped>

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
  position: relative;
  display: flex;
  justify-content: normal;
  align-items: center;
  height: 80px;

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
  outline: 0 none;
  height: 40px;
  width: 700px;
  padding: 6px;
  border-radius: 26px;
     font-size: 20px;
     border-width: 0px;
  background-color: #FFFFFF;
     color: #000000;
     box-shadow: 0px 0px 2px rgba(66,66,66,.79);
     text-shadow: 0px 0px 0px rgba(66,66,66,.75);
}
#search_box:hover {
  box-shadow: 0 0 11px rgba(33,33,33,.2); 
}
#navbar{
  padding-left: 170px;

}
</style>