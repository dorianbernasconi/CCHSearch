<script setup lang="ts">

import { RouterView } from 'vue-router'
import HeaderBar from "./components/HeaderBar.vue";
import SwitchBar from "./components/SwitchBar.vue";
import FilterMenu from "./components/FilterMenu.vue";
import FilterMenu2 from "./components/FilterMenu2.vue";

import FooterBar from "./components/Footerbar.vue";

import StartPage from "./views/StartPage.vue";

import { ref } from 'vue'
let zIndex = ref(10);

</script>

<template >
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
  <head>

  </head>
  <body> 
     <div id="start" v-bind:style="{ zIndex:zIndex  }" class="start">
     <StartPage  @startpage="zIndex = 0" />
  </div>
  <div class="main">

      <div class="">
        <HeaderBar/>
      </div>
      <FilterMenu2/>
      <div id="main-view" class="main-container"  >
        <RouterView />
      </div>

      
    <div class="foot">
     <FooterBar/> 
    </div> 

  </div>    

</body>

</template>


<style >



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
  z-index: 1;
  float:right;
  height: 100%;
  padding-left: 00px;
  transition: 0.5s;
}


.foot{
  bottom: 0;

}
.start{
  opacity:    1; 
  background: rgb(255, 255, 255); 
  width:      100%;
  height:     100%; 
  z-index:    10;
  top:        0; 
  left:       0; 
  position:   fixed; 
}
.header-background-color{
  background-color: #343a40 !important;
}
.text{
    color:rgb(65, 65, 65);
    font-family: Inter;
    font-size: 15px;
}


</style>



