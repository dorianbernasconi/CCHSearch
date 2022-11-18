import type { CardElement } from "./type"
import {searchStore} from "../stores/search"


export function getDocuments(keyWord:string, index:string, min:string, max:string, field:string){
  const store = searchStore();

    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        min: min,
        max: max,
        field: field
    }).toString()

     return fetch('http://'+ store.ip  +':4567/query/elastic/request/string?' + params, {
        method: "POST",
      })
        .then(res => res.json())
        .then( res => {
          return res["documents"] as Array<CardElement>
      })
}

export function getSimilarity(filepath: string,){
  const store = searchStore();

  console.log(filepath)
  let params = new URLSearchParams({
      filepath: filepath
  }).toString()

  return fetch('http://'+store.ip+':4567/query/elastic/similarity/string?' + params, {
      method: "POST",
  
    })
      .then(res => res.json())
      .then(res => {
        return res as Array<Element>
    })
    .then( res => {
        const jsonArray :any  = res;
        return jsonArray["documents"]
    })
}
  
