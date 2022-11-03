<script setup lang="ts">

import { RouterView } from 'vue-router'
import HeaderBar from "./components/HeaderBar.vue";
import SwitchBar from "./components/SwitchBar.vue";
import FilterMenu from "./components/FilterMenu.vue";

</script>

<template >
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>

  <head>

  </head>
  <body>
  <div class="main">
    <div class="">
      <HeaderBar/>
    </div>

    <div class="">
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
    font-family: Inter;
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



