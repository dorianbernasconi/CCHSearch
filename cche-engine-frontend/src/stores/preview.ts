import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type {CardElement} from "../api/type"

export const previewStore = defineStore('preview', () => {

  // how to define object ref
  const card  = ref<CardElement>({    score:0,
    id: "",
    filename: "",
    src: "",
    pageNumber: 0,
    lastModificationDate:0,
    affaire:"",
    echelle: "",
    price:0,
    wordList: [""],
    modtime: "",
    kh: false,
    jpgname: ""})
  const src = ref("");
  const filename = ref("01_MANDATS_VAL_A");
  const affaire = ref("0");
  const echelle = ref("10");

  return {card, src,filename,affaire,echelle}
})
