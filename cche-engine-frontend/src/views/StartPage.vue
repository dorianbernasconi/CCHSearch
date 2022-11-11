<script>


function search(){
  store.keyword = value.value;
}
</script>

<template>
  <div class="nav-bar header-background-color" role="navigation">
    <div class="nav-bar-el header-bar-element">

      <img class="" src="src/components/icons/logo.png" />
    </div>
    <div id="navbar" class="nav-bar-el ">
      <input  v-model="value" v-on:keyup.enter="search()"  id="search_box" type="text" placeholder='&#x1F50E'>
    </div>
    <!--<button @click="search()" > SEARCH </button>-->
  </div>

</template>

<style>

</style>