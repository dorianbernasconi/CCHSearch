


<script setup lang="ts">
import { ref } from 'vue'
import type {PropType} from "vue";

const props = defineProps({
    labelId : String,
    dropdownArray : Array as PropType<Array<string>>
})
defineEmits(["fieldSelector"])

let isDroping = ref(false)
let label = ref(props.dropdownArray![0])

function changeSpanValue(spanName: string) {
    label.value = spanName
}

</script>

<template>


    <nav>

        <label  @click="isDroping = !isDroping" ><span>{{ label }}</span></label>
        <input  type="checkbox"  class="touch" id="touch ">

        <ul class="slide" :class="{'drop' : isDroping } ">
            <li @click="$emit('fieldSelector',element),changeSpanValue(element)"  v-for="element in dropdownArray">{{ element }}</li>
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
    font-size: 0.8em;
    border-radius: 3px;
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