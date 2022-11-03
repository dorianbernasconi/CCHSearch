<script setup lang="ts">

import { ref } from 'vue'
import Dropdown from '../Dropdown.vue'
import { searchStore } from '@/stores/search';


const search = searchStore();

function modifyfieldSelector(field:string){
    search.fieldSelector = field;
    console.log(field)
}

const fieldArray = ["Tous","Titre"]

</script>


<template>

    <div class="filter">
        <Dropdown :dropdown-array="fieldArray" @field-selector="modifyfieldSelector" class="filter-element" id="field-dropdown"/>


    </div>

    
    <p id="auteur" class="input_box_name text">ELLLLLLLLl</p>


</template>




<style  scoped>

.filter-element{
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
}
.filter{
    display: flex;
    margin-left: 0;
}

</style>