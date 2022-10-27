


<script setup lang="ts">
import Card from "../components/Card.vue";
import { ref } from 'vue'
import { getElastic } from "../api/apiService"
import { previewStore} from "../stores/preview"
import type {CardElement} from "../api/type"



const preview = previewStore();

function updatePreview(page:CardElement){
    console.log(page.filename)
}

function getNewDetails() {
    getElastic("porte", "home2", "0", "50").then((o => {
        cards = o;
    }))
}
getNewDetails();
let cards : CardElement[] = new Array<CardElement>();


//cards.push(el)
/*, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filenameB",
        affaire: "affaireB",
        echelle: 2
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filenameC",
        affaire: "affaireC",
        echelle: 3
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    }, {
        src: "src/assets/0449_DETAIL_MENUISERIE_CUISINES_31.jpg",
        filename: "filename",
        affaire: "affaire",
        echelle: 1
    },
*/

</script>
<template>
    <div class="cards">
        <Card @click="updatePreview(page)" v-for="(page, i) in cards" v-bind="page"></Card>

    </div>
</template>

<style scoped>
.cards {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    grid-auto-rows: var(10px);
    grid-gap: 2rem;
    right: 0;
    left: 0;
    padding-bottom: 200px;
}
</style>
