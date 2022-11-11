


<script setup lang="ts">
import { ref } from 'vue'
import type {PropType} from "vue";
import DropdownElement from './DropdownElement.vue';
import { getFieldValues } from "../../api/solrService";
const props = defineProps({
    labelId : String,
    dropdownArray : Array as PropType<Array<string>>
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
        <label  @click="isDroping = !isDroping" ><span>{{ labelId }}</span></label>
        <input  type="checkbox"  class="touch" id="touch ">

        <ul class="slide" :class="{'drop' : isDroping } ">
            <DropdownElement @set-current-element="setCurrentElement" @remove-current-element="removeCurrentElement"  v-for="element in props.dropdownArray" :name="element" :current-element="currentElement"></DropdownElement>
        </ul>

    </nav>

</template>

<style scoped>
body {
    background-color: #ededed;
    font-family: "Open Sans", sans-serif;
}


ul {
    font-size: 0.5em;
    cursor: pointer;

    list-style-type: none;
}

li a {

    color: #2d2f31;
    width: 100%;

}


nav {
    width: 200px;
    background: #d9d9d9;
    margin: 20px auto;
}

span {
    padding: 10px;
    background-color: #464646;
    color: white;
    font-size: 0.5em;
    font-variant: small-caps;
    cursor: pointer;
    display: block;
}

span::after {
    float: right;
    right: 10%;
    content: "+";
}

.slide {
    clear: both;
    width: 100%;
    height: 0px;
    overflow: hidden;
    text-align: center;
    transition: height .4s ease;
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