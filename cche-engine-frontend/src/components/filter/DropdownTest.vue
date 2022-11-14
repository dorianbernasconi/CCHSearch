


<script setup lang="ts">
import { ref } from 'vue'
import type {PropType} from "vue";
import DropdownElement from './DropdownElement.vue';
import { getFieldValues } from "../../api/solrService";
import type { FieldCounter } from '@/api/type';
const props = defineProps({
    labelId : String,
    dropdownArray : Array as PropType<Array<FieldCounter>>
})
const emit = defineEmits(["fieldSelector","getCurrentElement"])

const array = ref<Array<string>>()
const currentElement = ref<string>("");
let isDroping = ref(false)
/*

*/
const setCurrentElement = ((name:string) => {
    currentElement.value = name;
})

const removeCurrentElement = (() => {
    currentElement.value = "";
})

const emitCurrentElement = (() => {
    emit("getCurrentElement",currentElement.value)
})

</script>

<template>
    <nav>
        <label  @click="isDroping = !isDroping"   ><span class="text" >{{ labelId }}</span></label>
        <input  type="checkbox"  class="touch" id="touch ">

        <ul class="slide" :class="{'drop' : isDroping } ">
            <DropdownElement @set-current-element="setCurrentElement" @remove-current-element="removeCurrentElement"  v-for="element in props.dropdownArray" :obj="element" :current-element="currentElement"></DropdownElement>
        </ul>

    </nav>

</template>

<style scoped>
body {
    background-color: #ededed;
    font-family: "Open Sans", sans-serif;
}


ul {
    font-size: 0.8em;
    cursor: pointer;

    list-style-type: none;
    

}

li a {

}


nav {
    border-radius: 3px;
    width: 200px;
    background: #d9d9d9;
    margin: 20px auto;
}

span {
    padding: 10px;
    background-color: #464646;
    color: white;
    font-size: 0.8em;
    font-variant: small-caps;
    cursor: pointer;
    display: block;
    border-radius: 3px;
    
}

span::after {
    float: right;
    right: 10%;
    content: "❯";
}



.collapsed{
    transform: rotate(90deg);

}


.slide {
    clear: both;
    width: 100%;
    height: 0px;
    overflow: hidden;
    text-align: center;
    overflow-y: scroll;
    transition: height .4s ease;
    border-radius: 5;
}

.slide li {
    padding-top: 20px;
    padding-right: 40px;
    padding-bottom: 20px;

}

.drop {
    height: 140px;
}
.touch {
    position: absolute;
    opacity: 0;
    height: 0px;
}

</style>


.touch:checked+.slide {
    height: 140px;

}