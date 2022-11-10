


<script setup lang="ts">
import { ref } from 'vue'
import type { FieldCounter } from '@/api/type';
import type {PropType} from "vue";

const props = defineProps({
    name: String,
    count: Number,
});

const isActive = ref<Boolean>(false);

const setActive = () => {
    isActive.value = !isActive.value;
};

const removeActive = () => {
    isActive.value = !isActive.value;
};


</script>

<template>

    <div >
        <span @click="setActive()" class="facet-label"><a >TEST</a></span>
        <a class="facet-select"></a>
        <a  v-if="isActive" >
           <span @click="removeActive()" class="remove-icon">✖</span>
        </a>
        <span @click="setActive()" class="facet-label"><a >10</a></span>
    </div>

</template>

<style>

</style>