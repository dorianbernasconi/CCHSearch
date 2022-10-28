<script setup lang="ts">
  import { RouterLink, RouterView } from 'vue-router'

import HeaderBar from "./components/HeaderBar.vue";
import Detail from "./views/Detail.vue";
import All from "./views/All.vue";

import { ref,shallowRef} from 'vue'
import SwitchBar from "./components/SwitchBar.vue";
import FilterMenu from "./components/FilterMenu.vue";

let currentComponent = shallowRef(All)

/*
function loadComponent(component : string){
  if(component == "All"){
    currentComponent.value = All;
    getDocument("fenetre","01_MANDATS_VAL_A","0","10").then(o =>{
      const jsonArray :any  = o;
      console.log(jsonArray)
      lines.value = jsonArray["documents"] 
     });
    console.log(lines)
  }
  else if(component == "Detail"){
    currentComponent.value = Detail;
  }
}*/

let lines = ref();

</script>

<template >
  <head>

  </head>
  <body>
  <div class="main">
    <div class="">
      <HeaderBar/>
    </div>

    <div class="">
      <!--<SwitchBar @switchMain="loadComponent"  />  -->
      <SwitchBar /> 
    </div>
    <FilterMenu/>
    <div class="main-container">
      <RouterView />
    </div>

    




  </div>
</body>
</template>


<style >


.text{
    color:rgb(65, 65, 65);
    font-family:  Arial, sans-serif;
}
.main {
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  position: fixed;
  background-color: rgb(255, 255, 255);
  
}
.main-container {
  display: inline;
  min-width: 600px;
  overflow-y: scroll;
  width: 100%;
  float:left;
  height: 100%;
}


</style>



