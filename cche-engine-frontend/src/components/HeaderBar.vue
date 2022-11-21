<script setup lang="ts">
import { RouterLink } from 'vue-router'

import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import {searchStore} from "../stores/search"
import { ref } from 'vue'

const store = searchStore();

let value = ref("");


function search(){
  store.keyword = value.value;  
}

store.$subscribe(() =>{
  value.value = store.keyword;
})

</script>

<template>


  <div id="header-bar" > 
    <div class="nav-bar header-background-color" role="navigation">


      <div id="navbar" >
        <div id="logo">
        <a href="https://cche.ch/fr/"><img  class="" src="src/components/icons/logovec.png" /></a>
      </div>
    
        <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text"  placeholder='&#x1F50E' >
      </div>
   
   <!-- -->
   <!-- <button @click="search()" > SEARCH </button>-->
    </div>


  <div id="switch-bar" class="header-background-color">
      <div class="micro-switch-bar">
    <nav>
        <RouterLink class="switch-button header-background-color" to="/">Tous</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/plan">Plan</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/detail">Détail</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/image">Image</RouterLink>
        <RouterLink class="switch-button header-background-color" to="/autre">Autre</RouterLink>

    </nav>
    </div>
  </div>
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


#header-bar{
z-index: 2;
}

#logo{
  position: fixed;
  left: 50px;
  min-width: 120px;
  width: 10%;
}

#navbar{
  padding-top: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  min-width: 800px;
  background-color: #343a40 !important;

}

#switch-bar {
  display: flex;
  min-width: 800px;
  width: 100%;
  height: 60px;
  z-index: 1;
  border-bottom: 1px solid grey;
  display: flex;
  align-items: center;
  justify-content: center;
}

.nav-bar {
  position: relative;
  display: flex;
  z-index: 2;
  justify-content: normal;
  align-items: center;
  height: 80px;
}




#search_box {

  outline: 0 none;
  height: 40px;
  min-width: 300px;
  width: 40%;
  padding: 6px;
  border-radius: 26px;
  font-size: 20px;
  border-width: 0px;
  background-color: #FFFFFF;
  color: #000000;
  box-shadow: 0px 0px 2px rgba(66,66,66,.79);
  text-shadow: 0px 0px 0px rgba(66,66,66,.75);
  margin: 0 auto;
    display: block;
  
}
#search_box:hover {
  box-shadow: 0 0 11px rgba(33,33,33,.2); 
}


img{}
.micro-switch-bar {
  min-width: 230px;

}

.switch-button {
  background-color: #464646;
  color: #d6dbe1;
  border: none;
  margin :  10px;
  margin-right: 30px;
  font-size: 20px;
}

.switch-button:focus {
  background-color: #464646;
  color: #4f92ff;
}

.switch-button:hover {
  cursor: pointer;
}



</style>

.nav-bar-el{
  padding-left: 20px;
  width: fit-content;
}