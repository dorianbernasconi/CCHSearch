


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

}


nav {
    width: 230px;
    background: #ffffff;
    margin: 20px auto;
    margin-top: 0px ;
    margin-bottom: 0px;

}
span {
    padding: 10px;
    padding-bottom: 0;
    padding-top: 0;
    background-color: #ffffff;
    color: rgb(23, 23, 23);
    font-size: 1em;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    font-weight: 500;
    font-variant: small-caps;
    cursor: pointer;
    display: block;    
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
    width: 92%;
    height: 0px;
    overflow: hidden;
    text-align: center;
    overflow-y: scroll;
    transition: height .4s ease;
    border-radius: 5;
    -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
    overflow-y: scroll; 
    background-color: white;
    margin-left:10px;

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