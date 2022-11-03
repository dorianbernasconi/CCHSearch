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

  <div class="nav-bar" role="navigation">
    <div class="nav-bar-el">
      <CCHEWhiteIcon />
    </div>
    <div class="nav-bar-el ">
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>
    </div>
    <!--<button @click="search()" > SEARCH </button>-->
  </div>

</template>

<style  scoped>
.nav-bar-brand {
  margin-left: 80px;
}

.nav-bar {
  position: relative;
  display: flex;
  justify-content: normal;
  align-items: center;
  height: 110px;
  background-color: #464646;

}

.nav-bar-input {
  padding-left: 100px;
  width: 30%;
}

.nav-bar-el{
  padding-left: 100px;
  width: fit-content;
}

#search_box {
  height: 40px;
  width: 700px;
  border-radius: 30px;
  font-size: 1.5rem;
  color: #464646;
}
</style>