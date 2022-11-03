<script setup lang="ts">

import { ref } from 'vue'
import Dropdown from '../Dropdown.vue'
import { searchStore } from '@/stores/search';


const search = searchStore();

const fieldArray = ["Tous","Titre"]
const knowHow = ["Know","Unknow"]

function modifyfieldSelector(field:string){
    search.fieldSelector = field;
    console.log(field)
}

function modifyKnowHow(field:string){
    // Modify here to add know how elastic   
}
</script>

<template>

    <div class="filter">
        <Dropdown id="field-dropdown" class="filter-element" :dropdown-array="fieldArray" @field-selector="modifyfieldSelector" />
        <Dropdown id="knowhow-dropdown" class="filter-element"  :dropdown-array="knowHow" @field-selector="modifyKnowHow" />
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