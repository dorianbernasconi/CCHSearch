<script setup lang="ts">
import CCHEWhiteIcon from "./icons/CcheWhite.vue";
import { getElasticDetail } from "../api/apiService"

import type {CardElement} from "../api/type"
import {getSimilarity} from "../api/apiService";
import { previewStore } from "../stores/preview"
import Cards from "../components/Cards.vue";
import { ref } from 'vue'

let img_preview = ref<String>("-")
let titre = ref<String>("-")
let affaire = ref<String>("-")
let emplacement = ref<String>("-")
let lastModificationDate = ref(0)
let echelle = ref<String>("-")


const preview = previewStore();
let img_val: string = "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg"
let cards = ref<Array<CardElement>>([])

function getSimilarityCard() {
  getSimilarity(preview.card.id).then((o => {
    console.log(o);
    cards.value = o;
  }))
}

getSimilarityCard();


function getPreviewInfo() {
    img_preview.value = preview.card!.src
    titre.value = preview.card?.filename
    affaire.value = preview.card?.affaire.join("-")
    lastModificationDate.value = preview.card?.lastModificationDate
    echelle.value = preview.card?.echelle
    emplacement.value = preview.card?.filepath
}

preview.$subscribe(() => {
    getPreviewInfo();
})
defineEmits(["close"])

</script>

<template>
    <div class="preview-conainer">
        <button id="close-button" @click="$emit('close')"> &#x2715 </button>
        <div class="preview-top">

            <div class="img-container">
                <img class="img-main" :src=img_val alt="" />
            </div>

            <p class="titre"> Titre : {{ titre }}</p>
            <div>
                <div class="preview-text-element">
                    <p class="left gray-text">Titre de l'affaire : </p>
                    <p class="right black-text">{{ affaire }}</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Emplacement : </p>
                    <p class=" right black-text">{{ emplacement }}</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Date de modification : </p>
                    <p class="right black-text">{{ lastModificationDate }}</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Echelle : </p>
                    <p class="right black-text">{{ echelle }}</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Entreprise : </p>
                    <p class="right black-text">-</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Auteur : </p>
                    <p class="right black-text">-</p>
                </div>
                <div class="preview-text-element">
                    <p class="left gray-text">Coût : </p>
                    <p class="right black-text">-</p>
                </div>
            </div>
        </div>
        <div class="preview-bottom">
            <Cards  :cards="cards" />
        </div>
    </div>
</template>

<style  scoped>
.img-main {
    width: 80%;
    max-height: 800px;
    margin-left: auto;
    margin-right: auto;
    display: block
}

#close-button {
    height: 40px;
    width: 40px;
    font-size: 25px;
    color: aliceblue;
    background-color: rgb(41, 40, 40);
}

.titre {
    font-size: 20px;
    font-weight: bold;
}

.preview-container {
    margin-bottom: 200px;

}

.preview-text-element {
    white-space: nowrap;
    overflow: hidden;
    width: 100%;
    margin-left: 20px;
    text-align: left;
}

.preview-bottom{
    margin-bottom: 200px;
}

.right {
    width: 80%;
    float: right;
    color: black;
}

.left {
    color: gray;
    width: 20%;
    float: left;
}
</style>
