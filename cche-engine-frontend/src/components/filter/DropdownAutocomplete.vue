

<script setup lang="ts">
import {ref,computed} from 'vue'
import type {PropType} from "vue";
let searchTerm = ref('')

const props = defineProps({
  arrayElements: Array as PropType<Array<string>>,
})



function selectAffaire(affaire:string){
  selectedElement.value = affaire
  searchTerm.value = ''
  isDroping.value = !isDroping
}

let isDroping = ref(false)

let selectedElement = ref('')


const searchAffaires = computed(() => {
  if (searchTerm.value === '') {
    return []
  }

  let matches = 0

  const tmp = Array.from(props.arrayElements!.values())
  return  tmp.filter(affaire => {

    if (
      affaire.toLowerCase().includes(searchTerm.value.toLowerCase())
      && matches < 10
    ) {
      matches++
      return affaire
    }
  })
}); 

function test(){
    console.log("test")
}

</script>

<template >
    <nav>

   
    <label   for="z"> {{selectedElement}} </label>

    <input id="z"  @click="isDroping = !isDroping" type="text"  class="filter-input" v-model="searchTerm" >
    <ul class="slide" :class="{'drop' : isDroping } ">
        <li>
            {{ searchAffaires.length }} sur {{ arrayElements?.length}} résultats
        </li>
        <li
        v-for="affaire in searchAffaires" :key="affaire" @click="selectAffaire(affaire)"> {{ affaire }} </li>
    </ul>
</nav>

</template>

<style scoped>
.filter-input{
     padding: 4px;
     font-size: 18px;
     border-width: 0px;
     border-color: #CCCCCC;
     background-color: #FFFFFF;
     color: #000000;
     border-style: solid;
     border-radius: 3px;
     box-shadow: 0px 0px 2px rgba(66,66,66,.43);
     text-shadow: -50px 0px 0px rgba(66,66,66,.0);
}
 .filter-input:focus {
     outline:none;
}



ul {
    font-size: 0.5em;
    cursor: pointer;

    list-style-type: none;
}


nav {
    width: 200px;
    background: #ffffff;
}


.slide {
    background: #969696;

    clear: both;
    width: 100%;
    height: 0px;
    overflow: hidden;
    text-align: center;
    transition: height .4s ease;
}

.slide li {
    padding-top: 5px;
    padding-right: 40px;
    padding-bottom: 5px;

}

.drop {
    height: 300px;
}
.touch {
    position: absolute;
    opacity: 0;
    height: 0px;
}

label{
    font-size: 0.5em;
    cursor: pointer;
    color: #969696;
    margin-left: 10px;
}

</style>