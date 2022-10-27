<script setup lang="ts">
import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import {previewStore} from "../stores/preview"
import { ref } from 'vue'


let img_preview = ref("")
let titre = ref("")
let affaire = ref("")


const preview = previewStore();

function getPreviewInfo() {
    img_preview = ref(preview.src)
    titre = ref(preview.filename)

    affaire = ref(preview.affaire)

}
preview.$subscribe(() => {
    getPreviewInfo();
})

const emits = defineEmits(["close"])

</script>

<template>
<button id="close-button" @click="$emit('close')"> &#x2715 </button>
<div>
    <img :src=img_preview />

    <p id="title" >{{ titre }}</p>
    <p id="affaire" >{{ affaire }}</p>

</div>

</template>

<style  scoped>

#close-button{
    height:40px;
    width:40px;
    font-size: 25px;
    color:aliceblue;
    background-color: rgb(41, 40, 40);    
}
</style>
