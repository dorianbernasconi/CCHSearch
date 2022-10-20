import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const searchStore = defineStore('search', () => {
  const keyword = ref("");
  const manda = ref("01_MANDATS_VAL_A");
  const start = ref("0");
  const rows = ref("10");

  return { keyword, manda,start,rows}
})
