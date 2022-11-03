<script setup lang="ts">

import { ref,shallowRef } from 'vue'
import ElasticFilter from "./filter/ElasticFilter.vue";
import SolrFilter from "./filter/SolrFilter.vue";
import {searchStore} from "../stores/search";

const search = searchStore();

let isSidenavOpen = ref(false);
let sidenavClass = ref("sidenav-close")
let closeButtonValue = ref('&#9776;')
let filterMenu = shallowRef(SolrFilter)

function switchStateFilterWindow() {
  if(isSidenavOpen.value){
    sidenavClass.value = "sidenav-close";
    closeButtonValue.value = "&#9776;";
  }else{
    sidenavClass.value = "sidenav-open";
    closeButtonValue.value = "&times;";
  }
  isSidenavOpen.value = !isSidenavOpen.value;
}

search.$subscribe(() => {
  console.log(search.filterType);
  if(search.filterType == 'elastic'){
    filterMenu.value = ElasticFilter
}
  else{
    filterMenu.value = SolrFilter
  }
});

</script>


<template>
  <div  id="mySidenav" :class='["sidenav",sidenavClass]'>
    <a href="javascript:void(0)" class="closebtn" v-html="closeButtonValue" @click="switchStateFilterWindow"></a>
    <label class="switch_index">
      <span class="slider round"></span>
    </label>

    <component :is="filterMenu"></component>
  </div>
</template>




<style  scoped>
.sidenav-open {
  width: 360px;
  height: 75%;
  border-radius: 5px;
  box-shadow: 1px 1px 5px #8a8a8a;
  background-color: rgb(255, 255, 255);
  overflow-x: hidden;
  transition: 0.5s;
  font-size: 30px;
}

.sidenav-close {
  width: 70px;
  height: 30px;
  overflow-y: hidden;
  overflow-x: hidden;
  transition: 0.5s;
  font-size: 30px;
}

.sidenav{
  z-index: 1;
  position: fixed;
  padding-top: 60px;
  top: 110px;
}

.sidenav a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  color: #000000;
  display: block;
  transition: 0.3s;
}

.sidenav a:hover {
  color: #000000;
  background-color:  rgb(255, 255, 255);
}

 .closebtn {
  position: absolute;
  z-index: 1;
  top: 0;
  right: 25px;
}
</style>