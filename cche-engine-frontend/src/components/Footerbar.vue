

<script setup lang="ts">
</script>


<template>

<!--

    make a simple footer bar
-->


<div class="footer-bar">

    <div class="maine"></div>

</div>


</template>


<style scoped >


.footer-bar {
    
  overflow: hidden;
  background-color: #343a40 !important;
  position: fixed;
  bottom: 0;
  z-index: 2;
  width: 100%;


}

.footer-bar a {
  float: left;
  display: flex;
  color: #972929;

  text-decoration: none;
  font-size: 17px;
}

.footer-bar a:hover {
  background: #c03737;
  color: black;
}

.footer-bar a.active {
  background-color: #25af7c;
  color: white;
}

.maine {
  padding: 10px;
  margin-bottom: 10px;
}
</style>
