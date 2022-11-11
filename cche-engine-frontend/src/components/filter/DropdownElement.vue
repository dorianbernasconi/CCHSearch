


<script setup lang="ts">
import { ref } from 'vue'
import type { FieldCounter } from '@/api/type';
import type {PropType} from "vue";

defineProps({
    currentElement: String,
    name: String,
    count: Number,
});

defineEmits(["setCurrentElement","removeCurrentElement"]);



/** 
const isActive = ref<Boolean>(false);

const setActive = () => {
    isActive.value = !isActive.value;
};

const removeActive = () => {
    isActive.value = !isActive.value;
};*/


</script>

<template>

    <div >
        <span @click="$emit('setCurrentElement',name)" class="facet-label"><a >{{name}}</a></span>
        <a class="facet-select"></a>
        <a  v-if="name == currentElement" >
           <span @click="$emit('removeCurrentElement')" class="remove-icon">✖</span>
        </a>
        <span  @click="$emit('setCurrentElement',name)" class="facet-label"><a >{{count}}</a></span>
    </div>

</template>

<style>

</style>