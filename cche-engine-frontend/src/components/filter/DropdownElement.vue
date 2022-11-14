


<script setup lang="ts">
import { ref } from 'vue'
import type { FieldCounter } from '@/api/type';
import type {PropType} from "vue";

defineProps({
    currentElement: String,
    name: String ,
    count: Number,
    obj: Object as PropType<FieldCounter>
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

    <div class="drop-el">
        <span @click="$emit('setCurrentElement',obj!.name)" class="facet-label " ><a >{{obj!.name}}</a></span>
        <a class="facet-select"></a>
        <a  v-if="obj!.name == currentElement" >
           <span @click="$emit('removeCurrentElement')" class="remove-icon">✖</span>
        </a>
        <span  @click="$emit('setCurrentElement',obj!.name)" class="facet-count"><a >{{obj!.count}}</a></span>
    </div>

</template>

<style scoped>



.facet-label{
    width: 50%;
    float: left;
    display: table-cell;
padding-right: 1em;
text-indent: -15px;
padding-left: 15px;
padding-bottom: 0.5rem;
overflow-wrap: break-word;
-webkit-hyphens: auto;
-o-hyphens: auto;
-ms-hyphens: auto;
hyphens: auto;
}
.facet-count{
    width: 30%;
    float: right;
}
a{
    color: #040910;
text-decoration: none;
background-color: transparent;
}
.drop-el{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    font-size: 15px;
    padding: 0.5em;
    border-bottom: 1px solid #e0e0e0;
    cursor: pointer;
}
.remove-icon{

}

</style>