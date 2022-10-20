<script setup lang="ts">
import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import All from "../views/All.vue";
import Detail from "../views/Detail.vue";
import { RouterLink } from 'vue-router'

</script>
    
<template>
  
  <!--
  <div id="bar">
      <div class="micro-bar">
        <button @click="$emit('switchMain', 'All')" class="switch-button"> Tous </button>
        <button @click="$emit('switchMain', 'Detail')" class="switch-button"> Detail </button>
        <button @click="$emit('switchMain','Plan')" class="switch-button"> Plan </button>
        <button @click="$emit('switchMain','Image')" class="switch-button"> Image </button>
      </div>
  </div>
-->
<div id="bar">
      <div class="micro-bar">
  <nav>
        <RouterLink class="switch-button" to="/">Tous</RouterLink>
        <RouterLink class="switch-button" to="/detail">Detail</RouterLink>
        <RouterLink class="switch-button" to="/image">Image</RouterLink>

        <RouterLink class="switch-button" to="/autre">Autre</RouterLink>

      </nav>
    </div>
  </div>
</template>
    
<style  scoped>
#bar {
  background-color: white;
  width: 100%;
  align-items: center;
  height: 60px;
  z-index: 1;
  border-bottom: 1px solid #dadada;
}

.micro-bar {
  top:20px;
  min-width: 230px;
  margin-left: 410px;
}

.switch-button {
  background-color: white;
  color: #969ba1;
  border: none;
  margin :  10px;
  font-size: 18px;
}

.switch-button:focus {
  background-color: white;
  color: #4f92ff;
}

.switch-button:hover {
  cursor: pointer;

}
</style>