<script setup lang="ts">

import { ref,onMounted } from 'vue'
import Dropdown from './Dropdown.vue'
import { searchStore } from '@/stores/search'
import DropdownAutocomplete from './DropdownAutocomplete.vue'
import DropdownTest from './DropdownTest.vue'
import {getFieldValues} from '../../api/solrService'
import type { FieldCounter } from '@/api/type'

const search = searchStore();
const fieldArray = ["Tous","Titre"]
const affaire = ref<Array<FieldCounter>>([])
const age = ref<Array<FieldCounter>>([])
const knowHow = ref<Array<FieldCounter>>([])


function modifyfieldSelector(field:string){
    search.fieldSelector = field;
    console.log(field)
}

const affaireId = ref<string>("Affaire")
const ageId = ref<string>("age")
const knowHowId = ref<string>("knowHow")



onMounted(() => {
    getFieldValues("affaire").then( (o : any) => {
        console.log( o["facet"]);
    affaire.value = o["facet"]; 
})
getFieldValues("modtime").then( (o : any) => {
        console.log( o["facet"]);
        age.value = o["facet"]; 
    console.log(age.value)
})
getFieldValues("kh").then( (o : any) => {
        console.log( o["facet"]);
        knowHow.value = o["facet"]; 
    console.log(affaire.value)
})
})

function modifyKnowHow(field:string){
    search.kh = field;
}

const getCurrentElementAffaire = (element:string) => {
    search.affaire = element;
}

</script>

<template>

    <div class="filter">
        <DropdownTest class="filter-element" :dropdownArray="affaire" @getCurrentElement="getCurrentElementAffaire" :labelId="affaireId"/>
        <DropdownTest class="filter-element" :dropdownArray="knowHow" @getCurrentElement="modifyKnowHow" :labelId="knowHowId"/>
        <DropdownTest class="filter-element" :dropdownArray="age" @getCurrentElement="getCurrentElementAffaire" :labelId="ageId"/>


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