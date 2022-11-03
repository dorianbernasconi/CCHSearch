import type { CardElement } from "./type"

export function getDocuments(keyWord:string, index:string, min:string, max:string, field:string){

    let params = new URLSearchParams({
        q: keyWord,
        index: index,
        min: min,
        max: max,
        field: field
    }).toString()

     return fetch('http://localhost:4567/query/elastic/request/string?' + params, {
        method: "POST",
      })
        .then(res => res.json())
        .then( res => {
          return res["documents"] as Array<CardElement>
      })
}

export function getSimilarity(filepath: string,){

  let params = new URLSearchParams({
      q: filepath
  }).toString()

  return fetch('http://localhost:4567/query/elastic/request/string?' + params, {
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
  
