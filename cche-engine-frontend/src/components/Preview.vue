<script setup lang="ts">
import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import {previewStore} from "../stores/preview"
import { ref } from 'vue'

let img_preview = ref<String >("") 
let titre = ref<String>("")
let affaire = ref<String>("")


const preview = previewStore();

function getPreviewInfo() {
    img_preview.value = preview.card!.src
    titre.value = preview.card?.filename
    affaire.value = preview.card?.affaire

}

preview.$subscribe(() => {
    getPreviewInfo();
})
 defineEmits(["close"])

</script>

<template>
<button id="close-button" @click="$emit('close')"> &#x2715 </button>
<div class="preview-top" >
    <img class="img-main" :src=img_preview.valueOf() alt="" />

    <p id="title" class="text"> {{ titre }}</p>
    <p id="affaire" class="text" >{{ affaire }}</p>

</div>

</template>

<style  scoped>

.img-main{
    width: 80%;
    max-height: 800px;
    margin-left: auto;
  margin-right: auto;
  display: block
}

#close-button{
    height:40px;
    width:40px;
    font-size: 25px;
    color:aliceblue;
    background-color: rgb(41, 40, 40);    
}
</style>
