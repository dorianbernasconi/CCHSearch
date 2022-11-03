


<script setup lang="ts">
    import Card from "../components/Card.vue";
    import type {CardElement} from "../api/type"
    import type { PropType } from "vue";

    let props = defineProps({
        callback : Function as PropType<Function>,
        cards : Array as PropType<Array<CardElement>> 
    })
    
</script>

<template>
    <div class="cards">
        <Card @click="props.callback!(page)"  v-for="(page, i) in props.cards!" :obj="page" :key="page.id"/>

    </div>
</template>

<style scoped>

.cards {
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    grid-gap: 2rem;
    right: 0;
    left: 0;
    padding: 50px;
    padding-bottom: 200px;
}
</style>
