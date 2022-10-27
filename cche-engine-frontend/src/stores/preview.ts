import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const previewStore = defineStore('preview', () => {

  const src = ref("");
  const filename = ref("01_MANDATS_VAL_A");
  const affaire = ref("0");
  const echelle = ref("10");

  return { src,filename,affaire,echelle}
})
