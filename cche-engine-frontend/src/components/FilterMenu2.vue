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
function openNav() {
  document.getElementById("mySidenav")!.style.width = "12.7%";
  document.getElementById("main-view")!.style.width = "87.3%";
}

function closeNav() {
  document.getElementById("mySidenav")!.style.width = "0%";
  document.getElementById("main-view")!.style.width= "100%";
}

</script>


<template>

<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" @click="closeNav()">&times;</a>
  <component class="" :is="filterMenu"></component>

</div>

<div id="main" class="filter-button">
  <span style="font-size:20p3x;cursor:pointer" @click="openNav()">&#9776; Filtres</span> 

  
</div>

</template>

<style scoped>
body {
  font-family: "Lato", sans-serif;
}

nav{
  background-color: #ffffff;
}

.sidenav::-webkit-scrollbar {
  display: none;
}

.filter-button{
  position: fixed;
  z-index: 2;
}
  .sidenav {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 0;
  left: 0;
  background-color: #ffffff !important;  overflow-x: hidden;
  padding-top: 60px;
  border-right: 1px solid rgba(0,0,0,0.16);
  transition:  .5s;
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}



.sidenav .closebtn {
  position: absolute;
  top: 0;
  right: 9px;
  font-size: 36px;
  margin-left: 50px;
margin-top: -8px;
color: rgba(166, 170, 168);

}

#main {
  transition: margin-left .5s;
  padding: 16px;
  width: 100px;
}

</style>
