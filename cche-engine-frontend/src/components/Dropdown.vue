


<script setup lang="ts">
import { ref } from 'vue'
import type {PropType} from "vue";



const props = defineProps({
    dropdownArray : Array as PropType<Array<string>>
})

function changeSpanValue(o: string) {
    label.value = o
}

let label = ref(props.dropdownArray![0])
const emit = defineEmits(["fieldSelector"])

</script>

<template>


    <nav>

        <label for="touch"><span>{{ label }}</span></label>
        <input type="checkbox" id="touch">

        <ul class="slide">
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

#touch {
    position: absolute;
    opacity: 0;
    height: 0px;
}

#touch:checked+.slide {
    height: 140px;

}
</style>