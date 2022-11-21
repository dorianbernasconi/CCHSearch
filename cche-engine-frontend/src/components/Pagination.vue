<!--

    This file the pagination component for the cche-engine-frontend.
    All the logic is contained in the parent component.
    It allow use to reuse the component in different context.
    
    It works with three props.
    On That define the number max of button visible at the same time.
    TotalPages define the total number of page.
    CurrentPage define the current page.

    It emit an event when the user click on a button.
    The event is called onClickNextPage and it return the number of the page clicked.


    !! Need to check if on enter input the value is correct !!
-->

<script setup lang="ts">

import { ref } from 'vue'

const props = defineProps({
    maxVisibleButtons: Number,
    totalPages: Number,
    currentPage: Number
});
defineEmits( ["onClickNextPage","selectPage"])

let inputValue = ref("");

function isPageActive(page: any) {
    return props.currentPage === page;
}

function startPage() {
    if (props.currentPage == 1) {
        return 1;
    }
    if (props.currentPage === props.totalPages) {
        console.log("CURRENT PAGE 2 :" + props.currentPage)

        return props.totalPages! - (props.maxVisibleButtons! - 1);
    }
    console.log("CURRENT PAGE :" + props.currentPage)
    return props.currentPage! -1;
}

function pages() {
    const range = [];
    for (
        let i = startPage();
        i <= Math.min(startPage() + props.maxVisibleButtons! - 1, props.totalPages!);
        i++
    ) {
        range.push({
            name: i,
            isDisabled: i === props.currentPage
        });
    }
    return range;
}

</script>
  
<template>

    <div class="pagination">
        <ul>
            <li class="pagination-item" v-for="page in pages()" :key="page.name">
                <button class="button-item" type="button" @click="$emit('onClickNextPage', page.name)"
                    :disabled="page.isDisabled" :class="{ active: isPageActive(page.name) }">
                    {{ page.name }}
                </button>
            </li>
            <li class="pagination-item">
                <input v-on:keyup.enter="$emit('selectPage', inputValue)" v-model="inputValue" class="button-item  input-element" placeholder='...' />
            </li>
            <li class="pagination-item">
                <button class="button-item" type="button" @click="$emit('selectPage', totalPages)">
                    {{ props.totalPages }}
                </button>
            </li>
        </ul>
    </div>

</template>
  

<style scoped>
.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 200px;
}

.pagination {
    list-style-type: none;
}

.pagination-item {
    display: inline-block;

}

input { 
    text-align: center; 
}

.button-item {
    --size: 42px;
    --margin: 6px;
    margin: 4px;
    background: #ffffff;
    max-width: auto;
    min-width: var(--size);
    color: rgb(0, 0, 0);
    height: var(--size);
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    padding: 0 6px;
    font-size: 18px;
    border: rgba(166, 170, 168);

}



.input-element {
    max-width: auto;
    width: 40px;
    color: rgb(0, 0, 0);
   


}

*:focus {
    outline: none;
}
.active {
    color: #000000;
    font-weight: bold;
}


</style>