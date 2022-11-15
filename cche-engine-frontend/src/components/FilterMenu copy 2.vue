<script setup lang="ts">

import { ref,shallowRef } from 'vue'
import ElasticFilter from "./filter/ElasticFilter.vue";
import SolrFilter from "./filter/SolrFilter.vue";
import {searchStore} from "../stores/search";

const search = searchStore();

let isSidenavOpen = ref(false);
let sidenavClass = ref("sidenav-close")
let closeButtonValue = ref('Filtre')
let filterMenu = shallowRef(SolrFilter)

function switchStateFilterWindow() {
  if(isSidenavOpen.value){
    sidenavClass.value = "sidenav-close";
    closeButtonValue.value = "Filtre";
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
    <a href="javascript:void(0)"  class="sidenav-trigger-btn text" :class='[isSidenavOpen ? "sidenav-trigger-open-btn" : "sidenav-trigger-close-btn"]' v-html="closeButtonValue" @click="switchStateFilterWindow"></a>
    <label class="switch_index">
      <span class="slider round"></span>
    </label>

    <component class="" :is="filterMenu"></component>
  </div>
</template>




<style  scoped>

#mySidenav{
  color: rgb(249, 249, 249);
}
.sidenav-open {
  width: 250px;
  height: 75%;
  border-radius: 5px;
  box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.13);
  background-color: rgb(255, 255, 255);
  overflow-x: hidden;
  transition: 0.5s;
}

.sidenav-close {
  
  height: 30px;
  overflow-y: hidden;
  overflow-x: hidden;
  transition: 0.5s;
  font-size: 25px;

}

.sidenav{
    height: 100%;
  width: 100;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
  z-index: 1;
  position: fixed;
  padding-top: 60px;
  top: 120px;
  left: 0;
  transition: 0.3s;

}

.sidenav a:hover {
  color: #000000;
  background-color: rgba(0, 0, 0, 0);
}

 #sidenav-trigger-btn {
  color: rgb(220, 223, 226);
  position: absolute;
  z-index: 1;
  top: 0;
  right: 25px;
}

.sidenav-trigger-close-btn{
  width: fit-content;

  color: rgb(49, 48, 48);
  position: absolute;
  z-index: 1;
  top: 23px;
  content: "❯";
  border-radius: "2";
  left: 25px;
}

.sidenav-trigger-open-btn{
  color: rgb(65, 63, 63);
  font-size: 30px;
  position: absolute;
  z-index: 1;
  top: 0;
  right: 25px;
}
</style>


#mySidenav{
  color: rgb(249, 249, 249);
}
.sidenav-open {
  width: 250px;
  height: 75%;
  border-radius: 5px;
  box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.13);
  background-color: rgb(255, 255, 255);
  overflow-x: hidden;
  transition: 0.5s;
}

.sidenav-close {
  
  height: 30px;
  overflow-y: hidden;
  overflow-x: hidden;
  transition: 0.5s;
  font-size: 25px;

}

.sidenav{
  z-index: 1;
  position: fixed;
  padding-top: 60px;
  top: 120px;
  left: 0;
  transition: 0.3s;

}

.sidenav a:hover {
  color: #000000;
  background-color: rgba(0, 0, 0, 0);}

 #sidenav-trigger-btn {
  color: rgb(220, 223, 226);
  position: absolute;
  z-index: 1;
  top: 0;
  right: 25px;
}

.sidenav-trigger-close-btn{
  width: fit-content;

  color: rgb(49, 48, 48);
  position: absolute;
  z-index: 1;
  top: 23px;
  content: "❯";
  border-radius: "2";
  left: 25px;
}

.sidenav-trigger-open-btn{
  color: rgb(65, 63, 63);
  font-size: 30px;
  position: absolute;
  z-index: 1;
  top: 0;
  right: 25px;
}

