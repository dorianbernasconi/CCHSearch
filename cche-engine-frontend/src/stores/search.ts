import { ref, computed } from 'vue'
import { defineStore } from 'pinia'


// The switchBar component will write to this store
// And 
export const searchStore = defineStore('search', () => {

  // For All Router Link component
  const keyword = ref("");


  const manda = ref("01_MANDATS_VAL_A");
  const start = ref("0");
  const rows = ref("10");



  const fieldSelector = ref("Tous");

  const filterType = ref("solr");



  return { keyword, manda,start,rows  ,filterType,fieldSelector}
})
