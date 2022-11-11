<script setup lang="ts">

import { ref,onMounted } from 'vue'
import Dropdown from './Dropdown.vue'
import { searchStore } from '@/stores/search'
import DropdownAutocomplete from './DropdownAutocomplete.vue'
import DropdownTest from './DropdownTest.vue'
import {getFieldValues} from '../../api/solrService'

const search = searchStore();
const fieldArray = ["Tous","Titre"]
const knowHow = ["Know","Unknow"]
const affaire = ref<Array<string>>([""])
function modifyfieldSelector(field:string){
    search.fieldSelector = field;
    console.log(field)
}

const aff = ref<string>("Affaire")



onMounted(() => {
    getFieldValues("affaire").then( (o : any) => {
        console.log( o["facet"]);
    affaire.value = o["facet"];
    console.log(affaire.value)
})})

function modifyKnowHow(field:string){
    // Modify here to add know how elastic   
}

const getCurrentElementAffaire = (element:string) => {
    search.affaire = element;
    console.log("Choose Element",element)
}

</script>

<template>

    <div class="filter">
        <DropdownTest :dropdownArray="affaire" @getCurrentElement="getCurrentElementAffaire" :labelId="aff"/>
    </div>

    


</template>




<style  scoped>

.filter-element{
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
    
}
.filter{
    margin-left: 0;
}

</style>