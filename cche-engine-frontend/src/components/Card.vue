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
    <p @onclick="a()" class="dots" style="text-align: right;">&#9776</p>
    <img class="img_card" :src="img_val" alt="" />

    <div class="text-center">

    <p id="title" class="item_etiquette text" >{{ props.obj!.filename }}</p>
    <p class="item_etiquette text">{{ props.obj!.affaire.join("-") }}</p>

    <p class="item_etiquette text">{{ props.obj!.echelle }}</p>
  </div>


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
  height: 380px;
  font-size: 15px;


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
  color: rgba(166, 170, 168);
  
}
#title{
  padding-top: 10.5px;
  font-weight: bold;
  font-size: 1rem;
  padding-bottom: 7px;
  color: rgb(0, 0, 0);

}

#affaire-name{

}

.dots{
  font-size: 25px;
  margin-bottom: -10px;
  font-weight:bold;
  color: rgba(166, 170, 168);
}
</style>

box-shadow: 5px 3px 15px 1px #c2c2c2;
