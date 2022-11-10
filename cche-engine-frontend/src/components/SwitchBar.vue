<script setup lang="ts">
import { RouterLink } from 'vue-router'


</script>
    
<template>

<div id="bar" class="header-background-color">
      <div class="micro-bar">
  <nav>
        <RouterLink class="switch-button header-background-color" to="/">Tous</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/plan">Plan</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/detail">Detail</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/image">Image</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/autre">Autre</RouterLink>

      </nav>
    </div>
  </div>
</template>
    
<style  scoped>
#bar {
  width: 100%;
  align-items: center;
  height: 60px;
  z-index: 1;
  border-bottom: 1px solid grey;
}

.micro-bar {
  top:20px;
  min-width: 230px;
  margin-left: 530px;
}

.switch-button {
  background-color: #464646;
  color: #d6dbe1;
  border: none;
  margin :  10px;
  font-size: 20px;
}

.switch-button:focus {
  background-color: #464646;
  color: #4f92ff;
}

.switch-button:hover {
  cursor: pointer;

}

.active{
  color: #4f92ff;

}
</style>