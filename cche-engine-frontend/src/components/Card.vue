<script setup lang="ts">
import type {CardElement} from "../api/type"
import type { PropType } from "vue";

const props = defineProps({
  obj : Object as PropType<CardElement> 
});
console.log("CQRD")

function a(){
  console.log("ADD")
}
</script>

<template>
  <div class="card card_medium">
    <p @onclick="a()" style="text-align: right;">&#9829</p>
    <img class="img_card" :src="props.obj!.src" alt="" />
    <div>
      <p class="item_etiquette text">{{ props.obj!.filename }}</p>
      <p  class="item_etiquette text">{{ props.obj!.echelle }}</p>
      <p  class="item_etiquette text">{{ props.obj!.affaire }}</p>
    </div>
    
  </div>
</template>

<style scoped>
.card_medium {
  box-shadow: 5px 3px 3px #8a8a8a;
  border: 1px solid rgb(0, 0, 0);
  border-radius: var(--card_border_radius);
  background-color: white;
}

.card {
  border: 2px solid #e7e7e7;
  border-radius: 4px;
  padding: 0.5rem;
  max-height: 350px;
}

.img_card {
  width: 100%;
    height: 80%;
 object-fit: contain;    
 text-align: center;
    display: block;
    margin-left: auto;
    margin-right: auto;
}
.item_etiquette{
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    text-align: left;
    margin-left: 5px;
}

</style>
