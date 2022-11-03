<script setup lang="ts">
import type { CardElement } from "../api/type"
import type { PropType } from "vue";

const props = defineProps({
  obj: Object as PropType<CardElement>
});

//props.obj!.src  
let img_val: string = props.obj!.src  
//"src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg"

function a() {
  console.log("ADD")
}
</script>

<template>
  <div class="card card_medium">
    <p @onclick="a()" class="dots" style="text-align: right;">&#8942</p>
    <img class="img_card" :src="img_val" alt="" />

    <p id="affaire-name" class="item_etiquette text">{{ props.obj!.affaire.join("-") }}</p>
    <p class="item_etiquette text">{{ props.obj!.filename }}</p>
    <p class="item_etiquette text">{{ props.obj!.echelle }}</p>


  </div>
</template>

<style scoped>
.card_medium {
  box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.13);
  
  border: 1px solid rgb(0, 0, 0);
  border-radius: var(--card_border_radius);
  background-color: white;
}

.card {
  border: 2px solid #e7e7e7;
  border-radius: 5px;
  padding: 0.5rem;
  height: 420px;

}

.img_card {
  width: 100%;
  height: 70%;
  min-width: none;
  object-fit: contain;
  text-align: center;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.item_etiquette {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  text-align: left;
  margin-left: 5px;
}

#affaire-name{
  font-weight: bold;
  font-size: 1.2rem;
}

.dots{
  font-size: 30px;
  margin-bottom: -10px;
  font-weight:bold}
</style>

box-shadow: 5px 3px 15px 1px #c2c2c2;
