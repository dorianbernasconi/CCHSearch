5<script setup lang="ts">
import type {CardElement} from "../api/type"
import {getSimilarity} from "../api/elasticService";
import { previewStore } from "../stores/preview"
import Cards from "../components/Cards.vue";
import { ref } from 'vue'

defineEmits(["close"])

const preview = previewStore();

let img_preview = ref<String>("-")
let titre = ref<String>("-")
let affaire = ref<String>("-")
let emplacement = ref<String>("-")
let lastModificationDate = ref(0)
let echelle = ref<String>("-")
//let img_val: string = "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg"
let cards = ref<Array<CardElement>>([])


function getPreviewInfo() {
    img_preview.value = preview.card!.src
    titre.value = preview.card?.filename
    affaire.value = preview.card?.affaire.join("-")
    lastModificationDate.value = preview.card?.lastModificationDate
    echelle.value = preview.card?.echelle
    emplacement.value = preview.card?.filepath
    getSimilarityCard();

}


function getSimilarityCard() {
  getSimilarity(preview.card.filepath).then((o => {
    console.log("SIM" +o);
    cards.value = o;
  }))
}

preview.$subscribe(() => {
    getPreviewInfo();
})

getSimilarityCard();

</script>

<template>
    <div class="prev">
        <button id="close-button" @click="$emit('close')"> &times; </button>
        <div class="preview-top">

            <div class="img-container">
                <img class="img-main" :src=img_preview.valueOf() alt="" />
            </div>

            <p class="titre text"> Titre : {{ titre }}</p>
            <div>
                <div class="preview-text-element text">
                    <p class="left gray-text">Titre de l'affaire : </p>
                    <p class="right black-text">{{ affaire }}</p>
                </div>
                <div class="preview-text-element text">
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
    display: block;
    padding-bottom: 30px;
}

#close-button {

    border: none;
    font-size: 25px;
    color: rgba(166, 170, 168);
    background-color: rgb(255, 255, 255);
    cursor: pointer;
    font-size: 36px;
    top: -5px;
    left: 3px;
}

.preview-top {
    box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.13);
    width: 89%;
    height: 95%;
    margin:auto;
    margin-top: -10px;
}

.closebtn {
  position: absolute;
  top: 0;
  right: 9px;
  font-size: 36px;
  margin-left: 50px;
margin-top: -8px;
color: rgba(166, 170, 168);

}


.prev{
    background-color: white;

}
.titre {
    font-size: 1rem;
    font-weight: bold;
    color: black;
    padding-bottom: 10px;
}

.preview-container {

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
    margin-right: 30px;
    width: 60%;
    float: right;
    color: rgba(166, 170, 168);
    font-size: 0.8rem;
    text-overflow: ellipsis;
white-space: nowrap;
overflow: hidden;

}

.left {
    color: rgba(166, 170, 168);
    width: 30%;
    float: left;
    font-size: 0.8rem;

}
</style>
